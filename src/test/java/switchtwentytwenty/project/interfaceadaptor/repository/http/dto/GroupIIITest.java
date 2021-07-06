package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GroupIIITest {

    @Test
    @DisplayName("To standard dto: without parent id")
    void toStandardDTO_WithoutParentID() {
        //arrange
        String designation = "Food";
        String id = UUID.randomUUID().toString();
        List<GroupThreeCategoryDTO> list = new ArrayList<>();
        GroupThreeCategoryDTO category = new GroupThreeCategoryDTO();
        category.setCategoryID(id);
        category.setCategoryName(designation);
        list.add(category);
        List<StandardCategoryDTO> expected = new ArrayList<>();
        StandardCategoryDTO expectedDto = new StandardCategoryDTO(designation, Constants.URL_CATEGORIES_GROUP_III + id, null);
        expected.add(expectedDto);
        //act
        List<StandardCategoryDTO> result = GroupThreeCategoryMapper.toStandardDTO(list);
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
        List<GroupThreeCategoryDTO> list = new ArrayList<>();
        GroupThreeCategoryDTO category = new GroupThreeCategoryDTO();
        category.setCategoryID(id);
        category.setCategoryName(designation);
        category.setParentID(parentID);
        list.add(category);
        List<StandardCategoryDTO> expected = new ArrayList<>();
        StandardCategoryDTO expectedDto = new StandardCategoryDTO(designation, Constants.URL_CATEGORIES_GROUP_III + id, Constants.URL_CATEGORIES_GROUP_III + parentID);
        expected.add(expectedDto);
        //act
        List<StandardCategoryDTO> result = GroupThreeCategoryMapper.toStandardDTO(list);
        //arrange
        assertEquals(expected, result);
    }

}