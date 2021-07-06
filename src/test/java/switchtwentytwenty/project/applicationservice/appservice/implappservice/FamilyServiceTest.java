package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.familydata.RegistrationDate;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.dto.outdto.FamilyOutDTO;
import switchtwentytwenty.project.dto.outdto.FamilyProfileOutDTO;
import switchtwentytwenty.project.dto.outdto.SystemRelationsOutDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FamilyServiceTest {

    @InjectMocks
    FamilyService familyService;
    @Mock
    IFamilyRepository familyRepository;

    @Test
    @DisplayName("Get family profile successfully")
    void getFamilyProfileSuccessfully() throws Exception {
        //arrange
        String familyID = UUID.randomUUID().toString();
        FamilyName familyName = new FamilyName("Mendes");
        RegistrationDate registrationDate = new RegistrationDate();
        Email administratorID = new Email("admin@gmail.com");
        Family family = Mockito.mock(Family.class);
        when(familyRepository.findByID(Mockito.any())).thenReturn(family);
        when(family.getName()).thenReturn(familyName);
        when(family.getRegistrationDate()).thenReturn(registrationDate);
        when(family.getAdministratorID()).thenReturn(administratorID);
        //act
        FamilyProfileOutDTO result = familyService.getFamilyProfile(familyID);
        //assert
        assertEquals(familyName.toString(), result.getFamilyName());
        assertEquals(registrationDate.toString().split(" ")[0], result.getRegistrationDate().split(" ")[0]);
        assertEquals(administratorID.toString(), result.getAdministratorID());
    }

    @Test
    @DisplayName("Failure get family profile: Invalid Relation Type Exception")
    void failureGetFamilyProfile_InvalidRelationTypeException() throws Exception {
        //arrange
        String familyID = UUID.randomUUID().toString();
        doThrow(InvalidRelationTypeException.class).when(familyRepository).findByID(Mockito.any());
        //act - assert
        assertThrows(InvalidRelationTypeException.class, () -> familyService.getFamilyProfile(familyID));
    }

    @Test
    @DisplayName("Failure get family profile: IO Exception")
    void failureGetFamilyProfile_IOException() throws Exception {
        //arrange
        String familyID = UUID.randomUUID().toString();
        doThrow(IOException.class).when(familyRepository).findByID(Mockito.any());
        //act - assert
        assertThrows(IOException.class, () -> familyService.getFamilyProfile(familyID));
    }

    @Test
    @DisplayName("Failure get family profile: Invalid Email Exception")
    void failureGetFamilyProfile_InvalidEmailException() throws Exception {
        //arrange
        String familyID = UUID.randomUUID().toString();
        doThrow(InvalidEmailException.class).when(familyRepository).findByID(Mockito.any());
        //act - assert
        assertThrows(InvalidEmailException.class, () -> familyService.getFamilyProfile(familyID));
    }

    @Test
    @DisplayName("Failure get family profile: ElementNotFoundException")
    void failureGetFamilyProfile_ElementNotFoundException() throws Exception {
        //arrange
        String familyID = UUID.randomUUID().toString();
        doThrow(ElementNotFoundException.class).when(familyRepository).findByID(Mockito.any());
        //act - assert
        assertThrows(ElementNotFoundException.class, () -> familyService.getFamilyProfile(familyID));
    }

    @Test
    @DisplayName("Failure get family profile: Invalid Family ID")
    void failureGetFamilyProfile_InvalidFamilyID() {
        assertThrows(NullPointerException.class, () -> familyService.getFamilyProfile(null));
    }

    @Test
    @DisplayName("Get list of system relations")
    void getListOfSystemRelations() {
        //arrange
        List<String> relationsList = new ArrayList<>();
        relationsList.add(Constants.PARENT);
        relationsList.add(Constants.CHILD);
        relationsList.add(Constants.SPOUSE);
        relationsList.add(Constants.SIBLING);
        relationsList.add(Constants.UNCLE);
        relationsList.add(Constants.NEPHEW);
        relationsList.add(Constants.GRANDPARENT);
        relationsList.add(Constants.GRANDCHILD);
        relationsList.add(Constants.COUSIN);
        relationsList.add(Constants.FRIEND);
        relationsList.add(Constants.PARTNER);
        relationsList.add(Constants.NOT_DEFINED);
        SystemRelationsOutDTO expected = new SystemRelationsOutDTO(relationsList);
        //act
        SystemRelationsOutDTO result = familyService.getSystemRelations();
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get list of families")
    void getListOfFamilies() throws  InvalidEmailException {
        //arrange
        List<FamilyOutDTO> familiesDTO = new ArrayList<>();

        //Creating first family
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID,ledgerID,adminEmail,familyName);
        Family family = FamilyFactory.create(familyDTO);

        //Creating second family
        UUID secondId = UUID.randomUUID();
        FamilyID secondFamilyID = new FamilyID(secondId);
        FamilyName secondFamilyName = new FamilyName("Turing");
        Email secondAdminEmail = new Email("alan_turing@hotmail.com");
        LedgerID secondLedgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO secondFamilyDTO = new FamilyVoDTO(secondFamilyID,secondLedgerID,secondAdminEmail,secondFamilyName);
        Family secondFamily = FamilyFactory.create(secondFamilyDTO);

        //Creating third family
        UUID thirdId = UUID.randomUUID();
        FamilyID thirdFamilyID = new FamilyID(thirdId);
        FamilyName thirdFamilyName = new FamilyName("Turing");
        Email thirdAdminEmail = new Email("alan_turing@hotmail.com");
        LedgerID thirdLedgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO thirdFamilyDTO = new FamilyVoDTO(thirdFamilyID,thirdLedgerID,thirdAdminEmail,thirdFamilyName);
        Family thirdFamily = FamilyFactory.create(thirdFamilyDTO);

        //act
        familyRepository.save(family);
        familyRepository.save(secondFamily);
        familyRepository.save(thirdFamily);
        FamilyOutDTO familyOutDTO = new FamilyOutDTO(family.getName().toString(),
                family.getID().toString());
        FamilyOutDTO secondFamilyOutDTO = new FamilyOutDTO(secondFamily.getName().toString(),
                secondFamily.getID().toString());
        FamilyOutDTO thirdFamilyOutDTO = new FamilyOutDTO(thirdFamily.getName().toString(),
                thirdFamily.getID().toString());

        familiesDTO.add(familyOutDTO);
        familiesDTO.add(secondFamilyOutDTO);
        familiesDTO.add(thirdFamilyOutDTO);

        //assert
        int expected = 3;

        assertEquals(expected, familiesDTO.size());

    }

    @Test
    @DisplayName("Get list of families: no families added to the list")
    void getListOfFamilies_NullCase() throws  InvalidEmailException {
        //arrange
        List<FamilyOutDTO> familiesDTO = new ArrayList<>();

        //Creating first family
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID,ledgerID,adminEmail,familyName);
        Family family = FamilyFactory.create(familyDTO);

        //act
        familyRepository.save(family);

        //assert
        int expected = 0;

        assertEquals(expected, familiesDTO.size());

    }
}