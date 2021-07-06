import React, {useContext} from 'react';
import {getListOfFamilyMembers} from "../../context/FamilyActions";
import AppContext from "../../context/AppContext";

export default function PersonIDField(props) {
    const {state, dispatch} = useContext(AppContext);
    const {members} = state;
    const {data} = members;

    const items = data.map((user, index) => {

            return (
                <tr className="tbody" key={index}>
                    <td>
                        {user.name}
                    </td>
                </tr>
            )
        }
    );

    React.useEffect(() => {
        getListOfFamilyMembers(dispatch, "93a8cf5e-bbdc-4736-84aa-44991ec4fd66");
    }, []);

    return (
        <div>
            <input type="date" id={props.id}
                   required={props.required} onChange={(e) => props.changeHandler(e)}/>
            {items}
        </div>
    );
}