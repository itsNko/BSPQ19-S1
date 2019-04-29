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
	
	public ControllerRegistro(ServiceLocator rsl) throws RemoteException {
		ControllerRegistro.rsl = rsl;
	}

	public boolean registro(String nombre, String pass, double monedero) {
		try {
			System.out.println("###ControllerRegistro: ServiceLocator.getService().register###");
			return rsl.getService().registro(nombre, pass, monedero);
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
