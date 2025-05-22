package model.Storage;

import java.io.IOException;
import java.util.ArrayList;
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
    }
    
    public ArrayList <Plane> getPlanes() {
        return planes;
    }
}
