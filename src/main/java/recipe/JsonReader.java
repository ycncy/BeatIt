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

    public static List<String> allIngredients = new ArrayList<>();
    public static List<String> ingredients = new ArrayList<>();
    public static List<Recipe> recipes = new ArrayList<>();

    public static String getIngredients() {
        String ingredientsInput = null;
        if(ingredients.size() == 1) {
            ingredientsInput = ingredients.get(0);
        } else {
            for(int i = 0; i < ingredients.size() - 1; i++) {
                ingredientsInput += ingredients.get(i) + ",+";
            }
            ingredientsInput += ingredients.get(ingredients.size() - 1);
        }
        return ingredientsInput;
    }

    public static void setRecipes(String number) {
        recipes.clear();
        JSONParser parser = new JSONParser();

        try {
            URL spooncular = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + getIngredients() + "&number=" + number + "&apiKey=58dc69c6e25545be891d44c1147a74e1");
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

    public static void reader() {
        String line = "";
        String splitBy = ";";
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-z\\src\\main\\resources\\app\\foodapp\\view\\top-1k-ingredients.csv"));
            while((line = br.readLine()) != null) {
                String[] ingredients = line.split(splitBy);
                allIngredients.add(ingredients[0]);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println(allIngredients);
    }

}

