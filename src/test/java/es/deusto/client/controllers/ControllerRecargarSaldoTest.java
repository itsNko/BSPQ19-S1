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
public class ControllerRecargarSaldoTest {

	private ControllerRecargarSaldo crs;
	private SocioDTO s;
	
	@Mock
	private ServiceLocator rsl;
	@Mock
	private IServer server;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		s = new SocioDTO("Pepe", "1111111A", 0.0, "imagenTest.jpg");
		try {
			crs = new ControllerRecargarSaldo(rsl);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void constructorTest() {
		try {
			assertNotNull(new ControllerRecargarSaldo());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectSocioBienTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().selectSocio(s.getNombre())).thenReturn(s);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertEquals(s, crs.selectSocio(s.getNombre()));
	}

	@Test
	public void selectSocioMalTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().selectSocio(s.getNombre())).thenThrow(RemoteException.class);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		SocioDTO devuelto = crs.selectSocio(s.getNombre());
		SocioDTO vacio = new SocioDTO("", "", 0, "");
		
		boolean igual = false;
		if(devuelto.getNombre().equals(vacio.getNombre()) && devuelto.getPassword().equals(vacio.getPassword())
				&& devuelto.getMonedero() == vacio.getMonedero() && devuelto.getImagen().equals(vacio.getImagen())) {
			igual = true;
		}

		assertTrue(igual);

	}

	@Test
	public void updateMonederoBienTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().updateMonedero(s.getNombre(), s.getMonedero())).thenReturn(true);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(crs.updateMonedero(s.getNombre(), s.getMonedero()));
	}
	
	@Test
	public void updateMonederoMalTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().updateMonedero(s.getNombre(), s.getMonedero())).thenThrow(RemoteException.class);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		assertFalse(crs.updateMonedero(s.getNombre(), s.getMonedero()));
	}
}
