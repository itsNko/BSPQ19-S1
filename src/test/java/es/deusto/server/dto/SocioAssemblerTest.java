package es.deusto.server.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.deusto.client.data.Socio;

public class SocioAssemblerTest {

	SocioAssembler socAssem;
	
	@Before
	public void setUp() {
		socAssem = SocioAssembler.getInstance();
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(SocioAssembler.getInstance());
	}
	
	@Test
	public void socioAssembleTest() {
		Socio socio = new Socio("Prueba", "1234", 5.5, "imagen.jpg");
		SocioDTO socioDTO = socAssem.assemble(socio);
		
		boolean igual = false;
		if(socio.getNombre().equals(socioDTO.getNombre()) && socio.getPassword().equals(socioDTO.getPassword())
				&& socio.getMonedero() == socioDTO.getMonedero() && socio.getImagen().equals(socioDTO.getImagen())) {
			igual = true;
		}

		assertTrue(igual);
	}
}
