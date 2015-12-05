package service;

import java.util.LinkedHashMap;
import java.util.Map;

import modelo.Nota;

public interface ICaixaEletronico {

	public double ConsultarSaldoDaConta();

	public void DepositarNaConta(
			LinkedHashMap<Nota, Integer> _notasEQuantidades);

	public String SacarDaConta(int quantia);

	public boolean TemNotaDisponivelParaQuantia(int quantia,
			Map.Entry<Nota, Integer> notaEQuantidade);

	public String MostrarQuantidadeDeNotasNoCaixa();

}