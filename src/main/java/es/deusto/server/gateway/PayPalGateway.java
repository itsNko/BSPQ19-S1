package es.deusto.server.gateway;

import java.rmi.RemoteException;

import es.deusto.paypal.server.IPayPalServer;

public class PayPalGateway implements IGatewayPago {

	private String serverIP;
	private int serverPort;
	private String serverName;
	private IPayPalServer service;
	
	public PayPalGateway(String ip, int port, String serverName) throws RemoteException {
		this.serverIP = ip;
		this.serverPort = port;
		this.serverName = serverName;

		try {		
			String URL = "//" + this.serverIP + ":" + this.serverPort + "/" + this.serverName;
			System.out.println("###PayPalGateway: (IPayPalServer) lookup()###");
			this.service = (IPayPalServer) java.rmi.Naming.lookup(URL);
			System.out.println("###PayPalGateway: Se ha encontrado el servidor: //" + this.serverIP + ":" + this.serverPort + "/" + this.serverName + " correctamente###");
		} catch (Exception ex) {
			System.err.println("# Error locating remote PayPalServer: " + ex);
		}	
		
	}

	public IPayPalServer getService() {
		return this.service;
	}

	@Override
	public boolean pagar(String nombre, String password, double cantidad) throws RemoteException {
		return this.service.pagar(nombre, password, cantidad);
	}
}
