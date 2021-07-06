import {optionsFromWS, signInToWS, signUpToWS} from "./AppService";

export const ACCESS = "MENU_OPTIONS";

export const MENU_STARTED = "MENU_STARTED"
export const MENU_SUCCESS = "MENU_SUCCESS"
export const MENU_FAILURE = "MENU_FAILURE"

export const LOGIN_STARTED = 'LOGIN_STARTED';
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGIN_FAILURE = 'LOGIN_FAILURE';

export const SIGNUP_STARTED = 'SIGNUP_STARTED';
export const SIGNUP_SUCCESS = 'SIGNUP_SUCCESS';
export const SIGNUP_FAILURE = 'SIGNUP_FAILURE';

export const CLEAN_LOGIN_AND_SIGNUP_STATUS = 'CLEAN_LOGIN_AND_SIGNUP_STATUS';

// --------------------------------------

export function accessMenuOptions(dispatch, token) {
    const RESOURCE_URL = `/`;
    dispatch(menuStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    optionsFromWS(menuSuccess, menuFailure, dispatch, RESOURCE_URL, headers);
}

export function menuStarted() {
    return {
        area: ACCESS,
        type: MENU_STARTED,
    }
}

export function menuSuccess(res) {
    return {
        area: ACCESS,
        type: MENU_SUCCESS,
        payload: {
            options: res,
        }
    }
}

export function menuFailure(message) {
    return {
        area: ACCESS,
        type: MENU_FAILURE,
        payload: {
            error: message
        }
    }
}

// --------------------------------------


// --------------------------------------

export function signIn(credentials, dispatch) {
    dispatch(signInStarted());
    signInToWS(signInSuccess, signInFailure, credentials, dispatch);
}

export function signInStarted() {
    return {
        area: ACCESS,
        type: LOGIN_STARTED,
    };
}

export function signInSuccess(res) {
    return {
        area: ACCESS,
        type: LOGIN_SUCCESS,
        payload: {
            user: res,
        },
    };
}

export function signInFailure(message) {
    return {
        area: ACCESS,
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
        area: ACCESS,
        type: SIGNUP_STARTED,
    };
}

export function signUpSuccess(message) {
    return {
        area: ACCESS,
        type: SIGNUP_SUCCESS,
        payload: {
            success: message,
        },
    };
}

export function signUpFailure(message) {
    return {
        area: ACCESS,
        type: SIGNUP_FAILURE,
        payload: {
            error: message,
        },
    };
}

// --------------------------------------

export function logoutSuccess() {
    return {
        area: ACCESS,
        type: LOGIN_STARTED,
    }
}

export function logout(dispatch) {
    dispatch(logoutSuccess());
}

// --------------------------------------

export function cleanLoginAndSignupStatus(dispatch) {
    dispatch(clearLoginAndSignup());
}

export function clearLoginAndSignup() {
    return {
        area: ACCESS,
        type: CLEAN_LOGIN_AND_SIGNUP_STATUS,
    };
}
