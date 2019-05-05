package es.deusto.client.data;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Socio {

	@PrimaryKey
	private String nombre;
	private String password;
	private String nombreCompleto;
	private String apellidos;
	private String direccion;
	private double monedero;
	private String imagen;
	private List<Alquiler> alquileres;
	
	public Socio(String nombre, String password, String nombreCompleto, String apellidos, String direccion, double monedero, String imagen) {
		this.nombre = nombre;
		this.password = password;
		this.nombreCompleto = nombreCompleto;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.monedero = monedero;
		this.imagen = imagen;
	}
	
	public Socio() {
		this.nombre = "";
		this.password = "";
		this.nombreCompleto = "";
		this.apellidos = "";
		this.direccion = "";
		this.monedero = 0;
		this.imagen = "";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getMonedero() {
		return monedero;
	}
	
	public void setMonedero(double monedero) {
		this.monedero = monedero;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getImagen() {
		return imagen;
	}

	public List<Alquiler> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(List<Alquiler> alquileres) {
		this.alquileres = alquileres;
	}
	
}
