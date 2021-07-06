import React from "react";
import {Link, Route} from "react-router-dom";
import home from "../../images/profile.png";
import "../../css/Menu.css";
import AppContext from "../../context/AppContext";


export default function ProfileButton(){
    const { state, dispatch } = React.useContext(AppContext);
    const {user} = state;
    const data = user;

    const urlInfo = "/viewProfile/" + data.data.email;

    return (
        <div>
            <Link className="menu-link" to={urlInfo}>
                <div className="box" style={{ backgroundColor: "#DFEEEA" }}>
                    <img className="icon" src={home} alt="home2" />
                    <div className="text">Profile</div>
                </div>
            </Link>
        </div>
    );
}