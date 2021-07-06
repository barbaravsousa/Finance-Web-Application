import React, {useContext} from 'react';
import TreeView from '@material-ui/lab/TreeView';
import {makeStyles} from '@material-ui/core/styles';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import TreeItem from '@material-ui/lab/TreeItem';
import "../css/CategoryTree.css"
import {getStandardCategories} from "../context/CategoryActions";
import AppContext from "../context/AppContext";
import theme from "./CategoryTreeStyle"
import {ThemeProvider} from '@material-ui/styles'

const useStyles = makeStyles({
    root: {
        height: 110,
        flexGrow: 1,
        maxWidth: 400,
        color: 'black',
        margin: "0 auto",
        marginTop: "100px"
    },
    "@global": {
        ".MuiTreeItem-label": {
            width: "auto",
            transition: "0.3s",
            padding: "5px",
        },
        ".MuiTreeItem-label:hover": {
            backgroundColor: "rgb(0,0,0,0)",
            fontSize: "33px",
            display: "inline-block"
        },
        ".MuiTreeItem-root.Mui-selected > .MuiTreeItem-content .MuiTreeItem-label:hover, .MuiTreeItem-root.Mui-selected:focus > .MuiTreeItem-content .MuiTreeItem-label": {
            backgroundColor: "rgb(0,0,0,0)",
        },
        ".MuiTreeItem-iconContainer svg":{
            fontSize: "40px",
            paddingRight: "5px"
        }
    }

});

export default function CategoryTree() {

    let {state, dispatch} = useContext(AppContext);
    let {user, categoryTree} = state;
    const token = user.data.jwt;
    let {data} = categoryTree;


    const classes = useStyles();

    React.useEffect(() => {
        getStandardCategories(dispatch, token);
    }, []);


    const renderTree = (nodes) => (
        <TreeItem key={nodes.designation} nodeId={nodes.designation} label={nodes.designation}>
            {Array.isArray(nodes.children) ? nodes.children.map((node) => renderTree(node)) : null}
        </TreeItem>
    );

    return (
        <ThemeProvider theme={theme}>
            <div className="tree-container">
                <TreeView
                    className={classes.root}
                    defaultCollapseIcon={<ExpandMoreIcon/>}
                    defaultExpanded={['root']}
                    defaultExpandIcon={<ChevronRightIcon/>}
                >
                    {renderTree(data)}
                </TreeView>
            </div>
        </ThemeProvider>
    );
}
