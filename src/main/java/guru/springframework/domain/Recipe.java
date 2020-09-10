package guru.springframework.domain;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name="recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id") )
    private Set<Category> categories;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPreptime() {
        return preptime;
    }

    public void setPreptime(Integer preptime) {
        this.preptime = preptime;
    }

    public Integer getCooktime() {
        return cooktime;
    }

    public void setCooktime(Integer cooktime) {
        this.cooktime = cooktime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Set<Category> getCategory() {
        return categories;
    }

    public void setCategory(Set<Category> category) {
        this.categories = category;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
