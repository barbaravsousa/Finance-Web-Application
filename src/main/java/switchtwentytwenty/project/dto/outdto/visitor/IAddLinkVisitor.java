package switchtwentytwenty.project.dto.outdto.visitor;

import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;

public interface IAddLinkVisitor {

    /**
     * Visitor pattern method.
     *
     * @param dto CategoryOutDTO instance
     * @return Link instance
     * @throws ElementNotFoundException Category not found in system
     */
    Link visit(CategoryOutDTO dto) throws ElementNotFoundException;
}
