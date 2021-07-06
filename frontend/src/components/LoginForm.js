import '../css/LoginForm.css';
import AppContext from "../context/AppContext";
import React, {useState} from "react";
import {cleanLoginAndSignupStatus, signIn} from "../context/AuthorizationAndAuthenticationActions";
import {Link} from "react-router-dom";
import pig from "../images/pineapple-pig.png";

function LoginForm() {

    const {state, dispatch} = React.useContext(AppContext);
    const [credentials, setCredentials] = useState({
        username: "",
        password: "",
    });

    const {login, user} = state;
    const {status} = login;

    console.log(state);


    const handleClick = () => {
        signIn(credentials, dispatch);
    }
    console.log(credentials);

    const handleChangeUsername = (event) => {
        setCredentials({
            username: event.target.value,
            password: credentials.password
        })
    }

    const handleChangePassword = (event) => {
        setCredentials({
            password: event.target.value,
            username: credentials.username
        })
    }

    const clear = () => {
        cleanLoginAndSignupStatus(dispatch);
    }

    return (
        <div>
            <div className="login-home">
                <img src={pig} alt="Im a pineapple pig!" />
                <h1>Family Piggy Bank</h1>
            </div>
            <div className="card">
                <div className="login-content">
                    <h1>Welcome HOME!</h1>
                    <div className="login-input-wrapper">
                        <div className="login-input">
                            <label htmlFor="email"/>
                            <input
                                className="login-form__email"
                                type="email"
                                name="email"
                                id="email"
                                placeholder="Insert Username"
                                onChange={handleChangeUsername}
                            />
                        </div>
                        <div className="login-input">
                            <label htmlFor="password"/>
                            <input
                                type="password"
                                name="password"
                                id="password"
                                placeholder="Insert Your Password"
                                onChange={handleChangePassword}
                            />

                        </div>
                    </div>
                    <Link to={"/"}>
                    <button type="submit" className="btn" onClick={handleClick}>
                        Login
                    </button>
                    </Link>
                    <div className="loginStatus">
                        <h2>{status}</h2>
                    </div>
                   {/* <h6 className="link-option">Not a member of the Family? Please <Link to="/auth/signup" onClick={clear}><span>sign up</span></Link>.</h6>*/}
                </div>
            </div>
        </div>

    );
}

export default LoginForm;
