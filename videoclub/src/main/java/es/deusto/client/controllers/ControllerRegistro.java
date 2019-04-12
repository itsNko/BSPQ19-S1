package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.gui.VentanaInicio;
import es.deusto.client.remote.ServiceLocator;

public class ControllerRegistro {

	private static ServiceLocator rsl;

	public ControllerRegistro(String[] args) throws RemoteException {
		rsl = new ServiceLocator();
		new VentanaInicio(this);
		System.out.println("###ControllerRegistro: Constructor --> ServiceLocator.setService()###");
		rsl.setService(args[0], args[1], args[2]);
	}

	public boolean registro(String email, String pass, double monedero){
		boolean b;
		try {
			System.out.println("###ControllerRegistro: ServiceLocator.getService().register###");
			b = rsl.getService().registro(email, pass, monedero);
			if(b) {
				System.out.println("###ControllerRegistro: Se ha registrado correctamente###");
			} else {
				System.out.println("###ControllerRegistro: No se ha registrado correctamente###");
			}
			return b;
		} catch (Exception e){
			System.err.println("$ Error al registrarse " + e.getMessage());
			return false;
		}
	}

	public void exit(){
		System.exit(0);
	}

	public static void main(String[] args) throws RemoteException {    	
		new ControllerRegistro(args);
	}

	public static ServiceLocator getRsl() {
		return rsl;
	}

}
