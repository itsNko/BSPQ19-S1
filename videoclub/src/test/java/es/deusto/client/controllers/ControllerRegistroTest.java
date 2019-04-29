package es.deusto.client.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.client.gui.VentanaInicio;
import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.remote.IServer;

public class ControllerRegistroTest {

	private ControllerRegistro crs;

	@Mock
	private ServiceLocator rsl;
	@Mock
	private IServer server;
	
	private String[] args = {"","",""};
	private String nombre;
	private String pass;
	private double monedero;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void constructorTest() {
		try {
			assertNotNull(new ControllerRegistro());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void registroBienTest() {
//		try {
//			crs = new ControllerRegistro(args);
//			
//			when(rsl.getService()).thenReturn(server);
//			when(rsl.getService().registro(nombre, pass, monedero)).thenReturn(true);
//			
//			assertTrue(crs.registro(nombre, pass, monedero));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//	}
}
