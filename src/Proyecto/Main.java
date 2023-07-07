package Proyecto;
import Vista.VuelosGUI;
public class Main {
    
    /**
     * Inicia el programa
     * @param args 
     */
    public static void main(String[] args) {        
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VuelosGUI().setVisible(true);
            }
        });
         
         
    }
    
}
 