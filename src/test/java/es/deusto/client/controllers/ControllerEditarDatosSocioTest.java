package es.deusto.client.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.SocioDTO;
import es.deusto.server.remote.IServer;

@RunWith(MockitoJUnitRunner.class)
public class ControllerEditarDatosSocioTest {
	
	private ControllerEditarDatosSocio ceds;
	private SocioDTO s;

	String datosNuevos = "NombreCompleto;Apellido1 Apellido2;1111111A;Nueva Direccion";
	@Mock
	private ServiceLocator rsl;
	@Mock
	private IServer server;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		s = new SocioDTO("Pepe", "1111111A", "Pepe", "Apellidos", "Direccion de Pepe", 0.0, "imagenTest.jpg");
		try {
			ceds = new ControllerEditarDatosSocio(rsl);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void constructorTest() {
		try {
			assertNotNull(new ControllerEditarDatosSocio());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateDatosSocioBien() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().updateDatosSocio(s.getNombre(), datosNuevos)).thenReturn(true);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(ceds.updateDatosSocio(s.getNombre(), datosNuevos));
	}
	
	@Test
	public void updateDatosSocioMal() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().updateDatosSocio(s.getNombre(), datosNuevos)).thenThrow(RemoteException.class);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertFalse(ceds.updateDatosSocio(s.getNombre(), datosNuevos));
	}
}
