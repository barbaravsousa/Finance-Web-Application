import React from 'react';
import InputAdornment from "@material-ui/core/InputAdornment";
import {TextField} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import TagFacesIcon from "@material-ui/icons/TagFaces";


const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
        width: 300
    },
}));

export default function UsernameField(props) {
    const classes = useStyles();


    let response = "";

    const validateComponent = (component) => {
        if (component !== undefined) {
            component = component.trim();
            const format = new RegExp("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _ºª]*$");
            if (component.length === 0) response = " "
            else if (!component.match(format)) response = "* Contains invalid characters"
            return response;
        }
    }

    return (
        <div className="field-container">
            <TextField label="Username"
                       className={classes.margin}
                       type="text" id={props.id}
                       required={props.required}
                       value={props.value}
                       onChange={(e) => props.changeHandler(e)}
                       InputProps={{
                           startAdornment: (
                               <InputAdornment position="start">
                                   <TagFacesIcon/>
                               </InputAdornment>
                           ),
                       }}/>
            <div className="field-error-form">
                {validateComponent(props.data)}
            </div>
        </div>
    );
}