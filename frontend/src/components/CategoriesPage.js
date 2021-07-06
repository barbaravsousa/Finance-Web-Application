import React, {useContext} from "react";
import CategoryTree from "./CategoryTree";
import Menu from "./menuOptions/Menu";
import AppContext from "../context/AppContext";

function CategoryPage() {

    let {state, dispatch} = useContext(AppContext);

    return (
        <div>
            <div>
                <Menu/>
            </div>
            <CategoryTree/>
        </div>
    )
}

export default CategoryPage;