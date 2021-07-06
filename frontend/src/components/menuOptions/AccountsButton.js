import React from "react";
import "../../css/Menu.css";
import account from "../../images/menu-account.png";
import ListItemText from '@material-ui/core/ListItemText';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import {withStyles} from '@material-ui/core/styles';
import {useHistory} from 'react-router-dom';
import AppContext from "../../context/AppContext";


const StyledMenu = withStyles({
    paper: {
        border: "#d3d4d5",
        width: "178px",
        backgroundColor:"#B5D3CB",
        marginTop: "1px",
    },
})((props) => (
    <Menu
        elevation={0}
        getContentAnchorEl={null}
        anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'center',
        }}
        transformOrigin={{
            vertical: 'top',
            horizontal: 'center',
        }}
        {...props}
    />
));

const StyledMenuItem = withStyles((theme) => ({
    root: {
        '& .MuiTypography-body1': {
            fontFamily:"Staatliches",
            color: "white",
            fontSize: "1.1rem",
        },
        '&:focus': {
            backgroundColor: "#B5D3CB",
        },
    },
}))(MenuItem);


export default function AccountsButton() {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const {state} = React.useContext(AppContext);

    let admin = false;

    if(state.user.data.roles !== undefined && admin === false) {
        admin = state.user.data.roles.includes("ROLE_ADMIN")
    }

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const history = useHistory();

    return (
        <div>
            <div className="box" style={{backgroundColor: "#B5D3CB"}} onClick={handleClick}>
                <img className="icon" src={account} alt="accounts"/>
                <div className="text">Accounts</div>
            </div>
            <StyledMenu
                id="customized-menu"
                anchorEl={anchorEl}
                keepMounted
                open={Boolean(anchorEl)}
                onClose={handleClose}
            >
                <StyledMenuItem>
                    {admin && <ListItemText primary="Family Account" onClick={() => history.push("/accounts/familyCashAccount")}/>}
                </StyledMenuItem>
                <StyledMenuItem>
                    <ListItemText primary="Personal Account" onClick={() => history.push("/accounts")}/>
                </StyledMenuItem>
                <StyledMenuItem>
                    <ListItemText primary="Ledger"
                                  onClick={() => history.push("/users/" + state.user.data.email + "/ledger")}/>
                </StyledMenuItem>
            </StyledMenu>
        </div>
    );
}