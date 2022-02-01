import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import recipe.Recipe;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonReader {

    private static List<Recipe> recipes = new ArrayList<>();

    public static void setRecipes() {
        List<String> missedIngredients = new ArrayList<>();
        List<String> usedIngredients = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("C:\\Users\\sanchez\\IdeaProjects\\food-app-groupe-z\\src\\main\\java\\findByIngredients.json"));
            for (int i=0 ;i < jsonArray.size();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                JSONArray missedIngredientsArray = (JSONArray) jsonObject.get("missedIngredients");
                JSONArray usedIngredientsArray = (JSONArray) jsonObject.get("usedIngredients");
                for (int j=0 ;j< missedIngredientsArray.size();j++){
                    JSONObject missedObject = (JSONObject) missedIngredientsArray.get(j);
                    missedIngredients.add((String) missedObject.get("name"));
                }
                for(int k = 0; k < usedIngredientsArray.size(); k++) {
                    JSONObject usedObject = (JSONObject) usedIngredientsArray.get(k);
                    usedIngredients.add((String) usedObject.get("name"));
                }
                recipes.add(new Recipe((String) jsonObject.get("title"), (String) jsonObject.get("id"), missedIngredients, usedIngredients));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static Recipe getRecipe(String name) {
        Recipe recipe = null;
        for(Recipe recipe1 : recipes) {
            if(recipe1.getName() == name) {
                recipe = recipe1;
            }
        }
        return recipe;
    }

    public static Recipe getRecipeID(String id) {
        Recipe recipe = null;
        for(Recipe recipe1 : recipes) {
            if(recipe1.getId().equals(id)) {
                recipe = recipe1;
            }
        }
        return recipe;
    }

    public static void main(String args[]) {
        setRecipes();
        System.out.println(getRecipe("Cranberry Apple Crisp"));
    }
}

