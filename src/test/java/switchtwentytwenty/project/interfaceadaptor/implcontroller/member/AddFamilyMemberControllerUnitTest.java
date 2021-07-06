package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.dto.indto.PersonInDTO;
import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.exception.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddFamilyMemberControllerUnitTest {

    @InjectMocks
    AddFamilyMemberController controller;
    @Mock
    IFamilyAndMemberService service;

    @Test
    @DisplayName("Add family member successfully")
    void addFamilyMemberSuccessfully() throws Exception, BusinessErrorMessage {
        //arrange
        PersonInDTO mockPersonInDTO = Mockito.mock(PersonInDTO.class);
        PersonOutDTO mockPersonDTO = Mockito.mock(PersonOutDTO.class);

        when(service.addFamilyMember(Mockito.any(PersonDTO.class))).thenReturn(mockPersonDTO);

        //act
        ResponseEntity<Object> responseEntity = controller.addFamilyMember(mockPersonInDTO);
        boolean result = responseEntity.getStatusCode().equals(HttpStatus.CREATED);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Failure add family member: invalid person email")
    void failureAddFamilyMember_InvalidPersonEmail() throws Exception, BusinessErrorMessage {
        //arrange
        PersonInDTO mockPersonInDTO = Mockito.mock(PersonInDTO.class);

        doThrow(InvalidEmailException.class).when(service).addFamilyMember(Mockito.any(PersonDTO.class));

        //act
        ResponseEntity<Object> responseEntity = controller.addFamilyMember(mockPersonInDTO);
        boolean result = responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Failure add family member: invalid person name")
    void failureAddFamilyMember_InvalidPersonName() throws Exception, BusinessErrorMessage {
        //arrange
        PersonInDTO mockPersonInDTO = Mockito.mock(PersonInDTO.class);

        doThrow(InvalidPersonNameException.class).when(service).addFamilyMember(Mockito.any(PersonDTO.class));

        //act
        ResponseEntity<Object> responseEntity = controller.addFamilyMember(mockPersonInDTO);
        boolean result = responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Failure add family member: invalid person vat")
    void failureAddFamilyMember_InvalidPersonVAT() throws Exception, BusinessErrorMessage {
        //arrange
        PersonInDTO mockPersonInDTO = Mockito.mock(PersonInDTO.class);

        doThrow(InvalidVATException.class).when(service).addFamilyMember(Mockito.any(PersonDTO.class));

        //act
        ResponseEntity<Object> responseEntity = controller.addFamilyMember(mockPersonInDTO);
        boolean result = responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Failure add family member: invalid person birth date")
    void failureAddFamilyMember_InvalidPersonBirthDate() throws Exception, BusinessErrorMessage {
        //arrange
        PersonInDTO mockPersonInDTO = Mockito.mock(PersonInDTO.class);

        doThrow(InvalidDateException.class).when(service).addFamilyMember(Mockito.any(PersonDTO.class));

        //act
        ResponseEntity<Object> responseEntity = controller.addFamilyMember(mockPersonInDTO);
        boolean result = responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Failure add family member: person already exists")
    void failureAddFamilyMember_PersonAlreadyExists() throws Exception, BusinessErrorMessage {
        //arrange
        PersonInDTO mockPersonInDTO = Mockito.mock(PersonInDTO.class);

        doThrow(PersonAlreadyInSystemException.class).when(service).addFamilyMember(Mockito.any(PersonDTO.class));

        //act
        ResponseEntity<Object> responseEntity = controller.addFamilyMember(mockPersonInDTO);
        boolean result = responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Failure add family member: family not exists")
    void failureAddFamilyMember_FamilyNotExists() throws Exception, BusinessErrorMessage {
        //arrange
        PersonInDTO mockPersonInDTO = Mockito.mock(PersonInDTO.class);

        doThrow(ElementNotFoundException.class).when(service).addFamilyMember(Mockito.any(PersonDTO.class));

        //act
        ResponseEntity<Object> responseEntity = controller.addFamilyMember(mockPersonInDTO);
        boolean result = responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST);
        //assert
        assertTrue(result);
    }
}