import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import {addPayment, closePaymentForm,} from "../context/FinanceActions";
import DescriptionField from "./field/DesignationField";
import Button from "@material-ui/core/Button";
import {makeStyles} from "@material-ui/core/styles";
import "./../css/PersonalAccountForm.css"
import {Box, TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";
import EuroIcon from "@material-ui/icons/Euro";
import EventIcon from '@material-ui/icons/Event';
import {validateDesignation} from "./field/DescriptionField";
import {validateDate} from "./field/DateField";

const useStyles = makeStyles({
    buttonSubmit: {
        justifyContent: "center",
        alignItems: "center",
        display: "flex",
    },
    buttonClose: {
        justifyContent: "center",
        alignItems: "center",
        display: "flex",
    },
    buttonStyleSubmit: {
        width: "100px",
        backgroundColor: "rgb(181, 211, 203)",
        color: "black",
    },
    buttonStyleClose: {
        width: "100px",
        color: "white",
    }
});

function PaymentForm(props) {
    const {state, dispatch} = useContext(AppContext);
    const {payment, user, paymentForm} = state;
    let result = paymentForm.error;
    const classes = useStyles();

    const handleClose = () => {
        dispatch(closePaymentForm());
    };

    const [form, setForm] = useState({
        personID: "",
        designation: "",
        categoryID: "bce97861-bd6d-4a10-b304-aed1d322b96f",
        amount: null,
        date: "",
    });

    console.log(form)
    console.log(props.handleClose.accountId)

    const changeHandler = (e) => {
        setForm({
            ...form,
            [e.target.id]: e.target.value,
        });
    };

    const validateAmount = (initialAmount) => {

        if (initialAmount !== null && initialAmount !== undefined) {
            const format = new RegExp("^[0-9]+(.[0-9]{1,2})?$");
            // if (initialAmount.length === 0) response = ""
            if (!initialAmount.match(format)){
                return "*  Invalid amount"
            }
        }
        return "";
    }

    const submitHandler = (e) => {
        e.preventDefault();
        if (form.amount !== undefined && form.amount !== null && form.designation !== undefined && form.designation !== "" && form.date !== undefined && form.date !== "" && validateAmount(form.amount) === "" && validateDesignation(form.designation) === "" && validateDate(form.date) === "") {
            addPayment(form, dispatch, props.handleClose.accountId, user.data.jwt);
            if(result === "" || result === "Success!")
            handleClose();
        }
    };

    return (
        <div>
            <div onSubmit={(e) => submitHandler(e)}>
                <div className="block_one_account">
                    <div className="formFields">
                        <DescriptionField
                            id="designation"
                            required="required"
                            data={form.designation}
                            value={form.designation}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <TextField
                            label="Amount"
                            className="paymentFormAmountInput"
                            type="number"
                            id="amount"
                            data={form.amount}
                            value={form.amount}
                            required="required"
                            onChange={(e) => changeHandler(e)}
                            InputProps={{
                                       startAdornment: (
                                           <InputAdornment position="start">
                                               <EuroIcon/>
                                           </InputAdornment>
                                       ),
                                   }}
                        />
                    </div>
                    <div className="field-error" style={{height:"14px"}}>
                        {validateAmount(form.amount)}
                    </div>

                        <div className="formFields">
                            <TextField
                                InputLabelProps={{ shrink: true }}
                                label="Date"
                                id="date"
                                className="paymentFormDateInput"
                                type="date"
                                value={form.date}
                                required="required"
                                onChange={(e) => changeHandler(e)}
                                InputProps={{
                                    startAdornment: (
                                        <InputAdornment position="start">
                                            <EventIcon/>
                                        </InputAdornment>
                                    ),
                                }}
                            />
                        </div>
                    </div>

            </div>
            <div>

                <Box m={1} className={classes.buttonSubmit}>
                    <Button variant="contained" onClick={submitHandler} className={classes.buttonStyleSubmit}s>
                        Submit
                    </Button>
                </Box>
                <Box m={1} className={classes.buttonClose}>
                    <Button variant="contained" href="#contained-buttons" onClick={handleClose} className={classes.buttonStyleClose}>
                        Close
                    </Button>
                </Box>
                <div>
                </div>
            </div>
            <div className="field-error error-relations">{result}</div>
        </div>
    );

}

export default PaymentForm;