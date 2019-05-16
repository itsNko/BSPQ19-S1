package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

/**
 * clase ControllerEditarDatosSocio
 *
 */
public class ControllerEditarDatosSocio {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

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
			return rsl.getService().updateDatosSocio(nombreSocio, datosNuevos);
		}catch(Exception e) {
			System.err.println("$ Error al insertar película " + e.getMessage());
			return false;

		}
	}

}
