package es.deusto.server.services;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Socio;
import es.deusto.client.data.Videojuego;
import es.deusto.server.db.MySQL_DB;
import es.deusto.server.dto.AlquilerAssembler;
import es.deusto.server.dto.AlquilerDTO;
import es.deusto.server.dto.ArticuloAssembler;
import es.deusto.server.dto.ArticuloDTO;
import es.deusto.server.dto.SocioAssembler;
import es.deusto.server.dto.SocioDTO;
import es.deusto.server.db.IDAO;

public class AppServiceDB {

	private IDAO db;
	private ArticuloAssembler artAssem;
	private SocioAssembler socioAssem;
	private AlquilerAssembler alqAssem;

	public AppServiceDB() throws RemoteException {
		db = new MySQL_DB();
		artAssem = ArticuloAssembler.getInstance();
		socioAssem = SocioAssembler.getInstance();
		alqAssem = AlquilerAssembler.getInstance();
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
	
	
	public boolean insertarAlquiler(String nombre, double precio, String sinopsis, String genero, String fecha_estr, double puntuacion, String caratula, double coste, String nombreUsuario, boolean pv, String fechaFin, String fechaInicio, double descuento)
	{
		Articulo articulo;
		Alquiler alquiler;
		if(pv)
		{
			articulo = new Pelicula(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, descuento);
			alquiler = new Alquiler(articulo, coste, fechaInicio, fechaFin ,true, articulo.getNombre()+"-Pelicula");

		}else
		{
			articulo = new Videojuego(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, descuento);
			alquiler = new Alquiler(articulo, coste, fechaInicio, fechaFin,true, articulo.getNombre()+"-Videojuego");
		}
		return db.insertarAlquiler(alquiler, nombreUsuario);
	}
	
	public List<ArticuloDTO> listadoArticulos() {
		return artAssem.assemble(db.listadoArticulos());
	}
	
	public List<AlquilerDTO> historialAlquileres(String nombreSocio) {
		List<Alquiler> alquileres = db.historialAlquileres(nombreSocio);

		return alqAssem.assemble(alquileres);
	}

	public SocioDTO selectSocio(String nombreUsuario) {
		return socioAssem.assemble(db.selectSocio(nombreUsuario));
	}

	public boolean updateMonedero(String nombreUsuario, double monedero) {
		return db.updateMonedero(nombreUsuario, monedero);
	}
	

	public boolean updateDescuento(String nombreArticulo, double descuento) {
		return db.updateMonedero(nombreArticulo, descuento);
	}

	public boolean insertarPelicula(Pelicula pelicula) {
		
		return db.insertarPelicula(pelicula);
	}

}
