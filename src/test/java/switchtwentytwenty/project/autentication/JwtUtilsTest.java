package switchtwentytwenty.project.autentication;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.authentication.AuthenticationController;

import static org.junit.jupiter.api.Assertions.*;

//FIXME: fix failure tests!
@TestPropertySource(properties = {"security.basic.enabled=false", "management.security.enabled=false"})
@SpringBootTest
@Disabled
class JwtUtilsTest {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Test
    void getUserNameFromJwtToken() {
        //arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("sm","sm");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        String token =  jwtUtils.generateJwtToken(authentication);
        String expected = "sm";
        //act
        String result = jwtUtils.getUserNameFromJwtToken(token);
        //assert
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Invalid JWT signature")
    void validateJwtToken_invalidSignature() {
        //arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("sm","sm");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        String token =  jwtUtils.generateJwtToken(authentication);
        String invalidToken = token + "jfeh42u304ujdf";
        //act & assert
        assertFalse(jwtUtils.validateJwtToken(invalidToken));
    }

    @Test
    @DisplayName("Valid JWT ")
    void validateJwtToken_valid() {
        //arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("sm","sm");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        String token =  jwtUtils.generateJwtToken(authentication);
        //act & assert
        assertTrue(jwtUtils.validateJwtToken(token));
    }

    @Test
    @DisplayName("Invalid JWT format ")
    void validateJwtToken_invalidFormat() {
        //arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("sm","sm");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        String token =  jwtUtils.generateJwtToken(authentication);
        String invalidToken = token.replace(".","?");
        //act & assert
        assertFalse(jwtUtils.validateJwtToken(invalidToken));
    }
    @Test
    @DisplayName("Invalid JWT format ")
    void validateJwtToken_emptyJWT() {
        //arrange
        String token = "";
        //act & assert
        assertFalse(jwtUtils.validateJwtToken(token));
    }

    @Test
    @DisplayName("Invalid JWT unsupported ")
    void validateJwtToken_UnsupportedJWT() {
        //arrange
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        //act & assert
        assertFalse(jwtUtils.validateJwtToken(token));
    }

    @Test
    @DisplayName("Expired JWT ")
    void validateJwtToken_invalidExpiredJwtException() {
        //arrange
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyMzI0Mjk0MywiZXhwIjoxNjIzMzI5MzQzfQ.esk1spDLJIPKtODrBdUFbjtH2EZkwOF9wG3icg1bMRHuwWNS71TUvgD8QTbdB5lls_A";
        //act & assert
        assertFalse(jwtUtils.validateJwtToken(token));
    }


}


