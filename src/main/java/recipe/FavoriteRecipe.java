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

public class FavoriteRecipe {

    public static List<Recipe> favoriteRecipe = new ArrayList<>();

    public static List<Recipe> getRecipes() {
        JSONParser parser = new JSONParser();

        try {
            URL oracle = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=apples&apiKey=58dc69c6e25545be891d44c1147a74e1"); // URL to Parse
            URLConnection yc = oracle.openConnection();
            JSONArray in = (JSONArray) parser.parse(new BufferedReader(new InputStreamReader(yc.getInputStream())));

            for (Object item : in) {
                    List<String> missedIngredients = new ArrayList<>();
                    List<String> usedIngredients = new ArrayList<>();

                    JSONObject jsonObject = (JSONObject) item;
                    JSONArray missedIngredientsArray = (JSONArray) jsonObject.get("missedIngredients");
                    JSONArray usedIngredientsArray = (JSONArray) jsonObject.get("usedIngredients");
                    for (Object value : missedIngredientsArray) {
                        JSONObject missedObject = (JSONObject) value;
                        missedIngredients.add((String) missedObject.get("name"));
                    }
                    for (Object o : usedIngredientsArray) {
                        JSONObject usedObject = (JSONObject) o;
                        usedIngredients.add((String) usedObject.get("name"));
                    }
                    favoriteRecipe.add(new Recipe((String) jsonObject.get("title"), (long) jsonObject.get("id"), missedIngredients, usedIngredients));
                }
            } catch (IOException | ParseException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        return favoriteRecipe;
    }

    public static void addFavorite(Recipe recipe) {
        JSONObject obj = new JSONObject();
        obj.put("Name", recipe.getName());
        obj.put("id", recipe.getId());

        JSONArray usedIngredient = new JSONArray();
        JSONArray missedIngredient = new JSONArray();
        usedIngredient.addAll(recipe.getUsedIngerdients());
        missedIngredient.addAll(recipe.getMissedIngredients());
        obj.put("UsedIngredients", usedIngredient);
        obj.put("MissedIngredients", missedIngredient);

        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-z\\src\\main\\resources\\Favorite.json"));
            jsonArray.add(obj);


            FileWriter file = new FileWriter("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-z\\src\\main\\resources\\Favorite.json");
            file.append(jsonArray.toJSONString());
            file.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
