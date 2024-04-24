package es.deusto.server.dto;

import org.junit.Before;
import org.junit.Test;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Videojuego;

import static org.junit.Assert.*;
import java.util.List;

public class AlquilerAssemblerTotalTest {

        private AlquilerAssembler alquilerAssembler, potato, patata;

        @Before
        public void setUp() {
                alquilerAssembler = AlquilerAssembler.getInstance();
        }

        @Test
        public void testAssemble() {
                // Prepare test data
                Pelicula pelicula = new Pelicula("Articulo-Pelicula", 10.0, "Some sinopsis", "Some genre", "2022-01-01",
                                8.0,
                                "Some caratula", 0.0);
                Alquiler alquilerPelicula = new Alquiler(pelicula, 10.0, "2022-01-01", "2022-01-02", true,
                                "Articulo-Pelicula");

                Videojuego videojuego = new Videojuego("Articulo-Videojuego", 20.0, "Some description", "Some category",
                                "2022-01-01", 9.0, "Some caratula", 0.0);
                Alquiler alquilerVideojuego = new Alquiler(videojuego, 20.0, "2022-01-01", "2022-01-02", false,
                                "Articulo-Videojuego");

                List<Alquiler> alquileres = List.of(alquilerPelicula, alquilerVideojuego);

                // Execute the method
                List<AlquilerDTO> alquileresDTO = alquilerAssembler.assemble(alquileres);

                // Verify the result
                assertEquals(alquileres.size(), alquileresDTO.size());

                for (int i = 0; i < alquileres.size(); i++) {
                        Alquiler alquiler = alquileres.get(i);
                        AlquilerDTO alquilerDTO = alquileresDTO.get(i);

                        assertEquals(alquiler.getAlquilado().getNombre(), alquilerDTO.getAlquilado().getNombre());
                        assertEquals(alquiler.getCoste(), alquilerDTO.getCoste(), 0.001);
                        assertEquals(alquiler.getFecha_inicio(), alquilerDTO.getFecha_inicio());
                        assertEquals(alquiler.getFecha_fin(), alquilerDTO.getFecha_fin());
                        assertEquals(alquiler.isEnCurso(), alquilerDTO.isEnCurso());
                }
        }

        @Test
        public void testAssembleWithNullInput() {
                // Execute the method with null input
                List<AlquilerDTO> alquileresDTO = alquilerAssembler.assemble(null);

                // Verify the result
                assertTrue(alquileresDTO.isEmpty());
        }

        @Test
        public void testAssembleWithVideojuego() {
                // Prepare test data
                Videojuego videojuego = new Videojuego("Articulo-Videojuego", 20.0, "Some description", "Some category",
                                "2022-01-01", 9.0, "Some caratula", 0.0);
                Alquiler alquilerVideojuego = new Alquiler(videojuego, 20.0, "2022-01-01", "2022-01-02", false,
                                "Articulo-Videojuego");

                // Execute the method
                List<AlquilerDTO> alquileresDTO = alquilerAssembler.assemble(List.of(alquilerVideojuego));

                // Verify the result
                assertEquals(1, alquileresDTO.size());
                AlquilerDTO alquilerDTO = alquileresDTO.get(0);
                assertEquals(videojuego.getNombre(), alquilerDTO.getAlquilado().getNombre());
                assertEquals(20.0, alquilerDTO.getCoste(), 0.001);
                assertEquals("2022-01-01", alquilerDTO.getFecha_inicio());
                assertEquals("2022-01-02", alquilerDTO.getFecha_fin());
                assertFalse(alquilerDTO.isEnCurso());
        }

        // Black Box Tests using Equivalence Partitioning and Boundary Value Analysis

        @Test
        public void testAssembleWithEmptyList() {
                // Prepare test data
                List<Alquiler> alquileres = List.of();

                // Execute the method
                List<AlquilerDTO> alquileresDTO = alquilerAssembler.assemble(alquileres);

                // Verify the result
                assertTrue(alquileresDTO.isEmpty());
        }

        @Test
        public void testAssembleWithMultipleAlquileres() {
                // Prepare test data
                Pelicula pelicula1 = new Pelicula("Articulo-Pelicula", 10.0, "Some sinopsis", "Some genre",
                                "2022-01-01", 8.0,
                                "Some caratula", 0.0);
                Alquiler alquilerPelicula1 = new Alquiler(pelicula1, 10.0, "2022-01-01", "2022-01-02", true,
                                "Articulo-Pelicula");

                Videojuego videojuego = new Videojuego("Articulo-Videojuego", 15.0, "Some sinopsis", "Some genre",
                                "2022-01-01",
                                8.0,
                                "Some caratula", 0.0);
                Alquiler alquilerVideojuego = new Alquiler(videojuego, 15.0, "2022-01-01", "2022-01-02", true,
                                "Articulo-Videojuego");

                List<Alquiler> alquileres = List.of(alquilerPelicula1, alquilerVideojuego);

                // Execute the method
                List<AlquilerDTO> alquileresDTO = alquilerAssembler.assemble(alquileres);

                // Verify the result
                assertNotNull(alquileresDTO);
                assertTrue(alquileresDTO.size() > 0);
                assertEquals(alquileres.size(), alquileresDTO.size());
        }
}