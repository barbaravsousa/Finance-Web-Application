package switchtwentytwenty.project.interfaceadaptor.repository.http.irepository;

import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;

import java.util.List;

public interface IExternalCategoryRepository {

    /**
     * Get a list of standard categories from external source.
     *
     * @return The list of standard categories in StandardCategoryOutDTO format.
     */
    List<StandardCategoryDTO> getStandardCategories();
}
