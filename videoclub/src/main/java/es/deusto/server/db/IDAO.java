package es.deusto.server.db;

import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Socio;

public interface IDAO {

	public boolean insertarSocio(Socio s);
	public boolean existeSocio(String nombreSocio);
	public Socio inicioSesion(String nombreSocio, String password);
	boolean insertarAlquiler(Alquiler alquiler, String nombreUsuario);
	
	public List<Articulo> listadoArticulos();
	
	public List<Alquiler> historialAlquileres(String nombreSocio);
	
}
