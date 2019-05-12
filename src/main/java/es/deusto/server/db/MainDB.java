package es.deusto.server.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;
import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Socio;
import es.deusto.client.data.Videojuego;

public class MainDB {

	public static void main(String[] args) {
		try {
			PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			//
			//			//Insert data in the DB
			PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();				
			Transaction transaction = persistentManager.currentTransaction();
			
			Socio s1 = new Socio("Prueba1", "1111111A", "Prueba", "Uno Nueva", "Dirección 1", 20.75, "default-profile.png");
			Socio s2 = new Socio("Prueba2", "2222222B", "Prueba", "Dos Nueva", "Dirección 2", 10, "default-profile.png");
			Socio s3 = new Socio("Prueba3", "3333333C", "Prueba", "Tres Nueva", "Dirección 3", 0, "default-profile.png");

			//Cuenta especial para el empleado
			Socio admin = new Socio("Administrador", "admin", "Manolo", "García", "Dirección del administrador", 0 , "default-profile.png");
			admin.setBloquearMaquina(false);
			
			//Cuenta especial para el director
			Socio director = new Socio("Director", "direct", "Juanjo", "Lorenzo", "Director general",0, "default-profile.png");
			director.setBloquearMaquina(false);
			
			Articulo art1 = new Pelicula("Los vengadores", 5.5, "Descripcion de Los Vengadores", "Acción","20/09/2014", 9, "vengadores.jpg",0);
			Articulo art2 = new Pelicula("Harry Potter",5, "Descripcion de Harry Potter", "Acción","29/01/2009", 9, "harryPotter.jpg",0);
			Articulo art3 = new Pelicula("Star Wars I", 6.25, "Descripcion de Star Wars I", "Ciencia ficción","13/06/2010", 9, "starWars.jpg",0);

			Articulo art4 = new Videojuego("Sonic", 5, "Descripcion de Sonic", "Plataformas","10/02/2004", 7, "sonic.JPG",0);
			Articulo art5 = new Videojuego("Mario Bros", 4.75, "Descripcion de Mario", "Aventura","31/03/2008", 8.5, "mario.jpg",0);
			Articulo art6 = new Videojuego("GTA V", 7, "Descripcion de GTA V", "Acción","20/03/2015", 9, "GTAV.jpg",0);
//
			Alquiler alquiler1 = new Alquiler(art1, art1.getPrecio(), "15/04/2019", "20/04/2019", true, art1.getNombre() + "-Pelicula");
			Alquiler alquiler2 = new Alquiler(art2, art2.getPrecio(), "15/04/2019", "20/04/2019", true, art2.getNombre() + "-Pelicula");
//
						try {
							transaction.begin();
			
							persistentManager.makePersistent(s1);
							persistentManager.makePersistent(s2);
							persistentManager.makePersistent(s3);

							persistentManager.makePersistent(admin);
							persistentManager.makePersistent(director);
//			
							System.out.println("- Inserted into db: " + s1.getNombre());
							System.out.println("- Inserted into db: " + s2.getNombre());
							System.out.println("- Inserted into db: " + s3.getNombre());
							
							System.out.println("- Inserted into db: " + admin.getNombre());
							System.out.println("- Inserted into db: " + director.getNombre());
			
							persistentManager.makePersistent(art1);
							persistentManager.makePersistent(art2);
							persistentManager.makePersistent(art3);
							persistentManager.makePersistent(art4);
							persistentManager.makePersistent(art5);
							persistentManager.makePersistent(art6);
			
							System.out.println("- Inserted into db: " + art1.getNombre());
							System.out.println("- Inserted into db: " + art2.getNombre());
							System.out.println("- Inserted into db: " + art3.getNombre());
							System.out.println("- Inserted into db: " + art4.getNombre());
							System.out.println("- Inserted into db: " + art5.getNombre());
							System.out.println("- Inserted into db: " + art6.getNombre());
			
							transaction.commit();
						} catch(Exception ex) {
							System.err.println("* Exception inserting data into db: " + ex.getMessage());
						} finally {		    
							if (transaction.isActive()) {
								transaction.rollback(); // Deshace los cambios en caso de que de algun error
							}
			
							persistentManager.close();
						}
//			
//						//Select data using a Query and delete data
						persistentManager = persistentManagerFactory.getPersistenceManager();
						transaction = persistentManager.currentTransaction();
			
						try {
							transaction.begin();
			
							@SuppressWarnings("unchecked")
							Query<Socio> usuariosQuery = persistentManager.newQuery("SELECT FROM " + Socio.class.getName());
							for (Socio socio : usuariosQuery.executeList()) {
								System.out.println("- Selected from db: " + socio.getNombre());
								if(socio.getNombre().equals("Prueba3")) {
									// Borra el socio con nombre 'Fran'
									persistentManager.deletePersistent(socio);
									System.out.println("- Deleted from db: " + socio.getNombre());
								}
			
							}
			
							transaction.commit();
						} catch(Exception ex) {
							System.err.println("* Exception executing a query: " + ex.getMessage());
						} finally {
							if (transaction.isActive()) {
								transaction.rollback();
							}
			
							persistentManager.close();
						}
			
						// Añadir alquiler a socio
						persistentManager = persistentManagerFactory.getPersistenceManager();
						transaction = persistentManager.currentTransaction();
			
						try {
							transaction.begin();
			
							Extent<Articulo> a = persistentManager.getExtent(Articulo.class, true);
							Iterator<Articulo> iter2 = a.iterator();
			
							while (iter2.hasNext()) {
								Articulo art = (Articulo) iter2.next();
								if (art.getNombre().equals(alquiler1.getAlquilado().getNombre())) {
									alquiler1.setAlquilado(art);
								}
							}
							transaction.commit();
			
							transaction.begin();
			
							Extent<Articulo> a2 = persistentManager.getExtent(Articulo.class, true);
							Iterator<Articulo> iter3 = a2.iterator();
			
							while (iter3.hasNext()) {
								Articulo art = (Articulo) iter3.next();
								if (art.getNombre().equals(alquiler2.getAlquilado().getNombre())) {
									alquiler2.setAlquilado(art);
								}
							}
							transaction.commit();
			
							transaction.begin();
							Extent<Socio> e = persistentManager.getExtent(Socio.class, true);
							Iterator<Socio> iter = e.iterator();
			
							while (iter.hasNext()) {
								Socio s = (Socio) iter.next();
								if (s.getNombre().equals("Prueba1")) {
									System.out.println("- Añadidos alquileres a socio: " + s.getNombre());
									s.getAlquileres().add(alquiler1);
									s.getAlquileres().add(alquiler2);
								}
							}
			
							transaction.commit();
						} catch(Exception ex) {
							System.err.println("* Exception executing a query: " + ex.getMessage());
						} finally {
							if (transaction.isActive()) {
								transaction.rollback();
							}
			
							persistentManager.close();
						}

						// Update de un atributo socio y a su vez de un alquiler suyo
						persistentManager = persistentManagerFactory.getPersistenceManager();
						transaction = persistentManager.currentTransaction();
			
						try {
							transaction.begin();
			
							Extent<Socio> e = persistentManager.getExtent(Socio.class, true);
							Iterator<Socio> iter = e.iterator();
			
							@SuppressWarnings("unchecked")
							Query<Alquiler> alquilerQuery = persistentManager.newQuery("SELECT FROM " + Alquiler.class.getName());
			
							while (iter.hasNext()) {
								Socio s = (Socio) iter.next();
								if(s.getNombre().equals("Prueba1")) {
									for(Alquiler alquiler: alquilerQuery.executeList()) {
										if(alquiler.isEnCurso() && alquiler.getAlquilado().getNombre().equals(art1.getNombre()) && s.getAlquileres().contains(alquiler)) {
											System.out.println("- Data modified: " + alquiler.isEnCurso() + " ---> FALSE");
											alquiler.setEnCurso(false);
										}
									}
								}
			
								if (s.getNombre().equals("Prueba2")) {
									System.out.println("- Data modified: " + s.getPassword() + " ---> " + s.getPassword() + "M");
									s.setPassword(s.getPassword() + "M");
								}
							}
			
							transaction.commit();
						} catch(Exception ex) {
							System.err.println("* Exception executing a query: " + ex.getMessage());
						} finally {
							if (transaction.isActive()) {
								transaction.rollback();
							}
			
							persistentManager.close();
						}
						persistentManager = persistentManagerFactory.getPersistenceManager();
						transaction = persistentManager.currentTransaction();
						try {
						transaction.begin();
						Query query = persistentManager.newQuery("javax.jdo.query.SQL", "SELECT * FROM SOCIO WHERE NOMBRE = 'iker'");
						query.setClass(Socio.class);
						query.setUnique(true);
						Socio socio = (Socio) query.execute();
						System.out.println(socio.getMonedero());
						}catch(Exception ex) {
							System.err.println("* Exception: " + ex.getMessage());
						}
						
						
						persistentManager = persistentManagerFactory.getPersistenceManager();
						transaction = persistentManager.currentTransaction();
						try {
							transaction.begin();
							@SuppressWarnings("unchecked")
							Query<Alquiler> query = persistentManager.newQuery("javax.jdo.query.SQL",
									"SELECT * FROM ALQUILER WHERE ALQUILERES_NOMBRE_OWN = '" + "Prueba1" + "'");
							query.setClass(Alquiler.class);
							List<Alquiler> alquileres = (List<Alquiler>) new ArrayList<Alquiler>();
							for(Alquiler alquiler:alquileres) {
								System.out.println(alquiler.getAlquilado());
							}
						} catch (Exception ex) {
							System.err.println("* Exception: " + ex.getMessage());
							
						}

			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();
			try {
				transaction.begin();

				Query q = persistentManager.newQuery("SELECT FROM " + Socio.class.getName());
				List<Socio> socios = q.executeList();
				Iterator<Socio> iter = socios.iterator();
				
				Socio socio = null;
				
				List<Alquiler> alquileres = new ArrayList<Alquiler>();
				while (iter.hasNext())
				{
					Socio s = iter.next();

					if(s.getNombre().equals("Prueba1")) {
						socio = s;
						alquileres = s.getAlquileres();
					}
				}

				transaction.commit();

				System.out.println(alquileres.get(0).getAlquilado().getNombre());
				System.out.println(alquileres.get(0).getAlquilado().getCaratula());
				System.out.println(socio.getPassword());
			} catch(Exception ex) {
				System.err.println("* Exception executing a query: " + ex.getMessage());
			} finally {
				if (transaction.isActive()) {
					transaction.rollback();
				}

				persistentManager.close();
			}
			
			Socio so = persistentManager.getObjectById(Socio.class, "Prueba1");

	        System.out.println(so.getNombre());
	        System.out.println(so.getMonedero());
	        System.out.println(so.getAlquileres().get(0).getNombreArticulo());
	        
	        Articulo art = persistentManager.getObjectById(Pelicula.class, so.getAlquileres().get(0).getNombreArticulo());
	        System.out.println(art.getNombre());
	        
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}

	}

}
