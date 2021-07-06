package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsOutDTOTest {


    @Test
    void testValidOptionsOutDTO() {
        //arrange
        OptionsOutDTO dto = new OptionsOutDTO();
        List<String> allow = new ArrayList<>();
        List<String> expected = new ArrayList<>();
        //act
        dto.setAllow(allow);
        List<String> result = dto.getAllow();
        //assert
        assertEquals(expected, result);
        assertNotNull(dto);
    }

    @Test
    void testCreateOptionsOutDTO() {
        //arrange
        List<String> allow = new ArrayList<>();
        OptionsOutDTO dto = new OptionsOutDTO(allow);
        List<String> expected = new ArrayList<>();
        //act
        dto.setAllow(allow);
        List<String> result = dto.getAllow();
        //assert
        assertEquals(expected, result);
        assertNotNull(dto);
    }



}