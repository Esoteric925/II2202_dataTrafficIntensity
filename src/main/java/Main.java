import com.google.gson.*;
import org.json.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {

        JsonParser parser = new JsonParser();

        Object obj = parser.parse(new FileReader("/Users/Amir/IdeaProjects/II2202_dataCenterTraffic/Measurements.json"));

        JsonArray jsonArray = (JsonArray) obj;


        for (JsonElement js : jsonArray){
            System.out.println("js "  + js.getAsJsonObject().get("idPath"));
        }

    }
}
