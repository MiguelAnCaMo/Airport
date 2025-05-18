package model;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.*;

/**
 *
 * @author migue
 */
public class JsonPassenger {
    
    public static ArrayList<Passenger> readPassengers(String path) throws IOException {
        String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
        JSONArray array  = new JSONArray(content);
        
        ArrayList<Passenger> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
        JSONObject obj = array.getJSONObject(i);

        long id = obj.getLong("id");
        String firstname = obj.getString("firstname");
        String lastname = obj.getString("lastname");
        LocalDate birthDate = LocalDate.parse(obj.getString("birthDate"));
        int countryPhoneCode = obj.getInt("countryPhoneCode");
        int phone = obj.getInt("phone");
        String country = obj.getString("country");
        
        
        //crear objeto tipo passenger y guardar en la lista para devolverla
        Passenger p = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
        list.add(p);
    }
        
        return list;
    }
}
