package org.example.exercice_cuisine.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @NotNull
 private UUID id;
    @NotBlank
 private String name;
    @NotBlank
 private String description;
}
