@Test
public void assembleWithNullAlquileresReturnsEmptyList() {
    List<Alquiler> alquileres = null;
    List<AlquilerDTO> alquileresDTO = sut.assemble(alquileres);
    assertTrue(alquileresDTO.isEmpty());
}

@Test
public void assembleWithEmptyAlquileresReturnsEmptyList() {
    List<Alquiler> alquileres = new ArrayList<>();
    List<AlquilerDTO> alquileresDTO = sut.assemble(alquileres);
    assertTrue(alquileresDTO.isEmpty());
}

@Test
public void assembleWithPeliculaAlquilerReturnsPeliculaDTO() {
    articulo = new Pelicula("Pelicula-1", 1.0, "Sinopsis", "Genero", "2024-03-19", 10.0, "./caratula1.jpg", 0.1);
    alquiler = new Alquiler(articulo, 10.0, "2024-03-19", "2024-03-19", true, "pelicula1-Pelicula");
    alquileres.add(alquiler);

    List<AlquilerDTO> alquileresDTO = sut.assemble(alquileres);
    assertEquals("Pelicula-1", alquileresDTO.get(0).getAlquilado().getNombre());
}

@Test
public void assembleWithVideojuegoAlquilerReturnsVideojuegoDTO() {
    articulo = new Videojuego("Videojuego-1", 1.0, "Sinopsis", "Genero", "2024-03-19", 10.0, "./caratula1.jpg", 0.1);
    alquiler = new Alquiler(articulo, 10.0, "2024-03-19", "2024-03-19", true, "pelicula1-Videojuego");
    alquileres.add(alquiler);

    List<AlquilerDTO> alquileresDTO = sut.assemble(alquileres);
    assertEquals("Videojuego-1", alquileresDTO.get(0).getAlquilado().getNombre());
}

@Test
public void assembleWithLibroAlquilerReturnsEmptyList() {
    articulo = new Videojuego("Videojuego-1", 1.0, "Sinopsis", "Genero", "2024-03-19", 10.0, "./caratula1.jpg", 0.1);
    alquiler = new Alquiler(articulo, 10.0, "2024-03-19", "2024-03-19", true, "pelicula1-Libro");
    alquileres.add(alquiler);

    List<AlquilerDTO> alquileresDTO = sut.assemble(alquileres);
    assertTrue(alquileresDTO.isEmpty());
}

@Test
public void assembleWithEmptyAlquileresReturnsEmptyList() {
    List<AlquilerDTO> alquileresDTO = sut.assemble(alquileres);
    assertTrue(alquileresDTO.isEmpty());
}