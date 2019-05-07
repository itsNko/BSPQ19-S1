package es.deusto.server.dto;

import es.deusto.client.data.Socio;

public class SocioAssembler {

	private static SocioAssembler instance = new SocioAssembler();

	private SocioAssembler() {}

	public static SocioAssembler getInstance() {
		return instance;
	}

	public SocioDTO assemble(Socio socio) {
		System.out.println("* Assembling Socio ...");
		
		SocioDTO socioDTO = new SocioDTO(socio.getNombre(), socio.getPassword(), socio.getNombreCompleto(), socio.getApellidos(), socio.getDireccion(), socio.getMonedero(), socio.getImagen());
		socioDTO.setAlquileres(socio.getAlquileres());
		
		return socioDTO;
	}
}
