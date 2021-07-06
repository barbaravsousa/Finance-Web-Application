package switchtwentytwenty.project.dto.outdto;


import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MovementOutDTOMapper {


    //Constructor methods

    /**
     * Sole constructor.
     */
    private MovementOutDTOMapper() {}

    //Business Methods

    /**
     * Transaction list to DTO list.
     *
     * @param list      - transaction list
     * @param accountID - account identifier
     * @return a Collection of OUTMovementDTO
     */
    public static List<MovementOutDTO> toDTOList(List<Transaction> list, AccountID accountID) {
        List<MovementOutDTO> dtoList = new ArrayList<>();
        for (Transaction transaction : list) {
            MovementOutDTO dto = toDTO(transaction, accountID);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Transaction to OUTMovementDTO.
     *
     * @param transaction - a transaction
     * @param accountID   - account identifier
     * @return dto
     */
    public static MovementOutDTO toDTO(Transaction transaction, AccountID accountID) {
        return new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(transaction.getMyAmount(accountID).floatValue())
                .withAccountID(accountID.toString())
                .withMovementType(transaction.getMyMovementType(accountID).toString())
                .withDate(transaction.getDate().toString())
                .withCategory(transaction.getCategoryID().toString())
                .withBalanceToThisDate(transaction.getBalanceToThisDate().floatValue())
                .withDescription(transaction.getDescription().toString())
                .build();
    }

}
