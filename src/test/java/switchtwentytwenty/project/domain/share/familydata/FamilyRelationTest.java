package switchtwentytwenty.project.domain.share.familydata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;

import java.io.IOException;

public class FamilyRelationTest {

    @Test
    @DisplayName("Get relation type")
    void getRelationType() throws Exception {
        //arrange
        RelationType result;
        Email personID = new Email("margaret_howard@gmail.com");
        Email kinID = new Email("linus_tovards@hotmail.com");
        RelationType relationType = RelationType.getInstance(Constants.COUSIN);
        FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationType);
        //act
        result = familyRelation.getRelationType();
        //assert
        Assertions.assertEquals(result, relationType);
    }

    @Test
    @DisplayName("Null person ID")
    void nullPersonID() throws Exception {
        //arrange
        Email personID = null;
        Email kinID = new Email("linus_tovards@hotmail.com");
        RelationType relationType = RelationType.getInstance(Constants.COUSIN);
        //act and assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            new FamilyRelation(personID, kinID, relationType);
        });
    }

    @Test
    @DisplayName("Null kin ID")
    void nullKinID() throws Exception {
        //arrange
        Email personID = new Email("linus_tovards@hotmail.com");
        Email kinID = null;
        RelationType relationType = RelationType.getInstance(Constants.COUSIN);
        //act and assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            new FamilyRelation(personID, kinID, relationType);
        });
    }

    @Test
    @DisplayName("Null relation type ID")
    void nullRelationTypeID() throws Exception {
        //arrange
        Email personID = new Email("linus_tovards@hotmail.com");
        Email kinID = new Email("margaret_howard@gmail.com");
        RelationType relationType = null;
        //act and assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            new FamilyRelation(personID, kinID, relationType);
        });
    }

    @Test
    @DisplayName("Same Persons involved - part 1")
    void samePersonInvolvedOne() throws Exception {
        //arrange
        boolean result;
        Email personID = new Email("margaret_howard@gmail.com");
        Email kinID = new Email("linus_tovards@hotmail.com");
        RelationType relationType = RelationType.getInstance(Constants.COUSIN);
        RelationType otherRelationType = RelationType.getInstance(Constants.CHILD);
        FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationType);
        FamilyRelation identicalFamilyRelation = new FamilyRelation(kinID, personID, otherRelationType);
        //act
        result = familyRelation.samePersonsInvolved(identicalFamilyRelation);
        //assert
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Same Persons involved - part 2")
    void samePersonInvolvedTwo() throws Exception {
        //arrange
        boolean result;
        Email personID = new Email("margaret_howard@gmail.com");
        Email kinID = new Email("linus_tovards@hotmail.com");
        RelationType relationType = RelationType.getInstance(Constants.COUSIN);
        RelationType otherRelationType = RelationType.getInstance(Constants.CHILD);
        FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationType);
        FamilyRelation identicalFamilyRelation = new FamilyRelation(personID, kinID, otherRelationType);
        //act
        result = familyRelation.samePersonsInvolved(identicalFamilyRelation);
        //assert
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Not same Person involved")
    void notSamePersonInvolved() throws Exception{
        //arrange
        boolean result;
        Email personID = new Email("margaret_howard@gmail.com");
        Email kinID = new Email("linus_tovards@hotmail.com");
        Email otherID = new Email("alan_turing@hotmail.com");
        RelationType relationType = RelationType.getInstance(Constants.COUSIN);
        RelationType otherRelationType = RelationType.getInstance(Constants.CHILD);
        FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationType);
        FamilyRelation identicalFamilyRelation = new FamilyRelation(personID, otherID, otherRelationType);
        //act
        result = familyRelation.samePersonsInvolved(identicalFamilyRelation);
        //assert
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Parent/Child family relation - is my child")
    void parentChildFamilyRelationIsMyChild() throws Exception {
        //arrange
        boolean resultPersonToKin;
        boolean resultKinToPerson;

        Email personID = new Email("alan_turing@hotmail.com");
        Email kinID = new Email("maggy_hamilton@gmail.com");
        RelationType relationType = RelationType.getInstance(Constants.PARENT);
        FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationType);

        //act
        resultPersonToKin = familyRelation.isMyChild(personID, kinID);
        resultKinToPerson = familyRelation.isMyChild(kinID, personID);

        //assert
        Assertions.assertTrue(resultPersonToKin);
        Assertions.assertFalse(resultKinToPerson);
    }

    @Test
    @DisplayName("Parent/Child family relation - is my parent")
    void parentChildFamilyRelationIsMyParent() throws Exception {
        //arrange
        boolean resultPersonToKin;
        boolean resultKinToPerson;

        Email personID = new Email("alan_turing@hotmail.com");
        Email kinID = new Email("maggy_hamilton@gmail.com");
        RelationType relationType = RelationType.getInstance(Constants.CHILD);
        FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationType);

        //act
        resultPersonToKin = familyRelation.isMyChild(personID, kinID);
        resultKinToPerson = familyRelation.isMyChild(kinID, personID);

        //assert
        Assertions.assertFalse(resultPersonToKin);
        Assertions.assertTrue(resultKinToPerson);
    }

    @Test
    @DisplayName("Parent/Child family relation - is my cousin")
    void parentChildFamilyRelationIsMyCousin() throws Exception{
        //arrange
        boolean result;

        Email personID = new Email("alan_turing@hotmail.com");
        Email kinID = new Email("maggy_hamilton@gmail.com");
        RelationType relationType = RelationType.getInstance(Constants.COUSIN);
        FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationType);

        //act
        result = familyRelation.isMyChild(personID, kinID);

        //assert
        Assertions.assertFalse(result);
    }
}
