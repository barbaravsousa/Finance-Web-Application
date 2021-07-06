package switchtwentytwenty.project.autentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.relation.Role;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserJPATest {

    @Test
    @DisplayName("Test Getters")
    void TestGetters() {
        //arrange
        String username = "Constantino";
        String email = "constantinoisveryhappy@gmail.com";
        String password = "IWantToBecomeRich";
        String familyID = UUID.randomUUID().toString();
        UserJPA userJPA =new UserJPA(username,email,password,familyID);
        //act
        String getUsername= userJPA.getUsername();
        String getEmail = userJPA.getEmail();
        String getPassword= userJPA.getPassword();
        String getFamilyID =userJPA.getFamilyID();
        //assert
        assertEquals(username,getUsername);
        assertEquals(password,getPassword);
        assertEquals(email,getEmail);
        assertEquals(familyID,getFamilyID);
    }
}