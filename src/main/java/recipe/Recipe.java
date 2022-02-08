package recipe;

import java.util.List;

public class Recipe {

    private final String name;
    private final long id;
    private final List<String> usedIngerdients;
    private final List<String> missedIngredients;

    public Recipe(String name, long id, List<String> usedIngerdients, List<String> missedIngredients) {
        this.id = id;
        this.name = name;
        this.usedIngerdients = usedIngerdients;
        this.missedIngredients = missedIngredients;
    }

    public void addUsedIngerdients() {

    }

    public List<String> getUsedIngerdients() {
        return usedIngerdients;
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
        return name + '\n';
    }
}
