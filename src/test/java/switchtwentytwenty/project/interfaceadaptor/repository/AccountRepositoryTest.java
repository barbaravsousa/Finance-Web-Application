package switchtwentytwenty.project.interfaceadaptor.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.AccountJPARepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryTest {
    @InjectMocks
    AccountRepository accountRepository;
    @Mock
    AccountJPARepository jpaRepository;

    @Test
    void existsByIDTrue() {
        //arrange
        when(jpaRepository.existsById(Mockito.anyString())).thenReturn(true);
        AccountID accountID = new AccountID(UUID.randomUUID());
        //act
        boolean result = accountRepository.existsByID(accountID);
        //assert
        assertTrue(result);
    }

    @Test
    void existsByIDFalse() {
        //arrange
        when(jpaRepository.existsById(Mockito.anyString())).thenReturn(false);
        AccountID accountID = new AccountID(UUID.randomUUID());
        //act
        boolean result = accountRepository.existsByID(accountID);
        //assert
        assertFalse(result);
    }
}