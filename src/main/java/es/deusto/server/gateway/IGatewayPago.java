package es.deusto.server.gateway;

import java.rmi.RemoteException;

public interface IGatewayPago {
	public boolean pagar(String nombre, String password, double cantidad) throws RemoteException;
}