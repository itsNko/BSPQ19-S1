package es.deusto.server.dto;

import java.io.Serializable;

/**
 * Clase PeliculaDTO.
 */
public class PeliculaDTO extends ArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String sinopsis;
	private String genero;
	private String fecha_estr;
	private double puntuacion;
	
	/**
	 * Constructor completo del objeto PeliculaDTO.
	 */
	public PeliculaDTO(String nombre, double precio, String sinopsis, String genero, String fecha_estr, double puntuacion, String caratula, double descuento) {
		super(nombre, caratula, precio, descuento);
		this.sinopsis = sinopsis;
		this.genero = genero;
		this.fecha_estr = fecha_estr;
		this.puntuacion = puntuacion;
	}
	
	/**
	 * Devuelve el valor del atributo sinopsis del PeliculaDTO.
	 */
	public String getSinopsis() {
		return sinopsis;
	}

	/**
	 * Actualiza el atributo sinopsis del PeliculaDTO con el valor que se introduce por parametro.
	 * @param sinopsis
	 */
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	/**
	 * Devuelve el valor del atributo genero del PeliculaDTO.
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Actualiza el atributo genero del PeliculaDTO con el valor que se introduce por parametro.
	 * @param genero
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Devuelve el valor del atributo fecha_estr del PeliculaDTO.
	 */
	public String getFecha_estr() {
		return fecha_estr;
	}

	/**
	 * Actualiza el atributo fecha_estr del PeliculaDTO con el valor que se introduce por parametro.
	 * @param fecha_estr
	 */
	public void setFecha_estr(String fecha_estr) {
		this.fecha_estr = fecha_estr;
	}

	/**
	 * Devuelve el valor del atributo puntuacion del PeliculaDTO.
	 */
	public double getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Actualiza el atributo puntuacion del PeliculaDTO con el valor que se introduce por parametro.
	 * @param puntuacion
	 */
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * Devuelve el nombre de la clase PeliculaDTO.
	 */
	@Override
	public String getClassName() {
		return "PeliculaDTO";
	}
}
