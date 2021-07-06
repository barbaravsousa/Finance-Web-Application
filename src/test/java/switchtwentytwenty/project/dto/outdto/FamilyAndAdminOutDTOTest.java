package switchtwentytwenty.project.dto.outdto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FamilyAndAdminOutDTOTest {

    @Test
    void getAttributes() {
        //arrange
         String familyName = "Other";
         String adminName = "other";
         String familyID = UUID.randomUUID().toString();
         String personID = "other@gmail.com";
         FamilyAndAdminOutDTO dto = new FamilyAndAdminOutDTO(familyName,adminName,familyID,personID);
         //act & assert
        assertEquals(familyName, dto.getFamilyName());
        assertEquals(adminName, dto.getAdminName());
        assertEquals(familyID, dto.getFamilyID());
        assertEquals(personID, dto.getPersonID());
    }

    @Test
    void getAttributes_WithEmptyFamilyName() {
        //arrange
        String familyName = "";
        String adminName = "other";
        String familyID = UUID.randomUUID().toString();
        String personID = "other@gmail.com";
        FamilyAndAdminOutDTO dto = new FamilyAndAdminOutDTO(familyName,adminName,familyID,personID);
        //act & assert
        assertEquals(familyName, dto.getFamilyName());
        assertEquals(adminName, dto.getAdminName());
        assertEquals(familyID, dto.getFamilyID());
        assertEquals(personID, dto.getPersonID());
    }

    @Test
    void getAttributes_WithEmptyAdminName() {
        //arrange
        String familyName = "Other";
        String adminName = "";
        String familyID = UUID.randomUUID().toString();
        String personID = "other@gmail.com";
        FamilyAndAdminOutDTO dto = new FamilyAndAdminOutDTO(familyName,adminName,familyID,personID);
        //act & assert
        assertEquals(familyName, dto.getFamilyName());
        assertEquals(adminName, dto.getAdminName());
        assertEquals(familyID, dto.getFamilyID());
        assertEquals(personID, dto.getPersonID());
    }

    @Test
    void getAttributes_WithEmptyPersonID() {
        //arrange
        String familyName = "Other";
        String adminName = "";
        String familyID = UUID.randomUUID().toString();
        String personID = "";
        FamilyAndAdminOutDTO dto = new FamilyAndAdminOutDTO(familyName,adminName,familyID,personID);
        //act & assert
        assertEquals(familyName, dto.getFamilyName());
        assertEquals(adminName, dto.getAdminName());
        assertEquals(familyID, dto.getFamilyID());
        assertEquals(personID, dto.getPersonID());
    }
}