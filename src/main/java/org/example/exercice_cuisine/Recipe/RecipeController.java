package org.example.exercice_cuisine.Recipe;



import org.example.exercice_cuisine.Category.Category;
import org.example.exercice_cuisine.Category.CategoryService;
import org.example.exercice_cuisine.Category.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/recipe")
@Controller

public class RecipeController {

    private RecipeService recipeService;
    private CategoryService categoryService;

    public RecipeController(RecipeService recipeService,CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }



    // http://localhost:8080/register
    @GetMapping("/add")
    public String addRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/recipe/recipeForm";
    }

    @PostMapping("/add")
    public String addRecipe(@Validated Recipe recipe, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Validation error: " + error);
            });
            return "category/recipe/recipeForm";
        }

            recipeService.createRecipe(recipe);
        return "redirect:/recipe/list";
    }
    // http://localhost:8080/list
    @GetMapping("/list")
    public String getAllRecipes(Model model){
        List<Recipe> recipes = recipeService.getAllRecipes();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("recipes", recipes);
        model.addAttribute("categories", categories);
        return "category/recipe/recipeList";
    }

    // http://localhost:8080/detail/ac96bc35-0bd1-4449-8730-fcd3082432d1 (attention exemple seulement)
    @GetMapping("/detail/{id}")
    public String getRecipe(@PathVariable("id") UUID id, Model model){
        Recipe recipe = recipeService.getRecipeById(id);
        Category category = categoryService.getCategoryById(recipe.getCategory_id());
        model.addAttribute("recipe", recipe);
        model.addAttribute("category", category);
        return "category/recipe/recipeDetail";
    }

    @GetMapping("/search")
    public String searchRecipe(@RequestParam String searchedName, Model model){
        List<Recipe> recipes = recipeService.getRecipesByName(searchedName);
        model.addAttribute("recipes", recipes);
        return "category/recipe/recipeSearchList";
    }

    // http://localhost:8080/update/ac96bc35-0bd1-4449-8730-fcd3082432d1 (attention exemple seulement)
    @GetMapping("/update/{id}")
    public String updateRecipe(@PathVariable UUID id, Model model){
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/recipe/recipeUpdate";
    }



    @PostMapping("/update")
    public String updateRecipe(@Validated Recipe recipe, BindingResult result, Model model){
        if(result.hasErrors()){ //
            model.addAttribute("categories", categoryService.getAllCategories()); //
            return "category/recipe/recipeUpdate";
        }
        if(recipe.getId() != null)
            recipeService.updateRecipe(recipe.getId(), recipe);
        return "redirect:/recipe/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable UUID id){
        recipeService.deleteRecipeById(id);
        return "redirect:/recipe/list";
    }
}
