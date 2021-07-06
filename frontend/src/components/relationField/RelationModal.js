import React, {useContext} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import {IconButton} from "@material-ui/core";
import AddCircleIcon from '@material-ui/icons/AddCircle';
import RelationsForm from "../RelationsForm";
import {closeRelationForm} from "../../context/PersonActions";
import AppContext from "../../context/AppContext";

const useStyles = makeStyles((theme) => ({
    paper: {
        position: 'fixed',
        width: '650px',
        height: "400px",
        backgroundColor: theme.palette.background.paper,
        padding: theme.spacing(2, 4, 3),
        left: "32%",
        top: "15%",
    },
    button: {
        position: "fixed",
        zIndex: "150",
    },
}));


export default function RelationModal(props) {
    const classes = useStyles();
    const [open, setOpen] = React.useState(false);
    const {state, dispatch} = useContext(AppContext);
    const {relationForm, selectRelation, user} = state;


    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleClose2 = () => {
        dispatch(closeRelationForm());
        setOpen(false);
    };


    console.log(props.info)

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
                    {open && <RelationsForm info={props} handleClose={handleClose2}/>}
                </div>
            </Modal>
        </div>
    );
}
