package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
public class GroupOneCategoryDTO implements Serializable {

    //Attributes

    private String id;
    private String name;
    private String parentId;


    //Business methods


    /**
     * Get category's designation
     *
     * @return string
     */


    public String getDesignation() {
        return this.name;
    }


    /**
     * Get parent category identifier
     *
     * @return string
     */


    public String getID() {
        return id;
    }


    /**
     * Get category's identifier
     *
     * @return string
     */
    public String getParentID() {
        return parentId;
    }
}


