package es.deusto.client.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.ArticuloDTO;
import es.deusto.server.dto.PeliculaDTO;
import es.deusto.server.remote.IServer;

public class ControllerListadoArticulosTest {

	private ControllerListadoArticulos cla;
	private List<ArticuloDTO> articulosDTO;

	@Mock
	private ServiceLocator rsl;
	@Mock
	private IServer server;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		articulosDTO = new ArrayList<ArticuloDTO>();
		articulosDTO.add(new PeliculaDTO("Pelicula", 3.5, "Sinopsis", "Genero", "01/05/2000", 5, "caratula.png", 0));
		try {
			cla = new ControllerListadoArticulos(rsl);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void constructorTest() {
		try {
			assertNotNull(new ControllerListadoArticulos());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listadoArticulosTestBien() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().listadoArticulos()).thenReturn(articulosDTO);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(articulosDTO, cla.listadoArticulos());
	}
	
	@Test
	public void listadoArticulosTestMal() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().listadoArticulos()).thenThrow(RemoteException.class);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		assertNull(cla.listadoArticulos());
	}
}
