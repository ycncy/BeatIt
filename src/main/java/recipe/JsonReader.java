package recipe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    public static List<Recipe> recipes = new ArrayList<>();

    public static void setRecipes(String request, String number) {
        recipes.clear();
        JSONParser parser = new JSONParser();

        try {
            URL spooncular = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + request + "&number=" + number + "&apiKey=58dc69c6e25545be891d44c1147a74e1");
            URLConnection url = spooncular.openConnection();
            JSONArray jsonArray = (JSONArray) parser.parse(new BufferedReader(new InputStreamReader(url.getInputStream())));
            for (Object value : jsonArray) {
                List<String> missedIngredients = new ArrayList<>();
                List<String> usedIngredients = new ArrayList<>();
                JSONObject jsonObject = (JSONObject) value;
                JSONArray missedIngredientsArray = (JSONArray) jsonObject.get("missedIngredients");
                JSONArray usedIngredientsArray = (JSONArray) jsonObject.get("usedIngredients");
                for (Object o : missedIngredientsArray) {
                    JSONObject missedObject = (JSONObject) o;
                    missedIngredients.add((String) missedObject.get("name"));
                }
                for (Object o : usedIngredientsArray) {
                    JSONObject usedObject = (JSONObject) o;
                    usedIngredients.add((String) usedObject.get("name"));
                }
                recipes.add(new Recipe((String) jsonObject.get("title"), (long) jsonObject.get("id"), missedIngredients, usedIngredients));
            }

        } catch (IOException | ParseException malformedURLException) {
            malformedURLException.printStackTrace();
        }
    }

    public static Recipe getRecipeString(String name) {
        Recipe recipe = null;
        for(Recipe recipe1 : recipes) {
            if(recipe1.getName().equals(name)) {
                recipe = recipe1;
            }
        }
        return recipe;
    }

    public static Recipe getRecipeInt(int id) {
        Recipe recipe = null;
        for(Recipe recipe1 : recipes) {
            if(recipe1.getId() == id) {
                recipe = recipe1;
            }
        }
        return recipe;
    }

    public static void main(String[] args) {
        setRecipes("apple", "2");
        for (Recipe recipe: recipes) {
            FavoriteRecipe.addFavorite(getRecipeString(recipe.getName()));
            System.out.println(recipe.getUsedIngerdients());
        }
    }
}

