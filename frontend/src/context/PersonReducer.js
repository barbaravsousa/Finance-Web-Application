import {
    ADD_PERSON_FAILURE,
    ADD_PERSON_STARTED,
    ADD_PERSON_SUCCESS,
    ADD_RELATION_FAILURE,
    ADD_RELATION_STARTED,
    ADD_RELATION_SUCCESS,
    ADD_EMAIL_FAILURE,
    ADD_EMAIL_STARTED,
    ADD_EMAIL_SUCCESS,
    DELETE_EMAIL_STARTED,
    DELETE_EMAIL_SUCCESS,
    DELETE_EMAIL_FAILURE,
    CLOSE_EMAIL_FORM,
    CLOSE_DELETE_EMAIL_FORM,
    CLOSE_PERSON_FORM,
    CLOSE_RELATION_FORM,
    GET_FAMILY_RELATIONS_LIST_BY_ID_FAILURE,
    GET_FAMILY_RELATIONS_LIST_BY_ID_STARTED,
    GET_FAMILY_RELATIONS_LIST_BY_ID_SUCCESS,
    GET_MEMBERS_FAILURE,
    GET_MEMBERS_STARTED,
    GET_MEMBERS_SUCCESS,
    GET_RELATION_TYPES_FAILURE,
    GET_RELATION_TYPES_STARTED,
    GET_RELATION_TYPES_SUCCESS,
    GET_USER_PROFILE_FAILURE,
    GET_USER_PROFILE_STARTED,
    GET_USER_PROFILE_SUCCESS,
    OPEN_EMAIL_FORM,
    OPEN_DELETE_EMAIL_FORM,
    OPEN_PERSON_FORM,
    OPEN_RELATION_FORM,
    SAVE_USER_RELATIONS_SUCCESS,
    SELECTED_EMAIL_SUCCESS
} from "./PersonActions";

