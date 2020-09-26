package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer preptime;
    private Integer cooktime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Byte[] image;

    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name="recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
