import React from "react";
import "../../css/Field.css"
import PersonIcon from '@material-ui/icons/Person';
import {makeStyles} from '@material-ui/core/styles';
import {TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";

const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
        width: 300
    },
}));


export default function PersonNameField(props) {
    const classes = useStyles();

    let response = "";

    const validateName = (text) => {
        if (text !== undefined) {
            text = text.trim();
            const format = new RegExp("^[a-z\u00C0-\u00ff A-Z]+$");
            if (text.length === 0) response = " "
            else if (text.match(new RegExp("[0-9]"))) response = "* Contains numeric characters"
            else if (!text.match(format)) response = "* Contains invalid characters"
            return response;
        }
        return response;
    }

    return (
        <div className="field-container">
            <TextField label="Name"
                       className={classes.margin}
                       type="text" id={props.id}
                       required="required"
                       value={props.value} o
                       onChange={(e) => props.changeHandler(e)}
                       InputProps={{
                           startAdornment: (
                               <InputAdornment position="start">
                                   <PersonIcon/>
                               </InputAdornment>
                           ),
                       }}/>
            <div className="field-error-form">
                {validateName(props.data)}
            </div>
        </div>
    );
}