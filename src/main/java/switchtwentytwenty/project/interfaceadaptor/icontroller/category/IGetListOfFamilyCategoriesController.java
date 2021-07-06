package switchtwentytwenty.project.interfaceadaptor.icontroller.category;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.exception.ElementNotFoundException;

public interface IGetListOfFamilyCategoriesController {

    /**
     * Get list of the categories on the family’s category tree.
     *
     * @param familyID - family identifier
     * @return list of categories
     */
    ResponseEntity<Object> getListOfFamilyCategoriesController(String familyID) throws ElementNotFoundException;

}
