package es.deusto.server.dto;

import java.io.Serializable;

import es.deusto.client.data.Articulo;

/**
 * Clase AlquilerDTO.
 */
public class AlquilerDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArticuloDTO alquilado;
	private double coste;
	private String fecha_inicio;
	private String fecha_fin;
	private boolean enCurso;
	
	/**
	 * Constructor completo del objeto AlquilerDTO.
	 */
	public AlquilerDTO(ArticuloDTO alquilado, double coste, String fecha_inicio, String fecha_fin, boolean enCurso) {
		this.alquilado = alquilado;
		this.coste = coste;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.enCurso = enCurso;
	}
	
	/**
	 * Devuelve el valor del atributo alquilado del AlquilerDTO.
	 */
	public ArticuloDTO getAlquilado() {
		return alquilado;
	}

	/**
	 * Actualiza el atributo alquilado del AlquilerDTO con el valor que se introduce por parametro.
	 * @param alquilado
	 */
	public void setAlquilado(ArticuloDTO alquilado) {
		this.alquilado = alquilado;
	}

	/**
	 * Devuelve el valor del atributo coste del AlquilerDTO.
	 */
	public double getCoste() {
		return coste;
	}

	/**
	 * Actualiza el atributo coste del AlquilerDTO con el valor que se introduce por parametro.
	 * @param coste
	 */
	public void setCoste(double coste) {
		this.coste = coste;
	}

	/**
	 * Devuelve el valor del atributo fecha_inicio del AlquilerDTO.
	 */
	public String getFecha_inicio() {
		return fecha_inicio;
	}

	/**
	 * Actualiza el atributo fecha_inicio del AlquilerDTO con el valor que se introduce por parametro.
	 * @param fecha_inicio
	 */
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	/**
	 * Devuelve el valor del atributo fecha_fin del AlquilerDTO.
	 */
	public String getFecha_fin() {
		return fecha_fin;
	}

	/**
	 * Actualiza el atributo fecha_fin del AlquilerDTO con el valor que se introduce por parametro.
	 * @param fecha_fin
	 */
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	/**
	 * Devuelve el valor del atributo enCurso del AlquilerDTO.
	 */
	public boolean isEnCurso() {
		return enCurso;
	}

	/**
	 * Actualiza el atributo enCurso del AlquilerDTO con el valor que se introduce por parametro.
	 * @param enCurso
	 */
	public void setEnCurso(boolean enCurso) {
		this.enCurso = enCurso;
	}
}
