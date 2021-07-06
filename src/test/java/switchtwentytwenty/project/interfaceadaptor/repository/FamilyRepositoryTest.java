package switchtwentytwenty.project.interfaceadaptor.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.datamodel.FamilyRelationJPA;
import switchtwentytwenty.project.datamodel.assembler.FamilyDomainDataAssembler;
import switchtwentytwenty.project.datamodel.assembler.FamilyRelationDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.FamilyJPARepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FamilyRepositoryTest {

    @InjectMocks
    FamilyRepository familyRepository;
    @Mock
    FamilyJPARepository familyJPARepository;
    @Mock
    FamilyDomainDataAssembler familyDomainDataAssembler;
    @Mock
    FamilyRelationDomainDataAssembler familyRelationDomainDataAssembler;

    @Test
    @DisplayName("Save family: without family relations")
    void testSave_WithoutFamilyRelations() {
        //arrange
        Family family = Mockito.mock(Family.class);
        FamilyJPA familyJPA = Mockito.mock(FamilyJPA.class);
        List<FamilyRelation> familyRelationList = new ArrayList<>();
        when(familyDomainDataAssembler.toData(Mockito.any(Family.class))).thenReturn(familyJPA);
        when(family.getFamilyRelationList()).thenReturn(familyRelationList);
        //act
        familyRepository.save(family);
        //assert
        verify(familyJPARepository).save(Mockito.any(FamilyJPA.class));
    }

    @Test
    @DisplayName("Save family: with family relations")
    void testSave_WithFamilyRelations() {
        //arrange
        Family family = Mockito.mock(Family.class);
        FamilyJPA familyJPA = Mockito.mock(FamilyJPA.class);
        List<FamilyRelation> familyRelationList = new ArrayList<>();
        familyRelationList.add(Mockito.mock(FamilyRelation.class));
        when(familyDomainDataAssembler.toData(Mockito.any(Family.class))).thenReturn(familyJPA);
        when(family.getFamilyRelationList()).thenReturn(familyRelationList);
        when(familyRelationDomainDataAssembler.toData(Mockito.any(FamilyRelation.class), Mockito.any(FamilyJPA.class)))
                .thenReturn(Mockito.any(FamilyRelationJPA.class));
        //act
        familyRepository.save(family);
        //assert
        verify(familyJPARepository).save(Mockito.any(FamilyJPA.class));
    }

    @Test
    @DisplayName("Find family by id: not found")
    void testFindByID_NotFound(){
        //arrange
        Optional<FamilyJPA> empty = Optional.empty();
        when(familyJPARepository.findById(Mockito.anyString())).thenReturn(empty);
        //act - assert
        assertThrows(ElementNotFoundException.class, () -> familyRepository.findByID(new FamilyID(UUID.randomUUID())));
    }

//    @Test
//    @DisplayName("Find family by id: found")
//    void testFindByID_Found() throws Exception {
//        //arrange
//        FamilyJpaToDomainDTO mockFamily = Mockito.mock(FamilyJpaToDomainDTO.class);
//        Optional<FamilyJPA> familyJPA = Optional.of(Mockito.mock(FamilyJPA.class));
//        when(familyJPARepository.findById(Mockito.anyString())).thenReturn(familyJPA);
//        when(familyDomainDataAssembler.toDomain(Mockito.any(FamilyJPA.class))).thenReturn(mockFamily);
//       //TODO: fix method -> mock método estático ->  when()
//        //act
//        Family result = familyRepository.findByID(new FamilyID(UUID.randomUUID()));
//        //assert
//        assertNotNull(result);
//    }

    @Test
    void existsByIDTrue() {
        //arrange
        when(familyJPARepository.existsById(Mockito.anyString())).thenReturn(true);
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        //act
        boolean result = familyRepository.existsByID(familyID);
        //assert
        assertTrue(result);
    }



}


