package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import java.util.Arrays;
import java.util.List;

@Slf4j//от Lombok для автоматич. генерации некоего регистратора
@Controller
@RequestMapping("/design") //обработка запросов на /design
@SessionAttributes("order")//чтобы атрибут order хранился и был доступен через несколько запросов
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private TacoRepository designRepo;
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        //model.addAttribute("design", new Taco());
        return "design";
    }
    private List<Ingredient> filterByType (List < Ingredient > ingredients, Type type){
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors,@ModelAttribute Order order) { //@Valid указывает Spring MVC сделать проверку
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing design: " + design);
        Taco saved = designRepo.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }
}
