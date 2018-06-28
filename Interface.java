package gui;

import operadora.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;


public class Interface {
	
	private static Operadora op = new Operadora("Vivo");
	
	 public static void main (String[] argv) {
		 char continuar = 'S'; 
		 Locale.setDefault(new Locale("pt","BR"));
		 Scanner scanner = new Scanner(System.in);
		 double valor;
		 do {
			  System.out.println("===================Selecione um opção=================");
			  System.out.println(" (1) Adicionar créditos           	   (2) Registrar ligação");
			  System.out.println(" (3) Listar créditos             	   (4) Listar conta");
			  System.out.println(" (5) Cadastrar um cliente        	   (6) Cria um plano");
			  System.out.println(" (7) Habilitar celular            	   (8) Excluir celular");
			  System.out.println(" (9) Listar clientes			  (10) Listar planos");
			  System.out.println("(11) Listar celulares			  (12) Extrato de ligações");
			  int opcao = scanner.nextInt();
			  scanner.nextLine();
			  if (opcao == 1) {
				  addCreditos();
			  }
			  else if (opcao == 2 ) {
				  novaLigacao();
				  
			  }
			  else if (opcao == 3) {
				  System.out.println("Insira o número do celular");
				  String numero = scanner.nextLine();
				  ListarCreditos(numero);
			  }
			  else if(opcao == 4) {
				  System.out.println("Insira o número do celular");
				  String numero = scanner.nextLine();
				  ListarConta(numero);
			  }
			  else if (opcao == 5) {
				  newCliente();
				  
			  }
			  else if (opcao == 6) {
				  newPlano();
			  }
			  else if (opcao == 7) {
				  newCelular();
			  }
			  else if (opcao == 8) {
				  System.out.println("Insira o numero do celular deseja deletar");
				  String numero = scanner.nextLine();
				  deleteConta(numero);
			  }
			  else if (opcao == 9) {
				  showClientes();
			  }
			  else if (opcao == 10) {
				  showPlanos();
			  }
			  else if (opcao == 11) {
				  showCelulares();
			  }
			  else if (opcao == 12) {
				  showLigacoes();
			  }
			  else
				  System.out.println("Opção inválida");
		System.out.println("Deseja realizar outra operação?");
		continuar = scanner.nextLine().charAt(0);
		}while((continuar == 'S') || (continuar == 's'));
		System.out.println("Obrigado. Volte sempre!");
		
		scanner.close();
	}

	

