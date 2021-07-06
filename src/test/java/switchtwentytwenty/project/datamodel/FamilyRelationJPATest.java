package switchtwentytwenty.project.datamodel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;

public class FamilyRelationJPATest {

    @Test
    @DisplayName("Test getters")
    void testGetters() {
        //arrange
        String familyID = "F-001";
        String registrationDate = "2020-01-01";
        String name = "Turing";
        String administratorID = "P-001";
        String ledgerID = "L-001";
        FamilyJPA familyJPA = new FamilyJPA(familyID, registrationDate, name, administratorID, ledgerID);

        String personID = "alan@gmail.com";
        String kinID = "margaret_hamilton@hotmail.com";
        FamilyRelationIDJPA id = new FamilyRelationIDJPA(personID, kinID);
        String relationType = Constants.CHILD;

        FamilyRelationJPA familyRelationJPA = new FamilyRelationJPA(id, relationType, familyJPA);
        //act
        String personIDResult = familyRelationJPA.getId().getPersonID();
        String kinIDResult = familyRelationJPA.getId().getKinID();
        String relationTypeResult = familyRelationJPA.getRelationType();

        //assert
        assertEquals(personIDResult, personID);
        assertEquals(kinIDResult, kinID);
        assertEquals(relationTypeResult, relationType);
    }
}
