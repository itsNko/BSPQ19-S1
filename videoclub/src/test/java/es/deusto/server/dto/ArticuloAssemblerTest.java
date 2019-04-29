package es.deusto.server.dto;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Videojuego;

public class ArticuloAssemblerTest {

	private ArticuloAssembler artAssem;
	private List<Articulo> articulos;
	private List<ArticuloDTO> articulosDTO;
	
	@Before
	public void setUp() {
		artAssem = ArticuloAssembler.getInstance();
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(ArticuloAssembler.getInstance());
	}
	
	@Test
	public void ArticuloAssembleVideojuegoTest() {
		Articulo juego = new Videojuego("", 0, "", "", "", 0, "", 0);
		articulos = new ArrayList<Articulo>();
		articulos.add(juego);
		 articulosDTO = artAssem.assemble(articulos);
		
		boolean igual = false;
		
		if(articulos.get(0).getCaratula().equals(articulosDTO.get(0).getCaratula()) &&
				articulos.get(0).getDescuento() == articulosDTO.get(0).getDescuento() &&
				articulos.get(0).getNombre().equals(articulosDTO.get(0).getNombre()) &&
				articulos.get(0).getPrecio() == articulosDTO.get(0).getPrecio()) {
			igual = true;
		}

		assertTrue(igual);
	}
	
	@Test
	public void ArticuloAssemblePeliculaTest() {
		Articulo peli = new Pelicula("", 0, "", "", "", 0, "", 0);
		articulos = new ArrayList<Articulo>();
		articulos.add(peli);
		 articulosDTO = artAssem.assemble(articulos);
		
		boolean igual = false;
		
		if(articulos.get(0).getCaratula().equals(articulosDTO.get(0).getCaratula()) &&
				articulos.get(0).getDescuento() == articulosDTO.get(0).getDescuento() &&
				articulos.get(0).getNombre().equals(articulosDTO.get(0).getNombre()) &&
				articulos.get(0).getPrecio() == articulosDTO.get(0).getPrecio()) {
			igual = true;
		}

		assertTrue(igual);
	}
}
