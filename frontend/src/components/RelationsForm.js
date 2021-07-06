import React, {useContext, useState} from 'react';
import AppContext from '../context/AppContext';
import '../css/addRelationModal.css';
import {addRelation} from "../context/PersonActions";
import {closeRelationForm} from "../context/PersonActions";
import RelationTypesDropTable from "./RelationTypesDropTable";
import Button from "@material-ui/core/Button";
import {makeStyles} from "@material-ui/core/styles";
import "./../css/PersonalAccountForm.css"
import {Box, TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";
import PeopleIcon from '@material-ui/icons/People';

const useStyles = makeStyles((theme)=> ({
    buttonSubmit: {
        justifyContent: "center",
        alignItems: "center",
        display: "flex",
    },
    buttonClose: {
        justifyContent: "center",
        alignItems: "center",
        display: "flex",
    },
    buttonStyleSubmit: {
        width: "100px",
        backgroundColor: "rgb(181, 211, 203)",
        color: "black",
    },
    buttonStyleClose: {
        width: "100px",
        color: "white",
    },
    margin: {
        margin: theme.spacing(2),
        width: 300,
    }
}));


function RelationsForm(props) {
    const {state, dispatch} = useContext(AppContext);
    const {relationForm, selectRelation, user} = state;
    const {result} = relationForm;
    const classes = useStyles();

    const token = user.data.jwt;

    console.log(props.info.info.email)

    const [form, setForm] = useState({
        familyID: user.data.familyID,
        personEmail: props.info.info.email,
        kinEmail: "",
        relationType: selectRelation
    })

    const handleClose = () => {
        dispatch(closeRelationForm());
    };

    const changeHandler = (e) => {
        setForm({
            ...form,
            [e.target.id]: e.target.value
        })
    }

    console.log(form)


    const changeRelationType = (value) => {
        setForm({
            ...form,
            relationType: value
        })
    }

    console.log(form)

    const submitHandler = e => {
        e.preventDefault()
        addRelation(form, dispatch, form.familyID, token);
    }

    return (
        <div>
            <form onSubmit={(e) => submitHandler(e)}>

                <div className="block_one_account">
                    <div className="formFields">
                        <TextField label= "Family Member Email"
                                   className={classes.margin}
                                   id="kinEmail"
                                   required="required"
                                   onChange={(e) => changeHandler(e)}
                                   InputProps={{
                                       startAdornment: (
                                           <InputAdornment position="start">
                                               <PeopleIcon/>
                                           </InputAdornment>
                                       ),
                                   }}/>
                    </div>

                    <div className="formFields">
                        <RelationTypesDropTable changeRelationType={changeRelationType}/>
                    </div>

                </div>

                <div className="formFields">
                    <Box m={1} className={classes.buttonSubmit}>
                        <Button variant="contained" href="#contained-buttons" onClick={submitHandler}
                                className={classes.buttonStyleSubmit} s>
                            Submit
                        </Button>
                    </Box>

                    <Box m={1} className={classes.buttonClose}>
                        <Button variant="contained" href="#contained-buttons" onClick={props.handleClose}
                                className={classes.buttonStyleClose}>
                            Close
                        </Button>
                    </Box>
                </div>


            </form>
            <div className="field-error error-relations">{state.relationForm.result}</div>
        </div>
    );
}

export default RelationsForm;