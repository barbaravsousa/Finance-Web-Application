import {Provider} from "./AppContext";
import React, {useReducer} from "react";
import PropTypes from "prop-types";
import reducer from "./Reducer";

const initialState = {
    isLogged: false,
    user: [],
    userRelation: "",
    selectedEmail: "",
    login: {
        status: null
    },
    members: {
        refresh: false,
        loading: true,
        error: null,
        data: [],
    },
    families: {
        refresh: false,
        loading: true,
        error: null,
        data: [],
    },
    emails: {
        refresh: false,
        loading: true,
        error: null,
        data: [],
    },
    relations:  {
        refresh: false,
        loading: true,
        error: null,
        data: [],
    },
    profile: {
        refresh:false,
    },
    familyForm: {
        openForm: false,
        result: null,
    },
    signup: {
        statusFailure: null,
        statusSuccess: null,
        statusLoading: null
    },
    personForm: {
        openForm: false,
        result: null
    },
    relationForm: {
        loading: false,
        error: null,
    },
    paymentForm: {
        open: false,
        loading: false,
        error: "",
    },
    emailForm: {
        openForm: false,
        result: null
    },
    deleteEmailForm: {
        openDForm: false,
        result: null
    },
    familyProfile: {
        data: [],
        mapper: {}
    },
    systemRelationList: {
        error: null,
        data: [],
    },
    familyCashAccountForm: {
        loading: false,
        error: null,
    },
    personalCashAccountForm: {
        loading: false,
        error: null,
    },
    cashAccount:  {
        refresh: false,
        loading: true,
        error: null,
        data: [],
    },
    options: [],
    categoryTree: {
        refresh: false,
        loading: true,
        error: false,
        data: {
            designation: "Root",
            children: []
        }
    },
    ledger: {
        refresh: false,
        loading: false,
        error: null,
        data: [],
    },
    familyLedger: {
        refresh: false,
        loading: false,
        error: null,
        data: [],
    },
    familyCash: {
        loading: false,
        error: false,
        balance: null,
    },
    payment: {
        loading: false,
        error: false,
        balance: null,
    },
    
    familyCashAccount: {
        refreshCash: false,
    },
    familyOptions: []
};

const headers = {
    email: "Email",
    name: "Name",
    vat: "VAT",
    birthDate: "Birthdate",
    mainEmail: "Main Email",
    otherEmails: "Other Emails",
    telephoneNumbers: "Telephone Numbers",
    houseNumber: "House Number",
    street: "Street",
    city: "City",
    country: "Country",
    zipCode: "Zip Code",
};


const headers1 = {
    familyName: "FamilyName: ",
    registrationDate: "RegistrationDate: ",
    administratorID: "Administrator: ",
};

const headers2 = {
    balance: "Balance: ",
    accountDesignation: "Designation: ",
};


const AppProvider = (props) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <Provider
            value={{
                state,
                headers,
                headers1,
                headers2,
                dispatch,
            }}
        >
            {props.children}
        </Provider>
    );
};

AppProvider.propTypes = {
    children: PropTypes.node,
};

export default AppProvider;
