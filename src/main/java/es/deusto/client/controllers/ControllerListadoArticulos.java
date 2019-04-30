package es.deusto.client.controllers;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.ArticuloDTO;

public class ControllerListadoArticulos {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	public ControllerListadoArticulos() throws RemoteException {
	}
	
	// Solo utilizar este constructor en los tests unitarios
	public ControllerListadoArticulos(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}

	public List<ArticuloDTO> listadoArticulos() {
		try {
			return rsl.getService().listadoArticulos();
		} catch (Exception e){
			System.err.println("$ Error al obtener el listado de articulos " + e.getMessage());
			return null;
		}
	}
}
