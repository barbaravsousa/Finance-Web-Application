import React, {useState} from 'react';
import PhoneNumbersTable from "./PhoneNumbersTable";
import "../../css/Field.css"
import {IconButton, TextField} from "@material-ui/core";
import DeleteIcon from '@material-ui/icons/Delete';
import AddIcon from '@material-ui/icons/Add';
import PhoneIcon from '@material-ui/icons/Phone';
import {makeStyles} from '@material-ui/core/styles';
import InputAdornment from "@material-ui/core/InputAdornment";

const useStyles = makeStyles((theme) => ({
    iconButton: {
        color: 'white',
        height: 48,
        width: '150px',
        marginBottom: '-10px',
        marginRight: '-40px'
    },
    label: {
        textTransform: 'capitalize',
    },
    margin: {
        margin: theme.spacing(1),
        width: 300
    },
}));

export default function PhoneNumberField(props) {
    const classes = useStyles();

    const [phone, setPhone] = useState({
        phone: ""
    })

    const changePhone = (e) => {
        setPhone({
            phone: e.target.value
        })
    }

    const cleanField = () => {
        document.getElementById("phoneNumber").value = "";
        setPhone({
            phone: ""
        })
    }

    let response = "";

    const validatePhoneNumber = (phoneNumber) => {
        phoneNumber = phoneNumber.trim();
        if (phoneNumber.length === 0) response = ""
        else if (!phoneNumber.match(new RegExp("^[9][1-3&6][0-9]{7}$")) && !phoneNumber.match(new RegExp("^[2][1-9][0-9]{7}$"))) response = "* Invalid format"
        return response;
    }

    return (
        <div>
            <div>
                <span>
                    <TextField label="Phone Number"
                               className={classes.margin}
                               type="text" id="phoneNumber"
                               onChange={(e) => {
                                   changePhone(e);
                                   validatePhoneNumber(e.target.value)
                               }}
                               InputProps={{
                                   startAdornment: (
                                       <InputAdornment position="start">
                                           <PhoneIcon/>
                                       </InputAdornment>
                                   ),
                                   endAdornment: (
                                       <InputAdornment position="end">
                                           <div className={classes.iconButton}>
                                               <IconButton aria-label="add" onClick={() => {
                                                   props.addPhone(phone.phone);
                                                   cleanField()
                                               }}>
                                                   <AddIcon className={classes.iconButtonAdd}/>
                                               </IconButton>
                                               <IconButton aria-label="delete"
                                                           onClick={() => props.resetPhoneNumbers()}>
                                                   <DeleteIcon className={classes.iconButtonDelete}/>
                                               </IconButton>
                                           </div>
                                       </InputAdornment>
                                   ),
                               }}/>
                    <div>
                        <PhoneNumbersTable data={props.data}/>
                    </div>
                    <div className="field-error-form">
                        {validatePhoneNumber(phone.phone)}
                    </div>
                    </span>
            </div>
        </div>
    );
}

