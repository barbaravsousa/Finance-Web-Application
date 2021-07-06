package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import switchtwentytwenty.project.domain.constant.Constants;

import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RelationType;

import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FamilyRelationOutDTOMapperTest {

    @Test
    @DisplayName("toDTO Function ")
    void toDTO() throws InvalidEmailException, IOException, InvalidRelationTypeException {
        //arrange
        Email personID = new Email("constantino@gmail.com");
        Email kinID = new Email("mariana@hotmail.com");
        RelationType relationType= RelationType.getInstance(Constants.CHILD);
        FamilyRelation familyRelation =new FamilyRelation(personID,kinID,relationType);
        FamilyRelationOutDTO result =FamilyRelationOutDTOMapper.toDTO(familyRelation);
        String stringPersonID = personID.toString();
        String stringKinID= kinID.toString();
        String stringRelationType =relationType.toString();
        //act
        FamilyRelationOutDTO expected = new FamilyRelationOutDTO(stringPersonID,stringKinID,stringRelationType);
        //assert
        assertEquals(expected, result);
    }


}