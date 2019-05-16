package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

/**
 * clase ControllerPayPal
 *
 */
public class ControllerPayPal {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	/**
	 * Constructor vacio ControllerPayPal
	 * @throws RemoteException
	 */
	public ControllerPayPal() throws RemoteException {
	}
	
	/**
	 * Constructor ControllerPayPal
	 * Solo se usa en los test unitarios
	 * @param rsl
	 * @throws RemoteException
	 */
	public ControllerPayPal(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}
	
	/**
	 * Permite pagar usando el metodo de pago de PayPal pasando el nombre
	 * y constraseña para iniciar sesion en PayPal y la cantidad a pagar
	 * , llama al metodo de la clase Server
	 * @param nombrePaypal
	 * @param password
	 * @param cantidad
	 * @return true si se hace correctamente, false si se da Exception
	 */
	public boolean pagarPayPal(String nombrePaypal, String password, double cantidad) {
		try {
			return rsl.getService().pagarPaypal(nombrePaypal, password, cantidad);
		}catch(Exception e) {
			System.err.println("$ Error al pagar con PayPal " + e.getMessage());
			return false;
		}
	}
}
