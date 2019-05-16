package es.deusto.server.dto;

import java.io.Serializable;

/**
 * Clase ArticuloDTO.
 */
public abstract class  ArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String caratula;
	private double precio;
	private double descuento;
	
	/**
	 * Constructor completo del objeto ArticuloDTO.
	 */
	public ArticuloDTO(String nombre, String caratula, double precio, double descuento) {
		this.nombre = nombre;
		this.caratula = caratula;
		this.precio = precio;
		this.descuento = descuento;
	}

	/**
	 * Devuelve el valor del atributo nombre del ArticuloDTO.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Actualiza el atributo nombre del ArticuloDTO con el valor que se introduce por parametro.
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el valor del atributo caratula del ArticuloDTO.
	 */
	public String getCaratula() {
		return caratula;
	}

	/**
	 * Actualiza el atributo caratula del ArticuloDTO con el valor que se introduce por parametro.
	 * @param caratula
	 */
	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	/**
	 * Devuelve el valor del atributo precio del ArticuloDTO.
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Actualiza el atributo precio del ArticuloDTO con el valor que se introduce por parametro.
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	/**
	 * Devuelve el valor del atributo descuento del ArticuloDTO.
	 */
	public double getDescuento() {
		return descuento;
	}

	/**
	 * Actualiza el atributo descuento del ArticuloDTO con el valor que se introduce por parametro.
	 * @param descuento
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	
	/**
	 * Devuelve el nombre de la clase ArticuloDTO.
	 */
	public abstract String getClassName();
	
}
