package es.deusto.client.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import es.deusto.client.remote.ServiceLocator;
import es.deusto.server.dto.AlquilerDTO;
import es.deusto.server.dto.SocioDTO;
import es.deusto.server.remote.IServer;

public class ControllerAlquilerTest {

	private ControllerAlquiler crs;

	@Mock
	private ServiceLocator rsl;
	@Mock
	private IServer server;
	
	private String nombre;
	private double precio;
	private String sinopsis;
	private String genero;
	private String fecha_estr;
	private double puntuacion;
	private String caratula;
	private double coste;
	private String nombreUsuario;
	private boolean pv;
	private String fechaFin;
	private String fechaInicio;
	private double descuento;
	private SocioDTO s;
	private List<AlquilerDTO> listaAlquilerDTO;
	private AlquilerDTO alquilerDTO;
	private double monedero;

	@Before
	public void setUp() {
		alquilerDTO = new AlquilerDTO(null, coste, caratula, caratula, pv);
		listaAlquilerDTO = new ArrayList<AlquilerDTO>();
		listaAlquilerDTO.add(alquilerDTO);
		s = new SocioDTO("Pepe", "1111111A", "Pepe", "Apellidos", "Direccion de Pepe", 0.0, "imagenTest.jpg");
		MockitoAnnotations.initMocks(this);
		
	}

	@Test
	public void constructorTest() {
		try {
			assertNotNull(new ControllerAlquiler());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertarAlquilerBienTest() {
		try {
			crs = new ControllerAlquiler(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().insertarAlquiler(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, coste, nombreUsuario, pv, fechaFin, fechaInicio, descuento)).thenReturn(true);
			
			assertTrue(crs.insertarAlquiler(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, coste, nombreUsuario, pv, fechaFin, fechaInicio, descuento));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertarAlquilerMalTest() {
		try {
			crs = new ControllerAlquiler(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().insertarAlquiler(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, coste, nombreUsuario, pv, fechaFin, fechaInicio, descuento)).thenThrow(RemoteException.class);
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		assertFalse(crs.insertarAlquiler(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, coste, nombreUsuario, pv, fechaFin, fechaInicio, descuento));
	}
		
	@Test
	public void selectSocioBienTest() {
		try {
			crs = new ControllerAlquiler(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().selectSocio(s.getNombre())).thenReturn(s);
			
			assertEquals(s, crs.selectSocio(s.getNombre()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectSocioMalTest() {
		try {
			crs = new ControllerAlquiler(rsl);
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().selectSocio(s.getNombre())).thenThrow(RemoteException.class);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		SocioDTO devuelto = crs.selectSocio(s.getNombre());
		SocioDTO vacio = new SocioDTO("", "", "", "", "", 0, "");
		
		boolean igual = false;
		if(devuelto.getNombre().equals(vacio.getNombre()) && devuelto.getPassword().equals(vacio.getPassword())
				&& devuelto.getNombreCompleto().equals(vacio.getNombreCompleto()) && devuelto.getApellidos().equals(vacio.getApellidos())
				&& devuelto.getDireccion().equals(vacio.getDireccion()) && devuelto.getMonedero() == vacio.getMonedero() 
				&& devuelto.getImagen().equals(vacio.getImagen())) {
			igual = true;
		}

		assertTrue(igual);

	}
	
	@Test
	public void historialAlquileresBienTest() {
		try {
			crs = new ControllerAlquiler(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().historialAlquileres(nombreUsuario)).thenReturn(listaAlquilerDTO);
			
			assertEquals(listaAlquilerDTO.size(), crs.historialAlquileres(nombreUsuario).size());
		}catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void historialAlquileresMalTest() {
		try {
			crs = new ControllerAlquiler(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().historialAlquileres(nombreUsuario)).thenThrow(RemoteException.class);

		}catch (RemoteException e) {
			e.printStackTrace();
		}
		
		assertNull(crs.historialAlquileres(nombreUsuario));
	}
	
	@Test
	public void updateMonederoBienTest() {
		try {
			crs = new ControllerAlquiler(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().updateMonedero(nombreUsuario, monedero)).thenReturn(true);
			
			assertTrue(crs.updateMonedero(nombreUsuario, monedero));
		}catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateMonederoMalTest() {
		try {
			crs = new ControllerAlquiler(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().updateMonedero(nombreUsuario, monedero)).thenThrow(RemoteException.class);
			
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		
		assertFalse(crs.updateMonedero(nombreUsuario, monedero));
	}
}
