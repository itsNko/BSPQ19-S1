package es.deusto.server.dto;

import java.io.Serializable;

public abstract class  ArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String caratula;
	private double precio;
	
	public ArticuloDTO(String nombre, String caratula, double precio) {
		this.nombre = nombre;
		this.caratula = caratula;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public abstract String getClassName();
	
}
