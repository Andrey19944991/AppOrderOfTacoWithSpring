package tacos.data;

import tacos.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();//запрос для нахождения всех объектов в коллекции

    Ingredient findOne(String id);//запрос ингридиента по id

    Ingredient save(Ingredient ingredient); //сохранение объекта ингридиент

}
