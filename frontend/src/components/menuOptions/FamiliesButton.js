import React from "react";
import {Link} from "react-router-dom";
import "../../css/Menu.css";
import family from "../../images/menu-family.png";

export default function FamiliesButton(){
    return (
        <div>
            <Link className="menu-link" to="/families">
                <div className="box" style={{ backgroundColor: "#5e8b7e" }}>
                    <img className="icon" src={family} alt="family" />
                    <div className="text">Families</div>
                </div>
            </Link>
        </div>
    );
}