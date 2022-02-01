import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonReader {
    public static void main(String args[]) throws FileNotFoundException {
        JSONParser jsonParser = new JSONParser();
        int i = 0;
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("C:\\Users\\tata1\\Desktop\\findByIngredients.json"));
            while (jsonArray.iterator().hasNext()) {
                if(i == jsonArray.size()) {
                    return;
                }
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                System.out.println(jsonObject.get("id"));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

