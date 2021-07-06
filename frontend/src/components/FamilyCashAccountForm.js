import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import {closeFamilyCashAccountForm, createFamilyCashAccount} from "../context/FinanceActions";
import InitialAmountField from "./field/InitialAmountField";
import DesignationField from "./field/DesignationField";
import '../css/FamilyCashAccountForm.css';
import {Box} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {makeStyles} from "@material-ui/core/styles";


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


function FamilyCashAccountForm(props) {
    const {state, dispatch} = useContext(AppContext);
    const {cashAccount, user} = state;
    const {result} = cashAccount;
    const classes = useStyles();


    const handleClose = () => {
        dispatch(closeFamilyCashAccountForm());
    };

    const [form, setForm] = useState({
        familyAdministratorID: user.data.email,
        familyID: user.data.familyID,
        initialAmount: null,
        designation: "",
    });

    console.log(state)
    const changeHandler = (e) => {
        setForm({
            ...form,
            [e.target.id]: e.target.value,
        });
    };

    const submitHandler = (e) => {
        e.preventDefault();
        createFamilyCashAccount(form, dispatch, form.familyID, user.data.jwt);
    };

    return (
        <div>
            <div onSubmit={(e) => submitHandler(e)}>
                <div className="block_one_familyAccount">
                    <div className="formFields">
                        <InitialAmountField
                            id="initialAmount"
                            required="required"
                            data={form.initialAmount}
                            value={form.initialAmount}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <DesignationField
                            id="designation"
                            required="required"
                            data={form.designation}
                            value={form.designation}
                            changeHandler={changeHandler}
                        />
                    </div>
                </div>
            </div>
            <div>
                <Box m={1} className={classes.buttonSubmit}>
                    <Button variant="contained" href="#contained-buttons" onClick={submitHandler}
                            className={classes.buttonStyleSubmit}>
                        Submit
                    </Button>
                </Box>
                <Box m={1} className={classes.buttonSubmit}>
                    <Button variant="contained" href="#contained-buttons" onClick={props.handleClose}
                            className={classes.buttonStyleClose}>
                        Close
                    </Button>
                </Box>
            </div>
        </div>
    );
}

export default FamilyCashAccountForm