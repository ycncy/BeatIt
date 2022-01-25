package recipe;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String name;
    private int id;
    private List<String> usedIngerdients = new ArrayList<>();
    private List<String> unUsedIngredients = new ArrayList<>();

    public Recipe(String name, int id, List<String> usedIngerdients, List<String> unUsedIngredients) {
        this.id = id;
        this.name = name;
        this.usedIngerdients = usedIngerdients;
        this.unUsedIngredients = unUsedIngredients;
    }

    public List<String> getUsedIngerdients() {
        return usedIngerdients;
    }

    public List<String> getUnUsedIngredients() {
        return unUsedIngredients;
    }


}
