package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.domain.share.id.CategoryID;

public interface ICategoryIDGenerator {

    /**
     * Generates a CategoryID and returns it.
     *
     * @return the generated ID
     */
    CategoryID generate();
}
