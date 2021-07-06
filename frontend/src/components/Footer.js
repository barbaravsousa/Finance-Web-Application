import {AppBar, Container, Toolbar, Typography} from "@material-ui/core";

export default function Footer() {
    return (
        <AppBar position="static" style={{position:"absolute",bottom:"0",width:"100%",height:"60px",backgroundColor:"#f5f5f5", boxShadow:"none",
            webkitBoxShadow: "none",zIndex:"-2",alignItems:"center"}}>
            <Container maxWidth="md">
                <Toolbar style={{display:"flex",justifyContent: "center"}}>
                    <Typography style={{color:"rgb(100, 100, 100)",fontFamily:"Staatliches",fontSize:"0.8rem",alignText:"center"}}>
                        @2021 SWitCH - All rights reserved to CryToGetter
                    </Typography>
                </Toolbar>
            </Container>
        </AppBar>
    )
}