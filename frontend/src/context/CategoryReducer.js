import {
    GET_STANDARD_CATEGORY_FAILURE,
    GET_STANDARD_CATEGORY_STARTED,
    GET_STANDARD_CATEGORY_SUCCESS
} from "./CategoryActions";

function categoryReducer(state, action) {
    switch (action.type) {
        case GET_STANDARD_CATEGORY_STARTED:
            return {
                ...state,
                categoryTree: {
                    loading: true,
                    error: null,
                    data: {
                        designation: "Root",
                        children: []
                    },
                },
            };
        case GET_STANDARD_CATEGORY_SUCCESS:
            return {
                ...state,
                categoryTree: {
                    loading: false,
                    error: null,
                    data: {
                        designation: "Standard Tree",
                        children: [...action.payload.data]
                    }
                },
                childrenCategory: action.payload.data
            }
        case GET_STANDARD_CATEGORY_FAILURE:
            return {
                ...state,
                categoryTree: {
                    loading: true,
                    error: action.payload.data,
                    data: {
                        designation: "Root",
                        children: []
                    }
                },
            };
    }
}

export default categoryReducer;