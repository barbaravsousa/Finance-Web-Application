package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.FamilyService;
import switchtwentytwenty.project.dto.outdto.FamilyOutDTO;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetListOfFamiliesControllerTest {

    @InjectMocks
    GetListOfFamiliesController controller;
    @Mock
    FamilyService familyService;

    @Test
    void GetListOfFamiliesSuccessfully() throws Exception {

        String familyID = UUID.randomUUID().toString();
        String name = "Constantino";

        FamilyOutDTO dto = new FamilyOutDTO(name, familyID);
        // Mockito
        doReturn(Arrays.asList(dto)).when(familyService).getListOfFamilies();
        //act
        ResponseEntity<Object> result = controller.getListOfFamilies();

        //assert
        assertEquals(200, result.getStatusCodeValue());
    }


    @Test
    @DisplayName("Get list of families - throw NullPointerException")
    void getListOfFamiliesNullPointerException() throws InvalidRelationTypeException, IOException, InvalidEmailException {
        //arrange
        int statusCodeExpected = 422;

        String familyID = UUID.randomUUID().toString();

        //arrange mockito
        doThrow(NullPointerException.class).when(familyService).getListOfFamilies();

        //act
        ResponseEntity<Object> response = controller.getListOfFamilies();

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }
}