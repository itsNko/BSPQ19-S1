package es.deusto.server.dto;

import java.io.Serializable;

public abstract class  ArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String caratula;
	private double precio;
	private double descuento;
	
	public ArticuloDTO(String nombre, String caratula, double precio, double descuento) {
		this.nombre = nombre;
		this.caratula = caratula;
		this.precio = precio;
		this.descuento = descuento;
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
	
	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	
	public abstract String getClassName();
	
}
