package switchtwentytwenty.project.interfaceadaptor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.irepository.ILedgerRepository;
import switchtwentytwenty.project.datamodel.LedgerJPA;
import switchtwentytwenty.project.datamodel.PaymentJPA;
import switchtwentytwenty.project.datamodel.TransferJPA;
import switchtwentytwenty.project.datamodel.assembler.LedgerDomainDataAssembler;
import switchtwentytwenty.project.datamodel.assembler.PaymentDomainDataAssembler;
import switchtwentytwenty.project.datamodel.assembler.TransferDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.ledger.LedgerFactory;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Payment;
import switchtwentytwenty.project.domain.share.transactiondata.Transfer;
import switchtwentytwenty.project.dto.todomaindto.LedgerJpaToDomainDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.LedgerJPARepository;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Repository
public class LedgerRepository implements ILedgerRepository {

    @Autowired
    LedgerJPARepository repository;

    @Autowired
    LedgerDomainDataAssembler ledgerAssembler;

    @Autowired
    PaymentDomainDataAssembler paymentAssembler;

    @Autowired
    TransferDomainDataAssembler transferAssembler;

    /**
     * Find ledger by identifier.
     *
     * @param ledgerID - ledger identifier
     * @return ledger instance if found
     * @throws ElementNotFoundException - no ledger with this identifier was found
     * @throws InvalidMovementTypeException - illegal movement type; internal error
     * @throws ParseException - read
     */
    @Transactional
    @Override
    public Ledger findByID(LedgerID ledgerID) throws InvalidMovementTypeException, ParseException, ElementNotFoundException {
        Optional<LedgerJPA> optionalLedgerJPA = repository.findById(ledgerID.toString());

        if (optionalLedgerJPA.isPresent()) {
            LedgerJPA ledgerJPA = optionalLedgerJPA.get();

            LedgerJpaToDomainDTO ledgerJpaToDomainDTO = ledgerAssembler.toDomain(ledgerJPA);

            return LedgerFactory.create(ledgerJpaToDomainDTO);
        }
        throw new ElementNotFoundException("Ledger not found");
    }

    /**
     * Save ledger in date base.
     *
     * @param ledger - instance to be saved
     */
    @Override
    public void save(Ledger ledger) {
        LedgerJPA ledgerJPA = ledgerAssembler.toData(ledger);
        List<Payment> paymentList = ledger.getPaymentList();
        List<Transfer> transferList = ledger.getTransferList();

        for (Payment payment : paymentList) {
            PaymentJPA paymentJPA = paymentAssembler.toData(payment, ledgerJPA);
            ledgerJPA.addPayment(paymentJPA);
        }

        for (Transfer transfer : transferList) {
            TransferJPA transferJPA = transferAssembler.toData(transfer, ledgerJPA);
            ledgerJPA.addTransfer(transferJPA);
        }
        this.repository.save(ledgerJPA);
    }


    /**
     * Check if LedgerID already exists in the repository
     * @param id - ledgerID
     * @return - true if already exists
     */
    @Override
    public boolean existsByID(LedgerID id) {
        return this.repository.existsById(id.toString());
    }
}
