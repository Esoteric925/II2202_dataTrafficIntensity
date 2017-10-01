import com.google.gson.*;
import net.sf.json.JSON;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws IOException {

        Map<String,Integer> pairs = new HashMap();
        JsonParser parser = new JsonParser();

        Object measurement_obj = parser.parse(new FileReader("Measurements.json"));
        Object path_obj = parser.parse(new FileReader("Paths.json"));
        Object tracedir_obj = parser.parse(new FileReader("TraceDirections.json"));


        JsonArray jsonArrayMeasurements = (JsonArray) measurement_obj;
        JsonArray jsonArrayPaths = (JsonArray) path_obj;
        JsonArray jsonArrayTraceDir = (JsonArray) tracedir_obj;


        for (JsonElement jsMeasurements : jsonArrayMeasurements){
            int idPathM = jsMeasurements.getAsJsonObject().get("idPath").getAsInt();

            for(JsonElement jsPaths : jsonArrayPaths){

                int idPathP = jsPaths.getAsJsonObject().get("idPath").getAsInt();
                if(idPathM == idPathP){
                    int idTraceDirP = jsPaths.getAsJsonObject().get("idTraceDirection").getAsInt();

                    for(JsonElement jsTraceDir : jsonArrayTraceDir){
                        int idTraceDirT = jsTraceDir.getAsJsonObject().get("idTraceDirection").getAsInt();

                        if(idTraceDirP == idTraceDirT){
                            int source = jsTraceDir.getAsJsonObject().get("idSrc").getAsInt();
                            int destination = jsTraceDir.getAsJsonObject().get("idDst").getAsInt();
                            String pair = source + "-" + destination;


                            if(pairs.containsKey(pair)){
                                pairs.replace(pair, pairs.get(pair), pairs.get(pair) + 1);
                            }else{
                                pairs.put(pair, 1);
                            }




                        }
                    }

                }
            }


        }

        for (Map.Entry<String, Integer> entry : pairs.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    }
}
