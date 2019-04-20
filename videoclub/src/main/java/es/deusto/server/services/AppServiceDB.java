package es.deusto.server.services;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Socio;
import es.deusto.server.db.MySQL_DB;
import es.deusto.server.dto.ArticuloAssembler;
import es.deusto.server.dto.ArticuloDTO;
import es.deusto.server.dto.SocioAssembler;
import es.deusto.server.dto.SocioDTO;
import es.deusto.server.db.IDAO;

public class AppServiceDB {

	private IDAO db;
	private ArticuloAssembler artAssem;
	private SocioAssembler socioAssem;

	public AppServiceDB() throws RemoteException {
		db = new MySQL_DB();
		artAssem = ArticuloAssembler.getInstance();
		socioAssem = SocioAssembler.getInstance();
	}

	public boolean insertarSocio(String nombre, String pass, double monedero) {
		Socio s = new Socio(nombre, pass, monedero, "default-profile.png");
		return db.insertarSocio(s);
	}
	
	public boolean existeSocio(String nombreSocio) {
		return db.existeSocio(nombreSocio);
	}
	
	public SocioDTO inicioSesion(String nombreSocio, String password) {
		Socio socio = db.inicioSesion(nombreSocio, password);
		return socioAssem.assemble(socio);
	}
	
	
	public boolean insertarAlquiler(Articulo articulo, double coste, String nombreUsuario)
	{
		Alquiler alquiler = new Alquiler(articulo, coste, "fechaInicio", "fechaFin",true);
		return db.insertarAlquiler(alquiler, nombreUsuario);
	}
	
	public List<ArticuloDTO> listadoArticulos() {
		List<Articulo> articulos = db.listadoArticulos();
		
		return artAssem.assemble(articulos);
	}
	
	public Socio selectSocio(String nombreUsuario) {
		return db.selectSocio(nombreUsuario);
	}

	public boolean updateMonedero(String nombreUsuario, double monedero) {
		return db.updateMonedero(nombreUsuario, monedero);
	}

}
