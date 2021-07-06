import React, {useContext, useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import {IconButton} from "@material-ui/core";
import PersonalCashAccountForm from "./PersonalCashAccountForm";
import AddCircleIcon from '@material-ui/icons/AddCircle';
import {createPersonalCashAccount} from "../context/FinanceActions";
import AppContext from "../context/AppContext";
import {validateAmount} from "./field/InitialAmountField";
import {validateDesignation} from "./field/DesignationField";

const useStyles = makeStyles((theme) => ({
    paper: {
        position: 'fixed',
        width: '650px',
        height: "400px",
        backgroundColor: theme.palette.background.paper,
        padding: theme.spacing(2, 4, 3),
        left: "32%",
        top: "15%"
    },
    button: {
        position: "fixed",
        top: "770px",
        left: "47%",
        width: "400px",
        zIndex: "150",
    },
}));


export default function PersonalCashAccountModal() {
    const {state, dispatch} = useContext(AppContext);
    const {cashAccount, user} = state;
    const {result} = cashAccount;
    const [open, setOpen] = React.useState(false);


    const classes = useStyles();

    const submitHandler = (form) => {
        createPersonalCashAccount(form, dispatch, form.personId, user.data.jwt);
        if (validateAmount(form.initialAmount) === "" && validateDesignation(form.designation) === "") {
            setOpen(false)
        }
    };

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <div>
            <div>
                <div className={classes.button}>
                    <IconButton type="button" onClick={handleOpen}>
                        <AddCircleIcon style={{fontSize: 30}}/>
                    </IconButton>
                </div>
            </div>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                <div className={classes.paper}>
                    {open && <PersonalCashAccountForm handleClose={handleClose} submitHandler={submitHandler}/>}
                </div>
            </Modal>
        </div>
    );
}
