package switchtwentytwenty.project.autentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDTOTest {

    @Test
    @DisplayName("Create a valid LoginRequestDTO")
    void CreateValidLoginRequestDTO() {

            //arrange
            LoginRequestDTO dto = new LoginRequestDTO("constantino","sqeqwrssar");
            // act
            //assert
            assertNotNull(dto);
        }

    @Test
    @DisplayName("Get username")
    void getUsername() {
        //arrange
        LoginRequestDTO dto = new LoginRequestDTO("constantino","IWantToGetRich");
        String expected = "constantino";
        // act
        String result = dto.getUsername();
        //assert
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Get password")
    void getPassword() {
        //arrange
        LoginRequestDTO dto = new LoginRequestDTO("constantino","IWantToGetRich");
        String expected = "IWantToGetRich";
        // act
        String result = dto.getPassword();
        //assert
        assertEquals(expected,result);
    }
}