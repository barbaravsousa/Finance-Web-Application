package switchtwentytwenty.project.dto.outdto;

import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;

public class FamilyRelationOutDTOMapper {

    //Attributes

    //Constructor Methods

    /**
     * Sole constructor.
     */
    private FamilyRelationOutDTOMapper() {}

    //Getter and Setters

    //Business Methods

    /**
     * From Domain to Data Transfer Object.
     *
     * @param familyRelation - Domain instance
     * @return Data Transfer Object
     */
    public static FamilyRelationOutDTO toDTO(FamilyRelation familyRelation) {
        String personID = familyRelation.getPersonID().toString();
        String kinID = familyRelation.getKinID().toString();
        String relationType = familyRelation.getRelationType().toString();
        return new FamilyRelationOutDTO(personID, kinID, relationType);
    }


    //Equals and HashCode
}
