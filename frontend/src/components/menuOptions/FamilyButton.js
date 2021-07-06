import React from "react";
import "../../css/Menu.css";
import family from "../../images/menu-family.png";
import {Link} from "react-router-dom";
import AppContext from "../../context/AppContext";

export default function FamilyButton(){
    const { state, dispatch } = React.useContext(AppContext);
    const { familyProfile, user} = state;
    const data = user
    let name = "";


 /*   if(familyProfile.administratorID === state.members.data[0].mainEmail){
        name = familyProfile
    }*/

    console.log(data)
    console.log(state)
    console.log(name)

    const urlInfo = "/families/" + data.data.familyID + "/members";

    console.log(urlInfo)

    return (

        <div>
            <Link className="menu-link" to={urlInfo}>
            <div className="box" style={{ backgroundColor: "#5e8b7e" }}>
                <img className="icon" src={family} alt="accounts" />
                <div className="text">Family</div>
            </div>
            </Link>
        </div>
    );
}