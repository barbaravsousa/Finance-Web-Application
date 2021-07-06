import {getFromWS, postToWS} from "./AppService";

export const FINANCE = "FINANCE";

export const CREATE_FAMILY_CASH_ACCOUNT_STARTED = "CREATE_FAMILY_CASH_ACCOUNT_STARTED"
export const CREATE_FAMILY_CASH_ACCOUNT_SUCCESS = "CREATE_FAMILY_CASH_ACCOUNT_SUCCESS"
export const CREATE_FAMILY_CASH_ACCOUNT_FAILURE = "CREATE_FAMILY_CASH_ACCOUNT_FAILURE"

export const CREATE_PERSONAL_CASH_ACCOUNT_STARTED = "CREATE_PERSONAL_CASH_ACCOUNT_STARTED"
export const CREATE_PERSONAL_CASH_ACCOUNT_SUCCESS = "CREATE_PERSONAL_CASH_ACCOUNT_SUCCESS"
export const CREATE_PERSONAL_CASH_ACCOUNT_FAILURE = "CREATE_PERSONAL_CASH_ACCOUNT_FAILURE"

export const GET_ACCOUNTS_STARTED = "GET_ACCOUNTS_STARTED"
export const GET_ACCOUNTS_SUCCESS = "GET_ACCOUNTS_SUCCESS"
export const GET_ACCOUNTS_FAILURE = "GET_ACCOUNTS_FAILURE"
export const GET_ACCOUNTS_REFRESH = "GET_ACCOUNTS_REFRESH"

export const OPEN_FAMILY_CASH_ACCOUNT_FORM = "OPEN_FAMILY_CASH_ACCOUNT_FORM"
export const CLOSE_FAMILY_CASH_ACCOUNT_FORM = "CLOSE_FAMILY_CASH_ACCOUNT_FORM"

export const OPEN_PERSONAL_CASH_ACCOUNT_FORM = "OPEN_PERSONAL_CASH_ACCOUNT_FORM"
export const CLOSE_PERSONAL_CASH_ACCOUNT_FORM = "CLOSE_PERSONAL_CASH_ACCOUNT_FORM"

export const GET_LEDGER_MOVEMENTS_STARTED = "GET_LEDGER_MOVEMENTS_STARTED";
export const GET_LEDGER_MOVEMENTS_SUCCESS = "GET_LEDGER_MOVEMENTS_SUCCESS";
export const GET_LEDGER_MOVEMENTS_FAILURE = "GET_LEDGER_MOVEMENTS_FAILURE";

export const GET_FAMILY_LEDGER_MOVEMENTS_STARTED = "GET_FAMILY_LEDGER_MOVEMENTS_STARTED";
export const GET_FAMILY_LEDGER_MOVEMENTS_SUCCESS = "GET_FAMILY_LEDGER_MOVEMENTS_SUCCESS";
export const GET_FAMILY_LEDGER_MOVEMENTS_FAILURE = "GET_FAMILY_LEDGER_MOVEMENTS_FAILURE";

export const GET_FAMILY_CASH_ACCOUNT_BALANCE_STARTED = "GET_FAMILY_CASH_ACCOUNT_BALANCE_STARTED"
export const GET_FAMILY_CASH_ACCOUNT_BALANCE_SUCCESS = "GET_FAMILY_CASH_ACCOUNT_BALANCE_SUCCESS"
export const GET_FAMILY_CASH_ACCOUNT_BALANCE_FAILURE = "GET_FAMILY_CASH_ACCOUNT_BALANCE_FAILURE"

export const OPEN_PAYMENT_FORM = "OPEN_PAYMENT_FORM"
export const CLOSE_PAYMENT_FORM = "CLOSE_PAYMENT_FORM"

export const ADD_PAYMENT_STARTED = "ADD_PAYMENT_STARTED"
export const ADD_PAYMENT_SUCCESS = "ADD_PAYMENT_SUCCESS"
export const ADD_PAYMENT_FAILURE = "ADD_PAYMENT_FAILURE"

// --------------------------------------

