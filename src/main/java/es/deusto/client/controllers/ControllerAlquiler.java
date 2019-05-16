package es.deusto.client.controllers;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Socio;
import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.AlquilerDTO;
import es.deusto.server.dto.SocioDTO;

/**
 * clase ControllerAlquiler
 *
 */
public class ControllerAlquiler {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	/**
	 * Constructor vacio ControllerAlquiler
	 * @throws RemoteException
	 */
	public ControllerAlquiler() throws RemoteException {
	}
	
	/**
	 * Constructor ControllerAlquiler
	 * @param rsl
	 * @throws RemoteException
	 */
	public ControllerAlquiler(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}

	/**
	 * Para insertar un nuevo alquiler a un usuario, llama al metodo de la clase Server
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
	 * @return true si se hace correctamente, false tras la Exception e
	 */
	public boolean insertarAlquiler(String nombre, double precio, String sinopsis, String genero, String fecha_estr, double puntuacion, String caratula, double coste, String nombreUsuario, boolean pv, String fechaFin, String fechaInicio, double descuento) {
		boolean correcto;
		try {
			System.out.println("###ControllerAlquiler: ServiceLocator.getService().insertarAlquiler###");
			correcto = rsl.getService().insertarAlquiler(nombre,precio,sinopsis,genero,fecha_estr,puntuacion, caratula, coste, nombreUsuario, pv, fechaFin, fechaInicio, descuento);
			System.out.println("###ControllerAlquiler: Se ha insertado alquiler correctamente###");

			return correcto;
		}catch(Exception e) {
			System.err.println("$ Error al insertar alquiler " + e.getMessage());
			return false;

		}
	}
	
	/**
	 * Devuelve un socioDTO con el nombreUsuario, llama al metodo de la clase Server
	 * @param nombreUsuario
	 * @return SocioDTO o SocioDTO vacio tras Exception e
	 */
	public SocioDTO selectSocio(String nombreUsuario) {
		try {
			System.out.println("###ControllerAlquiler: ServiceLocator.getService().selectSocio###");
			return rsl.getService().selectSocio(nombreUsuario);

		}catch(Exception e) {
			System.err.println("$ Error al seleccionar socio " + e.getMessage());
			return new SocioDTO("", "", "", "", "", 0, "");
		}
	}

	/**
	 * Devuelve el historial de Alquileres de un socio por su nombre, llama al metodo de la clase Server
	 * @param nombreSocio
	 * @return Lista de AlquilerDTO o null tras Exception e
	 */
	public List<AlquilerDTO> historialAlquileres(String nombreSocio) {
		try {
			return rsl.getService().historialAlquileres(nombreSocio); 
		} catch(Exception e) {
			System.err.println("$ Error al devolver historial de alquileres " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Llama al metodo de la clase Server y se actualiza el monedero de un usuario, se le pasa la nueva
	 * cantidad del monedero y el nombreUsuario del usuario al que queremos actualizar el
	 * monedero
	 * @param nombreUsuario
	 * @param monedero
	 * @return true si se realiza correctamente, false si se da la Exception e
	 */
	public boolean updateMonedero(String nombreUsuario, double monedero) {
		try {
			System.out.println("###ControllerRecargarSaldo: ServiceLocator.getService().updateMonedero###");
			return rsl.getService().updateMonedero(nombreUsuario, monedero);
		}catch(Exception e) {
			System.err.println("$ Error al actualizar monedero " + e.getMessage());
			return false;
		}
	}

}
