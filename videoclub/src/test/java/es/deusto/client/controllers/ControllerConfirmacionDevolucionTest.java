package es.deusto.client.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.AlquilerDTO;
import es.deusto.server.dto.SocioDTO;
import es.deusto.server.remote.IServer;

public class ControllerConfirmacionDevolucionTest {
	
	private ControllerConfirmacionDevolucion crs;

	@Mock
	private ServiceLocator rsl;
	@Mock
	private IServer server;
	
	private String nombreUsuario;
	private String nombreArticulo;
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void constructorTest() {
		try {
			assertNotNull(new ControllerConfirmacionDevolucion());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void devolverAlquilerBienTest() {
		try {
			crs = new ControllerConfirmacionDevolucion(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().devolverAlquiler(nombreUsuario, nombreArticulo)).thenReturn(true);
			
			assertTrue(crs.devolverAlquiler(nombreUsuario, nombreArticulo));
			
		} catch (RemoteException e) {
			e.printStackTrace();		
		}
	}
	
	@Test
	public void devolverAlquilerMalTest() {
		try {
			crs = new ControllerConfirmacionDevolucion(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().devolverAlquiler(nombreUsuario, nombreArticulo)).thenThrow(RemoteException.class);
						
		} catch (RemoteException e) {
			e.printStackTrace();		
		}
		assertFalse(crs.devolverAlquiler(nombreUsuario, nombreArticulo));
	}
}
