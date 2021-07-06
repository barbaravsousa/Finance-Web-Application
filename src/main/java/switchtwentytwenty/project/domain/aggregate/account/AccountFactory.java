package switchtwentytwenty.project.domain.aggregate.account;

import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.dto.todomaindto.AccountJpaToDomainDTO;
import switchtwentytwenty.project.exception.AccountNotCreatedException;
import switchtwentytwenty.project.util.Util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Set;

public class AccountFactory {

    //Constructor methods

    /**
     * Sole constructor
     */
    private AccountFactory() {
    }


    //Business methods

    /**
     * Create BankAccount instance.
     *
     * @param bankAccountID - bank account identifier
     * @param designation   - account designation
     * @param accountType   - account type
     * @return Account instance
     */
    public static Account createBankAccount(AccountID bankAccountID, AccountDesignation designation, String accountType)
            throws AccountNotCreatedException {
        try {
            String classDirectory = getClassPath(accountType);
            Class<?> className = Class.forName(classDirectory);
            Constructor<?> constructor = className.getConstructor(AccountID.class, AccountDesignation.class);
            return (Account) constructor.newInstance(bankAccountID, designation);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException |
                IOException exception) {
            throw new AccountNotCreatedException("Failure to create account");
        }
    }


    public static Account createAccount(AccountJpaToDomainDTO accountJpaToDomainDTO) throws AccountNotCreatedException {
        String accountType = accountJpaToDomainDTO.getAccountType();
        AccountID accountID = accountJpaToDomainDTO.getAccountID();
        AccountDesignation designation = accountJpaToDomainDTO.getAccountDesignation();
        if (accountType.equals(Constants.CASH_ACCOUNT_TYPE)) {
            MoneyValue cashAmount = accountJpaToDomainDTO.getCashAmount();
            return createCashAccount(accountID, designation, cashAmount);
        } else {
            return createBankAccount(accountID, designation, accountType);
        }
    }

    /**
     * Create CashAccount instance.
     *
     * @param accountID          - account instance
     * @param accountDesignation - account's designation
     * @param initialAmount      - initial amount
     * @return Account instance
     * @throws AccountNotCreatedException - if account instantiation fails.
     */
    public static Account createCashAccount(AccountID accountID, AccountDesignation accountDesignation, MoneyValue initialAmount) throws AccountNotCreatedException {
        return new CashAccount(accountID, initialAmount, accountDesignation);
    }

    private static String getClassPath(String accountType) throws IOException {
        Properties properties = Util.loadConfigurationFile(Constants.ACCOUNT_TYPE_CONFIG_FILE);
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys){
            if(accountType.equals(key)){
                return properties.getProperty(key);
            }
        }
        throw new IllegalArgumentException("Invalid account type");
    }
}