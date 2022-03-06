package recipe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteRecipe {

    public static List<Recipe> favoriteRecipe = new ArrayList<>();
    public static List<Recipe> fav = new ArrayList<>();

    public static List<Recipe> getRecipes() {
        favoriteRecipe.clear();
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json"));
            for(int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                JSONArray missedIngredientsArray = (JSONArray) jsonObject.get("MissedIngredients");
                JSONArray usedIngredientsArray = (JSONArray) jsonObject.get("UsedIngredients");
                List<String> missedIngredients = new ArrayList<>();
                List<String> usedIngredients = new ArrayList<>();
                for (Object o : missedIngredientsArray) {
                    missedIngredients.add((String) o);
                }
                for (Object o : usedIngredientsArray) {
                    usedIngredients.add((String) o);
                }
                favoriteRecipe.add(new Recipe(jsonObject.get("Name").toString(),(long) jsonObject.get("id"), usedIngredients, missedIngredients));
                fav.add(new Recipe(jsonObject.get("Name").toString(),(long) jsonObject.get("id"), usedIngredients, missedIngredients));
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
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json"));
            jsonArray.add(obj);
            favoriteRecipe.add(recipe);
            fav.add(recipe);
            FileWriter file = new FileWriter("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json");
            file.append(jsonArray.toJSONString());
            file.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void removeFavorite(Recipe recipe) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", recipe.getName());
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray finalArray = new JSONArray();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json"));
            for(int i = 0; i < jsonArray.size(); i++) {
                if(!((JSONObject) jsonArray.get(i)).get("Name").equals(recipe.getName())) {
                    finalArray.add(jsonArray.get(i));
                }
            }
            favoriteRecipe.remove(recipe);
            FileWriter file = new FileWriter("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json");
            file.append(finalArray.toJSONString());
            file.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}