import React from "react";
import "../../css/Menu.css";
import profile from "../../images/menu-profile.png";
import AppContext from "../../context/AppContext";


export default function WelcomeUserButton(){
    const { state } = React.useContext(AppContext);
    return (
        <div className="box welcome" style={{ backgroundColor: "#2b5458" }}>
            <img className="icon" src={profile} alt="profile" />
            <div className="text">Welcome, {state.user.data.username}</div>
        </div>
    );
}