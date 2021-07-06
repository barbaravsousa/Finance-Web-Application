import React from 'react';
import "../../css/Field.css"
import LocationOnIcon from '@material-ui/icons/LocationOn';
import {TextField} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import InputAdornment from "@material-ui/core/InputAdornment";

const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
        width: 300
    },
}));

export default function ZipCodeField(props) {
    const classes = useStyles();


    let response = "";

    const validateZipCode = (zipCode) => {
        if (zipCode !== undefined) {
            zipCode = zipCode.trim();
            const format = new RegExp('^\\d{4}-\\d{3}$');
            if (zipCode.length === 0) response = " "
            else if (!zipCode.match(new RegExp('\\d'))) response = "* Contains invalid characters"
            else if (!zipCode.match(format)) response = "* Invalid format"
            return response;
        }
    }

    return (
        <div className="field-container">
            <TextField label="Zip Code"
                       className={classes.margin}
                       type="text" id={props.id}
                       required={props.required}
                       value={props.value}
                       onChange={(e) => props.changeHandler(e)}
                       InputProps={{
                           startAdornment: (
                               <InputAdornment position="start">
                                   <LocationOnIcon/>
                               </InputAdornment>
                           ),
                       }}/>
            <div className="field-error-form">
                {validateZipCode(props.data)}
            </div>
        </div>
    );
}
