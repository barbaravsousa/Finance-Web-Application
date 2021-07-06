package switchtwentytwenty.project.dto.outdto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryTreeOutDTO extends RepresentationModel<CategoryTreeOutDTO> {


    @Getter
    private String designation;
    @Getter
    private List<CategoryTreeOutDTO> children;

    /**
     * Sole Constructor.
     *
     * @param designation of the category
     */
    public CategoryTreeOutDTO(String designation) {
        this.designation = designation;
        this.children = new ArrayList<>();
    }

    /**
     * Add category tree to list of category trees.
     *
     * @param tree of categories
     */
    public void addChildTree(CategoryTreeOutDTO tree) {
        this.children.add(tree);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryTreeOutDTO that = (CategoryTreeOutDTO) o;
        return Objects.equals(designation, that.designation) && Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation, children);
    }

    @Override
    public String toString() {
        return designation + children;
    }
}
