package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.outdto.StandardCategoryOutDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllStandardCategoriesControllerTest {

    @InjectMocks
    GetAllStandardCategoriesController controller;
    @Mock
    ICategoryService service;

    @Test
    @DisplayName("Get All Standard Categories: empty")
    void getAllStandardCategories_Empty() {
        //arrange
        when(service.getListOfAllStandardCategories()).thenReturn(Collections.emptyList());
        //act
        ResponseEntity<Object> responseEntity = controller.getAllStandardCategories();
        boolean result = responseEntity.getStatusCode().equals(HttpStatus.OK);
        Object resultList = responseEntity.getBody();
        //assert
        assertTrue(result);
        assertEquals(Collections.emptyList(), resultList);
    }

    @Test
    @DisplayName("Get All Standard Categories: one category")
    void getAllStandardCategories_OneCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        String parentID = UUID.randomUUID().toString();
        String designation = "category";
        StandardCategoryOutDTO expectedDto = new StandardCategoryOutDTO(designation, id, parentID);
        List<StandardCategoryOutDTO> expected = new ArrayList<>();
        expected.add(expectedDto);
        when(service.getListOfAllStandardCategories()).thenReturn(expected);
        //act
        ResponseEntity<Object> responseEntity = controller.getAllStandardCategories();
        boolean result = responseEntity.getStatusCode().equals(HttpStatus.OK);
        Object resultList = responseEntity.getBody();
        //assert
        assertTrue(result);
        assertEquals(expected, resultList);
    }

    @Test
    @DisplayName("Get All Standard Categories: several categories")
    void getAllStandardCategories_SeveralCategories() {
        //arrange
        String id = UUID.randomUUID().toString();
        String otherId = UUID.randomUUID().toString();
        String parentID = UUID.randomUUID().toString();
        String otherParentID = UUID.randomUUID().toString();
        String designation = "category";
        String otherDesignation = "other category";
        StandardCategoryOutDTO expectedDto = new StandardCategoryOutDTO(designation, id, parentID);
        StandardCategoryOutDTO otherExpectedDto = new StandardCategoryOutDTO(otherDesignation, otherId, otherParentID);
        List<StandardCategoryOutDTO> expected = new ArrayList<>();
        expected.add(expectedDto);
        expected.add(otherExpectedDto);
        when(service.getListOfAllStandardCategories()).thenReturn(expected);
        //act
        ResponseEntity<Object> responseEntity = controller.getAllStandardCategories();
        boolean result = responseEntity.getStatusCode().equals(HttpStatus.OK);
        Object resultList = responseEntity.getBody();
        //assert
        assertTrue(result);
        assertEquals(expected, resultList);
    }
}