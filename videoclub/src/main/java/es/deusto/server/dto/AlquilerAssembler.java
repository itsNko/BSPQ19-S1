package es.deusto.server.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.client.data.Alquiler;

public class AlquilerAssembler {

	private static AlquilerAssembler instance = new AlquilerAssembler();

	private AlquilerAssembler() {}

	public static AlquilerAssembler getInstance() {
		return instance;
	}

	public List<AlquilerDTO> assemble(List<Alquiler> alquileres) {
		List<AlquilerDTO> alquileresDTO = new ArrayList<AlquilerDTO>();

		System.out.println("* Assembling Alquileres ...");
		
		for (Alquiler alq : alquileres) {
			alquileresDTO.add(new AlquilerDTO(alq.getAlquilado(), alq.getCoste(), alq.getFecha_inicio(), 
					alq.getFecha_fin(), alq.isEnCurso()));
		}

		return alquileresDTO;
	}
}
