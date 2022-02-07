package recipe;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private final String name;
    private final long id;
    private List<String> usedIngerdients = new ArrayList<>();
    private List<String> unUsedIngredients = new ArrayList<>();

    public Recipe(String name, long id, List<String> usedIngerdients, List<String> unUsedIngredients) {
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

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", id=" + id +
                ", usedIngerdients=" + usedIngerdients +
                ", unUsedIngredients=" + unUsedIngredients +
                '}';
    }
}
