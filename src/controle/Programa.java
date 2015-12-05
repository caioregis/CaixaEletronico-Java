package controle;

import java.util.LinkedHashMap;
import java.util.Scanner;

import modelo.Nota;
import service.CaixaEletronico;

public class Programa {

    private Scanner teclado;
    private int opcao;
    private CaixaEletronico ce;
    

	public void Run() {
    	
    	teclado = new Scanner(System.in);
        ce = new CaixaEletronico();

        for (;;)
        {
        	StringBuilder text = new StringBuilder();
        	Titulo(text, "Menu de Opções");
        	
        	text.append("\n1- Repor");
            text.append("\n2- Sacar");
            text.append("\n3- Consultar saldo");
            text.append("\n4- Fim");
            text.append("\nOpções: ");
            System.out.println(text);
            
            opcao = teclado.nextInt();
            
            if (opcao == 4)
                break;
            
            switch(opcao)
            {
                case 1: // depositar
                        
                	LinkedHashMap<Nota, Integer> notas = new LinkedHashMap<Nota, Integer>();
                        
                    StringBuilder textDeposito = new StringBuilder();
                    Titulo(textDeposito, "Reposição de notas");
                    System.out.println(textDeposito);
                        
                    int informeQuantidadeDeNotas[] = {100,50,20,10,5};
                        
                    for (int i : informeQuantidadeDeNotas) {
                    	System.out.print("Informe quantidade de notas de "+i+" reais: ");
                        notas.put(new Nota(i), teclado.nextInt());
					}

                    ce.DepositarNaConta(notas);
                        
                    break;
   
                case 2: // sacar
                	int quantiaSaque;
                	StringBuilder textSaque = new StringBuilder();
                        
                	Titulo(textSaque, "Saque");
                        
                    textSaque.append("\nQuantia: ");
                    System.out.println(textSaque);
                        
                    quantiaSaque = teclado.nextInt();
                    
                    System.out.println(ce.SacarDaConta(quantiaSaque));
                        
                    break;

                case 3: // consultar saldo
                	StringBuilder textConsulta = new StringBuilder();
                	
                	Titulo(textConsulta, "Consulta de Saldo");
                	
                	textConsulta.append("\nO Saldo é: " + ce.ConsultarSaldoDaConta());
                	textConsulta.append("\nQuantidade de saques: " + ce.getQuantidadeDeSaques());
                	textConsulta.append("\nValor dos saques: " + ce.getValorTotalSaques());
                	textConsulta.append("\n"+ce.MostrarQuantidadeDeNotasNoCaixa());	
                	System.out.println(textConsulta);
                    
                	break;
                    
                default: // opcao invalida
                	System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    public void Titulo(StringBuilder text, String acao) {
    	text.append("\n---------------------------------------");
        text.append("\nCaixa Eletrônico - " + acao);
        text.append("\n---------------------------------------");
    }
    
}
