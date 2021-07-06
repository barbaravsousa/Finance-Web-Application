import React from 'react';
import "../../css/Field.css"
import FingerprintIcon from '@material-ui/icons/Fingerprint';
import {makeStyles} from '@material-ui/core/styles';
import {TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";

const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
        width: 300
    },
}));

export default function VatField(props) {
    const classes = useStyles();

    let response = "";

    const validateControlDigit = (vat) => {
        const maxDigits = 9;
        let checkSum = 0;
        for (let index = 0; index < maxDigits - 1; index++) {
            checkSum += Number(vat.charAt(index)) * (maxDigits - index);
        }
        let checkDigit = 11 - (checkSum % 11);
        if (checkDigit >= 10) checkDigit = 0;
        return checkDigit === Number(vat.charAt(maxDigits - 1) - '0');
    }

    const validateVat = (vat) => {
        if (vat !== undefined) {
            vat = vat.trim();
            const format = new RegExp("([1-3])[0-9]{8}");
            if (vat.length === 0) response = ""
            else if (vat.match(new RegExp("[^0-9]"))) response = "* VAT contains non-numeric characters"
            else if (vat.length !== 9) response = "* VAT does not contain 9 digits"
            else if (!vat.match(format)) return response = "* VAT not corresponds to a Person's VAT"
            else if (!validateControlDigit(vat)) return response = "* VAT control digit is not valid"
            return response;
        }
    }

    return (
        <div className="field-container">
            <TextField label="VAT Number"
                       className={classes.margin}
                       type="text" id={props.id}
                       required={props.required}
                       value={props.value}
                       onChange={(e) => props.changeHandler(e)}
                       InputProps={{
                           startAdornment: (
                               <InputAdornment position="start">
                                   <FingerprintIcon/>
                               </InputAdornment>
                           ),
                       }}/>
            <div className="field-error-form">
                {validateVat(props.data)}
            </div>
        </div>
    );
}
