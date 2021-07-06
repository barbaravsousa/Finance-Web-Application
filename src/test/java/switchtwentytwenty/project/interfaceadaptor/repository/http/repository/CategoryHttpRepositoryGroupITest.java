package switchtwentytwenty.project.interfaceadaptor.repository.http.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.GroupOneCategoryDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.GroupOneCategoryDTOWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryHttpRepositoryGroupITest {

    @InjectMocks
    GroupOneExternalCategoryRepository repo;

    @Mock
    RestTemplate restTemplate;

    @Test
    @DisplayName("Get Standard Categories: null response")
    void getStandardCategories_NullResponse() {
        //arrange
        when(restTemplate.getForEntity(Constants.URL_CATEGORIES_GROUP_I, GroupOneCategoryDTOWrapper.class))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));
        //act
        List<StandardCategoryDTO> result = repo.getStandardCategories();
        //assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    @DisplayName("Get Standard Categories: empty list")
    void getStandardCategories_EmptyList() {
        //arrange
        GroupOneCategoryDTOWrapper data = new GroupOneCategoryDTOWrapper();
        List<GroupOneCategoryDTO> categories = new ArrayList<>();
        data.setCategoryDTOs(categories);

        when(restTemplate.getForEntity(Constants.URL_CATEGORIES_GROUP_I, GroupOneCategoryDTOWrapper.class))
                .thenReturn(new ResponseEntity<>(data, HttpStatus.OK));
        //act
        List<StandardCategoryDTO> result = repo.getStandardCategories();
        //assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    @DisplayName("Get Standard Categories: one element")
    void getStandardCategories_OneElement() {
        //arrange
        GroupOneCategoryDTOWrapper data = new GroupOneCategoryDTOWrapper();
        List<GroupOneCategoryDTO> externalCategories = new ArrayList<>();
        GroupOneCategoryDTO externalCategory = new GroupOneCategoryDTO();
        externalCategory.setId(UUID.randomUUID().toString());
        externalCategory.setName("Food");
        externalCategory.setParentId(UUID.randomUUID().toString());
        externalCategories.add(externalCategory);
        data.setCategoryDTOs(externalCategories);

        StandardCategoryDTO standardCategory = new StandardCategoryDTO(externalCategory.getDesignation(),
                Constants.URL_CATEGORIES_GROUP_I + externalCategory.getID(),
                Constants.URL_CATEGORIES_GROUP_I + externalCategory.getParentID());
        List<StandardCategoryDTO> expected = new ArrayList<>();
        expected.add(standardCategory);

        when(restTemplate.getForEntity(Constants.URL_CATEGORIES_GROUP_I, GroupOneCategoryDTOWrapper.class))
                .thenReturn(new ResponseEntity<>(data, HttpStatus.OK));
        //act
        List<StandardCategoryDTO> result = repo.getStandardCategories();
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Standard Categories: several elements")
    void getStandardCategories_SeveralElements() {
        //arrange
        GroupOneCategoryDTOWrapper data = new GroupOneCategoryDTOWrapper();
        List<GroupOneCategoryDTO> externalCategories = new ArrayList<>();
        GroupOneCategoryDTO externalCategory = new GroupOneCategoryDTO();
        externalCategory.setId(UUID.randomUUID().toString());
        externalCategory.setName("Food");
        externalCategory.setParentId(UUID.randomUUID().toString());
        GroupOneCategoryDTO externalOtherCategory = new GroupOneCategoryDTO();
        externalOtherCategory.setId(UUID.randomUUID().toString());
        externalOtherCategory.setName("Drink");
        externalOtherCategory.setParentId(UUID.randomUUID().toString());
        externalCategories.add(externalCategory);
        externalCategories.add(externalOtherCategory);
        data.setCategoryDTOs(externalCategories);

        StandardCategoryDTO standardCategory = new StandardCategoryDTO(externalCategory.getDesignation(),
                Constants.URL_CATEGORIES_GROUP_I + externalCategory.getID(),
                Constants.URL_CATEGORIES_GROUP_I + externalCategory.getParentID());
        StandardCategoryDTO otherStandardCategory = new StandardCategoryDTO(externalOtherCategory.getDesignation(),
                Constants.URL_CATEGORIES_GROUP_I + externalOtherCategory.getID(),
                Constants.URL_CATEGORIES_GROUP_I + externalOtherCategory.getParentID());
        List<StandardCategoryDTO> expected = new ArrayList<>();
        expected.add(standardCategory);
        expected.add(otherStandardCategory);

        when(restTemplate.getForEntity(Constants.URL_CATEGORIES_GROUP_I, GroupOneCategoryDTOWrapper.class))
                .thenReturn(new ResponseEntity<>(data, HttpStatus.OK));
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

        doThrow(RestClientException.class).when(restTemplate)
                .getForEntity(Constants.URL_CATEGORIES_GROUP_I, GroupOneCategoryDTOWrapper.class);

        //act
        List<StandardCategoryDTO> result = repo.getStandardCategories();

        //assert
        assertEquals(expected, result);
    }
}