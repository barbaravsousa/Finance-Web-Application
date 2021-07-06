package switchtwentytwenty.project.dto.outdto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class FamilyRelationOutDTO extends RepresentationModel<FamilyRelationOutDTO> {

    //Attributes

    @Getter
    private final String personID;
    @Getter
    private final String kinID;
    @Getter
    private final String relationType;

    //Constructor Methods

    public FamilyRelationOutDTO(String personID, String kinID, String relationType) {
        Objects.requireNonNull(personID);
        Objects.requireNonNull(kinID);
        Objects.requireNonNull(personID);
        this.personID = personID;
        this.kinID = kinID;
        this.relationType = relationType;
    }

    //Equals and hashCode

    /**
     * Override equals.
     *
     * @param o - other instance
     * @return true if there is a match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        FamilyRelationOutDTO that = (FamilyRelationOutDTO) o;
        return Objects.equals(personID, that.personID) && Objects.equals(kinID, that.kinID);
    }

    /**
     * Override hashCode.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), personID, kinID, relationType);
    }
}
