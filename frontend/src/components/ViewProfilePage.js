import AppContext from "../context/AppContext";
import {deleteEmailFromProfile, getUserProfile} from "../context/PersonActions";
import React, {useContext, useState} from "react";
import Menu from "./menuOptions/Menu";
import "../css/ViewProfilePage.css";
import pig from "../images/pineapple-pig-half1.png";
import EmailForm from "./EmailForm";
import DeleteEmailForm from "./DeleteEmailForm"
import AddIcon from '@material-ui/icons/Add';
import DeleteIcon from '@material-ui/icons/Delete';
import {Grid, TextField} from "@material-ui/core";
import InputAdornment from "@material-ui/core/InputAdornment";
import PersonIcon from "@material-ui/icons/Person";
import {makeStyles} from "@material-ui/core/styles";
import FingerprintIcon from "@material-ui/icons/Fingerprint";
import CakeIcon from "@material-ui/icons/Cake";
import EmailIcon from "@material-ui/icons/Email";
import PhoneIcon from "@material-ui/icons/Phone";
import LocationCityIcon from "@material-ui/icons/LocationCity";
import LocationOnIcon from "@material-ui/icons/LocationOn";
import PublicIcon from "@material-ui/icons/Public";


const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(2),
        width: 450
    },
    root: {
        flexGrow: 1,
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
        marginLeft: "480px",
    },
    paperOne: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
        marginLeft: "50px"
    }
}));


