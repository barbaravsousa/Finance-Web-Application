import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import PaymentForm from "./PaymentForm";
import "../css/PaymentModal.css";

const useStyles = makeStyles((theme) => ({
    paper: {
        position: 'fixed',
        width: '650px',
        height: "400px",
        backgroundColor: theme.palette.background.paper,
        padding: theme.spacing(2, 4, 3),
        left: "32%",
        top: "15%"
    },
    button: {
        position: "fixed",
        top: "790px",
        width: "400px",
        zIndex: "150",
    },
}));


export default function PaymentModal(props) {
    const classes = useStyles();
    return (
        <div>
            <Modal
                open={props.open}
                onClose={props.handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                <div className={classes.paper} id="paymentModalContainer">
                    {props.open && <PaymentForm handleClose={props}/>}
                </div>
            </Modal>
        </div>
    );
}
