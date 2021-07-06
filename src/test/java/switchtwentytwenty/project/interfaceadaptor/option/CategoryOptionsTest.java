package switchtwentytwenty.project.interfaceadaptor.option;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AuthorizationService;
import switchtwentytwenty.project.autentication.ERole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@ExtendWith(MockitoExtension.class)
public class CategoryOptionsTest {

    @InjectMocks
    CategoryOptions categoryOptions;
    @Mock
    HttpServletRequest requestMock;
    @Mock
    HttpServletResponse responseMock;
    @Mock
    Principal principal;
    @Mock
    AuthorizationService authorizationServiceMock;

    @Test
    @DisplayName("Options for category resource - Administrator")
    void categoryOptionsAdministrator() {
        //arrange
        String principalString = "Authorities=[ROLE_ADMIN]]";
        String role = ERole.ROLE_ADMIN.name();
        int expected = 200;

        //arrange mock
        Mockito.doReturn(principal).when(requestMock).getUserPrincipal();
        Mockito.doReturn(principalString).when(principal).toString();
        Mockito.doReturn(role).when(authorizationServiceMock).getRole(principalString);

        //act
        ResponseEntity<?> responseEntity = categoryOptions.options(requestMock, responseMock);

        //assert
        Assertions.assertEquals(expected, responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Options for category resource - System manager")
    void categoryOptionsSystemManager() {
        //arrange
        String principalString = "Authorities=[ROLE_SYSTEM_MANAGER]]";
        String role = ERole.ROLE_SYSTEM_MANAGER.name();
        int expected = 200;

        //arrange mock
        Mockito.doReturn(principal).when(requestMock).getUserPrincipal();
        Mockito.doReturn(principalString).when(principal).toString();
        Mockito.doReturn(role).when(authorizationServiceMock).getRole(principalString);

        //act
        ResponseEntity<?> responseEntity = categoryOptions.options(requestMock, responseMock);

        //assert
        Assertions.assertEquals(expected, responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Options for category resource - User")
    void categoryOptionsUser() {
        //arrange
        String principalString = "Authorities=[ROLE_USER]";
        String role = ERole.ROLE_USER.name();
        int expected = 200;

        //arrange mock
        Mockito.doReturn(principal).when(requestMock).getUserPrincipal();
        Mockito.doReturn(principalString).when(principal).toString();
        Mockito.doReturn(role).when(authorizationServiceMock).getRole(principalString);

        //act
        ResponseEntity<?> responseEntity = categoryOptions.options(requestMock, responseMock);

        //assert
        Assertions.assertEquals(expected, responseEntity.getStatusCodeValue());
    }
}
