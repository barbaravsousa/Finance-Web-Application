package switchtwentytwenty.project.dto.outdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@AllArgsConstructor
public class StandardCategoryOutDTO extends RepresentationModel<StandardCategoryOutDTO> {

    //Attributes
    @Getter
    private String designation;
    @Getter
    private String id;
    @Getter
    private String parentID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StandardCategoryOutDTO)) return false;
        StandardCategoryOutDTO that = (StandardCategoryOutDTO) o;
        return Objects.equals(designation, that.designation) && Objects.equals(id, that.id) && Objects.equals(parentID, that.parentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash( designation, id, parentID);
    }
}
