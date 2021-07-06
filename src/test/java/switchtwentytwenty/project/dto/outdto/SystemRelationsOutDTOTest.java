package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SystemRelationsOutDTOTest {

    @Test
    @DisplayName("Test to get System Relations List")
    void testGetter() {
        //arrange
        List<String> relationList = new ArrayList<>();
        relationList.add(Constants.PARENT);
        relationList.add(Constants.PARTNER);
        SystemRelationsOutDTO systemRelationsOutDTO = new SystemRelationsOutDTO(relationList);
        //act
        List<String> relationListResult = systemRelationsOutDTO.getSystemRelationsList();
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
        relationList.add(Constants.PARENT);
        relationList.add(Constants.PARTNER);
        SystemRelationsOutDTO relationListDTO = new SystemRelationsOutDTO(relationList);
        //act
        String relationListResult = relationListDTO.toString();
        String expected = "[parent, partner]";
        //assert
        assertEquals(expected, relationListResult);
    }

    @Test
    @DisplayName("HashCode test")
    void testEqualsHashCode() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add(Constants.PARENT);
        relationList1.add(Constants.PARTNER);

        List<String> relationList2 = new ArrayList<>();
        relationList2.add(Constants.PARENT);
        relationList2.add(Constants.PARTNER);

        List<String> relationList3 = new ArrayList<>();
        relationList3.add(Constants.NEPHEW);
        relationList3.add(Constants.PARTNER);

        //act
        SystemRelationsOutDTO relationListDTO1 = new SystemRelationsOutDTO(relationList1);
        SystemRelationsOutDTO relationListDTO2 = new SystemRelationsOutDTO(relationList2);
        SystemRelationsOutDTO relationListDTO3 = new SystemRelationsOutDTO(relationList3);

        //assert
        assertEquals(relationListDTO1, relationListDTO2);
        assertEquals(relationListDTO1.hashCode(), relationListDTO2.hashCode());
        assertNotEquals(relationListDTO1.hashCode(), relationListDTO3.hashCode());
    }

    @Test
    @DisplayName("Same SystemRelationsOutDTO")
    void sameOutSystemRelationsDTO() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add(Constants.PARENT);
        relationList1.add(Constants.PARTNER);
        List<String> relationList2 = new ArrayList<>();
        relationList2.add(Constants.PARENT);
        relationList2.add(Constants.PARTNER);
        //act
        SystemRelationsOutDTO relationListDTO1 = new SystemRelationsOutDTO(relationList1);
        SystemRelationsOutDTO relationListDTO2 = new SystemRelationsOutDTO(relationList2);
        //assert
        assertEquals(relationListDTO1, relationListDTO2);
    }

    @Test
    @DisplayName("Not same SystemRelationsOutDTO")
    void notSameOutSystemRelationsDTO() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add(Constants.PARENT);
        relationList1.add(Constants.SIBLING);
        List<String> relationList2 = new ArrayList<>();
        relationList2.add(Constants.SPOUSE);
        relationList2.add(Constants.SIBLING);
        //act
        SystemRelationsOutDTO relationListDTO1 = new SystemRelationsOutDTO(relationList1);
        SystemRelationsOutDTO relationListDTO2 = new SystemRelationsOutDTO(relationList2);
        //assert
        assertNotEquals(relationListDTO1, relationListDTO2);
    }

    @Test
    @DisplayName("Not equal objects")
    void outSystemRelationsDTONotEqualToCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        List<String> relationList = new ArrayList<>();
        relationList.add(Constants.PARENT);
        relationList.add(Constants.SIBLING);
        SystemRelationsOutDTO relationListDTO1 = new SystemRelationsOutDTO(relationList);
        //act
        boolean result = relationListDTO1.equals(category);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    void sameInstance() {
        //arrange
        List<String> relationList = new ArrayList<>();
        relationList.add(Constants.PARENT);
        relationList.add(Constants.SIBLING);
        SystemRelationsOutDTO systemRelationsOutDTO = new SystemRelationsOutDTO(relationList);
        SystemRelationsOutDTO sameSystemRelationsOutDTO = systemRelationsOutDTO;
        //assert
        assertEquals(systemRelationsOutDTO, sameSystemRelationsOutDTO);
    }

    @Test
    @DisplayName("Create DTO with null")
    void createDTOWithNull() {
        //arrange
        List<String> emptyList = new ArrayList<>();
        SystemRelationsOutDTO expected = new SystemRelationsOutDTO(emptyList);
        //act
        SystemRelationsOutDTO result = new SystemRelationsOutDTO(null);
        //assert
        assertEquals(expected, result);
    }
}