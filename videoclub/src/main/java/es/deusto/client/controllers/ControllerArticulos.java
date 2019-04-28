package es.deusto.client.controllers;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.client.data.Pelicula;
import es.deusto.client.remote.ServiceLocator;

public class ControllerArticulos {
	
	private ServiceLocator rsl = ControllerRegistro.getRsl();
	
	public ControllerArticulos() throws RemoteException {
	}
	
	public boolean insertarPelicula(String nombre, double precio, String sinopsis, String genero, String fecha_estr, double puntuacion, String caratula, double descuento) {
		boolean correcto;
		try {
			System.out.println("###ControllerArticulos: ServiceLocator.getService().insertarPelicula###");
			correcto = rsl.getService().insertarPelicula(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, descuento);
			System.out.println("###ControllerAlquiler: Se ha insertado alquiler correctamente###");

			return correcto;
		}catch(Exception e) {
			System.err.println("$ Error al insertar película " + e.getMessage());
			return false;

		}

}
	
	public boolean updateDescuento(String nombreArticulo, double descuento) {
		try {
			System.out.println("###ControllerArticulos: ServiceLocator.getService().updateDescuento###");
			return rsl.getService().updateDescuento(nombreArticulo, descuento);
		}catch(Exception e) {
			System.err.println("$ Error al actualizar descuento " + e.getMessage());
			return false;
		}
	}
	
	public boolean updatePrecio(String nombreArticulo, double precio) {
		try {
			System.out.println("###ControllerArticulos: ServiceLocator.getService().updatePrecio###");
			return rsl.getService().updatePrecio(nombreArticulo, precio);
		}catch(Exception e) {
			System.err.println("$ Error al actualizar precio " + e.getMessage());
			return false;
		}
	}
	
	public void exit(){
		System.exit(0);
	}
}
