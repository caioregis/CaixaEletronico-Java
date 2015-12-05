package modelo;

public interface IConta {

	public void Sacar(double quantia);
	public void Depositar(double quantia);
	public double getSaldo();
}