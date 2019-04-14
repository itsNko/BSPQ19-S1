package es.deusto.server.db;

import static org.junit.Assert.assertTrue;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.client.data.Socio;

public class MySQL_DB_Test {

	private PersistenceManagerFactory persistentManagerFactory;
	private PersistenceManager persistentManager;			
	private Transaction transaction;
	
	private Socio s;

	@Before
	public void setUp() {
		persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		persistentManager = null;			
		transaction = null;
		
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();	
		
		s = new Socio("Lucas", "12345678A", 20.25, "imagenSocio.png");
	}
	
	@Test
	public void testInsertarSocio() {
		try {
			transaction.begin();
			persistentManager.makePersistent(s);

			System.out.println("% 'Test insertar socio' --> Inserted into db: " + s.getNombre());

			transaction.commit();
			
			System.out.println("% 'Test insertar socio' --> Retrieving an Extent for Socios.");
			Extent<Socio> extent = persistentManager.getExtent(Socio.class, true);

			for (Socio socio : extent) {
				if(socio == s) {
					assertTrue(true);
					break;
				}
			}
	
			transaction.begin();
			persistentManager.deletePersistent(s);
			transaction.commit();
			
		} catch(Exception ex) {
			System.err.println("& 'Test insertar socio' --> Exception inserting data into db: " + ex.getMessage());
		}
			
	}
	
	@After
	public void tearDown() {
		if (transaction.isActive()) {
			transaction.rollback();
		}

		persistentManager.close();
	}
	
}
