package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Passenger;
/**
 *
 * @author migue
 */
public class PassengerController {
    
    private long id;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private int countryPhoneCode;
    private long phone;
    private String country;
    private Passenger passenger;
    private static ArrayList <Passenger> passengers = new ArrayList<>(); //este controlador manejara la lista de los pasajeros 

    public PassengerController() {
        
    }
    
    public Passenger createPassenger (long id, String firstname, String lastname, LocalDate birthDate, int countryPhoneCode, long phone, String country) {
        Passenger passenger = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
        passengers.add(passenger);
        return passenger; 
    } 

    public static ArrayList<Passenger> getPassengers() {
        return passengers;
    }
    
    //funcion que devuelve un modelo para poner a la lista de pasajeron en la interfaz
    public DefaultTableModel toPassengersJList() {              
        String[] columnas = {"ID", "Name", "Birthdate", "Age", "Phone", "Country", "Num Flight"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); //modelo para ser devuelto
        
        if (passengers.isEmpty()) {
            System.out.println("Lista de pasajeros vacia");
        } else {
            for (Passenger p : passengers) {
                Object[] fila = new Object[] {
                  p.getId(), p.getFirstname(), String.valueOf(p.getBirthDate()), p.calculateAge(), p.getPhone(), p.getCountry(), p.getNumFlights()
                };
                model.addRow(fila);
            } 
        }
        return model;
    }
    
}
