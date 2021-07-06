import {getFromWS} from "./AppService";

export const CATEGORY = "CATEGORY";

export const GET_STANDARD_CATEGORY_STARTED = "GET_STANDARD_CATEGORY_STARTED"
export const GET_STANDARD_CATEGORY_SUCCESS = "GET_STANDARD_CATEGORY_SUCCESS"
export const GET_STANDARD_CATEGORY_FAILURE = "GET_STANDARD_CATEGORY_FAILURE"


export function getStandardCategories(dispatch, token){
    const RESOURCE_URL = 'categories/standard/tree';
    dispatch(getStandardCategoriesStarted());
    const headers = {
        'Authorization': `Bearer ${token}`,
    }
    console.log("BEFORE FETCH")
    getFromWS(
        (res) => dispatch(getStandardCategoriesSuccess(res)),
        (err) => dispatch(getStandardCategoriesFailure(err.response)),
        RESOURCE_URL,
        headers
    );
}

export function getStandardCategoriesStarted() {
    return {
        area: CATEGORY,
        type: GET_STANDARD_CATEGORY_STARTED
    }
}

export function getStandardCategoriesSuccess(res) {
    return {
        area: CATEGORY,
        type: GET_STANDARD_CATEGORY_SUCCESS,
        payload: {
            data: [...res],
        },
    }
}

export function getStandardCategoriesFailure(err) {
    return {
        area: CATEGORY,
        type: GET_STANDARD_CATEGORY_FAILURE,
        payload: {
            error: err.message,
        },
    }
}