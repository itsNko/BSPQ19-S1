package es.deusto.server.dto;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Videojuego;

public class AlquilerAssemblerTest {

	private AlquilerAssembler alqAssem;
	private List<Alquiler> alquileres;
	private Articulo a;
	private String nombre = "nombre";
	private double precio = 0;
	private String sinopsis = "sinopsis";
	private String genero = "genero";
	private String fecha_estr = "fechaestr";
	private double puntuacion = 0;
	private String caratula = "caratula";
	private double descuento = 0;
	
	private Articulo b;
	private String descripcion = "descripcion";
	private String categoria = "categoria";
	private String fecha_lan = "fecha_lan";
	@Before
	public void setUp() {
		a = new Pelicula(nombre, precio, sinopsis, genero, fecha_estr, puntuacion, caratula, descuento);
		b = new Videojuego(nombre, precio, descripcion, categoria, fecha_lan, puntuacion, caratula, descuento);
		alqAssem = AlquilerAssembler.getInstance();
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(AlquilerAssembler.getInstance());
	}
	
	@Test
	public void AlquilerAssemblePeliculaTest() {
		Alquiler alquiler = new Alquiler(a, 0, "1", "2", false, "algo-Pelicula");
		alquileres = new ArrayList<Alquiler>();
		alquileres.add(alquiler);
		List<AlquilerDTO> alquileresDTO = alqAssem.assemble(alquileres);
		
		boolean igual = false;
		
		if(alquileres.get(0).getCoste() == alquileresDTO.get(0).getCoste() &&
				alquileres.get(0).getFecha_fin().equals(alquileresDTO.get(0).getFecha_fin()) &&
				alquileres.get(0).getFecha_inicio().equals(alquileresDTO.get(0).getFecha_inicio()) &&
				alquileres.get(0).isEnCurso() == alquileresDTO.get(0).isEnCurso()) {
			igual = true;
		}

		assertTrue(igual);
	}
	
	@Test
	public void AlquilerAssembleVideoJuegoTest() {
		Alquiler alquiler = new Alquiler(b, 0, "1", "2", false, "algo-Videojuego");
		alquileres = new ArrayList<Alquiler>();
		alquileres.add(alquiler);
		List<AlquilerDTO> alquileresDTO = alqAssem.assemble(alquileres);
		
		boolean igual = false;
		
		if(alquileres.get(0).getCoste() == alquileresDTO.get(0).getCoste() &&
				alquileres.get(0).getFecha_fin().equals(alquileresDTO.get(0).getFecha_fin()) &&
				alquileres.get(0).getFecha_inicio().equals(alquileresDTO.get(0).getFecha_inicio()) &&
				alquileres.get(0).isEnCurso() == alquileresDTO.get(0).isEnCurso()) {
			igual = true;
		}

		assertTrue(igual);
	}
}
