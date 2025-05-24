package controller;

import controller.utils.Response;
import controller.utils.Status;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.Flight;
import model.Location;
import model.Passenger;
import model.Plane;
import model.Storage.FlightStorage;
import model.Storage.LocationStorage;
import model.Storage.PassengerStorage;
import view.AirportFrame;

/**
 *
 * @author migue
 */
public class FlightController {

    private static FlightStorage fs;
    private static LocationStorage ls;
    AirplaneController ac = new AirplaneController();
    LocationController lc = new LocationController();
    private static AirportFrame airportFrame;
    private static PassengerStorage ps;
    PassengerController pc = new PassengerController();

    //dos contructores, uno para la carga del .json y el otro para agregar datos con la interfaz
    public FlightController(String a, AirportFrame af) throws IOException {
        fs = new FlightStorage();
        ls = new LocationStorage();
        airportFrame = af;
        ps = new PassengerStorage();
    }

    public FlightController() {

    }

    //buscará el avión y las ubicaciones por el id para un vuelo
    public Flight createFlight(String flightID, String planeID, String departureLocationId, String arrivalLocationId, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        //mega contructor para la creacion del vuelo 
        Flight flight = new Flight(flightID, ac.getPlaneByID(flightID), lc.getLocationByID(departureLocationId), lc.getLocationByID(arrivalLocationId), departureDate, hoursDurationArrival, minutesDurationArrival);
        fs.getFlights().add(flight);
        return flight;
    }

    public static ArrayList<Flight> sendFlights() {
        return fs.getFlights();
    }

    public DefaultTableModel toFlightsJList() {
        String[] columnas = {"ID", "Departure Airport ID", "Arrival Airport ID", "Scale airport", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); //modelo para ser devuelto

        if (fs.getFlights().isEmpty()) {
            System.out.println("Lista de vuelos vacia");
        } else {
            for (Flight f : fs.getFlights()) {
                //dos caminos, uno si scaleLocation es null y otro si no es asi
                if (f.getScaleLocation() == null) {
                    Object[] fila = new Object[]{ //objeto para poner en el modelo                                                                  
                        f.getId(), f.getDepartureLocation().getAirportId(), f.getArrivalLocation().getAirportId(), "NA", f.getDepartureDate(), f.calculateArrivalDate(), f.getPlane().getId(), f.getNumPassengers()
                    };
                    model.addRow(fila);
                } else {
                    Object[] fila = new Object[]{
                        f.getId(), f.getDepartureLocation().getAirportId(), f.getArrivalLocation().getAirportId(), f.getScaleLocation().getAirportCity(), f.getDepartureDate(), f.calculateArrivalDate(), f.getPlane().getId(), f.getNumPassengers()
                    };
                    model.addRow(fila);
                }
            }
        }

        return model;
    }

    public Response addPassengerToFlight(String flightId, int passengerIdSearch) {
        boolean found = false;
        for (Passenger p : ps.getPassengers()) {
            if (p.getId() == passengerIdSearch) {
                for (Flight f : fs.getFlights()) {
                    if (f.getId().equals(flightId)) {
                        f.addPassenger(p);
                        found = true;
                        break;
                    }

                }
            }

        }
        if (found) {
            Response b = new Response("Passenger added to flight ", Status.OK);
            return b.clone();
        }
        Response b = new Response("Passenger id not found ", Status.NOT_FOUND);
        return b.clone();
    }

    //devuelve un modelo para el comboBox de flights que esta en la sección Add to flight
    public static DefaultComboBoxModel<String> getFlightModel() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Flight f : fs.getFlights()) {
            model.addElement(f.getId());
        }
        return model;
    }

    public Response delayFlight(String flightsId, int hours, int minutes) {
        for (Flight f : fs.getFlights()) {
            if (flightsId.equals(f.getId())) {
                f.delay(hours, minutes);
                Response b = new Response("flight delayed succesfully  ", Status.OK);
                return b.clone();
            }
        }
        Response base = new Response("flight not found", Status.BAD_REQUEST);
        return base.clone();
    }

    public Response registerFlight(String id, String planeId, String departureLocationId, String scaleLocationId,
            String arrivalLocationId, LocalDateTime departureDate,
            int hoursDurationArrival, int minutesDurationArrival,
            int hoursDurationScale, int minutesDurationScale) {
        try {
            // Validación de campos obligatorios
            if (id == null || id.isEmpty() || planeId == null || departureLocationId == null
                    || arrivalLocationId == null || departureDate == null) {
                Response base = new Response("No field should be null or empty", Status.BAD_REQUEST);
                return base.clone();
            }

            // Validación de ID único
            for (Flight f : fs.getFlights()) {
                if (f.getId().equals(id)) {
                    Response base = new Response("Flight ID already exists", Status.BAD_REQUEST);
                    return base.clone();
                }
            }

            // Validación de fecha de salida (no debe ser en el pasado)
            if (departureDate.isBefore(LocalDateTime.now())) {
                Response base = new Response("Departure date must be in the future", Status.BAD_REQUEST);
                return base.clone();
            }

            // Validación de duración (vuelo)
            if (hoursDurationArrival < 0 || minutesDurationArrival < 0 || (hoursDurationArrival == 0 && minutesDurationArrival == 0)) {
                Response base = new Response("Flight duration must be greater than 00:00", Status.BAD_REQUEST);
                return base.clone();
            }

            // Validación de duración (escala)
            if ((hoursDurationScale < 0 || minutesDurationScale < 0)
                    || (hoursDurationScale == 0 && minutesDurationScale == 0 && scaleLocationId != null)) {
                Response base = new Response("Scale duration must be greater than 00:00 if scale location is provided", Status.BAD_REQUEST);
                return base.clone();
            }

            // Crear y registrar vuelo
            Flight newFlight = new Flight(id, ac.getPlaneByID(planeId), lc.getLocationByID(departureLocationId), lc.getLocationByID(scaleLocationId), lc.getLocationByID(arrivalLocationId),
                    departureDate, hoursDurationArrival, minutesDurationArrival,
                    hoursDurationScale, minutesDurationScale);
            fs.getFlights().add(newFlight);
            Response base = new Response("Flight created successfully", Status.CREATED);
            return base.clone();
        } catch (Exception ex) {
            Response base = new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
            return base.clone();
        }
    }

}
