package es.deusto.client.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * clase Articulo
 */
@PersistenceCapable
public abstract class Articulo {

	@PrimaryKey
	private String nombre;
	private String caratula;
	private double precio;
	private int unidades;
	private double porcentajeDescuento;

	/**
	 * Constructor Articulo
	 * @param nombre
	 * @param caratula
	 * @param precio
	 * @param porcentajeDescuento
	 */
	public Articulo(String nombre, String caratula, double precio, double porcentajeDescuento) {
		this.nombre = nombre;
		this.caratula = caratula;
		this.precio = precio;
		this.unidades = 1;
		this.porcentajeDescuento = porcentajeDescuento; 
	}

	/**
	 * Getter nombre del articulo
	 * @return String con el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter nombre del articulo
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de la caratula del articulo
	 * @return String caratula
	 */
	public String getCaratula() {
		return caratula;
	}

	/**
	 * Setter de la caratula del articulo
	 * @param caratula
	 */
	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	/**
	 * Getter del precio del articulo
	 * @return double precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Setter precio del articulo
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	/**
	 * Getter del descuento del articulo
	 * @return double descuento
	 */
	public double getDescuento() {
		return porcentajeDescuento;
	}

	/**
	 * Setter del descuento del articulo
	 * @param porcentajeDescuento
	 */
	public void setDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	/**
	 * Getter de unidades de stock del articulo
	 * @return integer unidades
	 */
	public int getUnidades() {
		return unidades;
	}

	/**
	 * Setter de las unidades de stock del articulo
	 * @param unidades
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	/**
	 * metodo que devuelve el nombre de la clase
	 * @return String className
	 */
	public abstract String getClassName();
	
}
