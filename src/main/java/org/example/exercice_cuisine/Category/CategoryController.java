package org.example.exercice_cuisine.Category;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/category")
@Controller

    public class CategoryController {

        private CategoryService categoryService;

        public CategoryController(CategoryService categoryService) {
            this.categoryService = categoryService;
        }



        // http://localhost:8080/register
        @GetMapping("/add")
        public String addCategory(Model model){
            model.addAttribute("category", new Category());
            return "category/recipe/categoryForm";
        }

    @PostMapping("/add")
    public String addCategory(@Validated Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category/recipe/categoryForm";
        }

        categoryService.createCategory(category);
        return "redirect:/category/list";
    }
        // http://localhost:8080/list
        @GetMapping("/list")
        public String getAllCategories(Model model){
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "category/recipe/categoryList";
        }

        // http://localhost:8080/detail/ac96bc35-0bd1-4449-8730-fcd3082432d1 (attention exemple seulement)
        @GetMapping("/detail/{id}")
        public String getCategory(@PathVariable("id") UUID id, Model model){
            Category category = categoryService.getCategoryById(id);
            model.addAttribute("category", category);
            return "category/recipe/categoryDetail";
        }

        @GetMapping("/search")
        public String searchCategory(@RequestParam String searchedName, Model model){
            List<Category> categories = categoryService.getCategoriesByName(searchedName);
            model.addAttribute("categories", categories);
            return "category/recipe/categorySearchList";
        }

        // http://localhost:8080/update/ac96bc35-0bd1-4449-8730-fcd3082432d1 (attention exemple seulement)
        @GetMapping("/update/{id}")
        public String updateCategory(@PathVariable UUID id, Model model){
            Category category = categoryService.getCategoryById(id);
            model.addAttribute("category", category);
            return "category/recipe/categoryUpdate";
        }



        @PostMapping("/update")
        public String updateCategory(@Validated Category category, BindingResult result){
            if(result.hasErrors()) {

                return "category/recipe/categoryUpdate";
            }
            if(category.getId() != null)
                categoryService.updateCategory(category.getId(), category);
            return "redirect:/category/list";
        }

        @GetMapping("/delete/{id}")
        public String deleteCategory(@PathVariable UUID id){
            categoryService.deleteCategoryById(id);
            return "redirect:/category/list";
        }
    }

