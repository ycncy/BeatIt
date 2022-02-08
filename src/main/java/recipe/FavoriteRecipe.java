package recipe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteRecipe {

    public static List<Recipe> favoriteRecipe=new ArrayList<>();

    public static List<Recipe> getRecipes() {
        List<String> missedIngredients = new ArrayList<>();
        List<String> usedIngredients = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("src/main/java/findByIngredients.json"));
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
                favoriteRecipe.add(new Recipe((String) jsonObject.get("title"), (long) jsonObject.get("id"), missedIngredients, usedIngredients));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return favoriteRecipe;
    }

    public static void addFavorite(Recipe recipe) {
        JSONObject obj = new JSONObject();
        obj.put("Name", recipe.getName());
        obj.put("id", recipe.getId());


        JSONArray usedIngredient = new JSONArray();
        JSONArray unUsedIngredient = new JSONArray();
        usedIngredient.addAll(recipe.getUsedIngerdients());
        unUsedIngredient.addAll(recipe.getUnUsedIngredients());
        obj.put("UsedIngredient", usedIngredient);
        obj.put("UnUsedIngredient", unUsedIngredient);



        JSONParser jsonParser = new JSONParser();


        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("C:\\Users\\sanchez\\IdeaProjects\\food-app-groupe-z\\src\\main\\resources\\Favorite.json"));
            jsonArray.add(obj);


            FileWriter file = new FileWriter("C:\\Users\\sanchez\\IdeaProjects\\food-app-groupe-z\\src\\main\\resources\\Favorite.json");
            file.append(jsonArray.toJSONString());
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



}