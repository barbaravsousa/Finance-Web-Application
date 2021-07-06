package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.CategoryService;
import switchtwentytwenty.project.dto.outdto.StandardCategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetListOfStandardCategoriesControllerTest {

    @InjectMocks
    GetListOfStandardCategoriesController controller;
    @Mock
    CategoryService service;

    @DisplayName("Rest controller - Status 200")
    @Test
    void getListOfStandardCategories() throws ElementNotFoundException {
        //arrange
        List<StandardCategoryOutDTO> list = new ArrayList<>();
        StandardCategoryOutDTO standardCategoryOutDTO = new StandardCategoryOutDTO("Abacaxi", "iagyja09", "189747ndjkso");
        list.add(standardCategoryOutDTO);

        //act
        Mockito.when(service.getListOfStandardCategories()).thenReturn(list);
        ResponseEntity<Object> standardList = controller.getListOfStandardCategories();
        int expected = 200;

        //assert
        assertEquals(expected, standardList.getStatusCodeValue());
    }
}