function ViewProfilePage(props) {
    const {state, dispatch} = useContext(AppContext);
    const {profile, user, emails} = state;
    const [openEmailForm, setOpenEmailForm] = useState(false);
    const [openDForm, setDForm] = useState(false);
    const classes = useStyles();
    const [selectedEmail, setSelectedEmail] = useState(null);


    const handleOpenDeleteClick = (email) => {
        setDForm(true); //se tiver aberto fecha, e vice versa
        setSelectedEmail(email);

    };
    const submitHandler = (e) => {
        e.preventDefault();
        deleteEmailFromProfile({
            personId: user.data.email,
            emailToDelete: selectedEmail,
            request: {
                method: `DELETE`,
            }
        }, dispatch, user.data.email, user.data.jwt);
        setDForm(false);
    };

    const getProfile = () => {
        getUserProfile(dispatch);
    };

    const handleOpenClick = () => {
        setOpenEmailForm(true)
    };

    const handleCloseEmailForm = () => {
        setOpenEmailForm(false)
    }

    const handleCloseDForm = () => {
        setDForm(false)
    };

    const {
        name,
        vat,
        birthDate,
        mainEmail,
        otherEmails = [],
        telephoneNumbers = [],
        houseNumber,
        street,
        city,
        country,
        zipCode,
    } = profile; //criar uma constante com todos os parametros de saida do backend, neste caso para o user profile

    React.useEffect(() => {
        getUserProfile(dispatch, user.data.email, user.data.jwt);
    }, [emails]);


    return (
        <div>
            <div>
                <Menu/>
            </div>
            <div>
                <div>{JSON.stringify(getProfile)}</div>
                <div className="container_profile">
                    <h2 className="h2"> {name}'s Profile </h2>
                </div>
            </div>
            <div className={classes.root}>
                <Grid container spacing={3}>
                    <Grid className={classes.paper}>
                        <div>
                            <TextField label="Name"
                                       className={classes.margin}
                                       value={name}
                                       variant="outlined"
                                       InputProps={{
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <PersonIcon/>
                                               </InputAdornment>
                                           ),
                                       }}/>
                        </div>
                        <div>
                            <TextField label="VAT"
                                       className={classes.margin}
                                       value={vat}
                                       variant="outlined"
                                       InputProps={{
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <FingerprintIcon/>
                                               </InputAdornment>
                                           ),
                                       }}/>
                        </div>
                        <div>
                            <TextField label="Birth Date"
                                       className={classes.margin}
                                       value={birthDate}
                                       variant="outlined"
                                       InputProps={{
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <CakeIcon/>
                                               </InputAdornment>
                                           ),
                                       }}/>
                        </div>
                        <div>
                            <TextField label="Main Email"
                                       className={classes.margin}
                                       value={mainEmail}
                                       variant="outlined"
                                       InputProps={{
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <EmailIcon/>
                                               </InputAdornment>
                                           ),
                                       }}/>
                        </div>
                        {otherEmails.length === 0 ? (<div>
                                <TextField label="Other Emails"
                                           className={classes.margin}
                                           value=""
                                           variant="outlined"
                                           InputProps={{
                                               startAdornment: (
                                                   <InputAdornment position="start">
                                                       <EmailIcon/>
                                                   </InputAdornment>
                                               ),
                                               endAdornment: (
                                                   <InputAdornment position="end">
                                                       <AddIcon className="hover_pointer" onClick={handleOpenClick}/>
                                                   </InputAdornment>
                                               )

                                           }}
                                />
                                {openEmailForm && <EmailForm handleClose={handleCloseEmailForm}/>}
                            </div>) :
                            otherEmails.map((email, idx) => (
                                    idx === otherEmails.length - 1 && otherEmails.length !== 4 ? (
                                            <div>
                                                <TextField label="Other Emails"
                                                           className={classes.margin}
                                                           value={email}
                                                           variant="outlined"
                                                           InputProps={{
                                                               startAdornment: (
                                                                   <InputAdornment position="start">
                                                                       <EmailIcon/>
                                                                   </InputAdornment>
                                                               ),
                                                               endAdornment: (
                                                                   <InputAdornment position="end">
                                                                       <AddIcon className="hover_pointer"
                                                                                onClick={handleOpenClick}/>
                                                                       <DeleteIcon className="hover_pointer"
                                                                                   onClick={() => handleOpenDeleteClick(email)}/>
                                                                   </InputAdornment>
                                                               )

                                                           }}
                                                />
                                                {openEmailForm && <EmailForm handleClose={handleCloseEmailForm}/>}
                                            </div>) :
                                        (
                                            <div>
                                                <TextField label="Other Emails"
                                                           className={classes.margin}
                                                           value={email}
                                                           variant="outlined"
                                                           InputProps={{
                                                               startAdornment: (
                                                                   <InputAdornment position="start">
                                                                       <EmailIcon/>
                                                                   </InputAdornment>
                                                               ),
                                                               endAdornment: (
                                                                   <InputAdornment position="end"
                                                                                   onClick={() => handleOpenDeleteClick(email)}>
                                                                       <DeleteIcon className="hover_pointer"/>
                                                                   </InputAdornment>
                                                               )
                                                           }}
                                                />
                                            </div>)
                                )
                            )
                        }
                        <DeleteEmailForm open={openDForm} handleClose={handleCloseDForm} selectedEmail={selectedEmail}
                                         submitHandler={submitHandler}/>
                    </Grid>
                    <Grid className={classes.paperOne}>
                        <div>
                            <TextField label="Telephone Numbers"
                                       className={classes.margin}
                                       value={telephoneNumbers.map((number) => (number))}
                                       variant="outlined"
                                       InputProps={{
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <PhoneIcon/>
                                               </InputAdornment>
                                           ),
                                       }}/>
                        </div>
                        <div>
                            <TextField label="House Number"
                                       className={classes.margin}
                                       value={houseNumber}
                                       variant="outlined"
                                       InputProps={{
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <LocationOnIcon/>
                                               </InputAdornment>
                                           ),
                                       }}/>
                        </div>
                        <div>
                            <TextField label="Street"
                                       className={classes.margin}
                                       value={street}
                                       variant="outlined"
                                       InputProps={{
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <LocationOnIcon/>
                                               </InputAdornment>
                                           ),
                                       }}/>
                        </div>
                        <div>
                            <TextField label="Zip-Code"
                                       className={classes.margin}
                                       value={zipCode}
                                       variant="outlined"
                                       InputProps={{
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <LocationOnIcon/>
                                               </InputAdornment>
                                           ),
                                       }}/>
                        </div>
                        <div>
                            <TextField label="City"
                                       className={classes.margin}
                                       value={city}
                                       variant="outlined"
                                       InputProps={{
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <LocationCityIcon/>
                                               </InputAdornment>
                                           ),
                                       }}/>
                        </div>
                        <div>
                            <TextField label="Country"
                                       className={classes.margin}
                                       value={country}
                                       variant="outlined"
                                       InputProps={{
                                           startAdornment: (
                                               <InputAdornment position="start">
                                                   <PublicIcon/>
                                               </InputAdornment>
                                           ),
                                       }}/>
                        </div>
                    </Grid>
                </Grid>
            </div>

            {/*<tr className="App-span">{headers.vat}: {vat}</tr>
                        <tr className="App-span">{headers.birthDate}: {birthDate}</tr>
                        <tr className="App-span">{headers.mainEmail}: {mainEmail}</tr>
                        <tr className="App-span">{headers.otherEmails}: {otherEmails}</tr>
                        <tr className="App-span">{headers.telephoneNumbers}: </tr>
                         <span className="App-span">{headers.telephoneNumbers}: {telephoneNumbers.join(', ')}</span>
                        { telephoneNumbers.map((number) => (<span className="App-span">{number}</span>)) }
                        <tr className="App-span">{headers.houseNumber}: {houseNumber}</tr>
                        <tr className="App-span">{headers.street}: {street}</tr>
                        <tr className="App-span">{headers.city}: {city}</tr>
                        <tr className="App-span">{headers.country}: {country}</tr>
                        <tr className="App-span">{headers.zipCode}: {zipCode}</tr>*/}
            <img
                src={pig}
                style={{
                    position: "fixed",
                    left: "0",
                    bottom: "-130px",
                    width: "250px",
                    zIndex: "-1",
                    opacity: "0.5",
                }}
            />
        </div>
    );
}

export default ViewProfilePage;