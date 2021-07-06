import "../../css/Menu.css";
import React, {useContext, useState} from "react";
import HomePageButton from "./HomePageButton";
import FamiliesButton from "./FamiliesButton";
import AccountsButton from "./AccountsButton";
import WelcomeUserButton from "./WelcomeUserButton";
import LogOutButton from "./LogOutButton";
import FamilyButton from "./FamilyButton";
import CategoryButton from "./CategoryButton";
import AppContext from "../../context/AppContext";
import ProfileButton from "./ProfileButton";

function Menu() {
    const {state} = useContext(AppContext);
    const {options} = state;
    const [flags, setFlags] = useState({
        familiesFlag: false,
        categoriesFlag: false,
        familyFlag: false,
        accountsFlag: false,
        profileFlag: false
    });

    console.log(state)
    console.log(options)

    const {_links} = options

    if (_links !== undefined) {
        const {families} = _links
        const {categories} = _links
        const {family} = _links
        const {account} = _links
        const {user_profile} = _links

        if (families !== undefined && flags.familiesFlag === false) {
            setFlags({
                ...flags,
                familiesFlag: true,
            })
        }

        if (categories !== undefined && flags.categoriesFlag === false) {
            setFlags({
                ...flags,
                categoriesFlag: true,
            })
        }

        if (family !== undefined && flags.familyFlag === false) {
            setFlags({
                ...flags,
                familyFlag: true,
            })
        }

        if (account !== undefined && flags.accountsFlag === false) {
            setFlags({
                ...flags,
                accountsFlag: true,
            })
        }

        if (user_profile !== undefined && flags.profileFlag === false) {
            setFlags({
                ...flags,
                profileFlag: true,
            })
        }

    }

    return (
        <div className="topnav">
            <div>
                <HomePageButton/>

                {flags.familiesFlag && <FamiliesButton/>}

                {flags.familyFlag && <FamilyButton/>}

                {flags.categoriesFlag && <CategoryButton/>}

                {flags.accountsFlag && <AccountsButton/>}

                {flags.profileFlag && <ProfileButton/>}

            </div>

            <div>
                <WelcomeUserButton/>

                <LogOutButton/>
            </div>


        </div>
    );
}

export default Menu;
