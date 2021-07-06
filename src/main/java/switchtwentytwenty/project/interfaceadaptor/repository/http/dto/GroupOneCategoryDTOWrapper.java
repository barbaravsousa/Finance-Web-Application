package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
public class GroupOneCategoryDTOWrapper {

    //Attributes
    private List<GroupOneCategoryDTO> categoryDTOs;

    /**
     * Return a shallow copy of the class's attribute.
     *
     * @return Data model
     */
    public List<GroupOneCategoryDTO> getCategoryDTOs() {
        return new ArrayList<>(categoryDTOs);
    }


}
