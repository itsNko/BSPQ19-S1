package es.deusto.server.dto;

import java.io.Serializable;
import java.util.List;

import es.deusto.client.data.Alquiler;

/**
 * Clase SocioDTO.
 */
public class SocioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String password;
	private String nombreCompleto;
	private String apellidos;
	private String direccion;
	private double monedero;
	private String imagen;
	private List<Alquiler> alquileres;
	private boolean bloquearMaquina;
	
	/**
	 * Constructor completo del objeto SocioDTO.
	 */
	public SocioDTO(String nombre, String password, String nombreCompleto, String apellidos, String direccion, double monedero, String imagen) {
		this.nombre = nombre;
		this.password = password;
		this.nombreCompleto = nombreCompleto;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.monedero = monedero;
		this.imagen = imagen;
		this.bloquearMaquina = false;
	}

	/**
	 * Devuelve el valor del atributo nombre del SocioDTO.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Actualiza el atributo nombre del SocioDTO con el valor que se introduce por parametro.
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el valor del atributo password del SocioDTO.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Actualiza el atributo password del SocioDTO con el valor que se introduce por parametro.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Devuelve el valor del atributo nombreCompleto del SocioDTO.
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Actualiza el atributo nombreCompleto del SocioDTO con el valor que se introduce por parametro.
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * Devuelve el valor del atributo apellidos del SocioDTO.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Actualiza el atributo apellidos del SocioDTO con el valor que se introduce por parametro.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Devuelve el valor del atributo direccion del SocioDTO.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Actualiza el atributo direccion del SocioDTO con el valor que se introduce por parametro.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Devuelve el valor del atributo monedero del SocioDTO.
	 */
	public double getMonedero() {
		return monedero;
	}
	
	/**
	 * Actualiza el atributo monedero del SocioDTO con el valor que se introduce por parametro.
	 */
	public void setMonedero(double monedero) {
		this.monedero = monedero;
	}
	
	/**
	 * Actualiza el atributo imagen del SocioDTO con el valor que se introduce por parametro.
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * Devuelve el valor del atributo imagen del SocioDTO.
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * Devuelve el valor del atributo alquileres del SocioDTO.
	 */
	public List<Alquiler> getAlquileres() {
		return alquileres;
	}

	/**
	 * Actualiza el atributo alquileres del SocioDTO con el valor que se introduce por parametro.
	 */
	public void setAlquileres(List<Alquiler> alquileres) {
		this.alquileres = alquileres;
	}

	/**
	 * Devuelve el valor del atributo bloquearMaquina del SocioDTO.
	 */
	public boolean isBloquearMaquina() {
		return bloquearMaquina;
	}

	/**
	 * Actualiza el atributo bloquearMaquina del SocioDTO con el valor que se introduce por parametro.
	 */
	public void setBloquearMaquina(boolean bloquearMaquina) {
		this.bloquearMaquina = bloquearMaquina;
	}
	
}
