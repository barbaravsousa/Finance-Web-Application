import React from 'react';
import "../../css/Field.css"
import CakeIcon from '@material-ui/icons/Cake';
import {makeStyles} from '@material-ui/core/styles';
import {TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";


const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
        width: 300

    },
}));

export const validateDate = (date) => {
    let response = "";
    if (date !== undefined) {
        if (date.length === 0) response = ""
        else if (new Date(date).valueOf() > new Date().valueOf())
            return "* Date after today"
        return response;
    }
}

export default function DateField(props) {
    const classes = useStyles();

    return (
        <div className="field-container">
            <TextField label="Birth Date"
                       className={classes.margin}
                       type="date" id={props.id}
                       value={props.value}
                       required={props.required}
                       onChange={(e) => props.changeHandler(e)}
                       InputProps={{
                           startAdornment: (
                               <InputAdornment position="start">
                                   <CakeIcon/>
                               </InputAdornment>
                           ),
                       }}/>
            <div className="field-error-form">
                {validateDate(props.data)}
            </div>
        </div>

    );
}