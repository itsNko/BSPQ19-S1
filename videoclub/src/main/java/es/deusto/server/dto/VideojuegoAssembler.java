package es.deusto.server.dto;

import es.deusto.client.data.Videojuego;

public class VideojuegoAssembler {

	private static VideojuegoAssembler instance = new VideojuegoAssembler();

	private VideojuegoAssembler() {}

	public static VideojuegoAssembler getInstance() {
		return instance;
	}

	public VideojuegoDTO assemble(Videojuego juego) {
		System.out.println("* Assembling Videojuego ...");
		
		return new VideojuegoDTO(juego.getNombre(), juego.getPrecio(), juego.getDescripcion(),
				juego.getCategoria(), juego.getFecha_lan(), juego.getPuntuacion(), juego.getCaratula(), juego.getDescuento());
	}
}
