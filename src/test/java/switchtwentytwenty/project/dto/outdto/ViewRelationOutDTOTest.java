package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ViewRelationOutDTOTest {

    @Test
    @DisplayName("Test getter")
    void testGetter() {
        //arrange
        List<String> relationList = new ArrayList<>();
        relationList.add("[João, Maria, Parent]");
        relationList.add("[João, Pedro, Sibling]");

        ViewRelationOutDTO relationListDTO = new ViewRelationOutDTO(relationList);

        //act
        List<String> relationListResult = relationListDTO.getRelationList();
        int expected = 2;
        int result = relationListResult.size();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to string")
    void testToString() {
        //arrange
        List<String> relationList = new ArrayList<>();
        relationList.add("[João, Maria, Parent]");
        relationList.add("[João, Pedro, Sibling]");

        ViewRelationOutDTO relationListDTO = new ViewRelationOutDTO(relationList);

        //act
        String relationListResult = relationListDTO.toString();
        String expected = "[[João, Maria, Parent], [João, Pedro, Sibling]]";

        //assert
        assertEquals(expected, relationListResult);
    }

    @Test
    @DisplayName("HashCode test")
    void testEqualsHashCode() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add("[João, Maria, Parent]");
        relationList1.add("[João, Pedro, Sibling]");

        List<String> relationList2 = new ArrayList<>();
        relationList2.add("[João, Maria, Parent]");
        relationList2.add("[João, Pedro, Sibling]");

        List<String> relationList3 = new ArrayList<>();
        relationList3.add("[João, Maria, Spouse]");
        relationList3.add("[João, Alfredo, Spouse]");

        //act
        ViewRelationOutDTO relationListDTO1 = new ViewRelationOutDTO(relationList1);
        ViewRelationOutDTO relationListDTO2 = new ViewRelationOutDTO(relationList2);
        ViewRelationOutDTO relationListDTO3 = new ViewRelationOutDTO(relationList3);

        //assert
        assertEquals(relationListDTO1, relationListDTO2);
        assertEquals(relationListDTO1.hashCode(), relationListDTO2.hashCode());
        assertNotEquals(relationListDTO1.hashCode(), relationListDTO3.hashCode());
    }

    @Test
    @DisplayName("Same ViewRelationDTO")
    void sameViewRelationDTO() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add("[João, Maria, Parent]");
        relationList1.add("[João, Pedro, Sibling]");
        List<String> relationList2 = new ArrayList<>();
        relationList2.add("[João, Maria, Parent]");
        relationList2.add("[João, Pedro, Sibling]");
        //act
        ViewRelationOutDTO viewRelationOutDTO1 = new ViewRelationOutDTO(relationList1);
        ViewRelationOutDTO viewRelationOutDTO2 = new ViewRelationOutDTO(relationList2);
        //assert
        assertEquals(viewRelationOutDTO1, viewRelationOutDTO2);
    }

    @Test
    @DisplayName("Not same ViewRelationDTO")
    void notSameViewRelationDTO() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add("[João, Maria, Parent]");
        relationList1.add("[João, Pedro, Sibling]");
        List<String> relationList2 = new ArrayList<>();
        relationList2.add("[João, Maria, Spouse]");
        relationList2.add("[João, Pedro, Sibling]");
        //act
        ViewRelationOutDTO viewRelationOutDTO1 = new ViewRelationOutDTO(relationList1);
        ViewRelationOutDTO viewRelationOutDTO2 = new ViewRelationOutDTO(relationList2);
        //assert
        assertNotEquals(viewRelationOutDTO1, viewRelationOutDTO2);
    }

    @Test
    @DisplayName("Not equal objects")
    void viewRelationDTONotEqualToCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        List<String> relationList = new ArrayList<>();
        relationList.add("[João, Maria, Parent]");
        relationList.add("[João, Pedro, Sibling]");
        ViewRelationOutDTO viewRelationOutDTO = new ViewRelationOutDTO(relationList);
        //act
        boolean result = viewRelationOutDTO.equals(category);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    void sameInstance() {
        //arrange
        List<String> relationList = new ArrayList<>();
        relationList.add("[João, Maria, Parent]");
        relationList.add("[João, Pedro, Sibling]");
        ViewRelationOutDTO viewRelationOutDTO = new ViewRelationOutDTO(relationList);
        ViewRelationOutDTO sameViewRelationOutDTO = viewRelationOutDTO;
        //assert
        assertEquals(viewRelationOutDTO, sameViewRelationOutDTO);
    }

    @Test
    void createDTOWithNull() {
        //arrange
        List<String> relationList = null;
        ViewRelationOutDTO dto = new ViewRelationOutDTO(relationList);
        //act
        int result = dto.listSize();
        //assert
        assertEquals(0, result);
    }

    @Test
    void getFamilyList() {
        //arrange
        List<String> familyRelationList = new ArrayList<>();
        familyRelationList.add("Ana is child of Adam");
        ViewRelationOutDTO dto = new ViewRelationOutDTO(familyRelationList);
        //act
        List<String> result = dto.getRelationList();
        //assert
        assertTrue(result.contains("Ana is child of Adam"));
    }
}