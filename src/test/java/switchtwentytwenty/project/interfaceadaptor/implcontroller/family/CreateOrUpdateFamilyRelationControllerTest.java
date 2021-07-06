package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.FamilyAndMemberService;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.indto.FamilyRelationInDTO;
import switchtwentytwenty.project.dto.outdto.FamilyRelationOutDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.family.CreateOrUpdateFamilyRelationController;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CreateOrUpdateFamilyRelationControllerTest {

    @Mock
    FamilyAndMemberService familyAndMemberServiceMock;
    @InjectMocks
    CreateOrUpdateFamilyRelationController createOrUpdateFamilyRelationController;

    @Test
    @DisplayName("Create family relation")
    void createFamilyRelation() throws Exception {
        //arrange
        int statusCodeExpected = 201;

        String personID = UUID.randomUUID().toString();
        String kinID = UUID.randomUUID().toString();
        String familyID = UUID.randomUUID().toString();
        String relation = Constants.CHILD;

        FamilyRelationOutDTO dto = new FamilyRelationOutDTO(personID, kinID, relation);

        FamilyRelationInDTO info = new FamilyRelationInDTO(personID, kinID, relation);

        //arrange mock
        doReturn(Optional.of(dto)).when(familyAndMemberServiceMock).createFamilyRelation(anyString(), anyString(), anyString(), anyString());

        //act
        ResponseEntity<Object> response = createOrUpdateFamilyRelationController.createOrUpdateFamilyRelation(familyID, info);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }

    @Test
    @DisplayName("Create family relation - with no return value")
    void createFamilyRelationWithNoReturnValue() throws Exception {
        //arrange
        int statusCodeExpected = 400;

        String personID = UUID.randomUUID().toString();
        String kinID = UUID.randomUUID().toString();
        String familyID = UUID.randomUUID().toString();
        String relation = Constants.CHILD;

        FamilyRelationInDTO info = new FamilyRelationInDTO(personID, kinID, relation);

        //arrange mock
        doReturn(Optional.empty()).when(familyAndMemberServiceMock).createFamilyRelation(anyString(), anyString(), anyString(), anyString());

        //act
        ResponseEntity<Object> response = createOrUpdateFamilyRelationController.createOrUpdateFamilyRelation(familyID, info);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }

    @Test
    @DisplayName("Create family relation - person not found")
    void createFamilyRelationPersonNotFound() throws Exception {
        //arrange
        String personID = "alan_turing@gmail.com";
        String kinID ="magaret_hamilton@hotmail.com";
        String familyID = UUID.randomUUID().toString();
        String relation = Constants.PARENT;

        FamilyRelationInDTO info = new FamilyRelationInDTO(personID, kinID, relation);

        //arrange mock
        doThrow(ElementNotFoundException.class).when(familyAndMemberServiceMock).createFamilyRelation(anyString(), anyString(), anyString(), anyString());

        //act and assert
        assertThrows(ElementNotFoundException.class, () -> {
            createOrUpdateFamilyRelationController.createOrUpdateFamilyRelation(familyID, info);
        });
    }
}
