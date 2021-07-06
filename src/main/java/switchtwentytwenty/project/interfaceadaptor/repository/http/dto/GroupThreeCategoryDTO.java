package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
public class GroupThreeCategoryDTO implements Serializable {


    //Attributes

    private String categoryName;
    private String categoryID;
    private String parentID;

    //Business methods

    /**
     * Get category's designation
     *
     * @return string
     */
    public String getDesignation() {
        return this.categoryName;
    }

    /**
     * Get parent category identifier
     *
     * @return string
     */
    public String getID() {
        return categoryID;
    }

    /**
     * Get category's identifier
     *
     * @return string
     */
    public String getParentID() {
        return parentID;
    }
}
