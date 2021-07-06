import {getFromWS, postToWS, deleteToWS, postToWS2} from "./AppService";

export const PERSON = "PERSON";

export const GET_MEMBERS_STARTED = "GET_MEMBERS_STARTED";
export const GET_MEMBERS_SUCCESS = "GET_MEMBERS_SUCCESS";
export const GET_MEMBERS_FAILURE = "GET_MEMBERS_FAILURE";

export const GET_FAMILY_RELATIONS_LIST_BY_ID_STARTED = "GET_FAMILY_RELATIONS_LIST_BY_ID_STARTED";
export const GET_FAMILY_RELATIONS_LIST_BY_ID_SUCCESS = "GET_FAMILY_RELATIONS_LIST_BY_ID_SUCCESS";
export const GET_FAMILY_RELATIONS_LIST_BY_ID_FAILURE = "GET_FAMILY_RELATIONS_LIST_BY_ID_FAILURE";

export const GET_USER_PROFILE_STARTED = "GET_USER_PROFILE_STARTED";
export const GET_USER_PROFILE_SUCCESS = "GET_USER_PROFILE_SUCCESS";
export const GET_USER_PROFILE_FAILURE = "GET_USER_PROFILE_FAILURE";

export const ADD_PERSON_STARTED = "ADD_PERSON_STARTED_TYPE"
export const ADD_PERSON_SUCCESS = "ADD_PERSON_SUCCESS_TYPE"
export const ADD_PERSON_FAILURE = "ADD_PERSON_FAILURE_TYPE"

export const ADD_RELATION_STARTED = "ADD_RELATION_STARTED_TYPE"
export const ADD_RELATION_SUCCESS = "ADD_RELATION_SUCCESS_TYPE"
export const ADD_RELATION_FAILURE = "ADD_RELATION_FAILURE_TYPE"

export const OPEN_PERSON_FORM = 'OPEN_PERSON_FORM';
export const CLOSE_PERSON_FORM = 'CLOSE_PERSON_FORM';

export const OPEN_RELATION_FORM = 'OPEN_RELATION_FORM';
export const CLOSE_RELATION_FORM = 'CLOSE_RELATION_FORM';

export const OPEN_EMAIL_FORM = 'OPEN_EMAIL_FORM';
export const CLOSE_EMAIL_FORM = 'CLOSE_EMAIL_FORM';

export const OPEN_DELETE_EMAIL_FORM = 'OPEN_DELETE_EMAIL_FORM';
export const CLOSE_DELETE_EMAIL_FORM = 'CLOSE_DELETE_EMAIL_FORM';

export const SAVE_USER_RELATIONS_SUCCESS = 'SAVE_USER_RELATIONS_SUCCESS_TYPE';

export const GET_RELATION_TYPES_STARTED = "RELATION_TYPES_STARTED_TYPE";
export const GET_RELATION_TYPES_SUCCESS = "RELATION_TYPES_SUCCESS_TYPE";
export const GET_RELATION_TYPES_FAILURE = "RELATION_TYPES_FAILURE_TYPE";


export const ADD_EMAIL_STARTED = "ADD_EMAIL_STARTED_TYPE"
export const ADD_EMAIL_SUCCESS = "ADD_EMAIL_SUCCESS_TYPE"
export const ADD_EMAIL_FAILURE = "ADD_EMAIL_FAILURE_TYPE"

export const DELETE_EMAIL_STARTED = "DELETE_EMAIL_STARTED_TYPE"
export const DELETE_EMAIL_SUCCESS = "DELETE_EMAIL_SUCCESS_TYPE"
export const DELETE_EMAIL_FAILURE = "DELETE_EMAIL_FAILURE_TYPE"

export const SELECTED_EMAIL_SUCCESS = "SELECTED_EMAIL"

