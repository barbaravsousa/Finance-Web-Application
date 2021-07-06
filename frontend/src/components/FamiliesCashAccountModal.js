import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import {IconButton} from "@material-ui/core";
import AddCircleIcon from '@material-ui/icons/AddCircle';
import FamilyCashAccountForm from "./FamilyCashAccountForm";

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
        width: 100,
        zIndex: "150",
        marginRight: "10px",

    },
}));


export default function FamiliesCashAccountModal() {
    const classes = useStyles();
    const [open, setOpen] = React.useState(false);


    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <div>
            <div>
                <div className={classes.button}>
                    <IconButton type="button" onClick={handleOpen}>
                        <AddCircleIcon style={{fontSize: 30}}/>
                    </IconButton>
                </div>
            </div>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                <div className={classes.paper}>
                    {open && <FamilyCashAccountForm handleClose={handleClose}/>}
                </div>
            </Modal>
        </div>
    );
}
