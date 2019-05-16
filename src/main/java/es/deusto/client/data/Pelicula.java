package es.deusto.client.data;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * clase Pelicula
 */
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Pelicula extends Articulo {

	private String sinopsis;
	private String genero;
	private String fecha_estr;
	private double puntuacion;
	
	/**
	 * Constructor de pelicula
	 * @param nombre
	 * @param precio
	 * @param sinopsis
	 * @param genero
	 * @param fecha_estr
	 * @param puntuacion
	 * @param caratula
	 * @param descuento
	 */
	public Pelicula(String nombre, double precio, String sinopsis, String genero, String fecha_estr, double puntuacion, String caratula, double descuento) {
		super(nombre, caratula, precio, descuento);
		this.sinopsis = sinopsis;
		this.genero = genero;
		this.fecha_estr = fecha_estr;
		this.puntuacion = puntuacion;
	}
	
	/**
	 * Getter de sinopsis de la pelicula
	 * @return string sinopsis
	 */
	public String getSinopsis() {
		return sinopsis;
	}

	/**
	 * Setter de sinopsis de la pelicula
	 * @param sinopsis
	 */
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	/**
	 * Getter de genero de la pelicula
	 * @return string genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Setter de genero de la pelicula
	 * @param genero
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Getter de la fecha de estreno de la pelicula
	 * @return string fecha de estreno
	 */
	public String getFecha_estr() {
		return fecha_estr;
	}

	/**
	 * Setter de la fecha de estreno de la pelicula
	 * @param fecha_estr
	 */
	public void setFecha_estr(String fecha_estr) {
		this.fecha_estr = fecha_estr;
	}

	/**
	 * Getter de la puntuacion de la pelicula
	 * @return double puntuacion
	 */
	public double getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Setter de la puntuacion de la pelicula
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
		return "Pelicula";
	}
	
}
