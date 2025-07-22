package org.example.exercice_cuisine.Category;

import org.example.exercice_cuisine.Recipe.Recipe;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    private final Map<UUID, Recipe> recipes;

    public RecipeService() {
        recipes = new HashMap<>();

    }

    public Recipe createRecipe(Recipe recipe) {
        recipe.setId(UUID.randomUUID());
        recipes.put(recipe.getId(), recipe);
        return recipe;
    }

    public Recipe getRecipeById(UUID id) {
        return recipes.get(id);
    }

    public List<Recipe> getAllRecipes() {
        return recipes.values().stream().toList();
    }

    public List<Recipe> getRecipesByName(String searchedName) {
        if(searchedName == null || searchedName.isEmpty())
            return new ArrayList<>();

        return recipes.values().stream()
                .filter(student ->
                        student.getName().toLowerCase().contains(searchedName.toLowerCase()))
                .toList();
    }


    public Recipe updateRecipe(UUID id, Recipe recipe) {
        Recipe recipeToUpdate = recipes.get(id);
        if(recipeToUpdate == null)
            return null;

        if(!recipe.getName().isBlank())
            recipeToUpdate.setName(recipe.getName());

        if(!recipe.getIngredients().isEmpty())
            recipeToUpdate.setIngredients(recipe.getIngredients());

        if(!recipe.getInstructions().isBlank())
            recipeToUpdate.setInstructions(recipe.getInstructions());

        return recipes.put(id, recipeToUpdate);
    }


    public void deleteRecipeById(UUID id) {
        recipes.remove(id);
    }
}