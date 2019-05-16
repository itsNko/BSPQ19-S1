package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

/**
 * clase ControllerConfirmacionDevolucion
 *
 */
public class ControllerConfirmacionDevolucion {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	/**
	 * Constructor vacio ControllerConfirmacionDevolucion
	 * @throws RemoteException
	 */
	public ControllerConfirmacionDevolucion() throws RemoteException {
	}
	/**
	 * Constructor ControllerConfirmacionDevolucion
	 * @param rsl
	 * @throws RemoteException
	 */
	public ControllerConfirmacionDevolucion(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}

	/**
	 * Metodo que permite devolver un alquiler, llama al metodo de la clase Server
	 * @param nombreUsuario
	 * @param nombreArticulo
	 * @param valoracion
	 * @return true si se hace correctamente, false si se da Exception e
	 */
	public boolean devolverAlquiler(String nombreUsuario, String nombreArticulo, int valoracion) {
		boolean correcto;
		try {
			System.out.println("###ControllerConfirmarDevolucion: ServiceLocator.getService().devolverAlquiler###");
			correcto = rsl.getService().devolverAlquiler(nombreUsuario, nombreArticulo, valoracion);
			System.out.println("###ControllerConfirmarDevolucion: Se ha devuelto el alquiler correctamente###");

			return correcto;
		}catch(Exception e) {
			System.err.println("$ Error al devolver alquiler " + e.getMessage());
			return false;

		}
	}
}
