package switchtwentytwenty.project.interfaceadaptor.icontroller.transaction;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.TransferInDTO;

public interface ITransferBetweenMembersCashController {

    /**
     * Make transfer between a family member's cash account and another family member's cash account
     *
     * @param info - TransferInDTO
     * @return a ResponseEntity in case of a successful transfer and in case of unsuccessful transfer
     */
    ResponseEntity<Object> transferBetweenTwoCashAccounts(TransferInDTO info);

}
