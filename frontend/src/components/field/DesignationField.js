import React from "react";
import {TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";
import LabelIcon from '@material-ui/icons/Label';
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
        width: 300
    },
}));


export const validateDesignation = (text) => {
    let response = "";

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

export default function DesignationField(props) {
    const classes = useStyles();

    return (
        <div>
            <div>
                <TextField label="Description"
                           className={classes.margin}
                           type="text" id={props.id}
                           required="required"
                           value={props.value}
                           onChange={(e) => props.changeHandler(e)}
                           InputProps={{
                               startAdornment: (
                                   <InputAdornment position="start">
                                       <LabelIcon/>
                                   </InputAdornment>
                               ),
                           }}/>
            </div>
            <div className="field-error" style={{height:"14px"}}>
                {validateDesignation(props.data)}
            </div>
        </div>
    );
}