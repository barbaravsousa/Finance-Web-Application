package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.autentication.ERole;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.outdto.FamilyCashAccountOutDTO;
import switchtwentytwenty.project.exception.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class GetFamilyCashAccountControllerTest {


    @InjectMocks
    GetFamilyCashAccountController controller;
    @Mock
    HttpServletRequest request;
    @Mock
    Principal principal;
    @Mock
    IAccountService service;
    @Mock
    IAuthorizationService authorizationService;

    @Test
    @DisplayName("Get Family Cash Account : Failure")
    void getFamilyCashAccountFailure() throws IOException, UserEmailNotFoundException, InvalidDateException, ElementNotFoundException, InvalidEmailException, InvalidPersonNameException, InvalidRelationTypeException, AccountNotCreatedException, InvalidVATException {
        //arrange

        int statusCodeExpected = 422;
        String username = "Maggy";
        String role = ERole.ROLE_ADMIN.name();

        //arrange mock
        doReturn(principal).when(request).getUserPrincipal();
        doReturn(username).when(principal).getName();
        doReturn(role).when(authorizationService).getRole(anyString());
        doThrow(InvalidEmailException.class).when(service).getFamilyCashAccount(username, role);

        //act
        ResponseEntity<Object> response = controller.getFamilyCashAccount(request);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }

    @Test
    @DisplayName("Get Family Cash Account : Success")
    void getFamilyCashAccountSuccess() throws IOException, UserEmailNotFoundException, InvalidDateException, ElementNotFoundException, InvalidEmailException, InvalidPersonNameException, InvalidRelationTypeException, AccountNotCreatedException, InvalidVATException {
        //arrange
        int statusCodeExpected = 200;
        FamilyCashAccountOutDTO familyCashAccount = new FamilyCashAccountOutDTO("My Account", "1000");
        String username = "Maggy";
        String role = ERole.ROLE_ADMIN.name();

        //arrange mock
        doReturn(principal).when(request).getUserPrincipal();
        doReturn(username).when(principal).getName();
        doReturn(role).when(authorizationService).getRole(anyString());
        doReturn(familyCashAccount).when(service).getFamilyCashAccount(username, role);

        //act
        ResponseEntity<Object> response = controller.getFamilyCashAccount(request);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }
}