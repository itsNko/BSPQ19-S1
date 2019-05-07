package es.deusto.client.controllers;

import java.rmi.RemoteException;

import org.junit.Before;
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
}
