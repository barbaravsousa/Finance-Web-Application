import React, {useContext} from "react";
import makeStyles from "@material-ui/core/styles/makeStyles";
import AppContext from "../context/AppContext";
import {DataGrid} from "@material-ui/data-grid";
import {getFamilyLedgerMovements} from "../context/FinanceActions";
import "../css/LedgerTable.css"
import TextField from "@material-ui/core/TextField";
import InputAdornment from "@material-ui/core/InputAdornment";
import LabelIcon from '@material-ui/icons/Label';
import {EuroSymbol} from "@material-ui/icons";

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
    margin: {
        margin: "10px auto 10px auto"
    }
});

export default function FamilyLedgerTable() {
    const {state, dispatch} = useContext(AppContext);
    const {familyCashAccount, familyLedger, user} = state;
    const {refresh} = familyLedger;
    const classes = useStyles();
    const data = familyLedger.data;
    const token = user.data.jwt;
    const familyID = user.data.familyID;

    const PAYMENT = "PAYMENT";
    const DEBIT_TRANSFER = "DEBIT_TRANSFER";

    React.useEffect(() => {
        getFamilyLedgerMovements(dispatch, familyID, token);
    }, [refresh]);

    const {
        balance,
        accountDesignation,
    } = familyCashAccount;

    const columns = [
        {field: 'date', headerName: 'Date', headerClassName: 'super-app-theme--header', width: 120},
        {field: 'movementType', headerName: 'Type', headerClassName: 'super-app-theme--header', width: 120},
        {field: 'amount', headerName: 'Amount', headerClassName: 'super-app-theme--header', width: 140},
        /*{
            field: 'otherAccount',
            headerName: 'Sender/Receiver Account',
            headerClassName: 'super-app-theme--header',
            width: 220
        },*/
        //{field: 'receiverAccount', headerName: 'Receiver Account',headerClassName: 'super-app-theme--header', width: 200},
        {field: 'familyMember', headerName: 'Family Member', headerClassName: 'super-app-theme--header', width: 200},
        {field: 'description', headerName: 'Description', headerClassName: 'super-app-theme--header', width: 170},
        /*{field: 'category', headerName: 'Category', headerClassName: 'super-app-theme--header', width: 150},*/
        {
            field: 'balanceToThisDate',
            headerName: 'Remaining Balance',
            headerClassName: 'super-app-theme--header',
            flex: 1
        },
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
            let otherAccount = data[index].receiverAccount;
            let amount = data[index].amount;
            if (data[index].movementType === PAYMENT || data[index].movementType === DEBIT_TRANSFER) {
                amount = "- " + amount;
            }
            let movementType = parseMovementType(data[index].movementType);
            if (otherAccount === DEBIT_TRANSFER) {
                otherAccount = data[index].senderAccount;
            }

            return {
                'id': index,
                'date': data[index].date,
                'movementType': movementType,
                'amount': amount + " €",
                'otherAccount': otherAccount,
                'familyMember': data[index].familyMember,
                'description': data[index].description,
                'category': data[index].category,
                'balanceToThisDate': data[index].balanceToThisDate + " €",
            }
        }
        )
    ;

    return (
        <div className="container">

            <div>
                <div>
                    <TextField label="Account Balance"
                               className={classes.margin}
                               value={balance}
                               variant="outlined"
                               InputProps={{
                                   startAdornment: (
                                       <InputAdornment position="start">
                                           <EuroSymbol/>
                                       </InputAdornment>
                                   ),
                               }}/>
                    </div>
                    <div>
                        <TextField label="Account Designation"
                                   className={classes.margin}
                                   value={accountDesignation}
                                   variant="outlined"
                                   InputProps={{
                                       startAdornment: (
                                           <InputAdornment position="start">
                                               <LabelIcon/>
                                           </InputAdornment>
                                       ),
                                   }}/>
                    </div>
            </div>

            <div>
                <div className="general_table">
                    <div style={{height: 500, width: 1300}} className={classes.root}>
                        <DataGrid rows={rows} columns={columns} pageSize={8}/>
                    </div>
                </div>
            </div>
        </div>
    );

}