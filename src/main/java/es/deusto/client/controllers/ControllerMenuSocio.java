package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

public class ControllerMenuSocio {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	public ControllerMenuSocio() throws RemoteException {
	}
	
	public ControllerMenuSocio(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}

	public boolean updateBloquearMaquina(String nombreAdmin) {
		try {
			return rsl.getService().bloquearMaquina(nombreAdmin);
		} catch (RemoteException e) {
			System.err.println("$ Error al bloquear la maquina " + e.getMessage());
			return false;
		}
		
	}
}
