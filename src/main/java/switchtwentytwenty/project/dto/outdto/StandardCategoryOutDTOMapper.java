package switchtwentytwenty.project.dto.outdto;

import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;

import java.util.ArrayList;
import java.util.List;

public class StandardCategoryOutDTOMapper {

    /**
     * Sole Constructor.
     */
    private StandardCategoryOutDTOMapper() {
    }


    /**
     * From Domain to Data Transfer Object.
     *
     * @param standard category
     * @return - out standard category dto
     */
    public static StandardCategoryOutDTO toDTO(Category standard) {
        String designation = standard.getDesignation().toString();
        String id = standard.getID().toString();
        if (standard.getParentID() != null) {
            String parentID = standard.getParentID().toString();
            return new StandardCategoryOutDTO(designation, id, parentID);
        }
        return new StandardCategoryOutDTO(designation, id, null);
    }

    /**
     * From StandardCategoryOutDTO to CategoryOutDTO.
     *
     * @param standardCategoryDTO outStandardCategoryDTO instance
     * @return CategoryOutDTO instance
     */
    public static CategoryOutDTO toOutCategoryDTO(StandardCategoryOutDTO standardCategoryDTO) {
        return new CategoryOutDTO(standardCategoryDTO.getId(), standardCategoryDTO.getDesignation(),standardCategoryDTO.getParentID());
    }

    /**
     * From StandardCategoryOutDTO list to CategoryOutDTO list.
     *
     * @param standardCategoryDTOList list of outStandardCategoryDTO instance
     * @return list of CategoryOutDTO instance
     */
    public static List<CategoryOutDTO> toOutCategoryDTOList(List<StandardCategoryOutDTO> standardCategoryDTOList) {
        List<CategoryOutDTO> dtoList = new ArrayList<>();
        for (StandardCategoryOutDTO standardCategoryOutDTO : standardCategoryDTOList) {
            dtoList.add(toOutCategoryDTO(standardCategoryOutDTO));
        }
        return dtoList;
    }

    /**
     * From StandardCategoryDTO list to StandardCategoryOutDTO list
     *
     * @param standardList of standard category DTO's
     * @return list with Out Standard Category DTO's
     */
    public static List<StandardCategoryOutDTO> toOutStandardCategoryDTOList(List<StandardCategoryDTO> standardList) {
        List<StandardCategoryOutDTO> dtoList = new ArrayList<>();
        for (StandardCategoryDTO standardCategoryDTO : standardList) {
            StandardCategoryOutDTO standardCategoryOutDTO = new StandardCategoryOutDTO(standardCategoryDTO.getDesignation(), standardCategoryDTO.getId(), standardCategoryDTO.getParentID());
            dtoList.add(standardCategoryOutDTO);
        }
        return dtoList;
    }

}
