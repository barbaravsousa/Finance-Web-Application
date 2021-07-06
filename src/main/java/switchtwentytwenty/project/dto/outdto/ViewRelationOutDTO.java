package switchtwentytwenty.project.dto.outdto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewRelationOutDTO extends RepresentationModel<ViewRelationOutDTO> {


    //Attributes
    @Getter
    private final List<String> relationList;


    //Constructor

    /**
     * Sole constructor
     *
     * @param relationList - relation list
     */
    public ViewRelationOutDTO(List<String> relationList) {
        if(relationList != null){
            this.relationList =new ArrayList<>(relationList) ;
        }
        else {
            this.relationList = new ArrayList<>();
        }

    }


    //Business Methods

    /**
     * @return size of the relation list
     */
    public int listSize() {
        return relationList.size();
    }

    /**
     * Add element do the relationList
     */
    public void add(String element) {
        this.relationList.add(element);
    }


    //Equals method
    /**
     * Override equal method
     *
     * @param o - object that we want to compare to
     * @return true if the two objects are true, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewRelationOutDTO)) return false;
        ViewRelationOutDTO that = (ViewRelationOutDTO) o;
        return Objects.equals(relationList, that.relationList);
    }

    /**
     * Override hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(relationList);
    }

    @Override
    public String toString() {
        return relationList.toString();
    }
}
