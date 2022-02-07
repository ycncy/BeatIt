package recipe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class FavoriteRecipe {


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
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("C:\\Users\\nicolas\\IdeaProjects\\food-app-groupe-z\\src\\main\\resources\\Favorite.json"));
            jsonArray.add(obj);
            jsonArray.remove()


            FileWriter file = new FileWriter("C:\\Users\\nicolas\\IdeaProjects\\food-app-groupe-z\\src\\main\\resources\\Favorite.json");
            file.append(jsonArray.toJSONString());
            file.append(System.lineSeparator());
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