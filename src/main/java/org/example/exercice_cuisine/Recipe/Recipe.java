package org.example.exercice_cuisine.Recipe;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.exercice_cuisine.Category.Category;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    @NotNull
    private UUID id;
    @Min(5)
    private String name;
    @NotBlank
    private List<String> ingredients;
    @NotBlank
    private String instructions;
    @NotNull
    private Category category;
}
