import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import InitialAmountField from "./field/InitialAmountField";
import DesignationField from "./field/DesignationField";
import Button from "@material-ui/core/Button";
import {makeStyles} from "@material-ui/core/styles";
import "./../css/PersonalAccountForm.css"
import {Box} from "@material-ui/core";

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

function PersonalCashAccountForm(props) {
    const {state} = useContext(AppContext);
    const {cashAccount, user} = state;
    const classes = useStyles();

    const [form, setForm] = useState({
        personId: user.data.email,
        initialAmount: null,
        designation: "",
    });

    const changeHandler = (e) => {
        setForm({
            ...form,
            [e.target.id]: e.target.value,
        });
    };

    return (
        <div>
            <div onSubmit={(e) => props.submitHandler(e)}>
                <div className="block_one_account">
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
                    <Button variant="contained" onClick={()=>props.submitHandler(form)} className={classes.buttonStyleSubmit}>
                        Submit
                    </Button>
                </Box>
                <Box m={1} className={classes.buttonClose}>
                    <Button variant="contained" href="#contained-buttons" onClick={props.handleClose} className={classes.buttonStyleClose}>
                        Close
                    </Button>
                </Box>
            </div>
        </div>
    );

}

export default PersonalCashAccountForm