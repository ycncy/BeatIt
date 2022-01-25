import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    public static void main(String args[]) {

        JSONParser parser = new JSONParser();

        JSONObject jsonObject;

        {
            try {
                jsonObject = (JSONObject) parser.parse(new FileReader(""));

                int name = (int) jsonObject.get("id");
                System.out.println(name);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
