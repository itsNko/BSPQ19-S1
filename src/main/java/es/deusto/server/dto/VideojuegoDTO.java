package es.deusto.server.dto;

import java.io.Serializable;

/**
 * Clase VideojuegoDTO
 */
public class VideojuegoDTO extends ArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String descripcion;
	private String categoria;
	private String fecha_lan;
	private double puntuacion;
	
	/**
	 * Constructor completo del objeto VideojuegoDTO.
	 */
	public VideojuegoDTO(String nombre, double precio, String descripcion, String categoria, String fecha_lan, double puntuacion, String caratula, double descuento) {
		super(nombre, caratula, precio, descuento);
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.fecha_lan = fecha_lan;
		this.puntuacion = puntuacion;
	}

	/**
	 * Devuelve el valor del atributo descripcion del VideojuegoDTO.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Actualiza el atributo descripcion del VideojuegoDTO con el valor que se introduce por parametro.
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve el valor del atributo categoria del VideojuegoDTO.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Actualiza el atributo categoria del VideojuegoDTO con el valor que se introduce por parametro.
	 * @param categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Devuelve el valor del atributo fecha_lan del VideojuegoDTO.
	 */
	public String getFecha_lan() {
		return fecha_lan;
	}

	/**
	 * Actualiza el atributo fecha_lan del VideojuegoDTO con el valor que se introduce por parametro.
	 * @param fecha_lan
	 */
	public void setFecha_lan(String fecha_lan) {
		this.fecha_lan = fecha_lan;
	}

	/**
	 * Devuelve el valor del atributo puntuacion del VideojuegoDTO.
	 */
	public double getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Actualiza el atributo puntuacion del VideojuegoDTO con el valor que se introduce por parametro.
	 * @param puntuacion
	 */
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * Devuelve el nombre de la clase VideojuegoDTO.
	 */
	@Override
	public String getClassName() {
		return "VideojuegoDTO";
	}

}
