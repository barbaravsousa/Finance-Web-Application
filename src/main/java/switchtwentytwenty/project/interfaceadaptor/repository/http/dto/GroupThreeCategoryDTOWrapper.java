package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class GroupThreeCategoryDTOWrapper {

    //Attributes
    @Getter
    @Setter
    private List<GroupThreeCategoryDTO> outputCategoryDTOList;

    /**
     * Sole constructor.
     */
    public GroupThreeCategoryDTOWrapper() {
        outputCategoryDTOList = new ArrayList<>();
    }
}
