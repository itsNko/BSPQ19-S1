package es.deusto.server.dto;

import es.deusto.client.data.Pelicula;

public class PeliculaAssembler {

	private static PeliculaAssembler instance = new PeliculaAssembler();

	private PeliculaAssembler() {}

	public static PeliculaAssembler getInstance() {
		return instance;
	}

	public PeliculaDTO assemble(Pelicula peli) {
		System.out.println("* Assembling Pelicula ...");
		
		return new PeliculaDTO(peli.getNombre(), peli.getPrecio(), peli.getSinopsis(), peli.getGenero(), 
				peli.getFecha_estr(), peli.getPuntuacion(), peli.getCaratula());
	}
}
