package switchtwentytwenty.project.autentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleJPATest {

    @Test
    @DisplayName("Not Equals: null")
    void notEquals_Null(){
        //arrange
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        //act
        boolean result = roleJPA.equals(null);
        //arrange
        assertFalse(result);
    }

    @Test
    @DisplayName("Not Equals: different classes")
    void notEquals_DifferentClasses(){
        //arrange
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        //act
        boolean result = roleJPA.equals("  ");
        //arrange
        assertFalse(result);
    }

    @Test
    @DisplayName("Equals: same instance")
    void equals_SameInstance(){
        //arrange
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        //act
        boolean result = roleJPA.equals(roleJPA);
        //arrange
        assertTrue(result);
    }

    @Test
    @DisplayName("Get role name")
    void getRoleName(){
        //arrange
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        //act
        ERole result = roleJPA.getName();
        //arrange
        assertEquals(ERole.ROLE_ADMIN,result);
    }

    @Test
    @DisplayName("Not Equals")
    void notEquals(){
        //arrange
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        roleJPA.setId(1);
        RoleJPA otherRoleJPA = new RoleJPA(ERole.ROLE_USER);
        otherRoleJPA.setId(1);
        //act
        boolean result = roleJPA.equals(otherRoleJPA);
        //arrange
        assertFalse(result);
    }

    @Test
    @DisplayName("Equals")
    void equals(){
        //arrange
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        roleJPA.setId(1);
        RoleJPA otherRoleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        otherRoleJPA.setId(1);
        //act
        boolean result = roleJPA.equals(otherRoleJPA);
        //arrange
        assertTrue(result);
    }

    @Test
    @DisplayName("Equals: different Ids")
    void equals_differentIds(){
        //arrange
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        roleJPA.setId(1);
        RoleJPA otherRoleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        otherRoleJPA.setId(2);
        //act
        boolean result = roleJPA.equals(otherRoleJPA);
        //arrange
        assertFalse(result);
    }

    @Test
    @DisplayName("Hash code: same")
    void hashCode_Same(){
        //arrange
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        roleJPA.setId(1);
        RoleJPA otherRoleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        otherRoleJPA.setId(1);
        //act
        int hashCode = roleJPA.hashCode();
        int otherHashCode = otherRoleJPA.hashCode();
        //arrange
        assertEquals(otherHashCode, hashCode);
    }

    @Test
    @DisplayName("Hash code: not same")
    void hashCode_NotSame(){
        //arrange
        RoleJPA roleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        roleJPA.setId(1);
        RoleJPA otherRoleJPA = new RoleJPA(ERole.ROLE_ADMIN);
        otherRoleJPA.setId(2);
        //act
        int hashCode = roleJPA.hashCode();
        int otherHashCode = otherRoleJPA.hashCode();
        //arrange
        assertNotEquals(otherHashCode, hashCode);
    }
}