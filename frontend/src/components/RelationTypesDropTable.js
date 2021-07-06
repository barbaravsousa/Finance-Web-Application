import React, {useContext} from "react";
import AppContext from "../context/AppContext";
import {getRelationTypes} from "../context/PersonActions";
import "../css/RelationModal.css"

function RelationTypesDropTable(props) {

    const {state, dispatch} = useContext(AppContext);
    let {systemRelationList, user} = state;
    let {data} = systemRelationList;

    const token = user.data.jwt;

    React.useEffect(() => {
        getRelationTypes(dispatch, token);
    }, []);

    const handleChange = (event) => {
        props.changeRelationType(event.target.value);
    }

    const possibleRelations = data.map((user) => {
            const info = user.split(',');
            return (
                <option > {info} </option>

            )
        }
    );

    return (
        <div>
            <div>
                <div className="dropdown">
                    <select id="relation" name="relation" className="select-selected" onClick={handleChange}>
                        <option className="option">Select a relation</option>
                        {possibleRelations}
                    </select>
                </div>
            </div>
        </div>
    )
}

export default RelationTypesDropTable;

