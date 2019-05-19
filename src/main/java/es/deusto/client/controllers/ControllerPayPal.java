package es.deusto.client.controllers;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.client.remote.ServiceLocator;

/**
 * clase ControllerPayPal
 *
 */
public class ControllerPayPal {

	private ServiceLocator rsl = ControllerRegistro.getRsl();
	Logger logger = LoggerFactory.getLogger("ClientLog");

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
	 * @return true si se hace correctamente\nfalse si se da Exception
	 */
	public boolean pagarPayPal(String nombrePaypal, String password, double cantidad) {
		try {
			logger.info("###ControllerPayPal: ServiceLocator.getService().pagarPayPal###");
			return rsl.getService().pagarPaypal(nombrePaypal, password, cantidad);
		}catch(Exception e) {
			logger.error("$ Error al pagar con PayPal " + e.getMessage());
			//System.err.println("$ Error al pagar con PayPal " + e.getMessage());
			return false;
		}
	}
}
