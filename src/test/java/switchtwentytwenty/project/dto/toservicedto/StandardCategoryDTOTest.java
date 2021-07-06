package switchtwentytwenty.project.dto.toservicedto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StandardCategoryDTOTest {

    @Test
    @DisplayName("Create an CategoryInDTO with no parentID")
    void createStandardCategoryDTO() {
        //arrange
        String designation = "Food";
        String id = UUID.randomUUID().toString();
        String parentId = UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id,parentId);
        // act
        //assert
        assertNotNull(dto);
    }

    @Test
    @DisplayName("Get Standard Category DTO designation ")
    void getStandardCategoryDTODesignation() {
        //arrange
        String designation = "Food";
        String id = UUID.randomUUID().toString();
        String parentId = UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id,parentId);
        // act
        String result =dto.getDesignation();
        //assert
        assertEquals(designation,result);
    }

    @Test
    @DisplayName("Get Standard Category DTO id ")
    void getStandardCategoryDTOId() {
        //arrange
        String designation = "Food";
        String id = UUID.randomUUID().toString();
        String parentId = UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id,parentId);
        // act
        String result =dto.getId();
        //assert
        assertEquals(id,result);
    }

    @Test
    @DisplayName("Get Standard Category DTO parent id ")
    void getStandardCategoryDTOParentId() {
        //arrange
        String designation = "Food";
        String id = UUID.randomUUID().toString();
        String parentId = UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id,parentId);
        // act
        String result =dto.getParentID();
        //assert
        assertEquals(parentId,result);
    }

    @Test
    @DisplayName("Test Hash Codes for similar dtos ")
    void testHashCodeForSimilarDtos() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentId =  UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id,parentId);
        StandardCategoryDTO secondDto = new StandardCategoryDTO(designation,id,parentId);
        // act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();
        //assert
        assertEquals(firstHashCode,secondHashCode);
    }


    @Test
    @DisplayName("Test Hash Codes for different DTOs - different designation")
    void testHashCodeForDifferentDesignationDtos() {
        //arrange
        String designation1= "Food";
        String designation2= "Transport";
        String id = UUID.randomUUID().toString();
        String parentId =  UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation1,id,parentId);
        StandardCategoryDTO secondDto = new StandardCategoryDTO(designation2,id,parentId);
        // act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();
        //assert
        assertNotEquals(firstHashCode,secondHashCode);
    }


    @Test
    @DisplayName("Test Hash Codes for different DTOs ")
    void testHashCodeForDTOsWithDifferentIds() {
        //arrange
        String designation= "Food";
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String parentId =  UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id1,parentId);
        StandardCategoryDTO secondDto = new StandardCategoryDTO(designation,id2,parentId);
        // act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();
        //assert
        assertNotEquals(firstHashCode,secondHashCode);
    }

    @Test
    @DisplayName("Test Hash Codes for different DTOs ")
    void testHashCodeForDTOsWithDifferentParentIds() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentId1 =  UUID.randomUUID().toString();
        String parentId2 =  UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id,parentId1);
        StandardCategoryDTO secondDto = new StandardCategoryDTO(designation,id,parentId2);
        // act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();
        //assert
        assertNotEquals(firstHashCode,secondHashCode);
    }

    @Test
    @DisplayName("Test Equals: exact same dto")
    void testEqualsForTheExactSameDTO() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id,parentID);

        // act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals: different objects ")
    void testEqualsForDifferentObjects() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id,parentID);
        BigDecimal numbers = new BigDecimal(10);
        // act
        boolean result = dto.equals(numbers);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Test Equals: different StandardCategoryDTO objects- different category ")
    void testEqualsForDifferentDesignationDto() {
        //arrange
        String designationDto1= "Food";
        String designationDto2= "transportation";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();

        StandardCategoryDTO dto = new StandardCategoryDTO(designationDto1,id,parentID);
        StandardCategoryDTO secondDto = new StandardCategoryDTO(designationDto2,id,parentID);
        // act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals: different StandardCategoryDTO objects- different id ")
    void testEqualsForDifferentIdDto() {
        //arrange
        String designation= "Food";
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();

        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id1,parentID);
        StandardCategoryDTO secondDto = new StandardCategoryDTO(designation,id2,parentID);
        // act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals: different StandardCategoryOutDTO objects- different parent ids ")
    void testEqualsForDifferentParentIdsDto() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentId1 =  UUID.randomUUID().toString();
        String parentId2 =  UUID.randomUUID().toString();
        StandardCategoryDTO dto = new StandardCategoryDTO(designation,id,parentId1);
        StandardCategoryDTO secondDto = new StandardCategoryDTO(designation,id,parentId2);
        // act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }
}