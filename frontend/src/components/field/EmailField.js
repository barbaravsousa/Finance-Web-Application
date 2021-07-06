import React from 'react';
import "../../css/Field.css"
import EmailIcon from '@material-ui/icons/Email';
import {makeStyles} from '@material-ui/core/styles';
import {TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";

const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
        width: 300
    },
}));

export default function EmailField(props) {
    const classes = useStyles();

    let response = "";

    const validateEmail = (email) => {
        if (email !== undefined) {
            email = email.trim();
            const format = new RegExp("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]+$");
            if (email.length === 0) response = ""
            else if (!email.match(format)) response = "* Email format invalid"
            else {
                response = ""
            }
            return response;
        }
    }

    return (
        <div className="field-container">
            <TextField label="Email"
                       className={classes.margin}
                       type="text" id={props.id}
                       value={props.value}
                       required={props.required} onChange={(e) => props.changeHandler(e)}
                       InputProps={{
                           startAdornment: (
                               <InputAdornment position="start">
                                   <EmailIcon/>
                               </InputAdornment>
                           ),
                       }}/>
            <div className="field-error-form">
                {validateEmail(props.data)}
            </div>
        </div>
    );
}