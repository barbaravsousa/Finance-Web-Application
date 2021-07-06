package switchtwentytwenty.project.datamodel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class AddressIDJPA implements Serializable {

    private static final long serialVersionUID = 4996635144045869416L;

    @Column(name = "houseNumber")
    private String houseNumber;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "zipCode")
    private String zipCode;
    @Column(name = "country")
    private String country;
}
