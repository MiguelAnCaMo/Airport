package model.Storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import model.Location;
import org.json.*;

/**
 *
 * @author migue
 */
public class JsonLocation {

    public static ArrayList<Location> readLocations(String path) throws IOException {
        String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
        JSONArray array = new JSONArray(content);

        ArrayList<Location> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);

            String id = obj.getString("airportId");
            String name = obj.getString("airportName");
            String city = obj.getString("airportCity");
            String country = obj.getString("airportCountry");
            double lat = obj.getDouble("airportLatitude");
            double lon = obj.getDouble("airportLongitude");
            //crear objeto tipo location y guardar en la lista para devolverla
            Location loc = new Location(id, name, city, country, lat, lon);
            list.add(loc);
        }
        return list;
    }

}
