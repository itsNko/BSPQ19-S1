package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

public class ControllerArticulo {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	public ControllerArticulo() throws RemoteException {
	}
	
	public boolean updateDescuento(String nombreArticulo, double descuento) {
		try {
			System.out.println("###ControllerRecargarSaldo: ServiceLocator.getService().updateMonedero###");
			return rsl.getService().updateMonedero(nombreArticulo, descuento);
		}catch(Exception e) {
			System.err.println("$ Error al actualizar monedero " + e.getMessage());
			return false;
		}
	}
}
