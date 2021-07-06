import {createMuiTheme} from '@material-ui/core/styles'

const categoryTreeStyle = createMuiTheme({
    typography: {
        body1: {
            fontFamily: "'Staatliches', sans-serif",
            fontWeight: 200,
            fontSize: "30px",
            color: "rgb(100, 100, 100)",
            '&:hover': {
                color: "rgb(50, 50, 50)",

            }
        }
    },
})

export default categoryTreeStyle