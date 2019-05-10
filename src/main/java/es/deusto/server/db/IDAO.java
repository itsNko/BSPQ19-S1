package es.deusto.server.db;

import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Socio;
import es.deusto.client.data.Videojuego;

public interface IDAO {

	public boolean insertarSocio(Socio s);
	//public boolean deleteSocio(Socio s);
	public boolean existeSocio(String nombreSocio);
	public Socio inicioSesion(String nombreSocio, String password);
	public boolean insertarAlquiler(Alquiler alquiler, String nombreUsuario);
	public boolean insertarPelicula(Pelicula pelicula);
	public boolean insertarVideojuego(Videojuego videojuego);
	
	public List<Articulo> listadoArticulos();
	public Socio selectSocio(String nombreUsuario);
	public List<Alquiler> selectAlquilerPorSocio(String nombreUsuario);
	public boolean updateMonedero(String nombreUsuario, Double monedero);
	
	public List<Alquiler> historialAlquileres(String nombreSocio);
	
	public boolean updatePrecio(String nombreArticulo, double precio);

	public boolean updateDescuento(String nombreArticulo, double descuento);
	public boolean updateDatosSocio(String nombreSocio, String datosNuevos);
	public boolean devolverAlquiler(String nombreUsuario, String nombreArticulo, int valoracion);
	
	public boolean bloquearMaquina(String nombreAdmin);
}
