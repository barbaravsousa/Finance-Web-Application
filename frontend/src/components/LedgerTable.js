import React, {useContext} from "react";
import makeStyles from "@material-ui/core/styles/makeStyles";
import AppContext from "../context/AppContext";
import {DataGrid} from "@material-ui/data-grid";
import {getLedgerMovements} from "../context/FinanceActions";
import {getUserProfile} from "../context/PersonActions";
import "../css/LedgerTable.css"

const useStyles = makeStyles({
    root: {
        '& .super-app-theme--header': {
            backgroundColor: '#B5D3CB',
            fontSize: "15px",
            fontFamily: "Verdana, Geneva, Tahoma, sans-serif",
        },
        background: "#F1F6F5",
        borderRadius: "10px",
        margin: "0 auto"
    },
});

export default function LedgerTable() {
    const {state, dispatch} = useContext(AppContext);
    const {ledger, user, profile} = state;
    const {refresh} = ledger;
    const classes = useStyles();
    const data = ledger.data;
    const token = user.data.jwt;
    const personID = user.data.email;
    const personName = profile.name;

    const PAYMENT = "PAYMENT";
    const DEBIT_TRANSFER = "DEBIT_TRANSFER";

    React.useEffect(() => {
        getLedgerMovements(dispatch, personID, token);
        getUserProfile(dispatch, personID, token);
    }, [refresh]);


    const columns = [
        {field: 'date', headerName: 'Date',headerClassName: 'super-app-theme--header', width: 200},
        {field: 'movementType', headerName: 'Type',headerClassName: 'super-app-theme--header', width: 200},
        {field: 'amount', headerName: 'Amount',headerClassName: 'super-app-theme--header', width: 200},
        {field: 'senderAccount', headerName: 'Sender Account',headerClassName: 'super-app-theme--header', width: 200},
        /*{field: 'receiverAccount', headerName: 'Receiver Account',headerClassName: 'super-app-theme--header', width: 200},
        {field: 'familyMember', headerName: 'Family Member',headerClassName: 'super-app-theme--header', width: 180},*/
        {field: 'description', headerName: 'Description', headerClassName: 'super-app-theme--header',width: 250},
        /*{field: 'category', headerName: 'Category',headerClassName: 'super-app-theme--header', width: 150},*/
        {field: 'balanceToThisDate', headerName: 'Remaining Balance',headerClassName: 'super-app-theme--header',flex:1},
    ];

    const parseMovementType = (initialMovementType) => {
        let str1 = initialMovementType.toLowerCase();
        let str2 = str1.split("_");
        for (let i = 0; i < str2.length; i++) {
            str2[i] = str2[i].charAt(0).toUpperCase() + str2[i].slice(1);
        }
        return str2.join(" ");
    }

    const rows = Object.keys(data).map(index => {
            let amount = data[index].amount;
            if (data[index].movementType === PAYMENT || data[index].movementType === DEBIT_TRANSFER) {
                amount = "- " + amount;
            }
            let movementType = parseMovementType(data[index].movementType);

            return {
                'id': index,
                'date': data[index].date,
                'movementType': movementType,
                'amount': amount + " €",
                'senderAccount': data[index].senderAccount,
                'receiverAccount': data[index].receiverAccount,
                'familyMember': data[index].familyMember,
                'description': data[index].description,
                'category': data[index].category,
                'balanceToThisDate': data[index].balanceToThisDate + " €",
            }
        }
        )
    ;

    return (
        <div>
            <div className="ledger-title">
                <h1>{personName}'s Ledger</h1>
            </div>
            <div className="general_table">
                <div style={{height: 500, width: '90%'}} className={classes.root}>
                    <DataGrid rows={rows} columns={columns} pageSize={8}/>
                </div>
            </div>
        </div>
    );

}