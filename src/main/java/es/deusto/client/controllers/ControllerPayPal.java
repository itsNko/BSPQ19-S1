package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

public class ControllerPayPal {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	public ControllerPayPal() throws RemoteException {
	}
	
	// Solo utilizar este constructor en los tests unitarios
	public ControllerPayPal(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}
	
	public boolean pagarPayPal(String nombrePaypal, String password, double cantidad) {
		try {
			return rsl.getService().pagarPaypal(nombrePaypal, password, cantidad);
		}catch(Exception e) {
			System.err.println("$ Error al pagar con PayPal " + e.getMessage());
			return false;
		}
	}
}