export function createFamilyCashAccount(form, dispatch, familyID, token) {
    const RESOURCE_URL = `families/${familyID}/familyCashAccount`;
    dispatch(createFamilyCashAccountStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    postToWS(createFamilyCashAccountSuccess, createFamilyCashAccountFailure, form, dispatch, RESOURCE_URL, headers);
}

export function createFamilyCashAccountStarted() {
    return {
        area: FINANCE,
        type: CREATE_FAMILY_CASH_ACCOUNT_STARTED,
    }
}

export function createFamilyCashAccountSuccess(res) {
    console.log(res)
    return {
        area: FINANCE,
        type: CREATE_FAMILY_CASH_ACCOUNT_SUCCESS,
        payload: {
            data: res,
        }
    }
}

export function createFamilyCashAccountFailure(message) {
    return {
        area: FINANCE,
        type: CREATE_FAMILY_CASH_ACCOUNT_FAILURE,
        payload: {
            error: message
        }
    }
}

// --------------------------------------

export function openFamilyCashAccountForm() {
    return {
        area: FINANCE,
        type: OPEN_FAMILY_CASH_ACCOUNT_FORM,
    }
}

export function closeFamilyCashAccountForm() {
    return {
        area: FINANCE,
        type: CLOSE_FAMILY_CASH_ACCOUNT_FORM,
    }
}

// --------------------------------------

export function createPersonalCashAccount(form, dispatch, personId, token) {
    const RESOURCE_URL = `users/${personId}/personalCashAccount`;
    dispatch(createPersonalCashAccountStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    postToWS(createPersonalCashAccountSuccess, createPersonalCashAccountFailure, form, dispatch, RESOURCE_URL, headers);
}

export function createPersonalCashAccountStarted() {
    return {
        area: FINANCE,
        type: CREATE_PERSONAL_CASH_ACCOUNT_STARTED,
    }
}

export function createPersonalCashAccountSuccess(res) {
    console.log(res)
    return {
        area: FINANCE,
        type: CREATE_PERSONAL_CASH_ACCOUNT_SUCCESS,
        payload: {
            data: res,
        }
    }
}

export function createPersonalCashAccountFailure(message) {
    return {
        area: FINANCE,
        type: CREATE_PERSONAL_CASH_ACCOUNT_FAILURE,
        payload: {
            error: message
        }
    }
}

// --------------------------------------

export function openPersonalCashAccountForm() {
    return {
        area: FINANCE,
        type: OPEN_PERSONAL_CASH_ACCOUNT_FORM,
    }
}

export function closePersonalCashAccountForm() {
    return {
        area: FINANCE,
        type: CLOSE_PERSONAL_CASH_ACCOUNT_FORM,
    }
}

// --------------------------------------

export function openPaymentForm() {
    return {
        area: FINANCE,
        type: OPEN_PAYMENT_FORM,
    }
}

export function closePaymentForm() {
    return {
        area: FINANCE,
        type: CLOSE_PAYMENT_FORM,
    }
}

// --------------------------------------

export function getListOfAccounts(dispatch, personId, token) {
    const RESOURCE_URL = `users/${personId}/accounts`
    dispatch(getListOfAccountsStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    getFromWS(
        (res) => dispatch(getListOfAccountsSuccess(res)),
        (err) => dispatch(getListOfAccountsFailure(err.message)),
        RESOURCE_URL, headers
    );
}

export function getListOfAccountsStarted() {
    return {
        area: FINANCE,
        type: GET_ACCOUNTS_STARTED,
    };
}

export function getListOfAccountsSuccess(res) {
    return {
        area: FINANCE,
        type: GET_ACCOUNTS_SUCCESS,
        payload: {
            data: [...res],
        },
    };
}

export function getListOfAccountsFailure(message) {
    return {
        area: FINANCE,
        type: GET_ACCOUNTS_FAILURE,
        payload: {
            error: message,
        },
    };
}

export function getListOfAccountsRefresh() {
    return {
        area: FINANCE,
        type: GET_ACCOUNTS_REFRESH,
    };
}


// ------------------------------------------------ //

export function getLedgerMovements(dispatch, personId, token) {
    const RESOURCE_URL = `users/${personId}/ledger`
    dispatch(getLedgerMovementsStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    getFromWS(
        (res) => dispatch(getLedgerMovementsSuccess(res)),
        (err) => dispatch(getLedgerMovementsFailure(err.message)),
        RESOURCE_URL, headers
    );
}


export function getLedgerMovementsStarted() {
    return {
        area: FINANCE,
        type: GET_LEDGER_MOVEMENTS_STARTED,
    };
}

export function getLedgerMovementsSuccess(res) {
    return {
        area: FINANCE,
        type: GET_LEDGER_MOVEMENTS_SUCCESS,
        payload: {
            data: [...res],
        },
    };
}

export function getLedgerMovementsFailure(message) {
    return {
        area: FINANCE,
        type: GET_LEDGER_MOVEMENTS_FAILURE,
        payload: {
            error: message,
        },
    };
}

export function getFamilyLedgerMovements(dispatch, familyID, token) {
    const RESOURCE_URL = `family/${familyID}/ledger`
    dispatch(getFamilyLedgerMovementsStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    getFromWS(
        (res) => dispatch(getFamilyLedgerMovementsSuccess(res)),
        (err) => dispatch(getFamilyLedgerMovementsFailure(err.message)),
        RESOURCE_URL, headers
    );
}


export function getFamilyLedgerMovementsStarted() {
    return {
        area: FINANCE,
        type: GET_FAMILY_LEDGER_MOVEMENTS_STARTED,
    };
}

export function getFamilyLedgerMovementsSuccess(res) {
    return {
        area: FINANCE,
        type: GET_FAMILY_LEDGER_MOVEMENTS_SUCCESS,
        payload: {
            data: [...res],
        },
    };
}

export function getFamilyLedgerMovementsFailure(message) {
    return {
        area: FINANCE,
        type: GET_FAMILY_LEDGER_MOVEMENTS_FAILURE,
        payload: {
            error: message,
        },
    };
}

// ------------------------------------------------ //

export function getFamilyCashAccountBalance(dispatch, token) {
    const RESOURCE_URL = `family/account/`
    dispatch(getFamilyCashAccountBalanceStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    getFromWS(
        (res) => dispatch(getFamilyCashAccountBalanceSuccess(res)),
        (err) => dispatch(getFamilyCashAccountBalanceFailure(err.message)),
        RESOURCE_URL, headers
    );
}

export function getFamilyCashAccountBalanceStarted() {
    return {
        area: FINANCE,
        type: GET_FAMILY_CASH_ACCOUNT_BALANCE_STARTED,
    };
}

export function getFamilyCashAccountBalanceSuccess(res) {
    return {
        area: FINANCE,
        type: GET_FAMILY_CASH_ACCOUNT_BALANCE_SUCCESS,
        payload: {
            data: res, //Aqui será um "res" universal para retornar toda a informação que vem do backEnd .
            /*{name: res.name} se quisesse obter informação específica do backend*/
        },
    }
}

export function getFamilyCashAccountBalanceFailure(message) {
    return {
        area: FINANCE,
        type: GET_FAMILY_CASH_ACCOUNT_BALANCE_FAILURE,
        payload: {
            error: message,
        },
    }
}

// --------------------------------------

export function addPayment(form, dispatch, accountID, token) {
    const RESOURCE_URL = `accounts/${accountID}/payment`;
    dispatch(addPaymentStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    postToWS(addPaymentSuccess, addPaymentFailure, form, dispatch, RESOURCE_URL, headers);
}

export function addPaymentStarted() {
    return {
        area: FINANCE,
        type: ADD_PAYMENT_STARTED,
    }
}

export function addPaymentSuccess(res) {
    console.log(res)
    return {
        area: FINANCE,
        type: ADD_PAYMENT_SUCCESS,
        payload: {
            data: res,
        }
    }
}

export function addPaymentFailure(message) {
    return {
        area: FINANCE,
        type: ADD_PAYMENT_FAILURE,
        payload: {
            error: message
        }
    }
}