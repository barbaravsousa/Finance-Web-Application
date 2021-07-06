import React from "react";
import Menu from "../menuOptions/Menu";
import pig from "../../images/pineapple-pig-half1.png"
import LedgerTable from "../LedgerTable";

export default function LedgerPage() {

    return (
        <div>
            <div>
                <Menu/>
            </div>
            <div>
                <LedgerTable/>
            </div>
            <img src={pig} alt="pineapple-pig" style={{
                position: "fixed",
                left: "0",
                bottom: "-130px",
                width: "250px",
                zIndex: "-1",
                opacity: "0.5"
            }}/>
        </div>
    );
}