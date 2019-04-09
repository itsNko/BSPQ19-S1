package es.deusto.main;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProgramaPrincipal extends UnicastRemoteObject implements IServer {

		private static final long serialVersionUID = 1L;
		private int cont = 0;

		protected ProgramaPrincipal() throws RemoteException {
			super();
		}

		public String sayHello() {
			cont++;
			System.out.println(" * Client number: " + cont);
			return "Hello World!";
		}

		public static void main(String[] args) {
			if (args.length != 3) {
				System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
				System.exit(0);
			}

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

			try {		
				IServer objServer = new ProgramaPrincipal();
				Naming.rebind(name, objServer);
				System.out.println("* Server '" + name + "' active and waiting...");
			} catch (Exception e) {
				System.err.println("- Exception running the server: " + e.getMessage());
				e.printStackTrace();
			}
		}
}
