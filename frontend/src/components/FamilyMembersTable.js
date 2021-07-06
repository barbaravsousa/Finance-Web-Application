import AppContext from "../context/AppContext";
import {getListOfFamilyMembers, saveUserRelations} from "../context/PersonActions";
import React, {useContext} from "react";
import {makeStyles} from "@material-ui/core";
import {DataGrid} from "@material-ui/data-grid";
import PersonModal from "./PersonModal";
import Button from "@material-ui/core/Button";
import {Link} from "react-router-dom";
import {getFamilyProfile2} from "../context/FamilyActions";

const useStyles = makeStyles({
    root: {
        '& .super-app-theme--header': {
            backgroundColor: '#5e8b7e',
            fontSize: "20px",
            fontFamily: "Verdana, Geneva, Tahoma, sans-serif",
        },
        marginTop: "20px",
        background: "#F1F6F5",
        borderRadius: "10px",
        margin: "0 auto"
    },
});

let urlInfoRelations = "";

export default function FamilyMembersTable() {
    const {state, dispatch} = useContext(AppContext);
    const {members, user, options, familyOptions} = state;
    const {data, refresh} = members;
    const familyID = user.data.familyID;
    const token = user.data.jwt;
    const classes = useStyles();

    const url = options._links.family.href;

    React.useEffect(() => {
        getFamilyProfile2(dispatch, familyID, token, url);
    }, []);

    const handleUserRelation = (username) => {
        saveUserRelations(username, dispatch);
    }

    const columns = [
        {
            field: 'name', headerName: 'Name', headerClassName: 'super-app-theme--header',
            width: 250
        },
        {
            field: 'relations',
            headerName: 'View Relations',
            flex: 0.3,
            headerClassName: 'super-app-theme--header',
            renderCell: (params) => (
                <Link to={params.value}>
                    <Button
                        id={user.mainEmail}
                        size="small"
                        style={{marginLeft: 10}}
                        onClick={() => handleUserRelation(user.name)}
                    >
                        Relations
                    </Button>
                </Link>
            ),
        },
    ];

    const rows = Object.keys(data).map(mainEmail => {
            urlInfoRelations = "/viewRelations/" + data[mainEmail].familyID + "/" + data[mainEmail].mainEmail;
            return {
                'id': mainEmail,
                'name': data[mainEmail].name,
                'relations': urlInfoRelations,
            }
        }
        )
    ;

    React.useEffect(() => {
        getListOfFamilyMembers(dispatch, familyID, token);
    }, [refresh]);

    return (
        <div>
            <div className="table-title">
                <h2 className="h2">{familyOptions.familyName}'s Members</h2>
            </div>
            <div>
                <div style={{height: 600, width: 600}} className={classes.root}>
                    <PersonModal/>
                    <DataGrid rows={rows} columns={columns} pageSize={10}/>
                </div>
            </div>
        </div>
    );

}
