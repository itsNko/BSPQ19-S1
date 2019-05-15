package es.deusto.client.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.rmi.RemoteException;

import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.SocioDTO;
import es.deusto.server.remote.IServer;

@RunWith(MockitoJUnitRunner.class)
public class ControllerPayPalTest {

	private ControllerPayPal cpp;
	private SocioDTO s;
	
	@Mock
	private ServiceLocator rsl;
	@Mock
	private IServer server;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		s = new SocioDTO("Pepe", "1111111A", "Pepe", "Apellidos", "Direccion de Pepe", 0.0, "imagenTest.jpg");
		try {
			cpp = new ControllerPayPal(rsl);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void constructorTest() {
		try {
			assertNotNull(new ControllerPayPal());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void pagarPayPalBienTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().pagarPaypal(s.getNombre(), s.getPassword(), 20.5)).thenReturn(true);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(cpp.pagarPayPal(s.getNombre(), s.getPassword(), 20.5));
	}
	
	@Test
	public void pagarPayPalMalTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().pagarPaypal(s.getNombre(), s.getPassword(), 20.5)).thenThrow(RemoteException.class);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertFalse(cpp.pagarPayPal(s.getNombre(), s.getPassword(), 20));
	}
	
}
