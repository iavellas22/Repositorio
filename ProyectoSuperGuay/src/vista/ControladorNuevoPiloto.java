package vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import controlador.Principal2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Piloto;

/**
 * Dialog to edit details of a person.
 * 
 */
public class ControladorNuevoPiloto {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido1;
    @FXML
    private TextField apellido2;
    @FXML
    private TextField numeroLicencia;
    @FXML
    private TextField usuario;
    @FXML
    private TextField contrasenia;
    @FXML
    private TextField repiteContrasenia;
    
    private ObservableList <Piloto> listaPilotos;
    private Principal2 principal;
    private Stage dialogStage;
    private Piloto piloto;
    private File ficheroPilotos;
    private boolean okClicked = false;
    private static final String nombreFicheroPilotos = "ficheroPilotos";

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    
    

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;

        nombre.setText(piloto.getNombre());
        apellido1.setText(piloto.getApellido1());
        apellido2.setText(piloto.getApellido2());
        numeroLicencia.setText(Integer.toString(piloto.getNumeroLicencia()));
        usuario.setText(piloto.getUsuario());
        contrasenia.setText(piloto.getContrasenia());
        
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	piloto = new Piloto();
        	ficheroPilotos= new File ("ficheroPilotos");
            piloto.setNombre(nombre.getText());
            piloto.setApellido1(apellido1.getText());
            piloto.setApellido2(apellido2.getText());
            piloto.setNumeroLicencia(Integer.parseInt(numeroLicencia.getText()));
            piloto.setUsuario(usuario.getText());
            String contrasenia1 = contrasenia.getText();
            piloto.setContrasenia((contrasenia1));
            String contrasenia2 = repiteContrasenia.getText();
            contraseniaValida(contrasenia1, contrasenia2);
            listaPilotos = FXCollections.observableArrayList();
            if(!existePiloto(piloto)) {
                listaPilotos.add(piloto);
            }
            try {
				ObjectOutputStream escritor = new ObjectOutputStream (new FileOutputStream(ficheroPilotos,true));
				 for(int i=0; i< listaPilotos.size(); i++) {
		            	escritor.writeObject(listaPilotos.get(i));
		            }
				 escritor.reset();
				 escritor.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
            
            okClicked = true;
            nombre.setText("");
            apellido1.setText("");
            apellido2.setText("");
            numeroLicencia.setText("");
            usuario.setText("");
            contrasenia.setText("");
            repiteContrasenia.setText("");
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Datos añadidos con éxito");
            alert.setHeaderText("Tus datos han sido añadidos! Puedes seguir agregando datos o salir pulsando cancel");
            alert.setContentText("");
            
            alert.showAndWait();
            //dialogStage.close();
        }
    }
    
    private boolean existePiloto (Piloto pilotoFichero) {
    	boolean existePiloto = false;
    	ficheroPilotos= new File(nombreFicheroPilotos);
    	try {
			ObjectInputStream lector = new ObjectInputStream(new FileInputStream(ficheroPilotos));
			Piloto piloto = (Piloto) lector.readObject();
			if(pilotoFichero.equals(piloto)) {
				Alert alert = new Alert(AlertType.INFORMATION);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Ya existe el usuario");
	            alert.setHeaderText("Ya estás registrado con estos datos");
	            alert.setContentText("");
	            existePiloto = true;
			}else{
				while (piloto!=null) {
					piloto = (Piloto) lector.readObject();
					if(pilotoFichero.equals(piloto)) {
						Alert alert = new Alert(AlertType.INFORMATION);
			            alert.initOwner(dialogStage);
			            alert.setTitle("Ya existe el usuario");
			            alert.setHeaderText("Ya estás registrado con estos datos");
			            alert.setContentText("");
			            existePiloto= true;
					}
				}
			}
			
			lector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return existePiloto;
    }
    
    private boolean contraseniaValida (String contrasenia, String contrasenia2) {
    	boolean valida= false;
    	
    	if (contrasenia.equalsIgnoreCase(contrasenia2)) {
    		valida= true;
    	}else {
    		String errorMessage="";
    		 Alert alert = new Alert(AlertType.ERROR);
             alert.initOwner(dialogStage);
             alert.setTitle("La contraseña no coincide");
             alert.setHeaderText("Por favor introduce de nuevo la contraseña");
             alert.setContentText(errorMessage);
             
             alert.showAndWait();
    		valida = false;
    	}
    	return valida;
    	
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        System.exit(0);
    }
    

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
    	
        String errorMessage = "";

        if (nombre.getText() == null || nombre.getText().length() == 0) {
            errorMessage += "Nombre no valido!\n"; 
        }
        if (apellido1.getText() == null || apellido1.getText().length() == 0) {
            errorMessage += "Apellido no valido!\n"; 
        }
        if (apellido2.getText() == null || apellido2.getText().length() == 0) {
            errorMessage += "Apellido no valido!\n"; 
        }

        if (numeroLicencia.getText() == null || numeroLicencia.getText().length() == 0) {
            errorMessage += "Numero de licencia no valido!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(numeroLicencia.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Numero de licencia no valido (debe de ser un numero entero)!\n"; 
            }
        }

        if (usuario.getText() == null || usuario.getText().length() == 0) {
            errorMessage += "Usuario no valido!\n"; 
        }
        
        if(contrasenia.getText() == null || contrasenia.getText().length() == 0) {
        	errorMessage += "Contraseña no valida!";
        }

        

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
   
    
}