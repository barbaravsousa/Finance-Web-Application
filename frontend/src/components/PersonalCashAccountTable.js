import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import {closePaymentForm, getListOfAccounts, openPaymentForm} from "../context/FinanceActions";
import Menu from "./menuOptions/Menu";
import pig from "../images/pineapple-pig-half1.png";
import "../css/AccountsTable.css";
import {DataGrid} from "@material-ui/data-grid";
import {Button, makeStyles} from "@material-ui/core";
import PersonalCashAccountModal from "./PersonalCashAccountModal";
import PaymentModal from "./PaymentModal";
import {getUserProfile} from "../context/PersonActions";



const useStyles = makeStyles({
    root: {
        '& .super-app-theme--header': {
            backgroundColor: '#B5D3CB',
            fontSize: "20px",
            fontFamily: "Verdana, Geneva, Tahoma, sans-serif",
        },

        marginTop: "60px",
        background: "#F1F6F5",
        borderRadius: "10px",
        margin: "0 auto"
    },
});


function PersonalCashAccountTable(props) {
    let {state, dispatch} = useContext(AppContext);
    let {cashAccount, user,profile, paymentForm} = state;
    const {data, refresh} = cashAccount;
    const {open} = paymentForm
    const token = user.data.jwt;
    const personId = user.data.email;
    const personName = profile.name
    const classes = useStyles();



    React.useEffect(() => {
        getListOfAccounts(dispatch, personId, token);
        getUserProfile(dispatch,personId,token)
    }, [refresh]);

    const[selectedAccountID, setSelectedAccountID] = useState (null)

    const handleOpenClick = (accountID) => {
        dispatch(openPaymentForm());
        setSelectedAccountID(accountID);
    }
    const handleClose = () => {
       dispatch(closePaymentForm());
    };


    const columns = [
        {field: 'designation', headerName: 'Designation', headerClassName: 'super-app-theme--header',flex: 1},
        {field: 'balance', headerName: 'Balance', headerClassName: 'super-app-theme--header',flex:1},
        {
            field: 'payment',
            headerName: "Payments",
            headerClassName: 'super-app-theme--header',
            width:190,
            renderCell:(params) => (
                <div>
                <Button
                    color="rgb(50, 50, 50)"
                    size="small"
                    style={{marginLeft:10}}
                    onClick={()=>handleOpenClick(params.value)}
                >
                    Add Payment
                </Button>
                </div>

            )
        },
    ];

    const rows = data.map(account => {

        return {
            'id': account.accountID,
            'balance': account.balance + " €",
            'designation': account.accountDesignation,
            'payment': account.accountID
        }
    });

    return (
        <div>
            <div>
                <Menu/>
            </div>
            <div className="ledger-title">
                <h2 className="h2">{personName}'s Personal Accounts</h2>
            </div>
            {open && <PaymentModal accountId={selectedAccountID} open={open} handleClose={handleClose}/>}
            <div style={{height: 600, width: 800}} className={classes.root}>
                <PersonalCashAccountModal/>
                <DataGrid rows={rows} columns={columns} pageSize={9}/>
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
export default PersonalCashAccountTable;