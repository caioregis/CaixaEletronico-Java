package modelo;

public class Conta implements IConta {

	private double saldo;

	public void Sacar(double quantia) {

		saldo -= quantia;
	}

	public void Depositar(double quantia) {
		
		saldo += quantia;
	}
	
	public double getSaldo() {
		
		return saldo;
	}
	
	public boolean TemSaldoDisponivelParaQuantia(int quantia) {
    	
    	return quantia > 0 && quantia <= saldo;
    }

}