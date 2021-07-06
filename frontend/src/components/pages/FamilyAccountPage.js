import React, {useContext} from "react";
import Menu from "../menuOptions/Menu";
import pig from "../../images/pineapple-pig-half1.png"
import FamilyLedgerTable from "../FamilyLedgerTable";
import FamiliesCashAccountModal from "../FamiliesCashAccountModal";
import AppContext from "../../context/AppContext";
import {getFamilyCashAccountBalance} from "../../context/FinanceActions";
import {getFamilyProfile} from "../../context/FamilyActions";

export default function FamilyAccountPage() {
    const {state, dispatch} = useContext(AppContext);
    const {familyOptions, user, familyLedger, familyCashAccount} = state;
    const {accountDesignation} = familyCashAccount;
    const token = user.data.jwt;
    const familyID = user.data.familyID;
    const {refresh} = familyLedger;

    const [ledger, setLedger] = React.useState(false);
    const [modal, setModal] = React.useState(true);

    const showLedger = () => {
        setLedger(true);
        setModal(false);
    };

    React.useEffect(() => {
        getFamilyProfile(dispatch, familyID, token);
        getFamilyCashAccountBalance(dispatch, token);
    }, [refresh]);

    if (accountDesignation !== undefined && ledger === false) {
        showLedger();
    }

    return (
        <div>
            <div>
                <Menu/>
            </div>
            <div className="ledger-title-family">
                <h2 className="h2">{familyOptions.familyName}'s Cash Account </h2>
            </div>
            <div>
                {modal && <FamiliesCashAccountModal/>}
            </div>
            <div>
                {ledger && <FamilyLedgerTable/>}
            </div>
            <img src={pig} alt="pineapple-pig" style={{
                position: "fixed",
                left: "0",
                bottom: "-130px",
                width: "250px",
                zIndex: "-1",
                opacity: "0.5"
            }}/>
        </div>
    );
}