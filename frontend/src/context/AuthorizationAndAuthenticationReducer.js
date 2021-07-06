import {
    MENU_FAILURE,
    MENU_STARTED,
    MENU_SUCCESS,
    LOGIN_STARTED,
    LOGIN_SUCCESS,
    LOGIN_FAILURE,
    SIGNUP_STARTED,
    SIGNUP_SUCCESS,
    SIGNUP_FAILURE,
    CLEAN_LOGIN_AND_SIGNUP_STATUS
} from "./AuthorizationAndAuthenticationActions";


function authorizationAndAuthenticationReducer(state, action) {
    switch (action.type) {
        case MENU_STARTED:
            return {
                ...state,
                options: [],
            };
        case MENU_SUCCESS:
            const {options} = action.payload
            return {
                ...state,
                options,
            };
        case MENU_FAILURE:
            return {
                ...state,
                error: "Error: " + action.payload.error,
            };

        case LOGIN_STARTED:
            return {
                ...state,
                login: {
                    status: null
                },
                isLogged: false,
                user: [],
                options: [],
                familyOptions:[],
                familyCashAccount: [],
            };
        case LOGIN_SUCCESS:
            const {user} = action.payload;
            return {
                ...state,
                login: {
                    status: null
                },
                user,
                isLogged: true,
            };
        case LOGIN_FAILURE:
            return {
                ...state,
                login: {
                    status: action.payload.error
                },
                isLogged: false,
            };

        case SIGNUP_STARTED:
            return {
                ...state,
                signup: {
                    statusLoading: "Loading..."
                }
            };

        case SIGNUP_SUCCESS:
            return {
                ...state,
                signup: {
                    statusSuccess: action.payload.success
                }
            };
        case SIGNUP_FAILURE:
            return {
                ...state,
                signup: {
                    statusFailure: action.payload.error
                }
            };
        case CLEAN_LOGIN_AND_SIGNUP_STATUS:
            return {
                ...state,
                signup: {
                    statusFailure: null,
                    statusLoading: null,
                    statusSuccess: null,
                },
                login: {
                    status: null
                }
            }
    }
}

export default authorizationAndAuthenticationReducer;