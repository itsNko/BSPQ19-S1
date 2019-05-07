package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

public class ControllerEditarDatosSocio {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	public ControllerEditarDatosSocio() throws RemoteException {
	}

	// Solo utilizar este constructor en los tests unitarios
	public ControllerEditarDatosSocio(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}

	public boolean updateDatosSocio(String nombreSocio, String datosNuevos) {
		try {
			return rsl.getService().updateDatosSocio(nombreSocio, datosNuevos);
		}catch(Exception e) {
			System.err.println("$ Error al insertar película " + e.getMessage());
			return false;

		}
	}

}
