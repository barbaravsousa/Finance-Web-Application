package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class GetListOfFamilyCategoriesControllerTest {

    @Mock
    ICategoryService categoryServiceMock;
    @InjectMocks
    GetListOfFamilyCategoriesController controller;

    @Test
    @DisplayName("Get list of family categories - empty list")
    void getListOfFamilyCategoriesEmptyList() throws ElementNotFoundException {
        //arrange
        int statusCodeExpected = 204;

        String familyID = UUID.randomUUID().toString();
        //arrange mockito
        doReturn(Collections.EMPTY_LIST).when(categoryServiceMock).getListOfFamilyCategories(familyID);

        //act
        ResponseEntity<Object> response = controller.getListOfFamilyCategoriesController(familyID);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);

    }

    @Test
    @DisplayName("Get list of family categories - sucessfully")
    void getListOfFamilyCategoriesSuccessfully() throws ElementNotFoundException {
        //arrange
        int statusCodeExpected = 200;

        String familyID = UUID.randomUUID().toString();

        String id = UUID.randomUUID().toString();
        String parentID = UUID.randomUUID().toString();
        String designation = "Designation";

        CategoryOutDTO dto = new CategoryOutDTO(id, designation,parentID);

        //arrange mockito
        doReturn(Arrays.asList(dto)).when(categoryServiceMock).getListOfFamilyCategories(familyID);

        //act
        ResponseEntity<Object> response = controller.getListOfFamilyCategoriesController(familyID);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }

    @Test
    @DisplayName("Get list of family categories - throw NullPointerException")
    void getListOfFamilyCategoriesNullPointerException() throws ElementNotFoundException {
        //arrange
        int statusCodeExpected = 422;

        String familyID = UUID.randomUUID().toString();

        //arrange mockito
        doThrow(NullPointerException.class).when(categoryServiceMock).getListOfFamilyCategories(familyID);

        //act
        ResponseEntity<Object> response = controller.getListOfFamilyCategoriesController(familyID);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }
}
