package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomCategoryInDTOTest {

    @Test
    void toStringTestSuccess() {
        String designation = "Food";
        String parentID = "234jsj";
        CustomCategoryInDTO customCategoryDTO = new CustomCategoryInDTO(designation, parentID);
        String expected = designation + parentID;
        assertEquals(expected, customCategoryDTO.toString());
        assertNotNull(customCategoryDTO.getDesignation());
        assertNotNull(customCategoryDTO.getParentID());

    }

    @Test
    @DisplayName("Get category's parentID")
    void getParentID() {
        //arrange
        String designation = "Food";
        String parentID = UUID.randomUUID().toString();
        CustomCategoryInDTO dto= new CustomCategoryInDTO(designation, parentID);
        // act
        String result = dto.getParentID();
        //assert
        assertEquals(parentID,result);
    }

}