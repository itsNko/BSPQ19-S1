package es.deusto.server.db;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.client.data.Socio;

public class MySQL_DB implements IDAO {

	PersistenceManagerFactory persistentManagerFactory;
	PersistenceManager persistentManager;			
	Transaction transaction;


	public MySQL_DB() {
		persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		persistentManager = null;			
		transaction = null;
	}

	@Override
	public boolean insertarSocio(Socio socio) {
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();	
			transaction.begin();


			persistentManager.makePersistent(socio);

			System.out.println("- Inserted into db: " + socio.getNombre());

			transaction.commit();

			return true;
		} catch(Exception ex) {
			System.err.println("* Exception inserting data into db: " + ex.getMessage());
			return false;
		} finally {		    
			if (transaction.isActive()) {
				transaction.rollback(); // Deshace los cambios en caso de que ocurra algún error
			}

			persistentManager.close();
		}
	}

	@Override
	public boolean existeSocio(String nombreSocio) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		persistentManager.getFetchPlan().setMaxFetchDepth(1);

		transaction = persistentManager.currentTransaction();

		boolean existe = false;
		try {
			System.out.println("* Retrieving an Extent for Products.");

			transaction.begin();
			Extent<Socio> extent = persistentManager.getExtent(Socio.class, true);


			for (Socio socio : extent) {
				if(socio.getNombre().equals(nombreSocio)) {
					existe = true;
					break;
				}
			}


			transaction.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retrieving an extent: " + ex.getMessage());
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}

		return existe;
	}

}
