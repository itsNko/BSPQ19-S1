package es.deusto.client.controllers;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.client.data.Pelicula;
import es.deusto.client.remote.ServiceLocator;

public class ControllerArticulos {
	
	private ServiceLocator rsl = ControllerRegistro.getRsl();
	
	public ControllerArticulos() throws RemoteException {
	}
	
	public boolean insertarPelicula(Pelicula pelicula) {
		boolean correcto;
		try {
			System.out.println("###ControllerAlquiler: ServiceLocator.getService().insertarAlquiler###");
			correcto = rsl.getService().insertarPelicula(pelicula);
			System.out.println("###ControllerAlquiler: Se ha insertado alquiler correctamente###");

			return correcto;
		}catch(Exception e) {
			System.err.println("$ Error al insertar película " + e.getMessage());
			return false;

		}

}
	
	public void exit(){
		System.exit(0);
	}
}
