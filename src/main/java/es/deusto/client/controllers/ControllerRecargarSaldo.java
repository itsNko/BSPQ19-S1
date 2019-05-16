package es.deusto.client.controllers;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.SocioDTO;

/**
 * clase ControllerRecargarSaldo
 *
 */
public class ControllerRecargarSaldo {
	
	private ServiceLocator rsl = ControllerRegistro.getRsl();
	Logger logger = LoggerFactory.getLogger("ClientLog");

	/**
	 * Constructor vacio ControllerRecargarSaldo
	 * @throws RemoteException
	 */
	public ControllerRecargarSaldo() throws RemoteException {
	}
	
	/**
	 * Constructor ControllerRecargarSaldo.
	 * Solo se usa en los test unitarios
	 * @param rsl
	 * @throws RemoteException
	 */
	public ControllerRecargarSaldo(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}
	
	/**
	 * metodo que devuelve un SocioDTO mediante el nombreUsuario, llama al metodo de la clase Server
	 * @param nombreUsuario
	 * @return SocioDTO o SocioDTO vacio en caso de Exception
	 */
	public SocioDTO selectSocio(String nombreUsuario) {
		try {
			logger.info("###ControllerRecargarSaldo: ServiceLocator.getService().selectSocio###");
			//System.out.println("###ControllerRecargarSaldo: ServiceLocator.getService().selectSocio###");
			return rsl.getService().selectSocio(nombreUsuario);

		}catch(Exception e) {
			logger.error("$ Error al seleccionar socio " + e.getMessage());
			//System.err.println("$ Error al seleccionar socio " + e.getMessage());
			return new SocioDTO("", "", "", "", "", 0, "");
		}
	}
	
	/**
	 * metodo para actualizar el valor del monedero de un usuario mediante el nombreUsuario
	 * y el nuevo valor del monedero, llama al metodo de la clase Server
	 * @param nombreUsuario
	 * @param monedero
	 * @return true si se hace correctamente \n false si se da Exception
	 */
	public boolean updateMonedero(String nombreUsuario, double monedero) {
		try {
			logger.info("###ControllerRecargarSaldo: ServiceLocator.getService().updateMonedero###");
			//System.out.println("###ControllerRecargarSaldo: ServiceLocator.getService().updateMonedero###");
			return rsl.getService().updateMonedero(nombreUsuario, monedero);
		}catch(Exception e) {
			logger.error("$ Error al actualizar monedero " + e.getMessage());
			//System.err.println("$ Error al actualizar monedero " + e.getMessage());
			return false;
		}
	}
}
