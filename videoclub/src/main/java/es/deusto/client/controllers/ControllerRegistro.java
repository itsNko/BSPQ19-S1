package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.gui.VentanaInicio;
import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.SocioDTO;

public class ControllerRegistro {

	private static ServiceLocator rsl;

	public ControllerRegistro(String[] args) throws RemoteException {
		rsl = new ServiceLocator();
		new VentanaInicio(this);
		System.out.println("###ControllerRegistro: Constructor --> ServiceLocator.setService()###");
		rsl.setService(args[0], args[1], args[2]);
	}
	
	public ControllerRegistro() throws RemoteException{
	}

	public boolean registro(String nombre, String pass, double monedero) {
		boolean b;
		try {
			System.out.println("###ControllerRegistro: ServiceLocator.getService().register###");
			b = rsl.getService().registro(nombre, pass, monedero);
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
	
	public boolean existeSocio(String nombreSocio) {
		try {
			return rsl.getService().existeSocio(nombreSocio);
		} catch (Exception e){
			System.err.println("$ Error al comprobar si existe el socio " + e.getMessage());
			return false;
		}
	}
	
	public SocioDTO inicioSesion(String nombreSocio, String password) {
		try {
			return rsl.getService().inicioSesion(nombreSocio, password);
		} catch (Exception e){
			System.err.println("$ Error al iniciar sesion " + e.getMessage());
			return null;
		}
	}

	public static void main(String[] args) throws RemoteException {    	
		new ControllerRegistro(args);
	}

	public static ServiceLocator getRsl() {
		return rsl;
	}

}
