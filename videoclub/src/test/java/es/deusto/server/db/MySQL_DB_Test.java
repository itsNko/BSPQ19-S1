package es.deusto.server.db;

import es.deusto.client.data.Socio;

public class MySQL_DB_Test {

	private MySQL_DB db;
	
	private Socio s;

	//@Before
	public void setUp() {
		db = new MySQL_DB();
		s = new Socio("Lucas", "12345678A", 20.25, "imagenSocio.png");
	}
	
	//@Test
	public void testInsertarSocioBien() {
		//assertTrue(db.insertarSocio(s));
	}
	// https://db.apache.org/jdo/pm.html
	
//	@After
//	public void tearDown() {
//		if (transaction.isActive()) {
//			transaction.rollback();
//		}
//
//		persistentManager.close();
//	}
	
}
