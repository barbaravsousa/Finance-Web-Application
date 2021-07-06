package switchtwentytwenty.project.dto.toservicedto;

import switchtwentytwenty.project.dto.indto.PaymentInDTO;

public abstract class PaymentDTOMapper {

    /**
     * Private constructor.
     */
    private PaymentDTOMapper() {}

    /**
     * Map to Payment dto.
     *
     * @param info - payment dto.
     * @return The payment dto.
     */
    public static PaymentDTO mapToDTO(PaymentInDTO info, String accountID) {
        PaymentDTO newDTO = new PaymentDTO();
        newDTO.setPersonID(info.getPersonID());
        newDTO.setAccountID(accountID);
        newDTO.setAmount(info.getAmount());
        newDTO.setCategoryID(info.getCategoryID());
        newDTO.setDate(info.getDate());
        newDTO.setDesignation(info.getDesignation());
        return newDTO;
    }
}
