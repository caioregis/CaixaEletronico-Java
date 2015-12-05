package service;

import java.util.LinkedHashMap;
import java.util.Map;

import modelo.Conta;
import modelo.Nota;

public class CaixaEletronico implements ICaixaEletronico {

    private int quantidadeDeSaques;
    private double valorTotalSaques;
    private LinkedHashMap<Nota, Integer> notas;
    private Conta conta;

    
    public CaixaEletronico() {
        this.conta = new Conta();
        notas = new LinkedHashMap<Nota, Integer>();
    }

    public double ConsultarSaldoDaConta() {
    	return conta.getSaldo();
    }

    public int getQuantidadeDeSaques() {
		return quantidadeDeSaques;
	}

	public double getValorTotalSaques() {
		return valorTotalSaques;
	}

	public void DepositarNaConta(LinkedHashMap<Nota, Integer> _notasEQuantidades) {
    	
    	double valorDeposito = 0;
    	
    	for (Map.Entry<Nota, Integer> notaEQuantidade : _notasEQuantidades.entrySet()) {

			if(!notas.containsKey(notaEQuantidade.getKey()))
				notas.put(notaEQuantidade.getKey(), notaEQuantidade.getValue());
			else {
				Integer soma = notas.get(notaEQuantidade.getKey()) + notaEQuantidade.getValue();
				notas.put(notaEQuantidade.getKey(), soma);
			}

			valorDeposito += notaEQuantidade.getKey().getValor() * notaEQuantidade.getValue();
		}
    	
    	conta.Depositar(valorDeposito);
    }
    
    public String SacarDaConta(int quantia) {
        
    	int quantidadeOriginal = quantia;
    	StringBuilder text = new StringBuilder();
 
        if (conta.TemSaldoDisponivelParaQuantia(quantia)) {
        	
        	LinkedHashMap<Nota, Integer> notasARemover = new LinkedHashMap<Nota, Integer>();
        	
        	for (Map.Entry<Nota, Integer> notaEQuantidade : notas.entrySet()) {

        		for(int i=1; i <= notaEQuantidade.getValue(); i++) {
        			
        			if(TemNotaDisponivelParaQuantia(quantia, notaEQuantidade)) {
    					
    					Integer subtrai = notas.get(notaEQuantidade.getKey()) - i;
    					notasARemover.put(notaEQuantidade.getKey(), subtrai);
    					
    					quantia -= notaEQuantidade.getKey().getValor();
    				}
        		}
    		}
        	
        	if (quantia == 0) {
        		
                this.quantidadeDeSaques += 1;
                this.valorTotalSaques += quantidadeOriginal;
                this.notas.putAll(notasARemover);
                conta.Sacar(quantidadeOriginal);
                
            	text.append("\nSaque realizado com sucesso!");
                
            } else
            	text.append("\nQuantidade de células nao é múltiplo do valor solicitado");
             
        }
        else
        	text.append("\nQuantia inválida ou saldo insuficiente");
        
        
        return text.toString();
    }
  
    public boolean TemNotaDisponivelParaQuantia(int quantia, Map.Entry<Nota, Integer> notaEQuantidade) {
		
    	return quantia >= notaEQuantidade.getKey().getValor() &&
				notaEQuantidade.getValue() > 0;
    }
    
	public String MostrarQuantidadeDeNotasNoCaixa() {

		if(!notas.isEmpty()) {
			StringBuilder text = new StringBuilder();
			
			for (Map.Entry<Nota, Integer> notaEQuantidade : notas.entrySet()) {
				text.append("Total de " + notaEQuantidade.getKey() + ": ");
				text.append(notaEQuantidade.getValue()+"\n");
			}	
			
			return text.toString();
		}
		
		return "Sem notas disponíveis";
	}
  
}
