package es.deusto.server.db;

import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Socio;

public interface IDAO {

	public boolean insertarSocio(Socio s);
	public boolean existeSocio(String nombreSocio);
	public boolean inicioSesion(String nombreSocio, String password);
	boolean insertarAlquiler(Alquiler alquiler);
	
	public List<Articulo> listadoArticulos();
	
}
