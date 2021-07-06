import {
    ADD_FAMILY_FAILURE,
    ADD_FAMILY_STARTED,
    ADD_FAMILY_SUCCESS,
    CLOSE_FAMILY_FORM,
    GET_FAMILIES_FAILURE,
    GET_FAMILIES_STARTED,
    GET_FAMILIES_SUCCESS,
    GET_FAMILY_PROFILE_FAILURE,
    GET_FAMILY_PROFILE_STARTED,
    GET_FAMILY_PROFILE_SUCCESS,
    OPEN_FAMILY_FORM,
    SAVE_FAMILY_NAME_SUCCESS,
} from "./FamilyActions";

function familyReducer(state, action) {
    switch (action.type) {
        case SAVE_FAMILY_NAME_SUCCESS:
            return {
                ...state,
                familyName: action.payload.familyName
            }
        case GET_FAMILIES_STARTED:
            return {
                ...state,
                families: {
                    formOpen: false,
                    loading: true,
                    error: null,
                    data: [],
                },
            };
        case GET_FAMILIES_SUCCESS:
            return {
                ...state,
                families: {
                    formOpen: false,
                    loading: false,
                    error: null,
                    data: [...action.payload.data],
                },
            };
        case GET_FAMILIES_FAILURE:
            return {
                ...state,
                families: {
                    formOpen: false,
                    loading: true,
                    error: action.payload.data,
                    data: [],
                },
            };
        case ADD_FAMILY_STARTED:
            return {
                ...state,
                familyForm: {
                    openForm: true,
                    result: "Loading..."
                },
            };
        case ADD_FAMILY_SUCCESS:
            return {
                ...state,
                families: {
                    refresh: true,
                    loading: false,
                    error: null,
                    data: [],
                },
                familyForm: {
                    openForm: false,
                    result: "Success!"
                },
            };
        case ADD_FAMILY_FAILURE:
            return {
                ...state,
                familyForm: {
                    openForm: true,
                    result: "Error: " + action.payload.error,
                },
            };

        case OPEN_FAMILY_FORM:
            return {
                ...state,
                familyForm: {
                    openForm: true,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case CLOSE_FAMILY_FORM:
            return {
                ...state,
                familyForm: {
                    openForm: false,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case GET_FAMILY_PROFILE_SUCCESS:
            const newProfile = state.familyProfile;
            newProfile.mapper[action.payload.data.administratorID]=action.payload.data
            return {
                ...state,
                familyProfile: newProfile,
                familyOptions: action.payload.data
            };

        case GET_FAMILY_PROFILE_FAILURE:
            return {
                ...state,
                familyProfile: "Failed",
            };
    }
}

export default familyReducer;