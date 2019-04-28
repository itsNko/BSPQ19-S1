//package es.deusto.client.controllers;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import static org.mockito.Mockito.*;
//
//import java.rmi.RemoteException;
//
//import es.deusto.client.data.Socio;
//import es.deusto.client.remote.ServiceLocator;
//import es.deusto.server.dto.SocioDTO;
//
//public class ControllerRecargarSaldoTest {
//
//	@Mock
//	private ServiceLocator rsl;
//	
//	SocioDTO s;
//	
//	@Before
//	public void setUp() {
//		s = new SocioDTO("Pepe", "1111111A", 0.0, "imagenTest.jpg");
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	@Test
//	public void selectSocioTest() {
//		try {
//			when(rsl.getService().selectSocio(s.getNombre())).thenReturn(s);
//			assertEquals(s, rsl.getService().selectSocio(s.getNombre()));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//	}
//}
