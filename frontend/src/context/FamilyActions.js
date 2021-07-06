import {getFromWS, getFromWS2, postToWS, signInToWS, signUpToWS,} from "./AppService";
import React from 'react'

export const FAMILY = "FAMILY";

export const GET_FAMILIES_STARTED = "GET_FAMILIES_STARTED";
export const GET_FAMILIES_SUCCESS = "GET_FAMILIES_SUCCESS";
export const GET_FAMILIES_FAILURE = "GET_FAMILIES_FAILURE";
export const GET_FAMILIES_REFRESH = "GET_FAMILIES_REFRESH";

export const ADD_FAMILY_STARTED = "ADD_FAMILY_STARTED_TYPE"
export const ADD_FAMILY_SUCCESS = "ADD_FAMILY_SUCCESS_TYPE"
export const ADD_FAMILY_FAILURE = "ADD_FAMILY_FAILURE_TYPE"

export const GET_FAMILY_PROFILE_STARTED = "GET_FAMILY_PROFILE_STARTED_TYPE"
export const GET_FAMILY_PROFILE_SUCCESS = "GET_FAMILY_PROFILE_SUCCESS_TYPE"
export const GET_FAMILY_PROFILE_FAILURE = "GET_FAMILY_PROFILE_FAILURE_TYPE"

export const LOGIN_STARTED = 'LOGIN_STARTED';
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGIN_FAILURE = 'LOGIN_FAILURE';

export const OPEN_FAMILY_FORM = 'OPEN_FAMILY_FORM';
export const CLOSE_FAMILY_FORM = 'CLOSE_FAMILY_FORM';

export const SIGNUP_STARTED = 'SIGNUP_STARTED';
export const SIGNUP_SUCCESS = 'SIGNUP_SUCCESS';
export const SIGNUP_FAILURE = 'SIGNUP_FAILURE';

export const SAVE_FAMILY_NAME_SUCCESS = 'SAVE_FAMILY_NAME_SUCCESS_TYPE';

export const CLEAN_LOGIN_AND_SIGNUP_STATUS = 'CLEAN_LOGIN_AND_SIGNUP_STATUS';

// --------------------------------------


export function openFamilyForm() {
    return {
        area: FAMILY,
        type: OPEN_FAMILY_FORM,
    }
}

export function closeFamilyForm() {
    return {
        area: FAMILY,
        type: CLOSE_FAMILY_FORM,
    }
}

// --------------------------------------

export function signIn(credentials, dispatch) {
    dispatch(signInStarted());
    signInToWS(signInSuccess, signInFailure, credentials, dispatch);
}

export function signInStarted() {
    return {
        area: FAMILY,
        type: LOGIN_STARTED,
    };
}

export function signInSuccess(res) {
    return {
        area: FAMILY,
        type: LOGIN_SUCCESS,
        payload: {
            user: res,
        },
    };
}

export function signInFailure(message) {
    return {
        area: FAMILY,
        type: LOGIN_FAILURE,
        payload: {
            error: message,
        },
    };
}

// --------------------------------------

export function signUp(info, dispatch) {
    dispatch(signUpStarted());
    signUpToWS(signUpSuccess, signUpFailure, info, dispatch);
}

export function signUpStarted() {
    return {
        area: FAMILY,
        type: SIGNUP_STARTED,
    };
}

export function signUpSuccess(message) {
    return {
        area: FAMILY,
        type: SIGNUP_SUCCESS,
        payload: {
            success: message,
        },
    };
}

export function signUpFailure(message) {
    return {
        area: FAMILY,
        type: SIGNUP_FAILURE,
        payload: {
            error: message,
        },
    };
}

// --------------------------------------

export function logoutSuccess() {
    return {
        area: FAMILY,
        type: LOGIN_STARTED,
    }
}

export function logout(dispatch) {
    dispatch(logoutSuccess());
}

export function saveFamilyName(familyName, dispatch) {
    console.log(familyName);
    dispatch(saveFamilyNameSuccess(familyName));
}

export function saveFamilyNameSuccess(familyName) {
    return {
        area: FAMILY,
        type: SAVE_FAMILY_NAME_SUCCESS,
        payload: {
            familyName: familyName,
        }
    }
}

// --------------------------------------

export function getListOfFamilies(dispatch, token) {
    const RESOURCE_URL = "families"
    dispatch(getListOfFamiliesStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    getFromWS(
        (res) => dispatch(getListOfFamiliesSuccess(res)),
        (err) => dispatch(getListOfFamiliesFailure(err.message)),
        RESOURCE_URL, headers
    );
}

export function getListOfFamiliesStarted() {
    return {
        area: FAMILY,
        type: GET_FAMILIES_STARTED,
    };
}

export function getListOfFamiliesSuccess(res) {
    return {
        area: FAMILY,
        type: GET_FAMILIES_SUCCESS,
        payload: {
            data: [...res],
        },
    };
}

export function getListOfFamiliesFailure(message) {
    return {
        area: FAMILY,
        type: GET_FAMILIES_FAILURE,
        payload: {
            error: message,
        },
    };
}

export function getListOfFamiliesRefresh() {
    return {
        area: FAMILY,
        type: GET_FAMILIES_REFRESH,
    };
}

// --------------------------------------

export function addFamily(form, dispatch, token) {
    const RESOURCE_URL = "families"
    dispatch(addFamilyStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    postToWS(addFamilySuccess, addFamilyFailure, form, dispatch, RESOURCE_URL, headers);
}

export function addFamilyStarted() {
    return {
        area: FAMILY,
        type: ADD_FAMILY_STARTED,
    }
}

export function addFamilySuccess() {
    return {
        area: FAMILY,
        type: ADD_FAMILY_SUCCESS,
    }
}

export function addFamilyFailure(message) {
    return {
        area: FAMILY,
        type: ADD_FAMILY_FAILURE,
        payload: {
            error: message
        }
    }
}

// --------------------------------------

export function getFamilyProfile(dispatch, familyID, token) {
    const RESOURCE_URL = `families/${familyID}`;
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    console.log(headers)
    getFromWS(
        (res) => dispatch(getFamilyProfileSuccess(res)),
        (err) => dispatch(getFamilyProfileFailure(err.message)),
        RESOURCE_URL,headers
    );
}

export function getFamilyProfile2(dispatch, familyID, token, url) {
    console.log("olá")
    console.log(url)
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    console.log(headers)
    getFromWS2(
        (res) => dispatch(getFamilyProfileSuccess(res)),
        (err) => dispatch(getFamilyProfileFailure(err.message)),
        url,headers
    );
}

export function getFamilyProfileSuccess(res) {
    return {
        area: FAMILY,
        type: GET_FAMILY_PROFILE_SUCCESS,
        payload: {
            data: res,
        },
    };
}

export function getFamilyProfileFailure(message) {
    return {
        area: FAMILY,
        type: GET_FAMILY_PROFILE_FAILURE,
        payload: {
            error: message,
        },
    };
}

// --------------------------------------

export function cleanLoginAndSignupStatus(dispatch) {
    dispatch(clearLoginAndSignup());
}

export function clearLoginAndSignup() {
    return {
        area: FAMILY,
        type: CLEAN_LOGIN_AND_SIGNUP_STATUS,
    };
}

