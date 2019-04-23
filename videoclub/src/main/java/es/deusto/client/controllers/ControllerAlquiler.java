package es.deusto.client.controllers;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Socio;
import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.AlquilerDTO;
import es.deusto.server.dto.SocioDTO;

public class ControllerAlquiler {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	public ControllerAlquiler() throws RemoteException {
	}

	public boolean insertarAlquiler(Articulo articulo, double coste, String nombreUsuario) {
		boolean correcto;
		try {
			System.out.println("###ControllerAlquiler: ServiceLocator.getService().insertarAlquiler###");
			correcto = rsl.getService().insertarAlquiler(articulo, coste, nombreUsuario);
			System.out.println("###ControllerAlquiler: Se ha insertado alquiler correctamente###");

			return correcto;
		}catch(Exception e) {
			System.err.println("$ Error al insertar alquiler " + e.getMessage());
			return false;

		}
	}
	
	public SocioDTO selectSocio(String nombreUsuario) {
		try {
			System.out.println("###ControllerAlquiler: ServiceLocator.getService().selectSocio###");
			return rsl.getService().selectSocio(nombreUsuario);

		}catch(Exception e) {
			System.err.println("$ Error al seleccionar socio " + e.getMessage());
			return new SocioDTO("", "", 0, "");
		}
	}

	public List<AlquilerDTO> historialAlquileres(String nombreSocio) {
		try {
			return rsl.getService().historialAlquileres(nombreSocio); 
		} catch(Exception e) {
			System.err.println("$ Error al devolver historial de alquileres " + e.getMessage());
			return null;
		}
	}
	
	public boolean updateMonedero(String nombreUsuario, double monedero) {
		try {
			System.out.println("###ControllerRecargarSaldo: ServiceLocator.getService().updateMonedero###");
			return rsl.getService().updateMonedero(nombreUsuario, monedero);
		}catch(Exception e) {
			System.err.println("$ Error al actualizar monedero " + e.getMessage());
			return false;
		}
	}
	

	public void exit(){
		System.exit(0);
	}

}
