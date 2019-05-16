package es.deusto.server.services;

import java.rmi.RemoteException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Socio;
import es.deusto.client.data.Videojuego;
import es.deusto.server.db.MySQL_DB;
import es.deusto.server.dto.AlquilerAssembler;
import es.deusto.server.dto.AlquilerDTO;
import es.deusto.server.dto.ArticuloAssembler;
import es.deusto.server.dto.ArticuloDTO;
import es.deusto.server.dto.SocioAssembler;
import es.deusto.server.dto.SocioDTO;
import es.deusto.server.gateway.IGatewayPago;
import es.deusto.server.gateway.PayPalGateway;
import es.deusto.server.db.IDAO;

/**
 * Clase del AppServiceDB.
 */
public class AppServiceDB {

	private IDAO db;
	private ArticuloAssembler artAssem;
	private SocioAssembler socioAssem;
	private AlquilerAssembler alqAssem;
	private IGatewayPago paypalGateway;

	Logger logger = LoggerFactory.getLogger("ServerLog");

	/**
	 * Constructor del AppServiceDB.
	 * @param args
	 * @throws RemoteException
	 */
	public AppServiceDB(String[] args) throws RemoteException {
		db = new MySQL_DB();
		artAssem = ArticuloAssembler.getInstance();
		socioAssem = SocioAssembler.getInstance();
		alqAssem = AlquilerAssembler.getInstance();
		paypalGateway = new PayPalGateway(args[0], Integer.parseInt(args[1]), args[3]);
		logger.info("AppServiceDB - Constructor");
	}

	/**
	 * Llama al metodo de base de datos para insertar un nuevo socio.
	 * @param nombre
	 * @param pass
	 * @param monedero
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean insertarSocio(String nombre, String pass, double monedero) {
		logger.info("AppServiceDB - insertarSocio()");
		Socio s = new Socio(nombre, pass, nombre, nombre, "-", monedero, "default-profile.png");
		return db.insertarSocio(s);
	}

	/**
	 * Llama al metodo de base de datos para comprobar si existe un socio.
	 * @param nombreSocio
	 * @return true si existe socio y en caso contrario false
	 */
	public boolean existeSocio(String nombreSocio) {
		logger.info("AppServiceDB - existeSocio()");
		return db.existeSocio(nombreSocio);
	}

	/**
	 * Llama al metodo de base de datos para realizar el proceso de inicio de sesion.
	 * @param nombreSocio
	 * @param password
	 * @return SocioDTO con los datos del socio que ha iniciado sesion en caso de hacerlo correctamente. En caso contrario, un SocioDTO vacio
	 */
	public SocioDTO inicioSesion(String nombreSocio, String password) {
		logger.info("AppServiceDB - inicioSesion()");
		Socio socio = db.inicioSesion(nombreSocio, password);
		return socioAssem.assemble(socio);
	}

	/**
	 * Llama al metodo de base de datos que inserta un nuevo alquiler.
	 * @param nombre
	 * @param precio
	 * @param sinopsis
	 * @param genero
	 * @param fecha_estr
	 * @param puntuacion
	 * @param caratula
	 * @param coste
	 * @param nombreUsuario
	 * @param pv
	 * @param fechaFin
	 * @param fechaInicio
	 * @param descuento
	 * @return true si no ha saltado ninguna excepción y false en caso contrario
	 */
	public boolean insertarAlquiler(String nombre, double precio, String sinopsis, String genero, String fecha_estr, double puntuacion, String caratula, double coste, String nombreUsuario, boolean pv, String fechaFin, String fechaInicio, double descuento) {
		logger.info("AppServiceDB - insertarAlquiler()");
		Articulo articulo;
		Alquiler alquiler;
		if(pv) {
			articulo = new Pelicula(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, descuento);
			alquiler = new Alquiler(articulo, coste, fechaInicio, fechaFin ,true, articulo.getNombre()+"-Pelicula");

		} else {
			articulo = new Videojuego(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, descuento);
			alquiler = new Alquiler(articulo, coste, fechaInicio, fechaFin,true, articulo.getNombre()+"-Videojuego");
		}
		return db.insertarAlquiler(alquiler, nombreUsuario);
	}

	/**
	 * Llama al metodo de base de datos que recupera el listado de articulos almacenados en la base de datos.
	 * @return Lista de ArticuloDTO en caso de que no haya saltado ninguna excepcion, en caso contrario null
	 */
	public List<ArticuloDTO> listadoArticulos() {
		logger.info("AppServiceDB - listadoArticulos()");
		return artAssem.assemble(db.listadoArticulos());
	}

