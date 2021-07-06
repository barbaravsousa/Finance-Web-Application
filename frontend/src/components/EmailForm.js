import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import { addEmailToProfile} from "../context/PersonActions";
import EmailField from "./field/EmailField";
import "../css/AddFamilyModal.css";
import "../css/addMemberModal.css";
import Button from "@material-ui/core/Button";

function EmailForm(props) {

    const{state, dispatch} = useContext(AppContext);
    const{emailForm,user}= state;
    const{result} = emailForm;
    const[formEmail, setFormEmail] = useState({
        personId: user.data.email,
        emailToAdd: "",
        request: {
            method: `POST`,
        }
    }
    );

    const changeHandler = (e) => {
        setFormEmail({
            ...formEmail,
            [e.target.id]: e.target.value,
        });
    };

    const submitHandler = (e) => {
        e.preventDefault();
        addEmailToProfile(formEmail, dispatch,formEmail.personId,user.data.jwt);
        props.handleClose();
    };


    return (
        <div className="add-Email-form">
         <form onSubmit={(e) => submitHandler(e)}>
        <div className="formFields_emailForm">
        <EmailField
            id="emailToAdd"
            required="required"
            data={formEmail.emailToAdd}
            value={formEmail.emailToAdd}
            changeHandler={changeHandler}
        />

        

        </div>
            <div className="formFields">
                    <Button className="new-family" type="submit" onClick={submitHandler}>
                        Submit
                    </Button>
                    <Button className="new-family" onClick={props.handleClose}>
                        Close
                    </Button>
                </div>
            </form>
           
        </div>
    );
}

export default EmailForm;