import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import {addFamily} from "../context/FamilyActions";
import "../../src/css/AddFamilyModal.css";
import FamilyNameField from "./field/FamilyNameField";
import PersonNameField from "./field/PersonNameField";
import EmailField from "./field/EmailField";
import VatField from "./field/VatField";
import DateField from "./field/DateField";
import StreetField from "./field/StreetField";
import ZipCodeField from "./field/ZipCodeField";
import CityField from "./field/CityField";
import CountryField from "./field/CountryField";
import HouseNumber from "./field/HouseNumber";
import PhoneNumbersField from "./field/PhoneNumberField";
import PasswordField from "./field/PasswordField";
import UsernameField from "./field/UsernameField";
import Button from '@material-ui/core/Button';
import {makeStyles} from "@material-ui/core/styles";
import {Box} from "@material-ui/core";

const useStyles = makeStyles({
    buttonSubmit: {
        width: "100%",
        float: "left",
        marginLeft: "40px",
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

function FamilyForm(props) {
    const {state, dispatch} = useContext(AppContext);
    const {familyForm, user} = state;
    const {result} = familyForm;
    const classes = useStyles();

    const [form, setForm] = useState({
        familyName: "",
        personName: "",
        birthDate: "",
        vat: "",
        houseNumber: "",
        street: "",
        city: "",
        country: "",
        email: "",
        zipCode: "",
        password: "",
        username: "",
        phoneNumbers: [],
    });
    console.log(form)
    const changeHandler = (e) => {
        setForm({
            ...form,
            [e.target.id]: e.target.value,
        });
    };

    const addPhone = (phone) => {
        if (phone !== "" && phone != null) {
            setForm({
                ...form,
                phoneNumbers: [...form.phoneNumbers, phone],
            });
        }
    };

    const resetPhoneNumbers = () => {
        setForm({
            ...form,
            phoneNumbers: [],
        });
    };

    const submitHandler = (e) => {
        e.preventDefault();
        addFamily(form, dispatch, user.data.jwt);
        props.handleClose();
    };

    return (
        <div>
            <form onSubmit={(e) => submitHandler(e)}>
                <div className="block_one">
                    <div className="formFields">
                        <FamilyNameField
                            id="familyName"
                            required="required"
                            data={form.familyName}
                            value={form.familyName}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <PersonNameField
                            id="personName"
                            required="required"
                            data={form.personName}
                            value={form.personName}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <EmailField
                            id="email"
                            required="required"
                            data={form.email}
                            value={form.email}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <VatField
                            id="vat"
                            required="required"
                            data={form.vat}
                            value={form.vat}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <DateField
                            id="birthDate"
                            required="required"
                            data={form.birthDate}
                            value={form.birthDate}
                            changeHandler={changeHandler}
                        />
                    </div>
                    <div className="formFields">
                        <PhoneNumbersField
                            id="phoneNumbers"
                            data={form.phoneNumbers}
                            addPhone={addPhone}
                            resetPhoneNumbers={resetPhoneNumbers}
                        />
                    </div>
                </div>
                <div className="block_two">
                    <div className="formFields">
                        <HouseNumber
                            id="houseNumber"
                            required="required"
                            data={form.houseNumber}
                            value={form.houseNumber}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <StreetField
                            id="street"
                            required="required"
                            data={form.street}
                            value={form.street}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <ZipCodeField
                            id="zipCode"
                            required="required"
                            data={form.zipCode}
                            value={form.zipCode}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <CityField
                            id="city"
                            required="required"
                            data={form.city}
                            value={form.city}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <CountryField
                            id="country"
                            required="required"
                            data={form.country}
                            value={form.country}
                            changeHandler={changeHandler}
                        />
                    </div>
                    <div className="formFields">
                        <UsernameField
                            id="username"
                            required="required"
                            data={form.username}
                            value={form.username}
                            changeHandler={changeHandler}
                        />
                    </div>
                    <div className="formFields">
                        <PasswordField
                            id="password"
                            required="required"
                            data={form.password}
                            value={form.password}
                            changeHandler={changeHandler}
                        />
                    </div>
                </div>
                <Box m={1} className={classes.buttonSubmit}>
                    <Button variant="contained" href="#contained-buttons" onClick={submitHandler} className={classes.buttonStyleSubmit}>
                        Submit
                    </Button>
                </Box>
                <Box m={1} className={classes.buttonSubmit}>
                    <Button variant="contained" href="#contained-buttons" onClick={props.handleClose} className={classes.buttonStyleClose}>
                        Close
                    </Button>
                </Box>
            </form>
            <div className="field-error error-date">{result}</div>
        </div>
    );
}

export default FamilyForm;
