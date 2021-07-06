import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import {addPerson} from "../context/PersonActions";
import VatField from "./field/VatField";
import EmailField from "./field/EmailField";
import DateField from "./field/DateField";
import ZipCodeField from "./field/ZipCodeField";
import PhoneNumberField from "./field/PhoneNumberField";
import PersonNameField from "./field/PersonNameField";
import StreetField from "./field/StreetField";
import CityField from "./field/CityField";
import CountryField from "./field/CountryField";
import UsernameField from "./field/UsernameField";
import PasswordField from "./field/PasswordField";
import "../../src/css/addMemberModal.css";
import HouseNumber from "./field/HouseNumber";
import Button from "@material-ui/core/Button";
import {Box} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles({
    buttonSubmit: {
        width: "100%",
        float: "left",
        marginLeft: "-100px",
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

export default function PersonForm(props) {
    const {state, dispatch} = useContext(AppContext);
    const {personForm, user, familyOptions} = state;
    const {result} = personForm;
    const classes = useStyles();

    console.log(familyOptions)

    const url = familyOptions._links.new_member.href;

    console.log(url)

    const [formPerson, setFormPerson] = useState({
            familyID: props.data,
            name: "",
            birthDate: "",
            vat: "",
            houseNumber: "",
            street: "",
            city: "",
            country: "",
            email: "",
            zipCode: "",
            username: "",
            password: "",
            phoneNumbers: [],
            request: {
                method: `POST`,
            }
        }
    );

    const changeHandler = (e) => {
        setFormPerson({
            ...formPerson,
            [e.target.id]: e.target.value,
        });
    };

    const addPhone = (phone) => {
        if (phone !== "" && phone != null) {
            setFormPerson({
                ...formPerson,
                phoneNumbers: [...formPerson.phoneNumbers, phone],
            });
        }
    };

    const resetPhoneNumbers = () => {
        setFormPerson({
            ...formPerson,
            phoneNumbers: [],
        });
    };

    const submitHandler = (e) => {
        e.preventDefault();
        addPerson(formPerson, dispatch, user.data.jwt, url);
        props.handleClose();
    };

    return (
        <div className="add-family-form">
            <form onSubmit={(e) => submitHandler(e)}>
                <div className="block_one_member">
                    <div className="formFields">
                        <PersonNameField
                            id="name"
                            required="required"
                            data={formPerson.name}
                            value={formPerson.name}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <EmailField
                            id="email"
                            required="required"
                            data={formPerson.email}
                            value={formPerson.email}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <VatField
                            id="vat"
                            required="required"
                            data={formPerson.vat}
                            value={formPerson.vat}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <DateField
                            id="birthDate"
                            required="required"
                            data={formPerson.birthDate}
                            value={formPerson.birthDate}
                            changeHandler={changeHandler}
                        />
                    </div>
                    <div className="formFields">
                        <PhoneNumberField
                            id="phoneNumbers"
                            data={formPerson.phoneNumbers}
                            addPhone={addPhone}
                            resetPhoneNumbers={resetPhoneNumbers}
                        />
                    </div>
                </div>

                <div className="block_two_member">
                    <div className="formFields">
                        <HouseNumber
                            id="houseNumber"
                            required="required"
                            data={formPerson.houseNumber}
                            value={formPerson.houseNumber}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <StreetField
                            id="street"
                            required="required"
                            data={formPerson.street}
                            value={formPerson.street}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <ZipCodeField
                            id="zipCode"
                            required="required"
                            data={formPerson.zipCode}
                            value={formPerson.zipCode}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <CityField
                            id="city"
                            required="required"
                            data={formPerson.city}
                            value={formPerson.city}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <CountryField
                            id="country"
                            required="required"
                            data={formPerson.country}
                            value={formPerson.country}
                            changeHandler={changeHandler}
                        />
                    </div>
                    <div className="formFields">
                        <UsernameField
                            id="username"
                            required="required"
                            data={formPerson.username}
                            value={formPerson.username}
                            changeHandler={changeHandler}
                        />
                    </div>

                    <div className="formFields">
                        <PasswordField
                            id="password"
                            required="required"
                            data={formPerson.password}
                            value={formPerson.password}
                            changeHandler={changeHandler}
                        />
                    </div>
                </div>
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
            </form>
            <div className="field-error error-date">{result}</div>
        </div>
    );
}
