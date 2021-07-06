package switchtwentytwenty.project.interfaceadaptor.repository;
/*
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.datamodel.PersonJPA;
import switchtwentytwenty.project.datamodel.assembler.PersonDomainAssembler;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.dto.todomaindto.PersonJpaToDomainDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.PersonJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {

    @InjectMocks
    PersonRepository personRepository;
    @Mock
    PersonJPARepository personJPARepository;
    @Mock
    PersonDomainAssembler assembler;


    @Test
    @DisplayName("Find by family id: empty list")
    void testFindByFamilyID_EmptyList() throws Exception {
        //arrange
        List<PersonJPA> emptyList = new ArrayList<>();
        when(personJPARepository.findAllByFamilyID(Mockito.anyString())).thenReturn(emptyList);
        //act
        PersonList personList = personRepository.findByFamilyID(new FamilyID(UUID.randomUUID()));
        int result = personList.size();
        //assert
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Find by family id: one element list")
    void testFindByFamilyID_OneElementList() throws Exception {
        //arrange
        List<PersonJPA> personJPAList = new ArrayList<>();
        personJPAList.add(Mockito.mock(PersonJPA.class));
        when(personJPARepository.findAllByFamilyID(Mockito.anyString())).thenReturn(personJPAList);
        when(assembler.toDomain(Mockito.any(PersonJPA.class))).thenReturn(Mockito.any(PersonJpaToDomainDTO.class));
        //act
        PersonList personList = personRepository.findByFamilyID(new FamilyID(UUID.randomUUID()));
        int result = personList.size();
        //assert
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Find by family id: more than one element list")
    void testFindByFamilyID_MoreThanOneElementList() throws Exception {
        //arrange
        List<PersonJPA> personJPAList = new ArrayList<>();
        personJPAList.add(Mockito.mock(PersonJPA.class));
        personJPAList.add(Mockito.mock(PersonJPA.class));
        when(personJPARepository.findAllByFamilyID(Mockito.anyString())).thenReturn(personJPAList);
        when(assembler.toDomain(Mockito.any(PersonJPA.class))).thenReturn(Mockito.any(PersonJpaToDomainDTO.class));
        //act
        PersonList personList = personRepository.findByFamilyID(new FamilyID(UUID.randomUUID()));
        int result = personList.size();
        //assert
        assertEquals(2, result);
    }
}*/

