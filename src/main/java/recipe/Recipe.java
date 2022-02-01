package recipe;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private final String name;
    private final String id;
    private List<String> usedIngerdients = new ArrayList<>();
    private List<String> unUsedIngredients = new ArrayList<>();

    public Recipe(String name, String id, List<String> usedIngerdients, List<String> unUsedIngredients) {
        this.id = id;
        this.name = name;
        this.usedIngerdients = usedIngerdients;
        this.unUsedIngredients = unUsedIngredients;
    }

    public void addUsedIngerdients() {

    }

    public List<String> getUsedIngerdients() {
        return usedIngerdients;
    }

    public List<String> getUnUsedIngredients() {
        return unUsedIngredients;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return name + " | " + id + " | " + getUnUsedIngredients() + " | " + getUsedIngerdients();
    }
}
