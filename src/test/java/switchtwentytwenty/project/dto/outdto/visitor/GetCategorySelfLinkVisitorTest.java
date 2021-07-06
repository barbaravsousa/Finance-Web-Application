package switchtwentytwenty.project.dto.outdto.visitor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;

public class GetCategorySelfLinkVisitorTest {

    @Test
    @DisplayName("Get self link")
    void getSelfLink() throws ElementNotFoundException {

        //arrange
        IAddLinkVisitor visitor = new GetCategorySelfLinkVisitor();

        String id = "001";
        String designation = "Food";
        String parentID = "002";
        CategoryOutDTO dto = new CategoryOutDTO(id, designation, parentID);

        //act
        Link selfLink = visitor.visit(dto);

        //assert
        Assertions.assertNotNull(selfLink);
    }

    @Test
    @DisplayName("Get self link - null input")
    void getSelfLinkNullInput() {

        //arrange
        IAddLinkVisitor visitor = new GetCategorySelfLinkVisitor();

        CategoryOutDTO dto = null;

        //act and assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            visitor.visit(dto);
        });
    }

}
