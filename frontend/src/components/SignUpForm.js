import React, {useState} from "react";
import AppContext from "../context/AppContext";
import {Link} from 'react-router-dom';
import {signUp} from "../context/FamilyActions";

function SignUpForm() {

    const {state, dispatch} = React.useContext(AppContext);
    let {signup} = state;
    let {statusFailure, statusSuccess, statusLoading} = signup;

    const [info, setInfo] = useState({
        username: "",
        email: "",
        password: "",
    })

    const handleClick = () => {
        signUp(info, dispatch);
    }

    const handleChangeUserName = (event) => {
        setInfo({
            username: event.target.value,
            password: info.password,
            email: info.email,
        })
    }
    const handleChangePassword = (event) => {
        setInfo({
            username: info.username,
            password: event.target.value,
            email: info.email,
        })
    }

    const handleChangeEmail = (event) => {
        setInfo({
            username: info.username,
            password: info.password,
            email: event.target.value,
        })
    }

    return (
        <div className="card">
            <div className="login-content">
                <h1>Join The Family!</h1>
                <div className="login-input-wrapper">
                    <div className="login-input">
                        <label text="name"/>
                        <input type="name"
                               placeholder="Insert Your Username"
                               id="username"
                               onChange={handleChangeUserName}
                        />
                        <label htmlFor="email"/>
                        <input
                            className="login-form__email"
                            type="email"
                            name="email"
                            id="email"
                            placeholder="Insert Email"
                            onChange={handleChangeEmail}
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
                <button type="submit" className="btn" onClick={handleClick}>
                    Sign Up
                </button>
                <div className="loginStatus">
                    <div className="success">
                        <h2>{statusSuccess}</h2>
                    </div>
                    <div className="loading">
                        <h2>{statusLoading}</h2>
                    </div>
                    <h2>{statusFailure}</h2>
                </div>

                <h6 className="link-option">Already of the Family? Please <Link to='/'><span>log in</span></Link>.
                </h6>
            </div>
        </div>

    );
}

export default SignUpForm;