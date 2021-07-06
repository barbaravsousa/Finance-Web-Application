import {FAMILY} from "./FamilyActions";
import {PERSON} from "./PersonActions";
import {FINANCE} from "./FinanceActions";
import {ACCESS} from "./AuthorizationAndAuthenticationActions";
import {CATEGORY} from "./CategoryActions";
import familyReducer from "./FamilyReducer";
import personReducer from "./PersonReducer";
import financeReducer from "./FinanceReducer";
import authorizationAndAuthenticationReducer from "./AuthorizationAndAuthenticationReducer";
import categoryReducer from "./CategoryReducer";


function reducer(state, action) {
    switch (action.area) {
        case FAMILY:
            return familyReducer(state, action);

        case PERSON:
            return personReducer(state, action);

        case FINANCE:
            return financeReducer(state, action);

        case ACCESS:
            return authorizationAndAuthenticationReducer(state, action);

        case CATEGORY:
            return categoryReducer(state, action);
    }
}

export default reducer;
