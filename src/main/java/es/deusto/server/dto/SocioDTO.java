package es.deusto.server.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.deusto.client.data.Alquiler;

public class SocioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String password;
	private double monedero;
	private String imagen;
	private List<Alquiler> alquileres;
	
	public SocioDTO(String nombre, String password, double monedero, String imagen) {
		this.nombre = nombre;
		this.password = password;
		this.monedero = monedero;
		this.imagen = imagen;
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
