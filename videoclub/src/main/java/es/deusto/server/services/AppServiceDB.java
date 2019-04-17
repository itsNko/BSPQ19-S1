package es.deusto.server.services;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Socio;
import es.deusto.server.db.MySQL_DB;
import es.deusto.server.dto.ArticuloAssembler;
import es.deusto.server.dto.ArticuloDTO;
import es.deusto.server.db.IDAO;

public class AppServiceDB {

	private IDAO db;
	private ArticuloAssembler artAssem;

	public AppServiceDB() throws RemoteException {
		db = new MySQL_DB();
		artAssem = ArticuloAssembler.getInstance();
	}

	public boolean insertarSocio(String nombre, String pass, double monedero) {
		Socio s = new Socio(nombre, pass, monedero, "default-profile.png");
		return db.insertarSocio(s);
	}
	
	public boolean existeSocio(String nombreSocio) {
		return db.existeSocio(nombreSocio);
	}
	
	public boolean inicioSesion(String nombreSocio, String password) {
		return db.inicioSesion(nombreSocio, password);
	}
	
	
	public boolean insertarAlquiler(Articulo articulo, double coste)
	{
		Alquiler alquiler = new Alquiler(articulo, coste, "fechaInicio", "fechaFin",true);
		return db.insertarAlquiler(alquiler);
	}
	
	public List<ArticuloDTO> listadoArticulos() {
		List<Articulo> articulos = db.listadoArticulos();
		
		return artAssem.assemble(articulos);
	}

}
