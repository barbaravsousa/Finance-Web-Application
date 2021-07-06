package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CustomCategoryOutDTOTest {

    @Test
    void testHashCodeForDifferentDTOs() {

        //arrange
        CustomCategoryOutDTO dto = new CustomCategoryOutDTO("Food");
        CustomCategoryOutDTO secondDto = new CustomCategoryOutDTO("Transportation");
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertNotEquals(hashCodeDto,hashCodeSecondDto);
    }
    @Test
    void testHashCodeSameDTOs() {

        //arrange
        CustomCategoryOutDTO dto = new CustomCategoryOutDTO("Food");
        CustomCategoryOutDTO secondDto = new CustomCategoryOutDTO("Food");
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertEquals(hashCodeDto,hashCodeSecondDto);
    }

    @Test
    @DisplayName("Test Equals for the different objects")
    void testEqualsDifferentObject() {
        //arrange
        CustomCategoryOutDTO dto = new CustomCategoryOutDTO("Food");
        //act
        boolean result = dto.equals(" ");
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals: null")
    void testEquals_null() {
        //arrange
        CustomCategoryOutDTO dto = new CustomCategoryOutDTO("Food");
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals: same DTO")
    void testEquals_sameDTO() {
        //arrange
        CustomCategoryOutDTO dto = new CustomCategoryOutDTO("Food");
        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals: similar DTO")
    void testEquals_similarDTO() {
        //arrange
        CustomCategoryOutDTO dto = new CustomCategoryOutDTO("Food");
        CustomCategoryOutDTO dto2 = new CustomCategoryOutDTO("Food");
        //act
        boolean result = dto.equals(dto2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals: different DTO")
    void testEquals_differentDTO() {
        //arrange
        CustomCategoryOutDTO dto = new CustomCategoryOutDTO("Food");
        CustomCategoryOutDTO dto2 = new CustomCategoryOutDTO("Drs");
        //act
        boolean result = dto.equals(dto2);
        //assert
        assertFalse(result);
    }

}