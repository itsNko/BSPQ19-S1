package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

public class ControllerArticulos {
	
	private ServiceLocator rsl = ControllerRegistro.getRsl();
	
	public ControllerArticulos() throws RemoteException {
	}
	
	// Solo utilizar este constructor en los tests unitarios
	public ControllerArticulos(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}
	
	public boolean insertarPelicula(String nombre, double precio, String sinopsis, String genero, String fecha_estr, double puntuacion, String caratula, double descuento) {
		boolean correcto;
		try {
			System.out.println("###ControllerArticulos: ServiceLocator.getService().insertarPelicula###");
			correcto = rsl.getService().insertarPelicula(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, descuento);
			System.out.println("###ControllerAlquiler: Se ha insertado la película correctamente###");

			return correcto;
		}catch(Exception e) {
			System.err.println("$ Error al insertar película " + e.getMessage());
			return false;

		}

}
	
	public boolean insertarVideojuego(String nombre, double precio, String descripcion, String categoria, String fecha_lanz, double puntuacion, String caratula, double descuento) {
		boolean correcto;
		try {
			System.out.println("###ControllerArticulos: ServiceLocator.getService().insertarVideojuego###");
			correcto = rsl.getService().insertarVideojuego(nombre, precio, descripcion, categoria, fecha_lanz, puntuacion, caratula, descuento);
			System.out.println("###ControllerAlquiler: Se ha insertado el videojuego correctamente###");

			return correcto;
		}catch(Exception e) {
			System.err.println("$ Error al insertar videojuego " + e.getMessage());
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
}
