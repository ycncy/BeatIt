package recipe;

import java.util.List;

public class Recipe {

    private final String name;
    private final long id;
    private final List<String> usedIngredients;
    private final List<String> missedIngredients;

    public Recipe(String name, long id, List<String> missedIngredients, List<String> usedIngredients) {
        this.id = id;
        this.name = name;
        this.usedIngredients = usedIngredients;
        this.missedIngredients = missedIngredients;
    }

    public List<String> getUsedIngerdients() {
        return usedIngredients;
    }

    public List<String> getMissedIngredients() {
        return missedIngredients;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
