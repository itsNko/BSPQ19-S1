package es.deusto.client.controllers;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.client.remote.ServiceLocator;

/**
 * clase ControllerMenuSocio
 *
 */
public class ControllerMenuSocio {

	private ServiceLocator rsl = ControllerRegistro.getRsl();
	Logger logger = LoggerFactory.getLogger("ClientLog");

	/**
	 * Constructor vacio ControllerMenuSocio
	 * @throws RemoteException
	 */
	public ControllerMenuSocio() throws RemoteException {
	}
	
	/**
	 * Constructor ControllerMenuSocio
	 * @param rsl
	 * @throws RemoteException
	 */
	public ControllerMenuSocio(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}

	/**
	 * metodo que permite bloquear el sistema/maquina para los usuarios,
	 * llama al metodo de la clase Server
	 * @param nombreAdmin
	 * @return true si se hace correctamente, false si se da RemoteException
	 */
	public boolean updateBloquearMaquina(String nombreAdmin) {
		try {
			logger.info("###ControllerMenuSocio: ServiceLocator.getService().updateBloquearMaquina###");
			return rsl.getService().bloquearMaquina(nombreAdmin);
		} catch (RemoteException e) {
			logger.error("$ Error al bloquear la maquina " + e.getMessage());
			//System.err.println("$ Error al bloquear la maquina " + e.getMessage());
			return false;
		}
		
	}
}
