package switchtwentytwenty.project.interfaceadaptor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.datamodel.AccountJPA;
import switchtwentytwenty.project.datamodel.assembler.AccountDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.dto.todomaindto.AccountJpaToDomainDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.AccountJPARepository;

import java.util.Optional;

@Repository
public class AccountRepository implements IAccountRepository {

    @Autowired
    AccountJPARepository repository;

    @Autowired
    AccountDomainDataAssembler assembler;


    /**
     * Find Account by ID.
     * @param id -  account ID.
     * @return The account found.
     * @throws ElementNotFoundException - if no account with this id found.
     */
    @Override
    public Account findByID(AccountID id) throws ElementNotFoundException, AccountNotCreatedException{
        Optional<AccountJPA> accountJPA = this.repository.findById(id.toString());
        if (!accountJPA.isPresent()) {
            throw new ElementNotFoundException("Account not found");
        }
        AccountJpaToDomainDTO accountJpaToDomainDTO = assembler.toDomain(accountJPA.get());
        return AccountFactory.createAccount(accountJpaToDomainDTO);
    }

    /**
     * Save Account.
     * @param account - the account to save.
     */
    @Primary
    @Override
    public void save(Account account) {
        AccountJPA accountJPA = assembler.toData(account);
        this.repository.save(accountJPA);
    }

    /**
     * Exists current account by ID.
     * @param id - current account ID.
     * @return - true if current account exists.
     */
    @Override
    public boolean existsByID(AccountID id) {
        return this.repository.existsById(id.toString());
    }
}
