package model.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import model.Plane;
import static model.json.JsonPlane.readPlanes;

/**
 *
 * @author migue
 */
public class AirplaneStorage {
    //almacenamiento general del sistema para guardar objetos de tipo Plane
    private static ArrayList <Plane> planes = new ArrayList<>();

    public AirplaneStorage() throws IOException {
        //llenando la lista con el metodo para leer archivos json
        planes = readPlanes("json/planes.json");
        //ordenanlo la lista por ids
        planes.sort(Comparator.comparing(Plane::getId));
    }
        
    public ArrayList <Plane> getPlanes() {
        return planes;
    }
}
