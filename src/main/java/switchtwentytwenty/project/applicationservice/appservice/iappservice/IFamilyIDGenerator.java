package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.domain.share.id.FamilyID;

public interface IFamilyIDGenerator {

    /**
     * Generate Ledger ID
     *
     * @return ledger ID
     */
    FamilyID generate();
}
