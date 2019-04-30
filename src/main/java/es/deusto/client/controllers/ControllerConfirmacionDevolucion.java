package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

public class ControllerConfirmacionDevolucion {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	public ControllerConfirmacionDevolucion() throws RemoteException {
	}
	public ControllerConfirmacionDevolucion(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}

	public boolean devolverAlquiler(String nombreUsuario, String nombreArticulo) {
		boolean correcto;
		try {
			System.out.println("###ControllerConfirmarDevolucion: ServiceLocator.getService().devolverAlquiler###");
			correcto = rsl.getService().devolverAlquiler(nombreUsuario, nombreArticulo);
			System.out.println("###ControllerConfirmarDevolucion: Se ha devuelto el alquiler correctamente###");

			return correcto;
		}catch(Exception e) {
			System.err.println("$ Error al devolver alquiler " + e.getMessage());
			return false;

		}
	}
}
