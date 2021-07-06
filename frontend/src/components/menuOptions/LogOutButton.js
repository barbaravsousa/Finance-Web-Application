import React from "react";
import "../../css/Menu.css";
import LogoutForm from "../LogoutForm";


export default function LogOutButton(){

    return (
        <div className="logout-box" style={{ backgroundColor: "#2b5458" }}>
            <LogoutForm />
        </div>
    );
}