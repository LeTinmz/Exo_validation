package org.example.exercice_cuisine.Category;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private final Map<UUID, Category> categories;

    public CategoryService() {
        categories = new HashMap<>();
        Category category = new Category(UUID.randomUUID(), "Dessert", "Sucré yumyum");
        Category category2 = new Category(UUID.randomUUID(), "Plat", "Salé yumyum");

        categories.put(category.getId(), category);
        categories.put(category2.getId(), category2);
    }

    public Category createCategory(Category category) {
        category.setId(UUID.randomUUID());
        categories.put(category.getId(), category);
        return category;
    }

    public Category getCategoryById(UUID id) {
        return categories.get(id);
    }

    public List<Category> getAllCategories() {
        return categories.values().stream().toList();
    }

    public Category getSingleCategoryByName(String searchedName){

    }
    public List<Category> getCategoriesByName(String searchedName) {
        if(searchedName == null || searchedName.isEmpty())
            return new ArrayList<>();

        return categories.values().stream()
                .filter(student ->
                        student.getName().toLowerCase().contains(searchedName.toLowerCase()))
                .toList();
    }


    public Category updateCategory(UUID id, Category category) {
        Category categoryToUpdate = categories.get(id);
        if(categoryToUpdate == null)
            return null;

        if(!category.getName().isBlank())
            categoryToUpdate.setName(category.getName());

        if(!category.getDescription().isBlank())
            categoryToUpdate.setDescription(category.getDescription());



        return categories.put(id, categoryToUpdate);
    }


    public void deleteCategoryById(UUID id) {
        categories.remove(id);
    }
}