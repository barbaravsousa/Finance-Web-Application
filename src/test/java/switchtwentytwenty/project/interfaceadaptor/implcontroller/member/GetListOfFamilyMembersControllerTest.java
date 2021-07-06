package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.PersonService;
import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.exception.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willThrow;

@ExtendWith(MockitoExtension.class)
class GetListOfFamilyMembersControllerTest {

    @InjectMocks
    GetListOfFamilyMembersController controller;
    @Mock
    PersonService service;

    @DisplayName("Rest controller - Status 200")
    @Test
    void getListOfFamilyMembers() throws InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException {
        //arrange
        List<PersonOutDTO> users = new ArrayList<>();
        PersonOutDTO user = new PersonOutDTO("Florença", "florenca@gmail.com","2434najao");
        users.add(user);

        //act
        Mockito.when(service.getListOfFamilyMembers("2434najao")).thenReturn(users);
        ResponseEntity<Object> listUsers = controller.getListOfFamilyMembers("2434najao");
        int expected = 200;

        //assert
        assertEquals(expected, listUsers.getStatusCodeValue());
    }

    @DisplayName("Rest controller - Status 400")
    @Test
    void getListOfFamilyMembersFailure() throws InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException {
        //arrange
        String familyID = "1243-ahdiuf";
        //act
        willThrow(new InvalidEmailException("Email Not Found")).given(service).getListOfFamilyMembers(familyID);
        ResponseEntity<Object> result = controller.getListOfFamilyMembers(familyID);
        int expected = 422;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }


}