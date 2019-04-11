package es.deusto.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

	String sayHello() throws RemoteException;

}
