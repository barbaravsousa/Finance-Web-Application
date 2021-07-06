import React from "react";
import AppContext from "../context/AppContext";
import { logout } from "../context/AuthorizationAndAuthenticationActions";
import logout3 from "../images/menu-logout.png";

function LogoutForm() {
  const { dispatch } = React.useContext(AppContext);

  const handleClick = () => {
      logout(dispatch)
  };

  return (
    <div className="box" onClick={handleClick}>
      <img className="icon" src={logout3} alt="logout3" />
      <div className="text">Logout</div>
    </div>
  );
}

export default LogoutForm;
