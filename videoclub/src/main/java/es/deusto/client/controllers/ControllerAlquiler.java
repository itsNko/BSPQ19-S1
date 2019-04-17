package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.data.Articulo;
import es.deusto.client.remote.ServiceLocator;

public class ControllerAlquiler {

	private static ServiceLocator rsl = ControllerRegistro.getRsl();

	public ControllerAlquiler() throws RemoteException {
	}
	
	
	public boolean insertarAlquiler(Articulo articulo, double coste, String nombreUsuario) {
		boolean correcto;
		try {
			System.out.println("###ControllerAlquiler: ServiceLocator.getService().insertarAlquiler###");
			correcto = rsl.getService().insertarAlquiler(articulo, coste, nombreUsuario);
			System.out.println("###ControllerAlquiler: Se ha insertado alquiler correctamente###");

			return correcto;
		}catch(Exception e) {
			System.err.println("$ Error al insertar alquiler " + e.getMessage());
			return false;

		}
	}

	public void exit(){
		System.exit(0);
	}

}
