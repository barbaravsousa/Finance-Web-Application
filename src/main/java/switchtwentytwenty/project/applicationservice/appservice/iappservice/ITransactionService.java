package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.dto.toservicedto.PaymentDTO;

import switchtwentytwenty.project.dto.outdto.PaymentOutDTO;
import switchtwentytwenty.project.dto.outdto.TransferOutDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Represents a Transaction Service interface
 */
public interface ITransactionService {

    /**
     * @param transferDTO - TransferDTO with the information that is necessary to make the transfer
     * @return
     * @throws InvalidEmailException        - email's error
     * @throws ParseException               - parse's error
     * @throws ElementNotFoundException     - element not found error
     * @throws InvalidVATException          - vat's error
     * @throws InvalidDateException         - date's error
     * @throws InvalidPersonNameException   - person's name error
     * @throws InstantiationException       - instantiation's error
     * @throws AccountNotCreatedException   - account's error
     * @throws DepositNotPossibleException  - deposit's error
     * @throws IOException                  - io's error
     * @throws WithdrawNotPossibleException - withdraw's error
     * @throws InvalidRelationTypeException - relation's error
     * @throws InvalidMovementTypeException - movement's error
     */
    TransferOutDTO transferBetweenCashAccounts(TransferDTO transferDTO) throws InvalidEmailException, ParseException, ElementNotFoundException, InvalidRelationTypeException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InvalidDateException, InvalidVATException, InvocationTargetException, WithdrawNotPossibleException, InvalidPersonNameException, IOException, AccountNotCreatedException, DepositNotPossibleException, InvalidMovementTypeException;

    /**
     * Add payment transaction
     *
     * @param dto - paymentDTO
     * @throws InvalidEmailException
     * @throws ElementNotFoundException
     * @throws WithdrawNotPossibleException
     * @throws InvalidMovementTypeException
     * @throws ParseException
     */
    PaymentOutDTO addPaymentTransaction(PaymentDTO dto, String user) throws InvalidEmailException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException, AccountNotCreatedException, InstantiationException, InvalidMovementTypeException, ParseException, WithdrawNotPossibleException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, UserEmailNotFoundException;
}