	/**
	 * Llama al metodo de base de datos que recupera el historial de alquileres de un socio.
	 * @param nombreSocio
	 * @return Lista de AlquilerDTO en caso de que no haya saltado ninguna excepcion, en caso contrario null
	 */
	public List<AlquilerDTO> historialAlquileres(String nombreSocio) {
		logger.info("AppServiceDB - historialAlquileres()");
		List<Alquiler> alquileres = db.historialAlquileres(nombreSocio);

		return alqAssem.assemble(alquileres);
	}

	/**
	 * Llama al metodo de base de datos que recupera un socio.
	 * @param nombreUsuario
	 * @return SocioDTO con sus datos si existe en la BD y en caso contrario un SocioDTO vacio
	 */
	public SocioDTO selectSocio(String nombreUsuario) {
		logger.info("AppServiceDB - selectSocio()");
		return socioAssem.assemble(db.selectSocio(nombreUsuario));
	}

	/**
	 * Llama al metodo de base de datos que actualiza el valor del atributo monedero de un socio.
	 * @param nombreUsuario
	 * @param monedero
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean updateMonedero(String nombreUsuario, double monedero) {
		logger.info("AppServiceDB - updateMonedero()");
		return db.updateMonedero(nombreUsuario, monedero);
	}

	/** Llama al metodo de base de datos que actualiza el valor del atributo descuento de un articulo.
	 * @param nombreArticulo
	 * @param descuento
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean updateDescuento(String nombreArticulo, double descuento) {
		logger.info("AppServiceDB - updateDescuento()");
		return db.updateMonedero(nombreArticulo, descuento);
	}

	/**
	 * Llama al metodo de base de datos que inserta una pelicula en la base de datos.
	 * @param nombre
	 * @param precio
	 * @param sinopsis
	 * @param genero
	 * @param fecha_estr
	 * @param puntuacion
	 * @param caratula
	 * @param descuento
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean insertarPelicula(String nombre, double precio, String sinopsis, String genero, String fecha_estr, double puntuacion, String caratula, double descuento) {
		logger.info("AppServiceDB - insertarPelicula()");
		Pelicula p1 = new Pelicula(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, descuento);
		return db.insertarPelicula(p1);
	}

	/**
	 * Llama al metodo de base de datos que inserta un videojuego en la base de datos.
	 * @param nombre
	 * @param precio
	 * @param descripcion
	 * @param categoria
	 * @param fecha_lanz
	 * @param puntuacion
	 * @param caratula
	 * @param descuento
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean insertarVideojuego(String nombre, double precio, String descripcion, String categoria, String fecha_lanz, double puntuacion, String caratula, double descuento) {
		logger.info("AppServiceDB - insertarVideojuego()");
		Videojuego v1 = new Videojuego(nombre, precio, descripcion, categoria, fecha_lanz, puntuacion, caratula, descuento);
		return db.insertarVideojuego(v1);
	}

	/**
	 * Llama al metodo de base de datos que actualiza el atributo precio de un articulo.
	 * @param nombreArticulo
	 * @param precio
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean updatePrecio(String nombreArticulo, double precio) {
		logger.info("AppServiceDB - updatePrecio()");
		return db.updatePrecio(nombreArticulo, precio);
	}

	/** Llama al metodo de base de datos que actualiza varios atributos de un socio.
	 * @param nombreSocio
	 * @param datosNuevos
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean updateDatosSocio(String nombreSocio, String datosNuevos) {
		logger.info("AppServiceDB - updateDatosSocio()");
		return db.updateDatosSocio(nombreSocio, datosNuevos);
	}

	/**
	 * Llama al metodo de base de datos que actualiza el estado de un alquiler y le asigna una valoracion.
	 * @param nombreUsuario
	 * @param nombreArticulo
	 * @param valoracion
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean devolverAlquiler(String nombreUsuario, String nombreArticulo, int valoracion) {
		logger.info("AppServiceDB - devolverAlquiler()");
		return db.devolverAlquiler(nombreUsuario, nombreArticulo, valoracion);
	}

	/**
	 * Llama al metodo de base de datos que actualiza el estado de la maquina para clientes.
	 * @param nombreAdmin
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean bloquearMaquina(String nombreAdmin) {
		logger.info("AppServiceDB - bloquearMaquina()");
		return db.bloquearMaquina(nombreAdmin);
	}

	/**
	 * Llama al metodo del gatewayPaypal para acceder a la funcionalidad que proporciona el servidor de PayPal.
	 * @param nombre
	 * @param password
	 * @param cantidad
	 * @return true si no ha saltado ninguna excepcion y en caso contrario false
	 */
	public boolean pagarPaypal(String nombre, String password, double cantidad) {
		logger.info("AppServiceDB - pagarPaypal()");
		try {
			return paypalGateway.pagar(nombre, password, cantidad);
		} catch (RemoteException e) {
			return false;
		}
	}
}
