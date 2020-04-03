package libs;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    private static JSONObject jsonObject = null;

    private static JSONParser parser = new JSONParser();

    public static JSONObject getJsonObject(String filename) {
        try {
            String filePath = System.getProperty("user.dir") + "/src/main/java/resources/" + filename;
            Object obj = parser.parse(new FileReader(filePath));
            return jsonObject = (JSONObject) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}