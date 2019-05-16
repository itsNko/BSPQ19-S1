package es.deusto.client.controllers;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.client.gui.VentanaInicio;
import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.SocioDTO;

/**
 * clase ControllerRegistro
 *
 */
public class ControllerRegistro {

	private static ServiceLocator rsl;
	Logger logger = LoggerFactory.getLogger("ClientLog");

	/**
	 * Constructor ControllerRegistro con los argumentos
	 * @param args
	 * @throws RemoteException
	 */
	public ControllerRegistro(String[] args) throws RemoteException {
		rsl = new ServiceLocator();
		new VentanaInicio(this);
		System.out.println("###ControllerRegistro: Constructor --> ServiceLocator.setService()###");
		rsl.setService(args[0], args[1], args[2]);
	}
	
	/**
	 * Constructor vacio ControllerRegistro
	 * @throws RemoteException
	 */
	public ControllerRegistro() throws RemoteException{
	}
	
	/**
	 * Constructor ControllerRegistro con ServiceLocator rsl
	 * @param rsl
	 * @throws RemoteException
	 */
	public ControllerRegistro(ServiceLocator rsl) throws RemoteException {
		ControllerRegistro.rsl = rsl;
	}

	/**
	 * 
	 * @param nombre
	 * @param pass
	 * @param monedero
	 * @return
	 */
	public boolean registro(String nombre, String pass, double monedero) {
		try {
			logger.info("###ControllerRegistro: ServiceLocator.getService().register###");
			//System.out.println("###ControllerRegistro: ServiceLocator.getService().register###");
			return rsl.getService().registro(nombre, pass, monedero);
		} catch (Exception e){
			logger.error("$ Error al registrarse " + e.getMessage());
			//System.err.println("$ Error al registrarse " + e.getMessage());
			return false;
		}
	}
	
	public boolean existeSocio(String nombreSocio) {
		try {
			logger.info("###ControllerRegistro: ServiceLocator.getService().existeSocio###");
			return rsl.getService().existeSocio(nombreSocio);
		} catch (Exception e){
			logger.error("$ Error al comprobar si existe el socio " + e.getMessage());
			//System.err.println("$ Error al comprobar si existe el socio " + e.getMessage());
			return false;
		}
	}
	
	public SocioDTO inicioSesion(String nombreSocio, String password) {
		try {
			logger.info("###ControllerRegistro: ServiceLocator.getService().inicioSesion###");
			return rsl.getService().inicioSesion(nombreSocio, password);
		} catch (Exception e){
			logger.error("$ Error al iniciar sesion " + e.getMessage());
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
