import React, {useContext, useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import {IconButton} from "@material-ui/core";
import PersonForm from "./PersonForm";
import PersonAddIcon from '@material-ui/icons/PersonAdd';
import AppContext from "../context/AppContext";

const useStyles = makeStyles((theme) => ({
    paper: {
        position: 'fixed',
        width: '900px',
        height: "800px",
        backgroundColor: theme.palette.background.paper,
        padding: theme.spacing(2, 4, 3),
        left: "27%",
        top: "10%"
    },
    button: {
        position:"fixed",
        top: "740px",
        left: "48%",
        width: "400px",
        zIndex: "150",
    },
}));

export default function PersonModal() {
    const classes = useStyles();
    const {state} = useContext(AppContext);
    const {user, familyOptions} = state;
    const familyID = user.data.familyID;

    const [open, setOpen] = React.useState(false);

    const [newMembersFlag, setNewMemberFlag] = useState({
        newMember: false
    });

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const {_links} = familyOptions

    if (_links !== undefined) {

        const {new_member} = _links

        if (new_member !== undefined && newMembersFlag.newMember === false) {
            console.log(JSON.stringify(new_member))
            setNewMemberFlag({
                ...newMembersFlag,
                newMember: true,
            })
        }
    }


    return (
        <div>
            <div>
                {newMembersFlag.newMember && <div className={classes.button}>
                    <IconButton type="button" onClick={handleOpen}>
                        <PersonAddIcon style={{fontSize: 30}}/>
                    </IconButton>
                </div>}
            </div>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                <div className={classes.paper}>
                    {open && <PersonForm data={familyID} handleClose={handleClose}/>}
                </div>
            </Modal>
        </div>
    );
}
