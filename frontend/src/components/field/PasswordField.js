import React, {useState} from 'react';
import VisibilityIcon from '@material-ui/icons/Visibility';
import VisibilityOffIcon from '@material-ui/icons/VisibilityOff';
import {IconButton, TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";
import HttpsIcon from "@material-ui/icons/Https";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
        width: 300
    },
    button: {},
}));

export default function PasswordField(props) {
    const [passwordVisible, setPasswordVisible] = useState({
        result: false,
        mode: 'password'
    })

    const classes = useStyles();

    /*
        const validatePassword = (email) => {
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
         */

    const changeMode = () => {
        if (passwordVisible.result === true) {
            setPasswordVisible({
                result: false,
                mode: "password"
            })
        } else {
            setPasswordVisible({
                result: true,
                mode: "text"
            })
        }

    }

    const passwordVisibility = () => {
        if (passwordVisible.result === true) {
            return (
                <IconButton className={classes.button}>
                    <VisibilityOffIcon/>
                </IconButton>
            )
        } else {
            return (
                <IconButton className={classes.button}>
                    <VisibilityIcon/>
                </IconButton>
            )
        }
    }

    return (
        <div>
            <TextField label="Password"
                       className={classes.margin}
                       type={passwordVisible.mode} id={props.id}
                       value={props.value}
                       required={props.required} onChange={(e) => props.changeHandler(e)}
                       InputProps={{
                           startAdornment: (
                               <InputAdornment position="start">
                                   <HttpsIcon/>
                               </InputAdornment>
                           ),
                           endAdornment: (
                               <InputAdornment position={"end"}>
                                   <span onClick={changeMode}>{passwordVisibility()}</span>
                               </InputAdornment>
                           )
                       }}/>
        </div>
    );
}