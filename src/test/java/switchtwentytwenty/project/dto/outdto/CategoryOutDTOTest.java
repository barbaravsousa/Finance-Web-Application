package switchtwentytwenty.project.dto.outdto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CategoryOutDTOTest {

    @Test
    @DisplayName("Test getters")
    void testGetters() {
        //arrange
        String id = UUID.randomUUID().toString();
        String parentID = UUID.randomUUID().toString();
        String designation = "Designation";
        String child1 = UUID.randomUUID().toString();
        String child2 = UUID.randomUUID().toString();


        CategoryOutDTO dto = new CategoryOutDTO(id, designation,parentID);

        //act
        String idResult = dto.getId();
        String designationResult = dto.getDesignation();
        String parentIdResult= dto.getParentID();

        //assert
        assertEquals(id, idResult);
        assertEquals(designation, designationResult);
        assertEquals(parentID,parentIdResult);
    }

}
