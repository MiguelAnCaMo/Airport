package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.*;
/**
 *
 * @author migue
 */
public class JsonPlane {
    
    public static ArrayList<Plane> readPlanes(String path) throws IOException {
        String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
        JSONArray array  = new JSONArray(content);
        ArrayList<Plane> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            
            String id = obj.getString("id");
            String brand = obj.getString("brand");
            String model = obj.getString("model");
            int maxCapacity = obj.getInt("maxCapacity");
            String airline = obj.getString("airline");
            //crear objeto tipo plane y guardar en la lista para devolverla
            Plane plane = new Plane(id, brand, model, maxCapacity, airline);
            list.add(plane);
        }
        
        return list;
    }
}
