package switchtwentytwenty.project.autentication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsImplTest {

    @Test
    void testEquals_true() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        UserDetailsImpl otherDetails = new UserDetailsImpl(id, username, email,familyID, roles, password,authorities);
        //act & assert
        assertEquals(userDetails, otherDetails);
    }

    @Test
    void testEquals_false() {
        //arrange
        Long id = 23556654L;
        Long otherid = 2843273857L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        UserDetailsImpl otherDetails = new UserDetailsImpl(otherid, username, email,familyID, roles, password,authorities);
        //act & assert
        assertNotEquals(userDetails, otherDetails);
    }

    @Test
    void testEquals_sameObject() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        //act
        boolean result = userDetails.equals(userDetails);
        //assert
        assertTrue(result);
    }
    @Test
    void testEquals_null() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        //act
        boolean result = userDetails.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    void testEquals_differentObject() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        //arrange designation
        Designation designation =new AccountDesignation("My account");
        //act
        boolean result = userDetails.equals(designation);
        //assert
        assertFalse(result);
    }



    @Test
    void testGetters() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);

        //act
        Long getId =userDetails.getId();
        String getEmail = userDetails.getEmail();
        String getUsername = userDetails.getUsername();
        String getPassword = userDetails.getPassword();
        String getFamilyID = userDetails.getFamilyID();
        Set<RoleJPA> getRoles = userDetails.getRoles();
        //assert
        assertEquals(id,getId);
        assertEquals(email,getEmail);
        assertEquals(familyID,getFamilyID);
        assertEquals(username,getUsername);
        assertEquals(password,getPassword);
        assertEquals(roles,getRoles);

    }

    @Test
    void verifyIfAccountIsEnable() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        //act
        boolean result = userDetails.isEnabled();
        // assert
        Assertions.assertTrue(result);
    }


    @Test
    void verifyIfAccountIsNotExpired() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        //act
        boolean result = userDetails.isAccountNonExpired();
        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void verifyIfAccountIsNotLocked() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        //act
        boolean result = userDetails.isAccountNonLocked();
        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void verifyIfCredentialsAreNotExpired() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        //act
        boolean result = userDetails.isCredentialsNonExpired();
        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void testHashCode_true() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        UserDetailsImpl userDetails2 = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        //act& assert
        assertEquals(userDetails.hashCode(),userDetails2.hashCode());
    }

    @Test
    void testHashCode_false() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        String familyID = UUID.randomUUID().toString();
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        Collection<? extends GrantedAuthority> authorities = null;
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(roleJPA);
        Long id2 = 12324232347L;
        String username2 ="raquel";
        String email2 ="raquel2@hotmail.com";
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,familyID, roles, password,authorities);
        UserDetailsImpl userDetails2 = new UserDetailsImpl(id2, username2,email2,familyID, roles, password,authorities);
        //act& assert
        assertNotEquals(userDetails.hashCode(),userDetails2.hashCode());
    }


}

