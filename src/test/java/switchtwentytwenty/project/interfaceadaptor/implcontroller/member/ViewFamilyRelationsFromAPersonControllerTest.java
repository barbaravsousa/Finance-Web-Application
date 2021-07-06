package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.FamilyAndMemberService;
import switchtwentytwenty.project.exception.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ViewFamilyRelationsFromAPersonControllerTest {

    @InjectMocks
    ViewFamilyRelationsFromAPersonController viewFamilyRelationsFromAPersonController;
    @Mock
    FamilyAndMemberService familyAndMemberService;

    @Test
    @DisplayName("Throw Illegal Argument Exception")
    void throwIllegalArgument() throws Exception{
        String personID = "qualquercoisa@hotmail.com";

        Mockito.doThrow(IllegalArgumentException.class).when(familyAndMemberService).getFamilyRelationByPersonID(personID);


        ResponseEntity<Object> result = viewFamilyRelationsFromAPersonController.getFamilyRelationByPersonID(personID);

        int expected = 422;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Null pointer Exception")
    void nullPointerException() throws Exception {

        String personID = "qualquercoisa@hotmail.com";

        Mockito.doThrow(NullPointerException.class).when(familyAndMemberService).getFamilyRelationByPersonID(personID);

        ResponseEntity<Object> result = viewFamilyRelationsFromAPersonController.getFamilyRelationByPersonID(personID);

        int expected = 422;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Invalid Email Exception")
    void invalidEmailException() throws Exception{

        String personID = "qualquercoisa";

        Mockito.doThrow(InvalidEmailException.class).when(familyAndMemberService).getFamilyRelationByPersonID(personID);

        ResponseEntity<Object> result = viewFamilyRelationsFromAPersonController.getFamilyRelationByPersonID(personID);

        int expected = 422;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Element Not Found Exception")
    void elementNotFoundException() throws Exception {

        String personID = "qualquercoisa@hotmail.com";

        Mockito.doThrow(ElementNotFoundException.class).when(familyAndMemberService).getFamilyRelationByPersonID(personID);

        ResponseEntity<Object> result = viewFamilyRelationsFromAPersonController.getFamilyRelationByPersonID(personID);

        int expected = 422;

        assertEquals(expected, result.getStatusCodeValue());
    }
}
