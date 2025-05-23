package model.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import model.Plane;
import static model.Storage.JsonPlane.readPlanes;

/**
 *
 * @author migue
 */
public class AirplaneStorage {
    private static ArrayList <Plane> planes = new ArrayList<>();

    public AirplaneStorage() throws IOException {
        planes = readPlanes("json/planes.json");
        planes.sort(Comparator.comparing(Plane::getId));
    }
        
    public ArrayList <Plane> getPlanes() {
        return planes;
    }
}
