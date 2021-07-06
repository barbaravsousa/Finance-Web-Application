import React from 'react';
import "./css/App.css";
import "./context/FamilyActions";
import "./context/AppContext";
import ViewProfilePage from "./components/ViewProfilePage";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import ViewRelationsFromPerson from "./components/ViewRelationsFromPerson";
import FamilyForm from "./components/FamilyForm";
import PersonForm from "./components/PersonForm";
import FamiliesTable from "./components/FamiliesTable";
import HomePage from "./components/pages/HomePage";
import LoginForm from "./components/LoginForm";
import AppContext from "./context/AppContext";
import NoRoute from "./components/NoRoute";
import FamilyProfile from "./components/FamilyProfile";
import FamilyCashAccountForm from "./components/FamilyCashAccountForm";
import PersonalCashAccountForm from "./components/PersonalCashAccountForm";
import PersonalCashAccountTable from "./components/PersonalCashAccountTable";
import MembersPage from "./components/pages/MembersPage";
import CategoryPage from "./components/CategoriesPage";
import LedgerPage from "./components/pages/LedgerPage";
import FamilyAccountPage from "./components/pages/FamilyAccountPage";
import PaymentModal from "./components/PaymentModal";
import Footer from "./components/Footer";
import * as PropTypes from "prop-types";

class Fragment extends React.Component {
    render() {
        return null;
    }
}

Fragment.propTypes = {children: PropTypes.node};

function App() {
    const {state} = React.useContext(AppContext);
    const {isLogged} = state;
    if (isLogged === true)
        return (
            <Router>
                <Switch>
                    <Route exact path="/viewProfile/:email" component={ViewProfilePage}/>
                    <Route exact path="/viewRelations/:id/:email" component={ViewRelationsFromPerson}/>
                    <Route exact path="/addFamily" component={FamilyForm}/>
                    <Route exact path="/addPerson" component={PersonForm}/>
                    <Route exact path="/families" component={FamiliesTable}/>
                    <Route exact path="/createFamilyCashAccount" component={FamilyCashAccountForm}/>
                    <Route exact path="/createPersonalCashAccount" component={PersonalCashAccountForm}/>
                    <Route exact path="/accounts" component={PersonalCashAccountTable}/>
                    <Route exact path="/accounts/familyCashAccount" component={FamilyAccountPage}/>
                    <Route exact path="/" component={HomePage}/>
                    <Route exact path="/personForm" component={PersonForm}/>
                    <Route exact path="/families/:email/members" component={MembersPage}/>
                    <Route exact path="/families/:id/profile" component={FamilyProfile}/>
                    <Route exact path="/categories" component={CategoryPage}/>
                    <Route exact path="/users/:id/ledger" component={LedgerPage}/>
                    <NoRoute component={LoginForm}/>
                </Switch>
                <Footer/>
            </Router>

        );
    else {
        return (
            <Router>
                <Route component={LoginForm}/>
                {/* <Route exat path ="/auth/signup" component={SignUpForm}/>*/}
            </Router>
        );
    }
}

export default App;
