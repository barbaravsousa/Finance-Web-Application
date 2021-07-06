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
import switchtwentytwenty.project.dto.outdto.CategoryTreeOutDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willThrow;

@ExtendWith(MockitoExtension.class)
public class GetStandardCategoryTreeControllerTest {

    @InjectMocks
    GetStandardCategoryTreeController controller;
    @Mock
    CategoryService service;

    @DisplayName("Rest controller - Status 200")
    @Test
    void getStandardCategoryTree() {
        //act
        List<CategoryTreeOutDTO> standardCategoryTreeOutDTO = new ArrayList<>();
        CategoryTreeOutDTO tree1 = new CategoryTreeOutDTO("Food");
        CategoryTreeOutDTO tree2 = new CategoryTreeOutDTO("Abacaxi");
        tree1.addChildTree(tree2);
        standardCategoryTreeOutDTO.add(tree1);
        Mockito.when(service.getStandardCategoriesTree()).thenReturn(standardCategoryTreeOutDTO);
        ResponseEntity<Object> standardTree = controller.getStandardCategoryTree();
        int expected = 200;

        //assert
        assertEquals(expected, standardTree.getStatusCodeValue());
    }
}
