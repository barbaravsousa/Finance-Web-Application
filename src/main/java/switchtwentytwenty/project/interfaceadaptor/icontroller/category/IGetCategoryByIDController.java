package switchtwentytwenty.project.interfaceadaptor.icontroller.category;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.exception.ElementNotFoundException;

public interface IGetCategoryByIDController {

    /**
     * Get category by identifier.
     *
     * @param categoryID category identifier
     * @return category dto
     */
    ResponseEntity<Object> getCategoryByID(String categoryID) throws ElementNotFoundException;
}
