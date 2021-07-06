package switchtwentytwenty.project.interfaceadaptor.icontroller.category;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.exception.ElementNotFoundException;

public interface IGetListOfStandardCategoriesController {

    /**
     * Gets list of standard categories in the system.
     *
     * @return list with standard categories dtos
     */
    ResponseEntity<Object> getListOfStandardCategories() throws ElementNotFoundException;
}
