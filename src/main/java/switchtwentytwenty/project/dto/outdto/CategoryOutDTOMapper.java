package switchtwentytwenty.project.dto.outdto;

import org.springframework.hateoas.Link;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.dto.outdto.visitor.GetCategorySelfLinkVisitor;
import switchtwentytwenty.project.dto.outdto.visitor.GetCategoryStandardLinkVisitor;
import switchtwentytwenty.project.exception.ElementNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CategoryOutDTOMapper {

    //Constructor Methods

    /**
     * Sole constructor.
     */
    private CategoryOutDTOMapper() {
    }


    //Business Methods

    /**
     * Transaction list to DTO list.
     *
     * @param categoryList - category list
     * @return a Collection of CategoryOutDTO
     */
    public static List<CategoryOutDTO> toDTOList(List<Category> categoryList) {
        List<CategoryOutDTO> dtoList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryOutDTO dto = toDTO(category);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Category to OUTCategoryDTO.
     *
     * @param category - a category instance
     * @return dto
     */
    public static CategoryOutDTO toDTO(Category category) {
        String id = category.getID().toString();
        String designation = category.getDesignation().toString();
        String parentID = null;
        if (category.getParentID() != null) {
            parentID = category.getParentID().toString();
        }
        CategoryOutDTO categoryOutDTO = new CategoryOutDTO(id, designation, parentID);

        try {
            Link selfLink = categoryOutDTO.accept(new GetCategorySelfLinkVisitor());
            Link standardLink = categoryOutDTO.accept(new GetCategoryStandardLinkVisitor());
            categoryOutDTO.add(selfLink);
            categoryOutDTO.add(standardLink);
        } catch (ElementNotFoundException exception) {
            exception.printStackTrace();
        }
        return categoryOutDTO;
    }


}