export function getListOfFamilyMembers(dispatch, familyID, token) {
    const RESOURCE_URL = `families/${familyID}/members`;
    dispatch(getListOfFamilyMembersStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    getFromWS(
        (res) => dispatch(getListOfFamilyMembersSuccess(res)),
        (err) => dispatch(getListOfFamilyMembersFailure(err.message)),
        RESOURCE_URL, headers
    );
}

export function getListOfFamilyMembersStarted() {
    return {
        area: PERSON,
        type: GET_MEMBERS_STARTED,
    };
}

export function getListOfFamilyMembersSuccess(res) {
    return {
        area: PERSON,
        type: GET_MEMBERS_SUCCESS,
        payload: {
            data: [...res],
        },
    };
}

export function getListOfFamilyMembersFailure(message) {
    return {
        area: PERSON,
        type: GET_MEMBERS_FAILURE,
        payload: {
            error: message,
        },
    };
}

// --------------------------------------

export function getFamilyRelations(dispatch, mainEmail) {
    const RESOURCE_URL = `families/relations/${mainEmail}`;
    dispatch(getFamilyRelationsListByIdStarted());
    getFromWS(
        (res) => dispatch(getFamilyRelationsListByIdSuccess(res)),
        (error) => dispatch(getFamilyRelationsListByIdFailure(error.message)),
        RESOURCE_URL,
    );
}

export function getFamilyRelationsListByIdStarted() {
    return {
        area: PERSON,
        type: GET_FAMILY_RELATIONS_LIST_BY_ID_STARTED,
    };
}

export function getFamilyRelationsListByIdSuccess(res) {
    return {
        area: PERSON,
        type: GET_FAMILY_RELATIONS_LIST_BY_ID_SUCCESS,
        payload: {
            data: [...res.relationList], //Aqui em vez de "relation" deve ser "relationList", ou seja, o que o backend retorna, e não a const criada em Apps.js .
        },
    };
}

export function getFamilyRelationsListByIdFailure(message) {
    return {
        area: PERSON,
        type: GET_FAMILY_RELATIONS_LIST_BY_ID_FAILURE,
        payload: {
            error: message,
        },
    };
}

// --------------------------------------

export function getUserProfile(dispatch, mainEmail,token) {
    const RESOURCE_URL = `users/${mainEmail}`;
    dispatch(getUserProfileStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    getFromWS(
        (res) => dispatch(getUserProfileSuccess(res)),
        (error) => dispatch(getUserProfileFailure(error.message)),
        RESOURCE_URL,headers
    );
}


//faltava a linha 10 e actyivar uma extensão nova do CORS "https://mybrowseraddon.com/access-control-allow-origin.html?v=0.1.6&type=install"

export function getUserProfileStarted() {
    return {
        area: PERSON,
        type: GET_USER_PROFILE_STARTED,
    };
}

export function getUserProfileSuccess(res) {
    return {
        area: PERSON,
        type: GET_USER_PROFILE_SUCCESS,
        payload: {
            data: res, //Aqui será um "res" universal para retornar toda a informação que vem do backEnd .
            /*{name: res.name} se quisesse obter informação específica do backend*/
        },
    };
}

export function getUserProfileFailure(message) {
    return {
        area: PERSON,
        type: GET_USER_PROFILE_FAILURE,
        payload: {
            error: message,
        },
    };
}

// --------------------------------------

export function addPerson(form, dispatch, token, url) {
    dispatch(addPersonStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    postToWS2(addPersonSuccess, addPersonFailure, form, dispatch, url, headers);
}


export function addPersonStarted() {
    return {
        area: PERSON,
        type: ADD_PERSON_STARTED,
    }
}

export function addPersonSuccess() {
    return {
        area: PERSON,
        type: ADD_PERSON_SUCCESS,
    }
}

export function addPersonFailure(message) {
    return {
        area: PERSON,
        type: ADD_PERSON_FAILURE,
        payload: {
            error: message
        }
    }
}

// --------------------------------------

export function addRelation(form, dispatch, familyID, token) {
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    const RESOURCE_URL = `families/${familyID}/relations`;
    dispatch(addRelationStarted());
    postToWS(addRelationSuccess, addRelationFailure, form, dispatch, RESOURCE_URL, headers);
}

export function addRelationStarted() {
    return {
        area: PERSON,
        type: ADD_RELATION_STARTED,
    }
}

export function addRelationSuccess() {
    return {
        area: PERSON,
        type: ADD_RELATION_SUCCESS,
    }
}

export function addRelationFailure(msg) {
    return {
        area: PERSON,
        type: ADD_RELATION_FAILURE,
        payload: {
            error: msg
        }
    }
}

// --------------------------------------

export function addEmailToProfile(form, dispatch, personId,token) {
    const RESOURCE_URL = `users/${personId}/email/`;
    dispatch(addEmailStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    postToWS(addEmailSuccess, addEmailFailure, form, dispatch, RESOURCE_URL,headers);
}

export function addEmailStarted() {
    return {
        area: PERSON,
        type: ADD_EMAIL_STARTED,
    }
}

export function addEmailSuccess() {
    return {
        area: PERSON,
        type: ADD_EMAIL_SUCCESS,
    }
}

export function addEmailFailure(msg) {
    return {
        area: PERSON,
        type: ADD_EMAIL_FAILURE,
        payload: {
            error: msg
        }
    }
}



// ---------------------------------------


export function deleteEmailFromProfile(form, dispatch, personId,token) {
    const RESOURCE_URL = `users/${personId}/email`;
    dispatch(deleteEmailStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    deleteToWS(deleteEmailSuccess, deleteEmailFailure, form, dispatch, RESOURCE_URL,headers);
}


export function deleteEmailStarted() {
    return {
        area: PERSON,
        type: DELETE_EMAIL_STARTED,
    }
}

export function deleteEmailSuccess() {
    return {
        area: PERSON,
        type: DELETE_EMAIL_SUCCESS,
    }
}

export function deleteEmailFailure(msg) {
    return {
        area: PERSON,
        type: DELETE_EMAIL_FAILURE,
        payload: {
            error: msg
        }
    }
}





// -------------------------------------
export function openPersonForm() {
    return {
        area: PERSON,
        type: OPEN_PERSON_FORM,
    }
}

export function closePersonForm() {
    return {
        area: PERSON,
        type: CLOSE_PERSON_FORM,
    }
}

export function openRelationForm() {
    return {
        area: PERSON,
        type: OPEN_RELATION_FORM,
    }
}

export function closeRelationForm() {
    return {
        area: PERSON,
        type: CLOSE_RELATION_FORM,
    }
}

export function openEmailForm() {
    return {
        area: PERSON,
        type: OPEN_EMAIL_FORM,
    }
}

export function closeEmailForm() {
    return {
        area: PERSON,
        type: CLOSE_EMAIL_FORM,
    }
}



export function openDeleteEmailForm() {
    return {
        area: PERSON,
        type: OPEN_DELETE_EMAIL_FORM,
    }
}

export function closeDeleteEmailForm() {
    return {
        area: PERSON,
        type: CLOSE_DELETE_EMAIL_FORM,
    }
}


export function selectedEmail(email,dispatch)
{
    dispatch(selectEmailSuccess(email));
}

export function selectEmailSuccess(email) {
    return {
        area: PERSON,
        type: SELECTED_EMAIL_SUCCESS,
        payload: {
            selectedEmail: email,
        }
    }
}





// --------------------------------------

export function saveUserRelations(userRelation, dispatch) {
    console.log(userRelation)
    dispatch(saveUserRelationsSuccess(userRelation));
}

export function saveUserRelationsSuccess(userRelation) {
    return {
        area: PERSON,
        type: SAVE_USER_RELATIONS_SUCCESS,
        payload: {
            userRelation: userRelation,
        }
    }
}

// --------------------------------------

export function getRelationTypes(dispatch, token) {
    const RESOURCE_URL = "families/relations/";
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    dispatch(getRelationTypesStarted());
    getFromWS(
        (res) => dispatch(getRelationTypesSuccess(res)),
        (error) => dispatch(getRelationTypesFailure(error.message)),
        RESOURCE_URL, headers
    );
}

export function getRelationTypesStarted() {
    return {
        area: PERSON,
        type: GET_RELATION_TYPES_STARTED,
    }
}

export function getRelationTypesSuccess(res) {
    return {
        area: PERSON,
        type: GET_RELATION_TYPES_SUCCESS,
        payload: {
            data: [...res.systemRelationsList],
        }
    }
}

export function getRelationTypesFailure(message) {
    return {
        area: PERSON,
        type: GET_RELATION_TYPES_FAILURE,
        payload: {
            error: message,
        }
    }
}