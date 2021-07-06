/*
import AppContext from "../context/AppContext";
import {getFamilyProfile} from "../context/FamilyAndAuthenticationActions";
import React, {useContext} from "react";
import "../css/FamilyProfile.css";

function ViewFamilyProfile(props) {
    const {state, dispatch} = useContext(AppContext); //allows to change the state
    const {familyProfile} = state;    //same name as in AppProvider

    console.log(props)
    console.log(state)


    const getFamilyProfileByID = () => {
        getFamilyProfile(dispatch, props.id);         //function described in actions.
    };

    const {headers1} = useContext(AppContext);

    const {
        registrationDate,
        administratorID,
    } = familyProfile;

    React.useEffect(() => {
        getFamilyProfile(dispatch, props.id);
    }, []);

    console.log(props)

    return (
        <div>

            <div className="profileFamily">

                <div>{JSON.stringify(getFamilyProfileByID)}</div>
                <div class="entity-list-row">
                    <div className="family-table-row">
                        <div className="registrationDate">
                            <span>{headers1.registrationDate}</span>
                        </div>
                        <div className="registrationDate2">
                            <span>{registrationDate}</span>
                        </div>
                    </div>
                    <div className="family-table-row2">
                        <div className="adminID">
                            <span>{headers1.administratorID}
                            </span>
                        </div>
                        <div className="adminID2">
                            <span>{administratorID}
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ViewFamilyProfile;*/
