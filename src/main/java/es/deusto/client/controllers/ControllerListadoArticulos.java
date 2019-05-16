package es.deusto.client.controllers;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.ArticuloDTO;

/**
 * clase ControllerListadoArticulos
 *
 */
public class ControllerListadoArticulos {

	private ServiceLocator rsl = ControllerRegistro.getRsl();

	/**
	 * Constructor vacio ControllerListadoArticulos
	 * @throws RemoteException
	 */
	public ControllerListadoArticulos() throws RemoteException {
	}
	
	/**
	 * Constructor ControllerListadoArticulos
	 * solo se usa en los tests unitarios
	 * @param rsl
	 * @throws RemoteException
	 */
	public ControllerListadoArticulos(ServiceLocator rsl) throws RemoteException {
		this.rsl = rsl;
	}

	/**
	 * metodo para obtener el listado de articulos, llama al metodo de la clase Server
	 * @return si se hace correctamente una lista de ArticuloDTO, si se da Exception e
	 * se devuelve null
	 */
	public List<ArticuloDTO> listadoArticulos() {
		try {
			return rsl.getService().listadoArticulos();
		} catch (Exception e){
			System.err.println("$ Error al obtener el listado de articulos " + e.getMessage());
			return null;
		}
	}
}
