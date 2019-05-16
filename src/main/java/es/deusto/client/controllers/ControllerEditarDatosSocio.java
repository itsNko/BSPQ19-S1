package es.deusto.client.controllers;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.client.remote.ServiceLocator;

/**
 * clase ControllerEditarDatosSocio
 *
 */
public class ControllerEditarDatosSocio {

	private ServiceLocator rsl = ControllerRegistro.getRsl();
	Logger logger = LoggerFactory.getLogger("ClientLog");

	/**
	 * Constructor vacio ControllerEditarDatosSocio
	 * @throws RemoteException
	 */
	public ControllerEditarDatosSocio() throws RemoteException {
	}

	/** Constructor ControllerEditarDatosSocio
	 * Solo se usa en los tests unitarios
	 * @param rsl
	 * @throws RemoteException
	 */
	public ControllerEditarDatosSocio(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}

	/**
	 * metodo para editar los datos de un socio, , llama al metodo de la clase Server
	 * @param nombreSocio
	 * @param datosNuevos
	 * @return true si se hecho correctamente, false si se da Exception e
	 */
	public boolean updateDatosSocio(String nombreSocio, String datosNuevos) {
		try {
			logger.info("###ControllerEditarDatosSocio: ServiceLocator.getService().updateDatosSocio###");
			return rsl.getService().updateDatosSocio(nombreSocio, datosNuevos);
		}catch(Exception e) {
			logger.error("$ Error al insertar película " + e.getMessage());
			//System.err.println("$ Error al insertar película " + e.getMessage());
			return false;

		}
	}

}
