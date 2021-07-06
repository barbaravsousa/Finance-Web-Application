package switchtwentytwenty.project.dto.indto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;

public class FamilyRelationInDTOTest {

    @Test
    @DisplayName("Test getters")
    void testGetters() {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID = "john_von_neuman@gmail.com";
        String relation = Constants.CHILD;

        FamilyRelationInDTO dto = new FamilyRelationInDTO(personID, kinID, relation);

        String otherPersonID = "margaret_hamilton@hotmail.com";
        String otherKinID = "thomas_mann@hotmail.com";
        String otherRelation = Constants.COUSIN;

        dto.setPersonEmail(otherPersonID);
        dto.setKinEmail(otherKinID);
        dto.setRelationType(otherRelation);

        //act
        String otherPersonIDResult = dto.getPersonEmail();
        String otherKinIDResult = dto.getKinEmail();
        String otherRelationResult = dto.getRelationType();

        //assert
        assertEquals(otherPersonID, otherPersonIDResult);
        assertEquals(otherKinID, otherKinIDResult);
        assertEquals(otherRelation, otherRelationResult);
    }
}
