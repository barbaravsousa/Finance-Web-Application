import AppContext from "../context/AppContext";
import {getFamilyProfile, getListOfFamilies,} from "../context/FamilyActions";
import React, {useContext} from "react";
import "../css/FamilyTable.css";
import Menu from "./menuOptions/Menu";
import pig from "../images/pineapple-pig-half1.png";
import {DataGrid} from '@material-ui/data-grid';
import Modal from "./FamilyModal";
import {makeStyles} from '@material-ui/core/styles';

const useStyles = makeStyles({
    root: {
        '& .super-app-theme--header': {
            backgroundColor: '#5e8b7e',
            fontSize: "20px",
            fontFamily: "Verdana, Geneva, Tahoma, sans-serif",
        },
        marginTop: "120px",
        background: "#F1F6F5",
        borderRadius: "10px",
        margin: "0 auto",

    },
});

function FamiliesTable() {
    let {state, dispatch} = useContext(AppContext);
    let {families, familyProfile, user} = state;
    const {data, refresh} = families;
    const token = user.data.jwt;
    const classes = useStyles();

    React.useEffect(() => {
        getListOfFamilies(dispatch, token);
    }, [refresh]);

    React.useEffect(() => {
        for (const fam of data) {
            getFamilyProfile(dispatch, fam.familyID, token)
        }
        console.log(data)
    }, [data]);

    const columns = [
        {
            field: 'name', headerName: 'Name', headerClassName: 'super-app-theme--header',
            flex: 0.6
        },
        {
            field: 'date', headerName: 'Date', headerClassName: 'super-app-theme--header',
            width: 170
        },
        {
            field: 'administrator',
            headerClassName: 'super-app-theme--header',
            flex: 1
        },
    ];
    const rows = Object.keys(familyProfile.mapper).map(administratorID => {
        return {
            'id': administratorID,
            'name': familyProfile.mapper[administratorID].familyName,
            'date': familyProfile.mapper[administratorID].registrationDate,
            'administrator': administratorID,
        }
    });

    return (
        <div>
            <div>
                <Menu/>
            </div>
            <div className="general_table">
            </div>
            <div style={{height: 600, width: '45%'}} className={classes.root}>
                <Modal/>
                <DataGrid rows={rows} columns={columns} pageSize={10}/>
                <img
                    src={pig}
                    style={{
                        position: "fixed",
                        left: "0",
                        bottom: "-130px",
                        width: "250px",
                        zIndex: "-1",
                        opacity: "0.5",
                    }}
                />
            </div>
        </div>
    )
        ;
}

export default FamiliesTable;
