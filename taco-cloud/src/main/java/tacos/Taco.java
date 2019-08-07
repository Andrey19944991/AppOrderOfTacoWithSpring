package tacos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
@Data
public class Taco {

    private Long id;
    private Date createdAt;
    @NotNull //было
    //@NotBlank(message = "Name must be at least 5 characters long 2")
    @Size(min=5,message = "Name must be at least 5 characters long") //Чтобы имя было не меньше 5 символов
    private String name;
    //@NotBlank(message = "You must choose at least 1 ingredient2")
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
