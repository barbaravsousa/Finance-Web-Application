package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import switchtwentytwenty.project.Application;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
class AddBankAccountControllerIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IPersonRepository personRepository;

    MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8
    );

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("Add current account successfully")
    void testAddCurrentAccountSuccessfully() throws Exception {
        //arrange
        String designation = "Account";
        String holderID = "admin@gmail.com";
        String accountType = "current";

        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(UUID.randomUUID()),new LedgerID(UUID.randomUUID()),new Email(holderID),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        FamilyID familyID = family.getID();

        List<String> telephones = new ArrayList<>();
        telephones.add("922658453");
        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                new TelephoneNumberList(telephones),
                new Email(holderID),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(voPersonDTO);
        personRepository.save(member);

        String data = "{\n" +
                "    \"designation\": \"" + designation + "\",\n" +
                "    \"holderID\": \"" + holderID + "\",\n" +
                "    \"accountType\": \"" + accountType + "\"" +
                "}";

        //act-assert
        this.mockMvc.perform(post("/accounts/bank")
                .contentType(mediaType)
                .content(data))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Failure add current account: holder not exists")
    void testFailureAddCurrentAccount_HolderNotExists() throws Exception {
        //arrange
        String designation = "Account";
        String holderID = "admin@gmail.com";
        String accountType = "current";

        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(UUID.randomUUID()),new LedgerID(UUID.randomUUID()),new Email(holderID),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        String data = "{\n" +
                "    \"designation\": \"" + designation + "\",\n" +
                "    \"holderID\": \"" + holderID + "\",\n" +
                "    \"accountType\": \"" + accountType + "\"" +
                "}";

        //act-assert
        this.mockMvc.perform(post("/accounts/bank")
                .contentType(mediaType)
                .content(data))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Failure add current account: invalid holder id")
    void testFailureAddCurrentAccount_InvalidHolderID() throws Exception {
        //arrange
        String designation = "Account";
        String holderID = "admingmail.com";
        String accountType = "current";

        String data = "{\n" +
                "    \"designation\": \"" + designation + "\",\n" +
                "    \"holderID\": \"" + holderID + "\",\n" +
                "    \"accountType\": \"" + accountType + "\"" +
                "}";

        //act-assert
        this.mockMvc.perform(post("/accounts/bank")
                .contentType(mediaType)
                .content(data))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Failure add current account: designation already exists")
    void testFailureAddCurrentAccount_DesignationAlreadyExists()
            throws Exception {
        //arrange
        String designation = "Account";
        String holderID = "admin@gmail.com";
        String accountType = "current";

        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(UUID.randomUUID()),new LedgerID(UUID.randomUUID()),new Email(holderID),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);

        familyRepository.save(family);
        FamilyID familyID = family.getID();

        List<String> telephones = new ArrayList<>();
        telephones.add("922658453");
        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                new TelephoneNumberList(telephones),
                new Email(holderID),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(voPersonDTO);
        personRepository.save(member);

        Account currentAccount = AccountFactory.createBankAccount(new AccountID(UUID.randomUUID()), new AccountDesignation(designation), Constants.CURRENT_ACCOUNT_TYPE);
        accountRepository.save(currentAccount);
        member.addAccountID(currentAccount.getID());
        personRepository.save(member);

        String data = "{\n" +
                "    \"designation\": \"" + designation + "\",\n" +
                "    \"holderID\": \"" + holderID + "\",\n" +
                "    \"accountType\": \"" + accountType + "\"" +
                "}";

        //act-assert
        this.mockMvc.perform(post("/accounts/bank")
                .contentType(mediaType)
                .content(data))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
