package es.deusto.client.remote;

import es.deusto.server.remote.IServer;

public class ServiceLocator {
	private IServer service;

	public void setService(String ip, String port, String serverName) {
		
		try {		
			String URL = "//" + ip + ":" + port + "/" + serverName;
			System.out.println("###ServiceLocator: (IServer) lookup()###");
			this.service = (IServer) java.rmi.Naming.lookup(URL);
			System.out.println("###ServiceLocator: Se ha encontrado el servidor:  //" + ip + ":" + port + "/" + serverName + " correctamente###");
		} catch (Exception ex) {
			System.err.println("# Error locating remote 'Server': " + ex);
		}		
	}

	public IServer getService() {
		System.out.println("###ServiceLocator: (getService) Se obtiene servicio del servidor...###");
		return this.service;
	}
}
