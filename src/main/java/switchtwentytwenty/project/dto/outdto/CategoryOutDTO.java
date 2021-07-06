package switchtwentytwenty.project.dto.outdto;

import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import switchtwentytwenty.project.dto.outdto.visitor.IAddLinkVisitor;
import switchtwentytwenty.project.exception.ElementNotFoundException;

import java.util.Objects;

public class CategoryOutDTO extends RepresentationModel<CategoryOutDTO> {

    //Attributes

    @Getter
    private final String id;
    @Getter
    private final String designation;
    @Getter
    private final String parentID;


    //Constructor Methods

    /**
     * Sole constructor.
     *
     * @param id          - identifier
     * @param designation - category designation
     * @param parentID    -    parent ID
     */
    public CategoryOutDTO(String id, String designation, String parentID) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(designation);
        this.id = id;
        this.designation = designation;
        this.parentID = parentID;
    }

    //Business methods

    /**
     * Accepts Visitor.
     *
     * @param visitor Visitor instance
     * @return Link instance
     * @throws ElementNotFoundException Category not found in system
     */
    public Link accept(IAddLinkVisitor visitor) throws ElementNotFoundException {
        return visitor.visit(this);
    }

}
