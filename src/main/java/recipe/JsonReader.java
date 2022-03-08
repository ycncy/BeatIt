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
    public static List<String> instructions = new ArrayList<>();
    public static String urlImage;

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
            URL spooncular = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + getIngredients() + "&number=" + number + "&apiKey=58dc69c6e25545be891d44c1147a74e1&limitLicense=true");
            URLConnection url = spooncular.openConnection();
            JSONArray jsonArray = (JSONArray) parser.parse(new BufferedReader(new InputStreamReader(url.getInputStream())));
            for (Object value : jsonArray) {
                List<String> missedIngredients = new ArrayList<>();
                List<String> usedIngredients = new ArrayList<>();

                JSONObject jsonObject = (JSONObject) value;
                JSONArray missedIngredientsArray = (JSONArray) jsonObject.get("missedIngredients");
                JSONArray usedIngredientsArray = (JSONArray) jsonObject.get("usedIngredients");
                urlImage = (String) jsonObject.get("image");

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

    public static void instructions(int id) {
        instructions.clear();
        JSONParser parser = new JSONParser();
        try {
            URL spooncular = new URL("https://api.spoonacular.com/recipes/" + id +  "/analyzedInstructions?apiKey=58dc69c6e25545be891d44c1147a74e1&limitLicense=true");
            URLConnection url = spooncular.openConnection();
            JSONArray jsonArray = (JSONArray) parser.parse(new BufferedReader(new InputStreamReader(url.getInputStream())));
            List<String> instructionslist = new ArrayList<>();
            for (Object value : jsonArray) {

                JSONObject jsonObject = (JSONObject) value;
                JSONArray instructions = (JSONArray) jsonObject.get("steps");

                for(Object o : instructions) {
                    JSONObject array = (JSONObject) o;
                    instructionslist.add((String) array.get("step"));
                }
            }
            instructions.addAll(instructionslist);
        } catch (IOException | ParseException malformedURLException) {
            malformedURLException.printStackTrace();
        }
    }

    public static void reader() {
        String line = "";
        String splitBy = ";";
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\app\\foodapp\\view\\top-1k-ingredients.csv"));
            while((line = br.readLine()) != null) {
                String[] ingredients = line.split(splitBy);
                allIngredients.add(ingredients[0]);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static String listToString(int id) {
        instructions(id);
        String instruction = null;
        int i = 2;
        for(String step : instructions) {
            if(instruction == null) {
                instruction = "Step 1 : " + step + "\n";
            } else {
                instruction += "Step " + i + " : " + instructions.get(i - 1) + "\n";
                i++;
            }
        }
        return instruction;
    }
}

