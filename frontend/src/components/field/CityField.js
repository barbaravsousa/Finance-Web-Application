import React from 'react';
import "../../css/Field.css"
import LocationCityIcon from '@material-ui/icons/LocationCity';
import {makeStyles} from '@material-ui/core/styles';
import {TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";

const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
        width: 300,
    },
}));


export default function CityField(props) {
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
    }

    return (
        <div className="field-container">
            <TextField label="City"
                       className={classes.margin}
                       type="text" id={props.id}
                       required={props.required}
                       value={props.value}
                       onChange={(e) => props.changeHandler(e)}
                       InputProps={{
                           startAdornment: (
                               <InputAdornment position="start">
                                   <LocationCityIcon/>
                               </InputAdornment>
                           ),
                       }}/>
            <div className="field-error-form">
                {validateName(props.data)}
            </div>
        </div>
    );
}