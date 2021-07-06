import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import {IconButton} from "@material-ui/core";
import GroupAddIcon from "@material-ui/icons/GroupAdd";
import FamilyForm from "./FamilyForm";

const useStyles = makeStyles((theme) => ({
    paper: {
        position: 'fixed',
        width: '900px',
        height: "800px",
        backgroundColor: theme.palette.background.paper,
        padding: theme.spacing(2, 4, 3),
        left: "28%",
        top: "7%"
    },
    button: {
        position: "fixed",
        top: "760px",
        left: "48%",
        width: "400px",
        zIndex: "150",
    },
}));


export default function FamilyModal() {
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
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                <div className={classes.paper}>
                    {open && <FamilyForm handleClose={handleClose}/>}
                </div>
            </Modal>
            <div>
                <div className={classes.button}>
                    <IconButton type="button" onClick={handleOpen}>
                        <GroupAddIcon style={{fontSize: 30}}/>
                    </IconButton>
                </div>
            </div>
        </div>
    );
}
