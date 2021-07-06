import React from 'react';
import {TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";
import {makeStyles} from "@material-ui/core/styles";
import EuroIcon from '@material-ui/icons/Euro';


const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
        width: 300
    },
}));

export const validateAmount = (initialAmount) => {

    if (initialAmount !== null && initialAmount !== undefined) {
        const format = new RegExp("^[0-9]+(.[0-9]{1,2})?$");
        // if (initialAmount.length === 0) response = ""
        if (!initialAmount.match(format)){
            return "*  Invalid amount"
        }
    }
    return "";
}

export default function InitialAmountField(props) {
    const classes = useStyles();

    return (
        <div>
            <div>
                <TextField label="Initial Amount"
                           className={classes.margin}
                           type="number" id={props.id}
                           value={props.value}
                           required={props.required} onChange={(e) => props.changeHandler(e)}
                           InputProps={{
                               startAdornment: (
                                   <InputAdornment position="start">
                                       <EuroIcon/>
                                   </InputAdornment>
                               ),
                           }}/>
            </div>
            <div className="field-error" style={{height:"14px"}}>
                {validateAmount(props.data)}
            </div>
        </div>
    );
}