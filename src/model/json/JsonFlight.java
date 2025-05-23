package model.json;

import controller.AirplaneController;
import controller.LocationController;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Flight;
import model.Location;
import model.Plane;
import org.json.*;

/**
 *
 * @author migue
 */
public class JsonFlight {
    //funcion para llenar las listas con objetos de tipo Flight
    public static ArrayList<Flight> readFlights(String path) throws IOException {

        String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
        JSONArray array = new JSONArray(content);
        ArrayList<Flight> list = new ArrayList<>();

        AirplaneController ac = new AirplaneController(); // para acceder a la lista que contiene los planes
        LocationController lc = new LocationController(); // para acceder a la lista que contiene las locations

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);

            String id = obj.getString("id");
            Plane plane = ac.getPlaneByID(obj.getString("plane"));
            Location departureLocation = lc.getLocationByID(obj.getString("departureLocation"));
            Location arrivalLocation = lc.getLocationByID(obj.getString("arrivalLocation"));
            Location scaleLocation;

            if (obj.isNull("scaleLocation")) {
                scaleLocation = null;
            } else {
                scaleLocation = lc.getLocationByID(obj.getString("scaleLocation"));
            }
            
            LocalDateTime departureDate = LocalDateTime.parse(obj.getString("departureDate"));
            int hour1 = obj.getInt("hoursDurationArrival");
            int minutes1 = obj.getInt("minutesDurationArrival");
            int hour2 = obj.getInt("hoursDurationScale");
            int minutes2 = obj.getInt("minutesDurationScale");
            //crear objeto tipo flight y guardar en la lista para despues devolverla
            if (scaleLocation == null) {
                 Flight flight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hour1, minutes1, 0, 0);
                list.add(flight);
            } else {
                Flight flight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hour1, minutes1, hour2, minutes2);
                list.add(flight);
            }

        }
        return list;
    }
}
