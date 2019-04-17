package es.deusto.server.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Videojuego;

public class ArticuloAssembler {

	private static ArticuloAssembler instance = new ArticuloAssembler();

	private ArticuloAssembler() {}

	public static ArticuloAssembler getInstance() {
		return instance;
	}

	public List<ArticuloDTO> assemble(List<Articulo> articulos) {
		List<ArticuloDTO> articulosDTO = new ArrayList<ArticuloDTO>();

		System.out.println("* Assembling Articulos ...");
		
		for (Articulo v : articulos) {
			if(v.getClassName().equals("Videojuego")) {
				Videojuego juego = (Videojuego) v;
				articulosDTO.add(new VideojuegoDTO(juego.getNombre(), juego.getPrecio(), juego.getDescripcion(),
						juego.getCategoria(), juego.getFecha_lan(), juego.getPuntuacion(), juego.getCaratula()));
			} else {
				Pelicula peli = (Pelicula) v;
				articulosDTO.add(new PeliculaDTO(peli.getNombre(), peli.getPrecio(), peli.getSinopsis(), peli.getGenero(), 
						peli.getFecha_estr(), peli.getPuntuacion(), peli.getCaratula()));
			}
			
		}

		return articulosDTO;
	}
}
