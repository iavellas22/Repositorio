package vista;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ControladorPiloto {


	    // Reference to the main application.
	
	

	
    public void resgitrarPilotoFXML() {
    	try {
    		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/NuevoPiloto.fxml"));
             Parent root1 = (Parent) fxmlLoader.load();
             Stage stage = new Stage();
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.initStyle(StageStyle.UNDECORATED);
             stage.setTitle("ABC");
             stage.setScene(new Scene(root1));  
             stage.show();
             
    		
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        
    }

}
