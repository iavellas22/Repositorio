package modelo;

import java.io.Serializable;

public class Piloto implements Serializable {
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int numeroLicencia;
	private String usuario;
	private String contrasenia;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public int getNumeroLicencia() {
		return numeroLicencia;
	}
	public void setNumeroLicencia(int numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
	public Piloto (String nombre, String ape1, String ape2, int numeroLicencia, String usuario, String contrasenia) {
		this.nombre= nombre;
		this.apellido1= ape1;
		this.apellido2= ape2;
		this.numeroLicencia= numeroLicencia;
		this.usuario= usuario;
		this.contrasenia= contrasenia;
	}
	
	public Piloto () {
		
	}

}
