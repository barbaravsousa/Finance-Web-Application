package switchtwentytwenty.project.interfaceadaptor.icontroller.category;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.CustomCategoryInDTO;
import switchtwentytwenty.project.exception.InvalidDateException;

public interface IAddCategoryToFamilyTreeController {

    /**
     * Method that allows the user to create a new custom family.
     *
     * @return the category name if successfully or a message error otherwise
     */
    ResponseEntity<Object> addCategoryToFamilyTree(CustomCategoryInDTO dto,String familyID)throws InvalidDateException;


}
