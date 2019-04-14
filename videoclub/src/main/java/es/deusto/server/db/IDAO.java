package es.deusto.server.db;

import es.deusto.client.data.Socio;

public interface IDAO {

	public boolean insertarSocio(Socio s);
	public boolean existeSocio(String nombreSocio);
	public boolean inicioSesion(String nombreSocio, String password);
	
}
