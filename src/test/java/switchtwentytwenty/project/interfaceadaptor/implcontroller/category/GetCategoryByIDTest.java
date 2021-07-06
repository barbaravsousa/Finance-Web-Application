package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.CategoryService;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class GetCategoryByIDTest {

    @Mock
    CategoryService categoryServiceMock;
    @InjectMocks
    GetCategoryByIDController getCategoryByIDController;

    @Test
    @DisplayName("Get existent category")
    void getExistentCategory() throws Exception {
        //arrange
        int statusCodeExpected = 200;
        String id = UUID.randomUUID().toString();
        String parentID = UUID.randomUUID().toString();
        CategoryOutDTO dto = new CategoryOutDTO(id, "Food",parentID);

        //arrange mock
        doReturn(dto).when(categoryServiceMock).getCategoryByID(anyString());

        //act
        ResponseEntity<Object> response = getCategoryByIDController.getCategoryByID(id);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }

    @Test
    @DisplayName("Get not existent category")
    void getNotExistentCategory() throws Exception {
        //arrange mock
        doThrow(ElementNotFoundException.class).when(categoryServiceMock).getCategoryByID(anyString());

        //act and assert
        assertThrows(ElementNotFoundException.class, () -> {
            getCategoryByIDController.getCategoryByID("1234");
        });
    }
}
