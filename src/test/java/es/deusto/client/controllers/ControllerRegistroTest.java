package es.deusto.client.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.SocioDTO;
import es.deusto.server.remote.IServer;

public class ControllerRegistroTest {

	private ControllerRegistro cr;

	@Mock
	private ServiceLocator rsl;
	@Mock
	private IServer server;

	private String[] args = {"127.0.0.1", "1099", "Server"};
	private String nombre = "Prueba";
	private String pass = "1234";
	private String nombreCompleto = "NombreCompleto";
	private String apellidos = "Apellidos";
	private String direccion = "Direccion X";
	private double monedero = 4.2;
	private String imagen = "imagen.jpg";

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		try {
			cr = new ControllerRegistro(rsl);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void constructorTest() {
		try {
			assertNotNull(new ControllerRegistro(args));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void constructorVacioTest() {
		try {
			assertNotNull(new ControllerRegistro());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void registroBienTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().registro(nombre, pass, monedero)).thenReturn(true);

			assertTrue(cr.registro(nombre, pass, monedero));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void registroMalTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().registro(nombre, pass, monedero)).thenThrow(RemoteException.class);

			assertFalse(cr.registro(nombre, pass, monedero));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void existeSocioBienTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().existeSocio(nombre)).thenReturn(true);

			assertTrue(cr.existeSocio(nombre));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void existeSocioMalTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().existeSocio(nombre)).thenThrow(RemoteException.class);

			assertFalse(cr.existeSocio(nombre));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void inicioSesionBienTest() {
		SocioDTO socio = new SocioDTO(nombre, pass, nombreCompleto, apellidos, direccion, monedero, imagen);
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().inicioSesion(nombre, pass)).thenReturn(socio);

			assertEquals(socio, cr.inicioSesion(nombre, pass));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void inicioSesionMalTest() {
		try {
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().inicioSesion(nombre, pass)).thenThrow(RemoteException.class);

			assertNull(cr.inicioSesion(nombre, pass));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void mainTest() {
		try {
			ControllerRegistro.main(args);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
