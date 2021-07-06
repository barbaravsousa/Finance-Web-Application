import React from "react";
import {Link} from "react-router-dom";
import home from "../../images/menu-home.png";
import "../../css/Menu.css";

export default function HomePageButton(){
    return (
        <div>
            <Link className="menu-link" to="/">
                <div className="box" style={{ backgroundColor: "#2b5458" }}>
                    <img className="icon" src={home} alt="home2" />
                    <div className="text">Home</div>
                </div>
            </Link>
        </div>
    );
}