function personReducer(state, action) {
    switch (action.type) {
        case GET_MEMBERS_STARTED:
            return {
                ...state,
                members: {
                    formOpen: false,
                    loading: true,
                    error: null,
                    data: [],
                },
            };
        case SAVE_USER_RELATIONS_SUCCESS:
            return {
                ...state,
                userRelation: action.payload.userRelation
            }

        case SELECTED_EMAIL_SUCCESS:
            return {
                ...state,
                selectedEmail: action.payload.selectedEmail
            }


        case GET_MEMBERS_SUCCESS:
            return {
                ...state,
                members: {
                    formOpen: false,
                    loading: false,
                    error: null,
                    data: [...action.payload.data],
                },
            };
        case GET_MEMBERS_FAILURE:
            return {
                ...state,
                members: {
                    formOpen: false,
                    loading: false,
                    error: action.payload.error,
                    data: [],
                },
            };
        case GET_FAMILY_RELATIONS_LIST_BY_ID_STARTED:
            return {
                ...state,
                relations: {
                    formOpen: false,
                    loading: true,
                    error: null,
                    data: [],
                },
            };
        case GET_FAMILY_RELATIONS_LIST_BY_ID_SUCCESS:
            return {
                ...state,
                relations: {
                    formOpen: false,
                    loading: false,
                    error: null,
                    data: [...action.payload.data],
                },
            };
        case GET_FAMILY_RELATIONS_LIST_BY_ID_FAILURE:
            return {
                ...state,
                relations: {
                    formOpen: false,
                    loading: true,
                    error: action.payload.data,
                    data: [],
                },
            };

        case GET_USER_PROFILE_STARTED:
            return {
                ...state,
                profile: "Started",
            };
        case GET_USER_PROFILE_SUCCESS:
            return {
                ...state,
                profile: action.payload.data,
            };
        case GET_USER_PROFILE_FAILURE:
            return {
                ...state,
                profile: "Failed",
            };
        case OPEN_PERSON_FORM:
            return {
                ...state,
                personForm: {
                    openForm: true,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case CLOSE_PERSON_FORM:
            return {
                ...state,
                personForm: {
                    openForm: false,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case OPEN_RELATION_FORM:
            return {
                ...state,
                relationForm: {
                    openForm: true,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case CLOSE_RELATION_FORM:
            return {
                ...state,
                relationForm: {
                    openForm: false,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case OPEN_EMAIL_FORM:
            return {
                ...state,
                emailForm: {
                    openForm: true,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case CLOSE_EMAIL_FORM:
            return {
                ...state,
                emailForm: {
                    openForm: false,
                    loading: false,
                    error: null,
                    status: null,
                }
            };


        case OPEN_DELETE_EMAIL_FORM:
            return {
                ...state,
                deleteEmailForm: {
                    openDForm: true,
                    loading: false,
                    error: null,
                    status: null,
                }
            };
        case CLOSE_DELETE_EMAIL_FORM:
            return {
                ...state,
                deleteEmailForm: {
                    openDForm: false,
                    loading: false,
                    error: null,
                    status: null,
                }
            };


        case ADD_PERSON_STARTED:
            return {
                ...state,
                personForm: {
                    openForm: true,
                    result: "Loading..."
                },
            };
        case ADD_PERSON_SUCCESS:
            return {
                ...state,

                members: {
                    refresh: true,
                    loading: false,
                    error: null,
                    data: [],
                },
                personForm: {
                    openForm: false,
                    result: "Success!"
                },

            };
        case ADD_PERSON_FAILURE:
            return {
                ...state,
                personForm: {
                    openForm: true,
                    result: action.payload.error
                },
            };

        case ADD_RELATION_STARTED:
            return {
                ...state,
                relationForm: {
                    openForm: true,
                    result: "Loading..."
                },
            };
        case ADD_RELATION_SUCCESS:
            return {
                ...state,
                relations: {
                    refresh: true,
                    loading: false,
                    error: null,
                    data: [],
                },
                relationForm: {
                    openForm: false,
                    result: "Success!"
                },
            };
        case ADD_RELATION_FAILURE:
            return {
                ...state,
                relationForm: {
                    openForm: true,
                    result: action.payload.error
                },
            };

        case ADD_EMAIL_STARTED:
            return {
                ...state,
                emailForm: {
                    openForm: true,
                    result: "Loading ..."
                },
            };

        case ADD_EMAIL_SUCCESS:
            return {
                ...state,
                emails: {
                    refresh:true,
                    loading: false,
                    error: null,
                    data: [],
                },
                emailForm: {
                    openForm: false,
                    result: "Success!"
                }
            };

        case ADD_EMAIL_FAILURE:
            return {
                ...state,
                emailForm: {
                    openForm: true,
                    result: action.payload.error
                },
            };


        case DELETE_EMAIL_STARTED:
            return {
                ...state,
                deleteEmailForm: {
                    openDForm: true,
                    result: "Loading ..."
                },
            };

        case DELETE_EMAIL_SUCCESS:
            return {
                ...state,
                emails: {
                    refresh:true,
                    loading: false,
                    error: null,
                    data: [],
                },
                deleteEmailForm: {
                    openDForm: false,
                    result: "Success!"
                }
            };

        case DELETE_EMAIL_FAILURE:
            return {
                ...state,
                deleteEmailForm: {
                    openDForm: true,
                    result: action.payload.error
                },
            };


        case GET_RELATION_TYPES_STARTED:
            return {
                ...state,
                systemRelationList: {
                    data: [],
                }
            }
        case GET_RELATION_TYPES_SUCCESS:
            return {
                ...state,
                systemRelationList: {
                    data: [...action.payload.data]
                }
            }
        case GET_RELATION_TYPES_FAILURE:
            return {
                ...state,
                systemRelationList: {
                    error: action.payload.data,
                    data: [],
                }
            }

    }
}

export default personReducer;