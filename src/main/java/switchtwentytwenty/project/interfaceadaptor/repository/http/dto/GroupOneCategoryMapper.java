package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupOneCategoryMapper {


    /**
     * From POJO to a internal DTO class that will be send as response in the controller class.
     *
     * @param standard POJO from JSON
     * @return an OutDTO
     */
    private static StandardCategoryDTO toStandardDTO(GroupOneCategoryDTO standard) {
        String designation = standard.getDesignation();
        String id = Constants.URL_CATEGORIES_GROUP_I + standard.getID();
        if (standard.getParentID() != null) {
            String parentID = Constants.URL_CATEGORIES_GROUP_I + standard.getParentID();
            return new StandardCategoryDTO(designation, id, parentID);
        }
        return new StandardCategoryDTO(designation, id, null);
    }

    /**
     * From list of POJOs to a list of internal DTOs.
     *
     * @param list POJO from JSON
     * @return an OutDTO
     */
    public static List<StandardCategoryDTO> toStandardDTOList(List<GroupOneCategoryDTO> list) {
        List<StandardCategoryDTO> result = new ArrayList<>();
        for (GroupOneCategoryDTO dto : list) {
            result.add(toStandardDTO(dto));
        }
        return result;
    }
}
