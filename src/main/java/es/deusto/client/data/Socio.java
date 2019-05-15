package es.deusto.client.data;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * Clase Socio.
 */
@PersistenceCapable
public class Socio {

	@PrimaryKey
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
	 * Constructor completo del objeto Socio.
	 */
	public Socio(String nombre, String password, String nombreCompleto, String apellidos, String direccion, double monedero, String imagen) {
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
	 * Constructor vacio del objeto Socio.
	 * Todos los atributos se inicializan vacios.
	 */
	public Socio() {
		this.nombre = "";
		this.password = "";
		this.nombreCompleto = "";
		this.apellidos = "";
		this.direccion = "";
		this.monedero = 0;
		this.imagen = "";
		this.bloquearMaquina = false;
	}

	/**
	 * Devuelve el valor del atributo nombre del Socio.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Actualiza el atributo nombre del Socio con el valor que se introduce por parametro.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el valor del atributo password del Socio.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Actualiza el atributo password del Socio con el valor que se introduce por parametro.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Devuelve el valor del atributo nombreCompleto del Socio.
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Actualiza el atributo nombreCompleto del Socio con el valor que se introduce por parametro.
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * Devuelve el valor del atributo apellidos del Socio.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Actualiza el atributo apellidos del Socio con el valor que se introduce por parametro.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Devuelve el valor del atributo direccion del Socio.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Actualiza el atributo direccion del Socio con el valor que se introduce por parametro.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Devuelve el valor del atributo monedero del Socio.
	 */
	public double getMonedero() {
		return monedero;
	}
	
	/**
	 * Actualiza el atributo monedero del Socio con el valor que se introduce por parametro.
	 */
	public void setMonedero(double monedero) {
		this.monedero = monedero;
	}
	
	/**
	 * Actualiza el atributo imagen del Socio con el valor que se introduce por parametro.
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * Devuelve el valor del atributo imagen del Socio.
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * Devuelve el valor del atributo alquileres del Socio.
	 */
	public List<Alquiler> getAlquileres() {
		return alquileres;
	}

	/**
	 * Actualiza el atributo alquileres del Socio con el valor que se introduce por parametro.
	 */
	public void setAlquileres(List<Alquiler> alquileres) {
		this.alquileres = alquileres;
	}

	/**
	 * Devuelve el valor del atributo bloquearMaquina del Socio.
	 */
	public boolean isBloquearMaquina() {
		return bloquearMaquina;
	}

	/**
	 * Actualiza el atributo bloquearMaquina del Socio con el valor que se introduce por parametro.
	 */
	public void setBloquearMaquina(boolean bloquearMaquina) {
		this.bloquearMaquina = bloquearMaquina;
	}
	
}
