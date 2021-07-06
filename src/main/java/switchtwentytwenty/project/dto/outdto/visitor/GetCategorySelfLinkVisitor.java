package switchtwentytwenty.project.dto.outdto.visitor;

import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.category.GetCategoryByIDController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
public class GetCategorySelfLinkVisitor implements IAddLinkVisitor {

    /**
     * Visitor pattern method.
     *
     * @param dto CategoryOutDTO instance
     * @return Link instance
     * @throws ElementNotFoundException Category not found in system
     */
    @Override
    public Link visit(CategoryOutDTO dto) throws ElementNotFoundException {
        return linkTo(methodOn(GetCategoryByIDController.class).getCategoryByID(dto.getId())).withRel("self link");
    }

}
