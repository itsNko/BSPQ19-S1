package es.deusto.server.db;

import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Socio;
import es.deusto.client.data.Videojuego;

/**
 * Interfaz IDAO.
 */
public interface IDAO {

	/**
	 * Introduce un socio nuevo en la base datos en caso de que no exista.
	 * @param s
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean insertarSocio(Socio s);
	//public boolean deleteSocio(Socio s);
	
	/**
	 * Comprueba si en la base de datos existe un socio con ese nombre o no.
	 * @param nombreSocio
	 * @return true si el socio existe y en caso contrario false
	 */
	public boolean existeSocio(String nombreSocio);
	
	/**
	 * Comprueba que existe un socio con ese nombre y contraseña.
	 * @param nombreSocio
	 * @param password
	 * @return SocioDTO con los datos del socio que ha iniciado sesion en caso de hacerlo correctamente. En caso contrario, un SocioDTO vacio
	 */
	public Socio inicioSesion(String nombreSocio, String password);
	
	/**
	 * Introduce un nuevo alquiler en la base de datos.
	 * @param alquiler
	 * @param nombreUsuario
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean insertarAlquiler(Alquiler alquiler, String nombreUsuario);
	
	/**
	 * Introduce una nueva pelicula en la base de datos.
	 * @param pelicula
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean insertarPelicula(Pelicula pelicula);
	
	/**
	 * Introduce un nuevo videojuego en la base de datos.
	 * @param videojuego
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean insertarVideojuego(Videojuego videojuego);
	
	/**
	 * Recupera el listado de articulos completo de la base de datos.
	 * @return Lista de articulos en caso de que no haya saltado ninguna excepcion, en caso contrario null
	 */
	public List<Articulo> listadoArticulos();
	
	/**
	 * Recupera un socio de la base de datos si existe uno con el nombre que se pasa como parametro
	 * @param nombreUsuario
	 * @return SocioDTO con sus datos si existe en la BD y en caso contrario un SocioDTO vacio
	 */
	public Socio selectSocio(String nombreUsuario);
	
	/**
	 * Recupera los alquileres existentes en la base de datos para un socio determinado.
	 * @param nombreUsuario
	 * @return Lista de alquileres en caso de que no haya saltado ninguna excepcion, en caso contrario null
	 */
	public List<Alquiler> selectAlquilerPorSocio(String nombreUsuario);
	
	/**
	 * Actualiza el atributo monedero de un socio determinado en la base de datos.
	 * @param nombreUsuario
	 * @param monedero
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean updateMonedero(String nombreUsuario, Double monedero);
	
	/**
	 * Recupera todos los alquileres vinculados a un determinado Socio de la base de datos.
	 * @param nombreSocio
	 * @return Lista de alquileres en caso de que no haya saltado ninguna excepcion, en caso contrario null
	 */
	public List<Alquiler> historialAlquileres(String nombreSocio);
	
	/**
	 * Actualiza el atributo precio de un articulo determinado en la base de datos.
	 * @param nombreArticulo
	 * @param precio
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean updatePrecio(String nombreArticulo, double precio);

	/**
	 * Actualiza el atributo descuento de un articulo determinado en la base de datos.
	 * @param nombreArticulo
	 * @param descuento
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean updateDescuento(String nombreArticulo, double descuento);
	
	/**
	 * Actualiza uno o varios atributos de un socio determinado en la base de datos.
	 * @param nombreSocio
	 * @param datosNuevos
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean updateDatosSocio(String nombreSocio, String datosNuevos);
	
	/**
	 * Actualiza el estado de un alquiler determinado en la base de datos.
	 * @param nombreUsuario
	 * @param nombreArticulo
	 * @param valoracion
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean devolverAlquiler(String nombreUsuario, String nombreArticulo, int valoracion);
	
	/**
	 * Actualiza el estado de la maquina de los clientes en la base de datos.
	 * @param nombreAdmin
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean bloquearMaquina(String nombreAdmin);
}
