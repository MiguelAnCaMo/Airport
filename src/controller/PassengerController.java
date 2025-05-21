package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Passenger;
import model.Storage.PassengerStorage;
/**
 *
 * @author migue
 */
public class PassengerController {
    
    //dos contructores, uno para la carga del .json y el otro para agregar datos con la interfaz
    private static PassengerStorage ps;

    public PassengerController(String a) throws IOException {
        ps = new PassengerStorage();
    }
    
    public PassengerController() {
        
        
    }
        //creando un passeger y agregandolo a la lista de passengers en storage
    public void createPassenger (long id, String firstname, String lastname, LocalDate birthDate, int countryPhoneCode, long phone, String country) {
        Passenger passenger = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
        ps.getPassengers().add(passenger);
    } 

    public static ArrayList<Passenger> sendPassengers() {
        return ps.getPassengers();
    }
    
    //metodo para modificar la información de un passenger si se encentra el id ingresado
    public void modifiePassengerInformation(long id, String firstname, String lastname, LocalDate birthDate, int countryPhoneCode, long phone, String country){
        ArrayList <Passenger> passengers = ps.getPassengers();
        for (Passenger passenger : passengers) {
            if (id == passenger.getId()) {
                passenger.setFirstname(firstname);
                passenger.setLastname(lastname);
                passenger.setBirthDate(birthDate);
                passenger.setCountryPhoneCode(countryPhoneCode);
                passenger.setPhone(phone);
                passenger.setCountry(country);
                break;
            } else {
                System.out.println("No se encontro un passenger con el id " +id);
            }  
        }
    }
    
    //funcion que devuelve un modelo para poner a la lista de pasajeron en la interfaz
    public DefaultTableModel toPassengersJList() {              
        String[] columnas = {"ID", "Name", "Birthdate", "Age", "Phone", "Country", "Num Flight"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); //modelo para ser devuelto
        
        if (ps.getPassengers().isEmpty()) {
            System.out.println("Lista de pasajeros vacia");
        } else {
            for (Passenger p : ps.getPassengers()) {
                Object[] fila = new Object[] { //objeto para poner en el modelo
                  p.getId(), p.getFirstname(), String.valueOf(p.getBirthDate()), p.calculateAge(), p.getPhone(), p.getCountry(), p.getNumFlights()
                };
                model.addRow(fila);
            } 
        }
        return model;
    }
    
}
