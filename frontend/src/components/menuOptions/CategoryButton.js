import React from "react";
import "../../css/Menu.css";
import family from "../../images/menu-categories.png";
import {Link} from "react-router-dom";

export default function CategoryButton() {

    const urlInfo = "/categories";

    return (
        <div>
            <Link className="menu-link" to={urlInfo}>
                <div className="box" style={{backgroundColor: "#a7c4bc"}}>
                    <img className="icon" src={family} alt="accounts"/>
                    <div className="text">Categories</div>
                </div>
            </Link>
        </div>
    );
}