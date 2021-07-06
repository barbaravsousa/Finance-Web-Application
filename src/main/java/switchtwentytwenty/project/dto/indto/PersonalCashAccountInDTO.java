package switchtwentytwenty.project.dto.indto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PersonalCashAccountInDTO {


    @Getter
    private final double initialAmount;

    @Getter
    public String designation;
}