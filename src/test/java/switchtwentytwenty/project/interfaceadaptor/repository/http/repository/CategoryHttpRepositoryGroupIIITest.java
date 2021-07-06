package switchtwentytwenty.project.interfaceadaptor.repository.http.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.GroupThreeCategoryDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.GroupThreeCategoryDTOWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryHttpRepositoryGroupIIITest {

    @InjectMocks
    GroupThreeExternalCategoryRepository repo;

    @Mock
    RestTemplate restTemplate;

    @Test
    @DisplayName("Get Standard Categories: null response")
    void getStandardCategories_NullResponse() {
        //arrange
        when(restTemplate.getForObject(Constants.URL_CATEGORIES_GROUP_III, GroupThreeCategoryDTOWrapper.class))
                .thenReturn(null);
        //act
        List<StandardCategoryDTO> result = repo.getStandardCategories();
        //assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    @DisplayName("Get Standard Categories: empty list")
    void getStandardCategories_EmptyList() {
        //arrange
        GroupThreeCategoryDTOWrapper data = new GroupThreeCategoryDTOWrapper();
        List<GroupThreeCategoryDTO> categories = new ArrayList<>();
        data.setOutputCategoryDTOList(categories);

        when(restTemplate.getForObject(Constants.URL_CATEGORIES_GROUP_III, GroupThreeCategoryDTOWrapper.class))
                .thenReturn(data);
        //act
        List<StandardCategoryDTO> result = repo.getStandardCategories();
        //assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    @DisplayName("Get Standard Categories: one element")
    void getStandardCategories_OneElement() {
        //arrange
        GroupThreeCategoryDTOWrapper data = new GroupThreeCategoryDTOWrapper();
        List<GroupThreeCategoryDTO> externalCategories = new ArrayList<>();
        GroupThreeCategoryDTO externalCategory = new GroupThreeCategoryDTO();
        externalCategory.setCategoryID(UUID.randomUUID().toString());
        externalCategory.setCategoryName("Food");
        externalCategory.setParentID(UUID.randomUUID().toString());
        externalCategories.add(externalCategory);
        data.setOutputCategoryDTOList(externalCategories);

        StandardCategoryDTO standardCategory = new StandardCategoryDTO(externalCategory.getDesignation(),
                Constants.URL_CATEGORIES_GROUP_III + externalCategory.getID(),
                Constants.URL_CATEGORIES_GROUP_III + externalCategory.getParentID());
        List<StandardCategoryDTO> expected = new ArrayList<>();
        expected.add(standardCategory);

        when(restTemplate.getForObject(Constants.URL_CATEGORIES_GROUP_III, GroupThreeCategoryDTOWrapper.class))
                .thenReturn(data);
        //act
        List<StandardCategoryDTO> result = repo.getStandardCategories();
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Standard Categories: several elements")
    void getStandardCategories_SeveralElements() {
        //arrange
        GroupThreeCategoryDTOWrapper data = new GroupThreeCategoryDTOWrapper();
        List<GroupThreeCategoryDTO> externalCategories = new ArrayList<>();
        GroupThreeCategoryDTO externalCategory = new GroupThreeCategoryDTO();
        externalCategory.setCategoryID(UUID.randomUUID().toString());
        externalCategory.setCategoryName("Food");
        externalCategory.setParentID(UUID.randomUUID().toString());
        GroupThreeCategoryDTO externalOtherCategory = new GroupThreeCategoryDTO();
        externalOtherCategory.setCategoryID(UUID.randomUUID().toString());
        externalOtherCategory.setCategoryName("Drink");
        externalOtherCategory.setParentID(UUID.randomUUID().toString());
        externalCategories.add(externalCategory);
        externalCategories.add(externalOtherCategory);
        data.setOutputCategoryDTOList(externalCategories);

        StandardCategoryDTO standardCategory = new StandardCategoryDTO(externalCategory.getDesignation(),
                Constants.URL_CATEGORIES_GROUP_III + externalCategory.getID(),
                Constants.URL_CATEGORIES_GROUP_III + externalCategory.getParentID());
        StandardCategoryDTO otherStandardCategory = new StandardCategoryDTO(externalOtherCategory.getDesignation(),
                Constants.URL_CATEGORIES_GROUP_III + externalOtherCategory.getID(),
                Constants.URL_CATEGORIES_GROUP_III + externalOtherCategory.getParentID());
        List<StandardCategoryDTO> expected = new ArrayList<>();
        expected.add(standardCategory);
        expected.add(otherStandardCategory);

        when(restTemplate.getForObject(Constants.URL_CATEGORIES_GROUP_III, GroupThreeCategoryDTOWrapper.class))
                .thenReturn(data);
        //act
        List<StandardCategoryDTO> result = repo.getStandardCategories();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Standard Categories: fails")
    void getStandardCategories_Fails() {
        //arrange
        List<StandardCategoryDTO> expected = new ArrayList<>();

        doThrow(RestClientException.class).when(restTemplate).getForObject(Constants.URL_CATEGORIES_GROUP_III, GroupThreeCategoryDTOWrapper.class);

        //act
        List<StandardCategoryDTO> result = repo.getStandardCategories();

        //assert
        assertEquals(expected, result);
    }
}