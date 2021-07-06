package switchtwentytwenty.project.autentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SignupDTOTest {

    @Test
    @DisplayName("Create a valid SignupDTO")
    void CreateValidSignupDTO() {

        //arrange

        Long id = 122334443L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password ="IAmTheAdmin";
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        String familyID = UUID.randomUUID().toString();
        SignupDTO dto= new SignupDTO(username, email, password,familyID,roles);
        // act & assert
        //act & assert
        assertNotNull(dto);

        assertEquals(username, dto.getUsername());
        assertEquals(email, dto.getEmail());
        assertEquals(password, dto.getPassword());
        assertEquals(roles, dto.getRole());
        assertEquals(familyID, dto.getFamilyID());
    }

}