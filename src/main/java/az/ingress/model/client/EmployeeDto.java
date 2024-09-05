package az.ingress.model.client;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EmployeeDto {
    Long id;
    String name;
    BigDecimal salary;
}
