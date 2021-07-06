package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupOneCategoryMapperTest {

    @Test
    @DisplayName("To standard dto: without parent id")
    void toStandardDTO_WithoutParentID() {
        //arrange
        String designation = "Food";
        String id = UUID.randomUUID().toString();
        List<GroupOneCategoryDTO> list = new ArrayList<>();
        GroupOneCategoryDTO category = new GroupOneCategoryDTO();
        category.setId(id);
        category.setName(designation);
        list.add(category);
        List<StandardCategoryDTO> expected = new ArrayList<>();
        StandardCategoryDTO expectedDto = new StandardCategoryDTO(designation, Constants.URL_CATEGORIES_GROUP_I + id, null);
        expected.add(expectedDto);
        //act
        List<StandardCategoryDTO> result = GroupOneCategoryMapper.toStandardDTOList(list);
        //arrange
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("To standard dto: with parent id")
    void toStandardDTO_WithParentID() {
        //arrange
        String designation = "Food";
        String id = UUID.randomUUID().toString();
        String parentID = UUID.randomUUID().toString();
        List<GroupOneCategoryDTO> list = new ArrayList<>();
        GroupOneCategoryDTO category = new GroupOneCategoryDTO();
        category.setId(id);
        category.setName(designation);
        category.setParentId(parentID);
        list.add(category);
        List<StandardCategoryDTO> expected = new ArrayList<>();
        StandardCategoryDTO expectedDto = new StandardCategoryDTO(designation, Constants.URL_CATEGORIES_GROUP_I + id, Constants.URL_CATEGORIES_GROUP_I + parentID);
        expected.add(expectedDto);
        //act
        List<StandardCategoryDTO> result = GroupOneCategoryMapper.toStandardDTOList(list);
        //arrange
        assertEquals(expected, result);
    }
}