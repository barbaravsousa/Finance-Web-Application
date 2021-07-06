package switchtwentytwenty.project.interfaceadaptor.icontroller.category;

import org.springframework.http.ResponseEntity;

public interface IGetStandardCategoryTreeController {

    /**
     * Allows the user to observe an existing standard category tree
     *
     * @return standard category tree if successful, bad request otherwise
     */
    ResponseEntity<Object> getStandardCategoryTree();

}
