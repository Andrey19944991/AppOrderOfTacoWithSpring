package tacos;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import javax.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Long id;
    private Date placedAt;

    @NotBlank (message = "Name is required") //чтоб все поля были не пустые, NotBlank от Hibernate Validator
    private String name;

    @NotBlank (message = "Street is required")
    private String street;

    @NotBlank (message = "City is required")
    private String city;

    @NotBlank (message = "State is required")
    private String state;

    @NotBlank (message = "Zip code is required")
    private String zip;

    @CreditCardNumber (message = "Not a valid credit card number")//Проверяет по алгоритму Луна
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY") //регулярное выражение
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV") //должны быть 3 цифры без дробной части
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }
}
