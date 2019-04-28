package es.deusto.client.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
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
		s = new SocioDTO("Pepe", "1111111A", 0.0, "imagenTest.jpg");
		MockitoAnnotations.initMocks(this);
		
	}

	@Test
	public void insertarAlquilerTest() {
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
	public void selectSocioTest() {
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
	public void historialAlquileresTest() {
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
	public void updateMonederoTest() {
		try {
			crs = new ControllerAlquiler(rsl);
			
			when(rsl.getService()).thenReturn(server);
			when(rsl.getService().updateMonedero(nombreUsuario, monedero)).thenReturn(true);
			
			assertTrue(crs.updateMonedero(nombreUsuario, monedero));
		}catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
