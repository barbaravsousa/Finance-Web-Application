package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.dto.outdto.LedgerMovementOutDTO;
import switchtwentytwenty.project.dto.outdto.MovementOutDTO;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ILedgerService {

    /**
     * Gets all the movements from a given account and between a certain dates.
     *
     * @param personIDString  - person identifier
     * @param accountIDString - account identifier
     * @param startDateString - initial date
     * @param endDateString   - final date
     */
    List<MovementOutDTO> getListOfMovementsBetweenDates(String personIDString, String accountIDString, String startDateString, String endDateString)
            throws ParseException, InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException,
            InvalidPersonNameException, InvalidMovementTypeException, IOException, InvalidRelationTypeException, UserEmailNotFoundException;


    /**
     * Get list of all ledger movements.
     * @param personID - personID.
     * @return The list of movements.
     * @throws InvalidEmailException - if invalid email.
     * @throws InvalidDateException - if invalid date.
     * @throws ElementNotFoundException - if element not found.
     * @throws InvalidVATException - if vat invalid.
     * @throws InvalidPersonNameException - if person name invalid.
     * @throws InvalidMovementTypeException - if movement type invalid.
     * @throws ParseException - if error occurs on parse.
     * @throws AccountNotCreatedException - if account not created.
     */
    List<LedgerMovementOutDTO> getListOfPersonLedgerMovements(String personID)
            throws InvalidEmailException, InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException,
            InvalidMovementTypeException, ParseException, AccountNotCreatedException;

    /**
     * Get list of all ledger movements.
     * @param familyID - familyID.
     * @return The list of movements.
     * @throws InvalidEmailException - if invalid email.
     * @throws InvalidDateException - if invalid date.
     * @throws ElementNotFoundException - if element not found.
     * @throws InvalidVATException - if vat invalid.
     * @throws InvalidPersonNameException - if person name invalid.
     * @throws InvalidMovementTypeException - if movement type invalid.
     * @throws ParseException - if error occurs on parse.
     * @throws AccountNotCreatedException - if account not created.
     */
    List<LedgerMovementOutDTO> getListOfFamilyLedgerMovements(String familyID)
            throws ElementNotFoundException, InvalidEmailException, InvalidRelationTypeException, IOException, InvalidMovementTypeException,
            ParseException, InvalidDateException, AccountNotCreatedException, InvalidVATException, InvalidPersonNameException;
}
