package es.deusto.server.db;

import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Socio;

public interface IDAO {

	public boolean insertarSocio(Socio s);
	//public boolean deleteSocio(Socio s);
	public boolean existeSocio(String nombreSocio);
	public Socio inicioSesion(String nombreSocio, String password);
	public boolean insertarAlquiler(Alquiler alquiler, String nombreUsuario);
	
	public List<Articulo> listadoArticulos();
	public Socio selectSocio(String nombreUsuario);
	public List<Alquiler> selectAlquilerPorSocio(String nombreUsuario);
	public boolean updateMonedero(String nombreUsuario, Double monedero);
	
	public List<Alquiler> historialAlquileres(String nombreSocio);
	
}
