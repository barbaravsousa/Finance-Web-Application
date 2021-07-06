import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import {deleteEmailFromProfile, closeDeleteEmailForm} from "../context/PersonActions";
import AddIcon from '@material-ui/icons/Add';
import CloseIcon from '@material-ui/icons/Close';
import {
    Dialog, DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    IconButton,
    useMediaQuery,
    useTheme
} from "@material-ui/core";


import "../css/AddFamilyModal.css";
import "../css/addMemberModal.css";
import Button from "@material-ui/core/Button";

function DeleteEmailForm(props) {
    const {state, dispatch} = useContext(AppContext);
    const {deleteEmailForm, user} = state;
    const {result} = deleteEmailForm;
    const theme = useTheme();
    const fullScreen = useMediaQuery(theme.breakpoints.down('sm'));

    return (
        <div>
            <Dialog
                fullScreen={fullScreen}
                open={props.open}
                onClose={props.handleClose}
                aria-labelledby="responsive-dialog-title"
            >
                <DialogTitle id="responsive-dialog-title">{"Delete Email"}</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Are you sure you want to delete this email?
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button autoFocus onClick={props.submitHandler}>
                        Yes
                    </Button>
                    <Button onClick={props.handleClose} autoFocus>
                        No
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default DeleteEmailForm;