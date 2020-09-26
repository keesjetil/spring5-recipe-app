package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;


    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading bootstrap data");
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        Recipe firstRecipe = new Recipe();
        Recipe secondRecipe = new Recipe();

        UnitOfMeasure eachUom = unitOfMeasureRepository.findByDescription("Each").get();
        UnitOfMeasure teaspoonUom = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tablespoonUom = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure cupUom = unitOfMeasureRepository.findByDescription("Cup").get();
        UnitOfMeasure pinchUom = unitOfMeasureRepository.findByDescription("Pinch").get();
        UnitOfMeasure ounceUom = unitOfMeasureRepository.findByDescription("Ounce").get();
        UnitOfMeasure dashUom = unitOfMeasureRepository.findByDescription("Dash").get();
        UnitOfMeasure pintUom = unitOfMeasureRepository.findByDescription("Pint").get();

        Category americanCategory = categoryRepository.findByDescription("American").get();
        Category italianCategory = categoryRepository.findByDescription("American").get();
        Category mexicanCategory = categoryRepository.findByDescription("American").get();
        Category fastfoodCategory = categoryRepository.findByDescription("American").get();

        firstRecipe.getCategories().add(americanCategory);
        firstRecipe.setCooktime(2);
        firstRecipe.setPreptime(2);
        firstRecipe.setDescription("American Pie");
        firstRecipe.setDifficulty(Difficulty.HARD);

        Notes americanPieNotes = new Notes();
        americanPieNotes.setRecipeNotes("Be carefull to not burn your pie");
        firstRecipe.setNotes(americanPieNotes);

        secondRecipe.getCategories().add(mexicanCategory);
        secondRecipe.setCooktime(2);
        secondRecipe.setPreptime(2);
        secondRecipe.setDescription("quacamole");
        secondRecipe.setDifficulty(Difficulty.EASY);

        Notes quacamoleNotes = new Notes();
        quacamoleNotes.setRecipeNotes("Be carefull its green");
        secondRecipe.setNotes(quacamoleNotes);

        firstRecipe.addIngredient(new Ingredient("Pie", new BigDecimal(2), eachUom));
        secondRecipe.addIngredient(new Ingredient("avocados", new BigDecimal(2), eachUom));

        recipes.add(firstRecipe);
        recipes.add(secondRecipe);

        return recipes;
    }
}
