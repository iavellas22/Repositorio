package controlador;

import java.io.IOException;

import javax.sound.midi.ControllerEventListener;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Piloto;
import vista.ControladorNuevoPiloto;

public class Principal2 extends Application {
	private Stage primaryStage;
    private AnchorPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Aviones");

        initRootLayout();

	}
	public void initRootLayout() {
    
    	
    	 try {
    	        FXMLLoader loader = new FXMLLoader();
    	        loader.setLocation(Principal2.class
    	                .getResource("../vista/Registro.fxml"));
    	        rootLayout = (AnchorPane) loader.load();

    	        Scene scene = new Scene(rootLayout);
    	        primaryStage.setScene(scene);

    	       

    	        primaryStage.show();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }

    	   
    	    
    	
    	
    	
    	
            }
	 public boolean showPersonEditDialog() {
		 boolean respuesta= false;
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Principal2.class.getResource("../vista/NuevoPiloto.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Nuevo Piloto");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            ControladorNuevoPiloto controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            //controller.setPiloto(person);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();
	            respuesta= controller.isOkClicked();

	            return respuesta;
	        } catch (IOException e) {
	            e.printStackTrace();
	           
	        }
	        return respuesta;
	    }
	
	   public void resgistrarPiloto() {
	        try {
	        	FXMLLoader loader = new FXMLLoader();
    	        loader.setLocation(Principal2.class
    	                .getResource("../vista/NuevoPiloto.fxml"));
    	        rootLayout = (AnchorPane) loader.load();

    	        Scene scene = new Scene(rootLayout);
    	        primaryStage.setScene(scene);

    	       

    	        primaryStage.show();

	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	public Stage getPrimaryStage() {
        return primaryStage;
    }


	public static void main(String[] args) {
		launch(args);
	}
}
