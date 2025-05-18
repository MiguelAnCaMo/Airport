package main;

import view.AirportFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import static model.JsonLocation.readLocations;
import model.Location;

/**
 *
 * @author migue
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    // metodo main para que la vista no se inicialice el programa en la vista
    public static void main(String args[]) throws IOException {
        System.setProperty("flatlaf.useNativeLibrary", "false");
              
        
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AirportFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
