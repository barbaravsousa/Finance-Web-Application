import {
    CLOSE_FAMILY_CASH_ACCOUNT_FORM,
    CLOSE_PERSONAL_CASH_ACCOUNT_FORM,
    CREATE_FAMILY_CASH_ACCOUNT_FAILURE,
    CREATE_FAMILY_CASH_ACCOUNT_STARTED,
    CREATE_FAMILY_CASH_ACCOUNT_SUCCESS,
    CREATE_PERSONAL_CASH_ACCOUNT_FAILURE,
    CREATE_PERSONAL_CASH_ACCOUNT_STARTED,
    CREATE_PERSONAL_CASH_ACCOUNT_SUCCESS,
    GET_ACCOUNTS_FAILURE,
    GET_ACCOUNTS_STARTED,
    GET_ACCOUNTS_SUCCESS,
    GET_FAMILY_CASH_ACCOUNT_BALANCE_FAILURE,
    GET_FAMILY_CASH_ACCOUNT_BALANCE_STARTED,
    GET_FAMILY_CASH_ACCOUNT_BALANCE_SUCCESS,
    GET_LEDGER_MOVEMENTS_FAILURE,
    GET_LEDGER_MOVEMENTS_STARTED,
    GET_LEDGER_MOVEMENTS_SUCCESS,
    OPEN_FAMILY_CASH_ACCOUNT_FORM,
    OPEN_PERSONAL_CASH_ACCOUNT_FORM,
    GET_FAMILY_LEDGER_MOVEMENTS_STARTED,
    GET_FAMILY_LEDGER_MOVEMENTS_SUCCESS,
    GET_FAMILY_LEDGER_MOVEMENTS_FAILURE,
    OPEN_PAYMENT_FORM,
    CLOSE_PAYMENT_FORM,
    ADD_PAYMENT_STARTED,
    ADD_PAYMENT_SUCCESS,
    ADD_PAYMENT_FAILURE
} from "./FinanceActions";

function financeReducer(state, action) {
    switch (action.type) {
        case CREATE_FAMILY_CASH_ACCOUNT_STARTED:
            return {
                ...state,
                familyCashAccountForm: {
                    openFamilyCashForm: true,
                    result: "Loading..."
                },
            };
        case CREATE_FAMILY_CASH_ACCOUNT_SUCCESS:
            return {
                ...state,
                familyLedger: {
                    refresh: true,
                    loading: false,
                    error: null,
                    data: [],
                },
                cashAccount: {
                    refresh: true,
                    loading: false,
                    error: null,
                    data: [],
                },
                familyCashAccountForm: {
                    openFamilyCashForm: false,
                    result: "Success!"
                },
            };
        case CREATE_FAMILY_CASH_ACCOUNT_FAILURE:
            return {
                ...state,
                familyCashAccountForm: {
                    openFamilyCashForm: true,
                    error: action.payload.error,
                },
            };
        case OPEN_FAMILY_CASH_ACCOUNT_FORM:
            return {
                ...state,
                familyCashAccountForm: {
                    openFamilyCashForm: true,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case CLOSE_FAMILY_CASH_ACCOUNT_FORM:
            return {
                ...state,
                familyCashAccountForm: {
                    openFamilyCashForm: false,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case ADD_PAYMENT_STARTED:
            return {
                ...state,
                paymentForm: {
                    openPaymentForm: true,
                    result: "Loading..."
                },
            };
        case ADD_PAYMENT_SUCCESS:
            return {
                ...state,
                payment: {
                    refresh: true,
                    loading: false,
                    error: null,
                    data: [],
                },
                cashAccount: {
                    refresh: true,
                    loading: false,
                    error: null,
                    data: [],
                },
                paymentForm: {
                    openPaymentForm: false,
                    result: "Success!"
                },
            };
        case ADD_PAYMENT_FAILURE:
            return {
                ...state,
                paymentForm: {
                    open: true,
                    error: action.payload.error.errorMessage,
                },
            };
        case OPEN_PAYMENT_FORM:
            return {
                ...state,
                paymentForm: {
                    open: true,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case CLOSE_PAYMENT_FORM:
            return {
                ...state,
                paymentForm: {
                    open: false,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case CREATE_PERSONAL_CASH_ACCOUNT_STARTED:
            return {
                ...state,
                personalCashAccountForm: {
                    openPersonalCashForm: true,
                    result: "Loading..."
                },
            };
        case CREATE_PERSONAL_CASH_ACCOUNT_SUCCESS:
            return {
                ...state,
                cashAccount: {
                    refresh: true,
                    loading: false,
                    error: null,
                    data: [],
                },
                personalCashAccountForm: {
                    openPersonalCashForm: false,
                    result: "Success!"
                },
            };
        case CREATE_PERSONAL_CASH_ACCOUNT_FAILURE:
            return {
                ...state,
                personalCashAccountForm: {
                    openPersonalCashForm: true,
                    result: "Error: " + action.payload.error,
                },
            };
        case OPEN_PERSONAL_CASH_ACCOUNT_FORM:
            return {
                ...state,
                personalCashAccountForm: {
                    openPersonalCashForm: true,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case CLOSE_PERSONAL_CASH_ACCOUNT_FORM:
            return {
                ...state,
                personalCashAccountForm: {
                    openPersonalCashForm: false,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case GET_ACCOUNTS_STARTED:
            return {
                ...state,
                cashAccount: {
                    formOpen: false,
                    loading: true,
                    error: null,
                    data: [],
                },
            };
        case GET_ACCOUNTS_SUCCESS:
            return {
                ...state,
                cashAccount: {
                    formOpen: false,
                    loading: false,
                    error: null,
                    data: action.payload.data,
                },
            };
        case GET_ACCOUNTS_FAILURE:
            return {
                ...state,
                cashAccount: {
                    formOpen: false,
                    loading: true,
                    error: action.payload.data,
                    data: [],
                },
            };
        case GET_FAMILY_CASH_ACCOUNT_BALANCE_STARTED:
            return {
                ...state,
                familyCashAccount: [],
            };
        case GET_FAMILY_CASH_ACCOUNT_BALANCE_SUCCESS:
            return {
                ...state,
                familyCashAccount: action.payload.data,
            };
        case GET_FAMILY_CASH_ACCOUNT_BALANCE_FAILURE:
            return {
                ...state,
                familyCashAccount: [],
            };
        case GET_LEDGER_MOVEMENTS_STARTED:
            return {
                ...state,
                ledger: {
                    loading: true,
                    error: null,
                    data: [],
                },
            };
        case GET_LEDGER_MOVEMENTS_SUCCESS:
            return {
                ...state,
                ledger: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data],
                },
            };
        case GET_LEDGER_MOVEMENTS_FAILURE:
            return {
                ...state,
                ledger: {
                    loading: false,
                    error: action.payload.data,
                    data: [],
                },
            };
        case GET_FAMILY_LEDGER_MOVEMENTS_STARTED:
            return {
                ...state,
                familyLedger: {
                    loading: true,
                    error: null,
                    data: [],
                },
            };
        case GET_FAMILY_LEDGER_MOVEMENTS_SUCCESS:
            return {
                ...state,
                familyLedger: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data],
                },
            };
        case GET_FAMILY_LEDGER_MOVEMENTS_FAILURE:
            return {
                ...state,
                familyLedger: {
                    loading: false,
                    error: action.payload.data,
                    data: [],
                },
            };
    }
}

export default financeReducer;