package switchtwentytwenty.project.datamodel.assembler;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.datamodel.FamilyRelationJPA;
import switchtwentytwenty.project.datamodel.FamilyRelationIDJPA;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;

import static org.junit.jupiter.api.Assertions.*;

class FamilyRelationDomainDataAssemblerTest {

    @Test
    void toDomain() throws Exception {
        //arrange
        FamilyRelationDomainDataAssembler assembler = new FamilyRelationDomainDataAssembler();
        String personID = "person@gmail.com";
        String kinID = "kin@gmail.com";
        FamilyRelationIDJPA id = new FamilyRelationIDJPA(personID,kinID);
        String relationType = "child";
        FamilyJPA familyJPA = new FamilyJPA();
        FamilyRelationJPA familyRelationJPA = new FamilyRelationJPA(id, relationType, familyJPA);
        //act
        FamilyRelation familyRelation = assembler.toDomain(familyRelationJPA);
        //assert
        assertNotNull(familyJPA);
    }

    @Test
    void testToFamilyRelationToDomain() throws Exception {
        //arrange
        FamilyRelationDomainDataAssembler assembler = new FamilyRelationDomainDataAssembler();
        String personId = "tiago@gmail.com";
        String kinId = "chico@gmail.com";
        FamilyJPA familyJPA = new FamilyJPA();
        FamilyRelationIDJPA familyRelationJPAId = new FamilyRelationIDJPA(personId,kinId);
        String relationType = "child";
        FamilyRelationJPA familyRelationJPA = new FamilyRelationJPA(familyRelationJPAId,relationType,familyJPA);
        //act
        FamilyRelation familyRelation = assembler.toDomain(familyRelationJPA);

        //assert
        assertNotNull(familyRelation);

    }
}