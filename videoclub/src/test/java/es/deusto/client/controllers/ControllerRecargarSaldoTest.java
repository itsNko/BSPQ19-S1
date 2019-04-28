package es.deusto.client.controllers;

import static org.junit.Assert.assertEquals;

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
public class ControllerRecargarSaldoTest {

	private ControllerRecargarSaldo crs;

	@Mock
	private ServiceLocator rsl;
	@Mock
	private IServer server;
	
	private SocioDTO s;

	@Before
	public void setUp() {
		s = new SocioDTO("Pepe", "1111111A", 0.0, "imagenTest.jpg");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void selectSocioTest() {
		try {
			crs = new ControllerRecargarSaldo(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().selectSocio(s.getNombre())).thenReturn(s);
			
			assertEquals(s, crs.selectSocio(s.getNombre()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}
