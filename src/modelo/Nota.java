package modelo;

public class Nota {

	private int valor;
	
	public Nota(int _valor) {
		valor = _valor;
	}
	
	@Override
	public String toString() {
		return "Nota " + valor;
	}

	public int getValor() {
		return valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + valor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		Nota other = (Nota) obj;
		if (valor != other.valor)
			return false;
		return true;
	}

	
}
