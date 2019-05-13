package es.deusto.paypal.data;

public class UsuarioPayPal {

	private String email;
	private String password;
	private double saldo;
	
	public UsuarioPayPal(String email, String password, double saldo) {
		this.email = email;
		this.password = password;
		this.saldo = saldo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
}
