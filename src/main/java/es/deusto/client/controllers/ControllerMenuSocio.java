package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

/**
 * clase ControllerMenuSocio
 *
 */
public class ControllerMenuSocio {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

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
			return rsl.getService().bloquearMaquina(nombreAdmin);
		} catch (RemoteException e) {
			System.err.println("$ Error al bloquear la maquina " + e.getMessage());
			return false;
		}
		
	}
}
