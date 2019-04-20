package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.data.Socio;
import es.deusto.client.remote.ServiceLocator;

public class ControllerRecargarSaldo {
	
	private static ServiceLocator rsl = ControllerRegistro.getRsl();

	public ControllerRecargarSaldo() throws RemoteException {
	}
	
	public Socio selectSocio(String nombreUsuario) {
		try {
			System.out.println("###ControllerRecargarSaldo: ServiceLocator.getService().selectSocio###");
			return rsl.getService().selectSocio(nombreUsuario);

		}catch(Exception e) {
			System.err.println("$ Error al seleccionar socio " + e.getMessage());
			return new Socio();
		}
	}
	
	public boolean updateMonedero(String nombreUsuario, double monedero) {
		try {
			System.out.println("###ControllerRecargarSaldo: ServiceLocator.getService().updateMonedero###");
			return rsl.getService().updateMonedero(nombreUsuario, monedero);
		}catch(Exception e) {
			System.err.println("$ Error al actualizar monedero " + e.getMessage());
			return false;
		}
	}

	public void exit(){
		System.exit(0);
	}
}
