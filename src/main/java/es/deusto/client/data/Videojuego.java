package es.deusto.client.data;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * clase Videojuego
 */
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Videojuego extends Articulo {

	private String descripcion;
	private String categoria;
	private String fecha_lan;
	private double puntuacion;
	
	/**
	 * Constructor videojuego
	 * @param nombre
	 * @param precio
	 * @param descripcion
	 * @param categoria
	 * @param fecha_lan
	 * @param puntuacion
	 * @param caratula
	 * @param descuento
	 */
	public Videojuego(String nombre, double precio, String descripcion, String categoria, String fecha_lan, double puntuacion, String caratula, double descuento) {
		super(nombre, caratula, precio, descuento);
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.fecha_lan = fecha_lan;
		this.puntuacion = puntuacion;
	}

	/**
	 * Getter descripcion del videojuego
	 * @return string descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Setter descripcion del videojuego
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Getter de categoria del videojuego
	 * @return string categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Setter de categoria del videojuego
	 * @param categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Getter de la fecha de lanzamiento del videojuego
	 * @return string fecha de lanzamiento
	 */
	public String getFecha_lan() {
		return fecha_lan;
	}

	/**
	 * Setter de la fecha de lanzamiento
	 * @param fecha_lan
	 */
	public void setFecha_lan(String fecha_lan) {
		this.fecha_lan = fecha_lan;
	}

	/**
	 * Getter de la puntuacion del videojuego
	 * @return double puntuacion
	 */
	public double getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Setter de la puntuacion del videojuego
	 * @param puntuacion
	 */
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	/* (non-Javadoc)
	 * @see es.deusto.client.data.Articulo#getClassName()
	 */
	@Override
	public String getClassName() {
		return "Videojuego";
	}
	
}
