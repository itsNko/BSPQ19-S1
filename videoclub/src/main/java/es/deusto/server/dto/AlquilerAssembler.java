package es.deusto.server.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Videojuego;

public class AlquilerAssembler {

	private static AlquilerAssembler instance = new AlquilerAssembler();
	private PeliculaAssembler pelAssem;
	private VideojuegoAssembler vidAssem;

	private AlquilerAssembler() {
		pelAssem = PeliculaAssembler.getInstance();
		vidAssem = VideojuegoAssembler.getInstance();
	}

	public static AlquilerAssembler getInstance() {
		return instance;
	}

	public List<AlquilerDTO> assemble(List<Alquiler> alquileres) {

		List<AlquilerDTO> alquileresDTO = new ArrayList<AlquilerDTO>();

		if(!(alquileres.isEmpty() || alquileres == null)) {
			System.out.println("* Assembling Alquileres ...");

			String[] partes;
			for (Alquiler alq : alquileres) {
				partes = alq.getNombreArticulo().split("-");
				if(partes[1].equals("Pelicula")) {
					Pelicula t = (Pelicula) alq.getAlquilado();
					PeliculaDTO pelDTO = pelAssem.assemble(t);
					alquileresDTO.add(new AlquilerDTO(pelDTO, alq.getCoste(), alq.getFecha_inicio(), 
							alq.getFecha_fin(), alq.isEnCurso()));
				} else if(partes[1].equals("Videojuego")) {
					Videojuego t = (Videojuego) alq.getAlquilado();
					VideojuegoDTO videojuegoDTO = vidAssem.assemble(t);
					alquileresDTO.add(new AlquilerDTO(videojuegoDTO, alq.getCoste(), alq.getFecha_inicio(), 
							alq.getFecha_fin(), alq.isEnCurso()));
				}

			}
			System.out.println("Después de Assembler alquileres");
			System.out.println(alquileresDTO.get(0).getFecha_fin());
			System.out.println(alquileresDTO.get(0).getAlquilado().getNombre());

		}
		
		return alquileresDTO;
	}
}
