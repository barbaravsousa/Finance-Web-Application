import React from "react";
import Menu from "../menuOptions/Menu";
import pig from "../../images/pineapple-pig-half1.png"
import FamilyMembersTable from "../FamilyMembersTable";

function MembersPage() {

    return (
        <div>
            <div>
                <Menu/>
            </div>
            <div>
                <FamilyMembersTable/>
            </div>
            <img src={pig} style={{
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

export default MembersPage;