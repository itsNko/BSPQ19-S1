package es.deusto.client.controllers;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;

/**
 * clase ControllerArticulos
 *
 */
public class ControllerArticulos {
	
	private ServiceLocator rsl = ControllerRegistro.getRsl();
	
	/**
	 * Constructor vacio ControllerArticulos
	 * @throws RemoteException
	 */
	public ControllerArticulos() throws RemoteException {
	}
	
	/**
	 * Constructor ControllerArticulos
	 * Solo se usa en los tests unitarios
	 * @param rsl
	 * @throws RemoteException
	 */
	public ControllerArticulos(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}
	
	/**
	 * Metodo para insertar una nueva pelicula, llama al metodo de la clase Server
	 * @param nombre
	 * @param precio
	 * @param sinopsis
	 * @param genero
	 * @param fecha_estr
	 * @param puntuacion
	 * @param caratula
	 * @param descuento
	 * @return true si se hace correctamente el metodo, false si se da Exception e
	 */
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
	
	/**
	 * Metodo para insertar un nuevo videojuego, llama al metodo de la clase Server
	 * @param nombre
	 * @param precio
	 * @param descripcion
	 * @param categoria
	 * @param fecha_lanz
	 * @param puntuacion
	 * @param caratula
	 * @param descuento
	 * @return true si se hace correctamente, false tras Exception e
	 */
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
	
	/**
	 * Metodo para actualizar el valor del descuento de un articulo
	 * Se le pasa el nombre del articulo al que se le quiere actualizar
	 * el descuento y el valor del descuento nuevo, llama al metodo de la clase Server
	 * @param nombreArticulo
	 * @param descuento
	 * @return true si se hace correctamente, false si se da Exception e
	 */
	public boolean updateDescuento(String nombreArticulo, double descuento) {
		try {
			System.out.println("###ControllerArticulos: ServiceLocator.getService().updateDescuento###");
			return rsl.getService().updateDescuento(nombreArticulo, descuento);
		}catch(Exception e) {
			System.err.println("$ Error al actualizar descuento " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Metodo para actualizar el valor del precio de un articulo
	 * se le pasa el nombre del articulo al que se le quiere actualizar
	 * el precio y el valor del precio nuevo, llama al metodo de la clase Server
	 * @param nombreArticulo
	 * @param precio
	 * @return true si se hace correctamente, false si se da Exception e
	 */
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
