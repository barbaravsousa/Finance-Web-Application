import Menu from "../menuOptions/Menu";
import AppContext from "../../context/AppContext";
import React from "react";
import pineapple from "../../images/pineapple-pig-half2.png";
import "../../css/HomePage.css";
import check from "../../images/menu-check.png";
import {accessMenuOptions} from "../../context/AuthorizationAndAuthenticationActions";
import Footer from "../Footer";

function HomePage() {
    const {state, dispatch} = React.useContext(AppContext);
    const {options, user} = state;
    const data = options.allow

    React.useEffect(() => {
        accessMenuOptions(dispatch, user.data.jwt);
    }, []);


    return (
        <div>
            <Menu/>
            <div className="text2">
                <p className="title">Family Piggy Bank</p>
                <div className="welcome-content">
                    <p></p>
                    <p>About</p>
                    <p>It's our mission to support families manage their finances!</p>
                    <div></div>
                    <img
                        src={pineapple}
                        style={{
                            position: "fixed",
                            right: "0",
                            bottom: "-135px",
                            width: "300px",
                            opacity: "0.5",
                        }}
                    ></img>
                    <div>
                        The Family Piggy Bank is a financial application and, as such, we
                        hope to help you manage your family finances. Using Family Piggy
                        Bank, it is possible to add members to your family and specify how
                        they relate to each other.{" "}
                        <p>
                            As a member of the family, you can insert all of your accounts'
                            information, be it a credit account or a cash account.
                        </p>
                        <p>
                            It is also possible to check the balance of every account and even
                            attribute a category to each of your transactions.
                        </p>
                    </div>
                </div>
                <br/>
                <br/>
                <br/>
                <div className="box-check">
                    <img src={check}/>
                    Save with your family
                </div>
                <div className="box-check">
                    <img src={check}/>
                    Organize your expenses in categories
                </div>
                <div className="box-check">
                    <img src={check}/>
                    Predict your future transactions
                </div>
                <div className="box-check">
                    <img src={check}/>
                    Track the balance of all of your accounts
                </div>
            </div>
        </div>
    );
}

export default HomePage;
