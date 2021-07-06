package switchtwentytwenty.project.interfaceadaptor.option;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AuthorizationService;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.dto.outdto.OptionsOutDTO;
import switchtwentytwenty.project.exception.InvalidEmailException;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class AccountOptionsTest {

    @InjectMocks
    AccountOptions controller;
    @Mock
    AuthorizationService authorizationService;
    @Mock
    HttpServletRequest request;
    @Mock
    Principal principal;

    @Test
    @DisplayName("Get Account options: system manager")
    void getAccountOptions_SystemManager() throws Exception {
        //arrange
        //mock request
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(principal.toString()).thenReturn("");
        Mockito.when(principal.getName()).thenReturn("");

        //mock calls to authorization service
        Mockito.when(authorizationService.getRole(Mockito.anyString())).thenReturn("ROLE_SYSTEM_MANAGER");
        Mockito.when(authorizationService.getPersonIDOfUser(Mockito.anyString())).thenReturn(new Email("sm@gmail.com"));

        //expected dto
        OptionsOutDTO expectedDTO = new OptionsOutDTO();

        //act
        ResponseEntity<Object> responseEntity = controller.options(request);
        HttpStatus status = responseEntity.getStatusCode();
        OptionsOutDTO resultDTO = (OptionsOutDTO) responseEntity.getBody();

        //assert
        assertEquals(HttpStatus.OK, status);
        assertEquals(expectedDTO, resultDTO);
    }

    @Test
    @DisplayName("Get Account options: family administrator")
    void getAccountOptions_FamilyAdministrator() throws Exception {
        //arrange
        //mock request
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(principal.toString()).thenReturn("");
        Mockito.when(principal.getName()).thenReturn("");

        //mock calls to authorization service
        Mockito.when(authorizationService.getRole(Mockito.anyString())).thenReturn("ROLE_ADMIN");
        Mockito.when(authorizationService.getPersonIDOfUser(Mockito.anyString())).thenReturn(new Email("admin@gmail.com"));
        Mockito.when(authorizationService.getFamilyID(Mockito.anyString())).thenReturn("");

        //act
        ResponseEntity<Object> responseEntity = controller.options(request);
        HttpStatus status = responseEntity.getStatusCode();
        OptionsOutDTO resultDTO = (OptionsOutDTO) responseEntity.getBody();
        assert resultDTO != null;
        Optional<Link> familyCashAccountLink = resultDTO.getLink("family_cash_account");
        Optional<Link> personCashAccountLink = resultDTO.getLink("person_cash_account");
        Optional<Link> personLedgerLink = resultDTO.getLink("person_ledger");
        Optional<Link> familyLedgerLink = resultDTO.getLink("family_ledger");

        //assert
        assertEquals(HttpStatus.OK, status);
        assertTrue(familyCashAccountLink.isPresent());
        assertTrue(personCashAccountLink.isPresent());
        assertTrue(personLedgerLink.isPresent());
        assertTrue(familyLedgerLink.isPresent());
    }

    @Test
    @DisplayName("Get Account options: user")
    void getAccountOptions_User() throws Exception {
        //arrange
        //mock request
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(principal.toString()).thenReturn("");
        Mockito.when(principal.getName()).thenReturn("");

        //mock calls to authorization service
        Mockito.when(authorizationService.getRole(Mockito.anyString())).thenReturn("ROLE_USER");
        Mockito.when(authorizationService.getPersonIDOfUser(Mockito.anyString())).thenReturn(new Email("user@gmail.com"));

        //act
        ResponseEntity<Object> responseEntity = controller.options(request);
        HttpStatus status = responseEntity.getStatusCode();
        OptionsOutDTO resultDTO = (OptionsOutDTO) responseEntity.getBody();
        assert resultDTO != null;
        Optional<Link> personCashAccountLink = resultDTO.getLink("person_cash_account");
        Optional<Link> personLedgerLink = resultDTO.getLink("person_ledger");

        //assert
        assertEquals(HttpStatus.OK, status);
        assertTrue(personCashAccountLink.isPresent());
        assertTrue(personLedgerLink.isPresent());
    }

    @Test
    @DisplayName("Get Account options: invalid email")
    void getAccountOptions_InvalidEmail() throws Exception {
        //arrange
        //mock request
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(principal.toString()).thenReturn("");
        Mockito.when(principal.getName()).thenReturn("");

        //mock calls to authorization service
        Mockito.when(authorizationService.getRole(Mockito.anyString())).thenReturn("ROLE_USER");
        Mockito.doThrow(InvalidEmailException.class).when(authorizationService).getPersonIDOfUser(Mockito.anyString());

        //act
        ResponseEntity<Object> responseEntity = controller.options(request);
        HttpStatus status = responseEntity.getStatusCode();

        //assert
        assertEquals(HttpStatus.BAD_REQUEST, status);
    }

}