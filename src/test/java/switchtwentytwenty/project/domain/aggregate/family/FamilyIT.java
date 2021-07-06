package switchtwentytwenty.project.domain.aggregate.family;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FamilyIT {

    @Test
    @DisplayName("Family Relation - is my child")
    void familyRelationIsMyChild() throws Exception {
        //arrange
        boolean result;
        Family family = new Family(new FamilyID(UUID.randomUUID()));
        Email personID = new Email("maggy_hamilton@hotmail.com");
        Email kinID = new Email("alan_turing@gmail.com");
        RelationType relationType = RelationType.getInstance(Constants.CHILD);
        family.createFamilyRelation(personID, kinID, relationType);
        //act
        result = family.isMyChild(kinID, personID);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Family Relation - is my child but just parent defined")
    void familyRelationIsMyChildButParentIsDefines() throws Exception {
        //arrange
        boolean result;
        Family family = new Family(new FamilyID(UUID.randomUUID()));
        Email personID = new Email("maggy_hamilton@hotmail.com");
        Email kinID = new Email("alan_turing@gmail.com");
        RelationType relationType = RelationType.getInstance(Constants.PARENT);
        family.createFamilyRelation(personID, kinID, relationType);
        //act
        result = family.isMyChild(personID, kinID);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Family Relation - is not my child")
    void familyRelationIsNotMyChild() throws Exception {
        //arrange
        boolean result;
        Family family = new Family(new FamilyID(UUID.randomUUID()));
        Email personID = new Email("maggy_hamilton@hotmail.com");
        Email kinID = new Email("alan_turing@gmail.com");
        RelationType relationType = RelationType.getInstance(Constants.COUSIN);
        family.createFamilyRelation(personID, kinID, relationType);
        //act
        result = family.isMyChild(personID, kinID);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Family Relation - is not defined")
    void familyRelationIsNotDefined() throws Exception {
        //arrange
        boolean result;
        Family family = new Family(new FamilyID(UUID.randomUUID()));
        Email personID = new Email("maggy_hamilton@hotmail.com");
        Email kinID = new Email("alan_turing@gmail.com");
        Email otherID = new Email("johnny_von_neumann@hotmail.com");
        RelationType relationType = RelationType.getInstance(Constants.CHILD);
        family.createFamilyRelation(personID, kinID, relationType);
        //act
        result = family.isMyChild(otherID, kinID);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Family Relation - first parameter null")
    void familyRelationFirstParameterNull() throws Exception {
        //arrange
        boolean result;
        Family family = new Family(new FamilyID(UUID.randomUUID()));
        Email personID = new Email("maggy_hamilton@hotmail.com");
        Email kinID = new Email("alan_turing@gmail.com");
        Email otherID = null;
        RelationType relationType = RelationType.getInstance(Constants.CHILD);
        family.createFamilyRelation(personID, kinID, relationType);
        //act
        result = family.isMyChild(otherID, kinID);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Family Relation - second parameter null")
    void familyRelationSecondParameterNull() throws Exception{
        //arrange
        boolean result;
        Family family = new Family(new FamilyID(UUID.randomUUID()));
        Email personID = new Email("maggy_hamilton@hotmail.com");
        Email kinID = new Email("alan_turing@gmail.com");
        Email otherID = null;
        RelationType relationType = RelationType.getInstance(Constants.CHILD);
        family.createFamilyRelation(personID, kinID, relationType);
        //act
        result = family.isMyChild(personID, otherID);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Family Relation - is my child after change")
    void familyRelationIsMyChildAfterChange() throws Exception {
        //arrange
        boolean result;
        Family family = new Family(new FamilyID(UUID.randomUUID()));
        Email personID = new Email("maggy_hamilton@hotmail.com");
        Email kinID = new Email("alan_turing@gmail.com");
        RelationType child = RelationType.getInstance(Constants.CHILD);
        RelationType cousin = RelationType.getInstance(Constants.COUSIN);
        family.createFamilyRelation(personID, kinID, cousin);
        family.createFamilyRelation(kinID, personID, child);
        //act
        result = family.isMyChild(personID, kinID);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Is my cash account - True")
    void isMyCashAccountTrue() throws Exception {
        //arrange
        boolean result;
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID,new LedgerID(UUID.randomUUID()),new Email("oleola@gmail.com"),new FamilyName("Bones"));
        Family family = FamilyFactory.create(familyDTO);

        AccountID accountID = new AccountID(UUID.randomUUID());

        family.addAccountID(accountID);

        //act
        result = family.isMyAccount(accountID);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Is my cash account - False")
    void isMyCashAccountFalse() throws Exception {
        //arrange
        boolean result;
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID,new LedgerID(UUID.randomUUID()),new Email("oleola@gmail.com"),new FamilyName("Bones"));
        Family family = FamilyFactory.create(familyDTO);

        AccountID accountID1 = new AccountID(UUID.randomUUID());
        AccountID accountID2 = new AccountID(UUID.randomUUID());

        family.addAccountID(accountID1);

        //act
        result = family.isMyAccount(accountID2);
        //assert
        assertFalse(result);
    }
}
