package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.Application;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.autentication.SignupDTO;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.dto.outdto.FamilyProfileOutDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.exception.BusinessErrorMessage;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class)
@ExtendWith(MockitoExtension.class)
public class GetFamilyProfileControllerIT {

    @Autowired
    GetFamilyProfileController controller;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IAuthorizationService authorizationService;
    @Mock
    HttpServletRequest request;
    @Mock
    Principal principal;

    @Test
    @DisplayName("Get Family Profile successfully")
    void getFamilyProfileSuccessfully()
            throws Exception {
        //arrange
        //create family
        String adminID = "admin@gmail.com";
        String familyName = "Costa";
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(
                UUID.randomUUID()), new LedgerID(UUID.randomUUID()), new Email(adminID), new FamilyName(familyName));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();
        String registrationDate = family.getRegistrationDate().toString();

        //create user
        String username = "admin";
        String roleJPA = "ROLE_ADMIN";
        Set<String> role = new HashSet<>();
        role.add(roleJPA);
        SignupDTO signupDTO = new SignupDTO(username, "admin@gmail.com", "admin", familyID, role);
        authorizationService.registerUser(signupDTO);

        //mock request
        Mockito.doReturn(principal).when(request).getUserPrincipal();
        Mockito.when(principal.toString()).thenReturn("Authorities=[ROLE_ADMIN]]");

        //expected dto
        FamilyProfileOutDTO expectedDTO = new FamilyProfileOutDTO(familyName, registrationDate, adminID);

        //act
        ResponseEntity<Object> responseEntity = controller.getFamilyProfile(request, familyID);
        HttpStatus status = responseEntity.getStatusCode();
        FamilyProfileOutDTO resultDTO = (FamilyProfileOutDTO) responseEntity.getBody();
        assert resultDTO != null;
        boolean isSameDate = expectedDTO.getRegistrationDate().contains(resultDTO.getRegistrationDate());

        //assert
        assertEquals(HttpStatus.OK, status);
        assertEquals(expectedDTO.getAdministratorID(), resultDTO.getAdministratorID());
        assertEquals(expectedDTO.getFamilyName(), resultDTO.getFamilyName());
        assertTrue(isSameDate);
    }

    @Test
    @DisplayName("Failure Get Family Profile: family not found")
    void failureGetFamilyProfile_FamilyNotFound() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String expectedError = Constants.FAMILY_NOT_FOUND;

        //act
        ResponseEntity<Object> responseEntity = controller.getFamilyProfile(request, familyID);
        HttpStatus status = responseEntity.getStatusCode();
        BusinessErrorMessage errorMessage = (BusinessErrorMessage) responseEntity.getBody();

        //assert
        assertEquals(HttpStatus.BAD_REQUEST, status);
        assert errorMessage != null;
        assertEquals(expectedError, errorMessage.getErrorMessage());
    }
}