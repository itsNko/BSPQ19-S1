import org.junit.Test;
import static org.junit.Assert.*;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Pelicula;
import es.deusto.server.dto.AlquilerAssembler;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Videojuego;
import es.deusto.server.dto.AlquilerAssembler;
import es.deusto.server.dto.AlquilerDTO;
import es.deusto.server.dto.PeliculaAssembler;
import es.deusto.server.dto.PeliculaDTO;
import es.deusto.server.dto.VideojuegoAssembler;
import es.deusto.server.dto.VideojuegoDTO;

import java.util.Arrays;
import java.util.List;

public class AlquilerAssemblerTest {

    @Test
    public void testAssemble() {
        // Create sample Alquiler objects
        Pelicula pelicula1 = new Pelicula("The Avengers");
        Alquiler alquiler1 = new Alquiler(pelicula1, 10.0, "2022-01-01", "2022-01-07", true);
        Alquiler alquiler2 = new Alquiler(new Videojuego("FIFA 22"), 15.0, "2022-02-01", "2022-02-07", false);

        // Create a list of Alquiler objects
        List<Alquiler> alquileres = Arrays.asList(alquiler1, alquiler2);

        // Call the assemble method
        List<AlquilerDTO> alquileresDTO = AlquilerAssembler.getInstance().assemble(alquileres);

        // Assert the size of the returned list
        assertEquals(2, alquileresDTO.size());

        // Assert the properties of the first AlquilerDTO object
        AlquilerDTO alquilerDTO1 = alquileresDTO.get(0);
        assertEquals("The Avengers", alquilerDTO1.getAlquilado().getNombre());
        assertEquals(10.0, alquilerDTO1.getCoste(), 0.01);
        assertEquals("2022-01-01", alquilerDTO1.getFecha_inicio());
        assertEquals("2022-01-07", alquilerDTO1.getFecha_fin());
        assertTrue(alquilerDTO1.isEnCurso());

        // Assert the properties of the second AlquilerDTO object
        AlquilerDTO alquilerDTO2 = alquileresDTO.get(1);
        assertEquals("FIFA 22", alquilerDTO2.getAlquilado().getNombre());
        assertEquals(15.0, alquilerDTO2.getCoste(), 0.01);
        assertEquals("2022-02-01", alquilerDTO2.getFecha_inicio());
        assertEquals("2022-02-07", alquilerDTO2.getFecha_fin());
        assertFalse(alquilerDTO2.isEnCurso());
    }
}