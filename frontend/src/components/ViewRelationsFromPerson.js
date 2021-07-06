import AppContext from "../context/AppContext";
import {getFamilyRelations, openRelationForm} from "../context/PersonActions";
import React, {useContext} from "react";
import Menu from "./menuOptions/Menu";
import "../css/addRelationModal.css";
import pig from "../images/pineapple-pig-half1.png";
import GoPrevious from "./GoPreviousPath";
import {makeStyles} from "@material-ui/core";
import {DataGrid} from "@material-ui/data-grid";
import RelationModal from "./relationField/RelationModal";


const useStyles = makeStyles({
    root: {
        '& .super-app-theme--header': {
            backgroundColor: '#5e8b7e',
            fontSize: "20px",
            fontFamily: "Verdana, Geneva, Tahoma, sans-serif",
        },
        '& .MuiDataGrid-root .MuiDataGrid-cellLeft': {
            fontFamily: "Verdana, Geneva, Tahoma, sans-serif",
            fontSize: "20px",
            textAlign: "center"
        },
        marginTop: "200px",
        background: "#F1F6F5",
        borderRadius: "10px",
        margin: "0 auto",
    },
});


function ViewRelationsFromPerson(props) {
    const {state, dispatch} = useContext(AppContext);
    const {relations, relationForm, userRelation} = state;
    const {data, refresh} = relations;
    const {openForm} = relationForm;
    const classes = useStyles();

    console.log(state);

    const handleClick = () => {
        dispatch(openRelationForm());
    };

    let admin = false;

    if (state.user.data.roles !== undefined && admin === false) {
        admin = state.user.data.roles.includes("ROLE_ADMIN")
    }

    React.useEffect(() => {
        getFamilyRelations(dispatch, props.match.params.email);
    }, [refresh]);


    const columns = [
        {
            field: 'relation',
            headerName: 'Relations',
            headerClassName: 'super-app-theme--header',
            headerAlign: "center",
            flex: 1,
        },

    ];
    const convertRelations = (element) => {
        const arrayElement = element.split(',');
        return arrayElement[0] + " is " + arrayElement[1] + " of " + arrayElement[2]
    }

    const rows = data.map((element, index) => {
        return {
            'id': index,
            'relation': convertRelations(element)
        }
    });


    return (
        <div>
            <div>
                <Menu/>
            </div>
            <GoPrevious/>
            <div style={{height: 400, width: 800}} className={classes.root}>
                {admin && <RelationModal info={props.match.params}/>}
                <DataGrid rows={rows} columns={columns} pageSize={6}/>
            </div>
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

    );
}

export default ViewRelationsFromPerson;
