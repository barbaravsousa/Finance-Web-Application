package switchtwentytwenty.project.dto.outdto.visitor;

import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.category.GetListOfStandardCategoriesController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class GetCategoryStandardLinkVisitor implements IAddLinkVisitor {

    /**
     * Visitor pattern method.
     *
     * @param dto CategoryOutDTO instance
     * @return Link instance
     * @throws ElementNotFoundException Category not found in system
     */
    @Override
    public Link visit(CategoryOutDTO dto) throws ElementNotFoundException {
        return linkTo(methodOn(GetListOfStandardCategoriesController.class).getListOfStandardCategories()).withRel("system's standard categories");
    }

}