	private static void imprimeLig(ArrayList<Ligacao> lista) {
		for(int i = 0; i<lista.size();i++) {
			GregorianCalendar data = lista.get(i).getDataLig();
			System.out.println("Duração: "+lista.get(i).getDuracao());
			System.out.println("Data: "+data.get(Calendar.DAY_OF_MONTH)+"/"+data.get(Calendar.MONTH)+"/"+data.get(Calendar.YEAR));
			System.out.print("\n");
		}
	}
	private static void showLigacoes() {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Insira o número do seu celular");
		 String numero = scanner.nextLine();
		 System.out.print("Insira o dia: ");
		 int dia = scanner.nextInt();
		 System.out.print("Insira o mês: ");
		 int mes = scanner.nextInt();
		 mes = mes -1;
		 System.out.print("Insira o ano: ");
		 int ano = scanner.nextInt();
		 scanner.nextLine();
		 GregorianCalendar inicio = new GregorianCalendar(ano,mes,dia);
		 try {
			int indice = Interface.op.findCelular(numero);
			ArrayList<Ligacao> lista = Interface.op.getCelulares().get(indice).getLigacoes(inicio);
			imprimeLig(lista);
		 } catch (NotInListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private static void showCelulares() {
		try {
			DecimalFormat df = new DecimalFormat();
			df.applyPattern("R$ #,##0.00");
			System.out.println("Lista de celulares: ");
			ArrayList<Celular> lista = Interface.op.getCelulares();
			for(int i = 0; i<lista.size();i++) {
				System.out.println("Número: "+lista.get(i).getNumero());
				System.out.print("\n");
				Cliente c;
				c = lista.get(i).getCliente();
				System.out.println("Nome do Cliente: "+c.getNomeCliente());
				System.out.println("Documento: "+c.getdocumento());
				System.out.println("Endereço: "+c.getEndereco());
				System.out.print("\n");
				Plano p = lista.get(i).getPlano();
				System.out.println("Nome do Plano: "+p.getNome());
				System.out.println("Valor por minuto: "+df.format(p.getValorMin()));
				System.out.print("\n");
				imprimeLig(lista.get(i).getLigacoes());
				if(lista.get(i).getTipo()=='C') {
					System.out.println("Créditos: "+df.format(lista.get(i).getCreditos()));
					GregorianCalendar validade = lista.get(i).getValidade();
					System.out.println("Validade: "+validade.get(Calendar.DAY_OF_MONTH)+"/"+validade.get(Calendar.MONTH)+"/"+validade.get(Calendar.YEAR));
				}else
					System.out.println("Vencimento: "+lista.get(i).getVencimento());
			}
			
		}catch (CloneNotSupportedException | CelularInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private static void showPlanos() {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("R$ #,##0.00");
		System.out.println("Lista de planos: ");
		ArrayList<Plano> lista = Interface.op.getPlanos();
		for(int i = 0; i<lista.size();i++) {
			System.out.println("Nome do Plano: "+lista.get(i).getNome());
			System.out.println("Valor por minuto: "+df.format(lista.get(i).getValorMin()));
			System.out.print("\n");
		}
	}



	private static void showClientes() {
		// TODO Auto-generated method stub
		System.out.println("Lista de clientes: ");
		ArrayList<Cliente> lista = Interface.op.getClientes();
		for(int i = 0; i<lista.size();i++) {
			System.out.println("Nome do Cliente: "+lista.get(i).getNomeCliente());
			System.out.println("Documento: "+lista.get(i).getdocumento());
			System.out.println("Endereço: "+lista.get(i).getEndereco());
			System.out.print("\n");
		}
	}



	private static void deleteConta(String numero) {
		try {
			int sucesso = Interface.op.deleteCelular(numero);
			if (sucesso == 1 )
				System.out.println("Conta deletada com sucesso");
			else if (sucesso == 0)
				 System.out.println("Cliente  possui conta");
		} catch (NotInListException | CelularInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private static void newPlano() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira o nome do plano");
		String nome = scanner.nextLine();
		System.out.println("Insira o valor por minuto");
		double valor = scanner.nextDouble();
		Interface.op.addPlano(nome, valor);
	}



	private static void newCelular() {
		try {
		Scanner scanner = new Scanner(System.in);
		String nome, nomePlano;
		char op;
		int venc = 0;
		int indice = -1;
		int indicePlano = -1;
		char tipo;
		System.out.println("Celular Cartão ou Assinatura?<C/A>");
		tipo = scanner.nextLine().charAt(0);
		if (tipo == 'A' || tipo == 'a') {
			System.out.println("Qual o dia de vencimento desejado?");
			venc = scanner.nextInt();
			scanner.nextLine();
		}
		System.out.println("Qual o nome do plano?");
		nomePlano = scanner.nextLine();
		indicePlano = Interface.op.findPlano(nomePlano);
		System.out.println("Cliente já cadastrado? <S/N>");
		op = scanner.nextLine().charAt(0);
		if (op == 'S' || op == 's') {
			System.out.print("Insira o nome do cliente: ");
			nome = scanner.nextLine();
			indice = Interface.op.findCliente(nome);
			Interface.op.addCelular(nome,tipo,Interface.op.getPlanos().get(indicePlano),venc);
		}
		else if (op == 'N' || op == 'n') {
			newCliente();
			ArrayList<operadora.Cliente> clientes = Interface.op.getClientes();
			indice = clientes.size();
			if(indice != 0)
				indice = indice -1;
			Interface.op.addCelular(clientes.get(indice).getNomeCliente(),tipo,Interface.op.getPlanos().get(indicePlano),venc);
		}
		System.out.println("Celular habilitado com sucesso");
		int indiceCel = Interface.op.getClientes().get(indice).getCelulares().size() - 1;
		String numero = Interface.op.getClientes().get(indice).getCelulares().get(indiceCel).getNumero();
		System.out.println("Seu número é: "+numero);
		} catch(NotInListException | CelularInvalidoException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void newCliente() {
		Scanner scanner = new Scanner(System.in);
		String nome, cpf, endereco;
		System.out.println("Insira o nome do cliente");
		nome = scanner.nextLine();
		System.out.println("Insira o cpf ou cnpj do cliente");
		cpf = scanner.nextLine();
		System.out.println("Insira o endereço do cliente");
		endereco = scanner.nextLine();
		Interface.op.addCliente(nome, cpf, endereco);
		
	}

	private static void ListarConta(String numero) {
		try {
			DecimalFormat df = new DecimalFormat();
			df.applyPattern("R$ #,##0.00");
			double valor = Interface.op.valorConta(numero);
			System.out.println("O valor da conta é: "+df.format(valor));
		} catch (NotInListException | CelularInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void ListarCreditos(String numero) {
		try {
			DecimalFormat df = new DecimalFormat();
			df.applyPattern("R$ #,##0.00");
			double credito = Interface.op.getCreditos(numero);
			GregorianCalendar validade = Interface.op.getValidade(numero);
			System.out.println("Crétidos: "+df.format(credito));
			System.out.println("Validade: "+validade.get(Calendar.DAY_OF_MONTH)+"/"+validade.get(Calendar.MONTH)+"/"+validade.get(Calendar.YEAR));
		} catch ( CelularInvalidoException | NotInListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void novaLigacao() {
		Scanner scanner = new Scanner(System.in);
		  System.out.println("Insira o número do seu celular");
		  String numero = scanner.nextLine();
		  System.out.println("Insira a duração da ligação");
		  double duracao = scanner.nextDouble();
		  System.out.print("Insira o dia: ");
		  int dia = scanner.nextInt();
		  System.out.print("Insira o mês: ");
		  int mes = scanner.nextInt();
		  mes = mes -1;
		  System.out.print("Insira o ano: ");
		  int ano = scanner.nextInt();
		  scanner.nextLine();
		  System.out.print("Insira a hora e os minutos separados por ':' : ");
		  String horas = scanner.nextLine();
		  int hora = Integer.parseInt(horas.substring(0,1));
		  int minutos = Integer.parseInt(horas.substring(3));
		  GregorianCalendar data = new GregorianCalendar(ano,mes,dia,hora,minutos);
		  try {
			Interface.op.newLigacao(numero, data, duracao);
		} catch (CelularInvalidoException | LigacaoInvalidaException | NotInListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
	}

	private static void addCreditos() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira o número do celular");
		String numero = scanner.nextLine();
		System.out.println("Insira o valor que deseja adicionar");
		double valor = scanner.nextDouble();
		try {
			Interface.op.addCreditos(numero, valor);
		} catch (NotInListException | CelularInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
