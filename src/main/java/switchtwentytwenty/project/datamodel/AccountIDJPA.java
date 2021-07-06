package switchtwentytwenty.project.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "AccountID")
@NoArgsConstructor
@AllArgsConstructor
public class AccountIDJPA {

    //Attributes
    @Id
    @Getter
    private String id;
    @ManyToOne
    @JoinColumn(name = "personID")
    @Getter
    private PersonJPA personJPA;

    //Constructor Methods

    /**
     * Check is has same id.
     * @param otherID - id to compare.
     * @return True if both id equals.
     */
    public boolean hasSameID(String otherID){
        return id.equals(otherID);
    }

}
