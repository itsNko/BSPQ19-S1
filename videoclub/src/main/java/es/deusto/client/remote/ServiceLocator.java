package es.deusto.client.remote;

import java.rmi.RemoteException;
import es.deusto.server.IServer;

public class ServiceLocator {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			System.out.println(name);
			IServer stubServer = (IServer) java.rmi.Naming.lookup(name);
			System.out.println("* Message coming from the server: '" + stubServer.sayHello() + "'");

			try {
				stubServer.sayHello();
				
			} catch(RemoteException e) {
				System.err.println(e.getMessage());
			}

		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
