package org.example.exercice_cuisine.Recipe;



import org.example.exercice_cuisine.Category.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/recipe")
@Controller

public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }



    // http://localhost:8080/register
    @GetMapping("/add")
    public String addRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        return "student/register";
    }

    @PostMapping("/add")
    public String addRecipe(@Validated Recipe recipe){
        if(recipe != null)
            recipeService.createRecipe(recipe);

        return "redirect:/list";
    }
    // http://localhost:8080/list
    @GetMapping("/list")
    public String getAllRecipes(Model model){
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "student/studentList";
    }

    // http://localhost:8080/detail/ac96bc35-0bd1-4449-8730-fcd3082432d1 (attention exemple seulement)
    @GetMapping("/detail/{id}")
    public String getRecipe(@PathVariable("id") UUID id, Model model){
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "student/studentDetail";
    }

    @GetMapping("/search")
    public String searchRecipe(@RequestParam String searchedName, Model model){
        List<Recipe> recipes = recipeService.getRecipesByName(searchedName);
        model.addAttribute("recipes", recipes);
        return "student/studentSearchList";
    }

    // http://localhost:8080/update/ac96bc35-0bd1-4449-8730-fcd3082432d1 (attention exemple seulement)
    @GetMapping("/update/{id}")
    public String updateRecipe(@PathVariable UUID id, Model model){
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "student/studentUpdate";
    }



    @PostMapping("/update")
    public String updateRecipe(@Validated Recipe recipe){
        if(recipe.getId() != null)
            recipeService.updateRecipe(recipe.getId(), recipe);

        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable UUID id){
        recipeService.deleteRecipeById(id);
        return "redirect:/list";
    }
}
