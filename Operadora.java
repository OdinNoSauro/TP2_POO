package operadora;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class Operadora extends Object implements Cloneable {
  private ArrayList<Cliente> clientes;
  private ArrayList<Plano> planos;
  private ArrayList<Celular> celulares;
  private String nomeOp;

  public Operadora(String nome) {
	  this.clientes = new ArrayList<Cliente>();
	  this.planos = new ArrayList<Plano>();
	  this.celulares = new ArrayList<Celular>();
	  this.nomeOp = nome;
  }

  public void addCliente(String nomeCliente, String documento, String endereco) {
	  Cliente novo = new Cliente(nomeCliente, documento, endereco);
    this.clientes.add(novo);
  }

  public void addPlano(String nome, double valor) {
	  Plano novo = new Plano(nome, valor);
    this.planos.add(novo);
  }

  public int findCliente(String nomeCliente) throws NotInListException {
    for (int i = 0; i < this.clientes.size(); i++){
      if (this.clientes.get(i).getNomeCliente().equals(nomeCliente)) {
        return i;
      }
    }
    throw new NotInListException("Cliente não existe");
  }

  public int findCelular(String numeroFone) throws NotInListException {
    for (int i = 0; i < this.celulares.size(); i++){
      if (this.celulares.get(i).getNumero().equals(numeroFone)) {
        return i;
      }
    }
    throw new NotInListException("Celular não existe");
  }

  public int findPlano(String nomePlano) throws NotInListException {
    for (int i = 0; i < this.planos.size(); i++){
      if (this.planos.get(i).getNome().equals(nomePlano)) {
        return i;
      }
    }
    throw new NotInListException("Plano não existe");
  }

  public void addCelular(String nomeCliente, char tipo, Plano plano, int vencimento) throws CelularInvalidoException, NotInListException {
    int indiceCliente = findCliente(nomeCliente);
    Celular novo = this.clientes.get(indiceCliente).newCelular(tipo,plano, vencimento);
    this.celulares.add(novo);
  }
  
  public void addCelular(String nomeCliente, char tipo, Plano plano, int vencimento, GregorianCalendar validade, double saldo, String numero) throws CelularInvalidoException, NotInListException {
	    int indiceCliente = findCliente(nomeCliente);
	    Celular novo = this.clientes.get(indiceCliente).newCelular(tipo,plano, vencimento,validade,saldo,numero);
	    this.celulares.add(novo);
	  }

  public int deleteCelular(String numero) throws NotInListException, CelularInvalidoException {
	  int indice = findCelular(numero);
	  if(this.celulares.get(indice).deletavel() == 0) {
		  this.celulares.remove(indice);
		  return 1;
	  }
	  else {
		  return 0;
    }
  }

  public void addCreditos(String numero, double valor) throws NotInListException, CelularInvalidoException {
	  int indiceCelular = findCelular(numero);
    this.celulares.get(indiceCelular).addCreditos(valor);
  }

  public void newLigacao(String numero, GregorianCalendar data, double duracao) throws CelularInvalidoException, LigacaoInvalidaException, NotInListException {
    int indiceCelular = findCelular(numero);
    this.celulares.get(indiceCelular).realizarLigacao(data, duracao);
  }

  public ArrayList<Ligacao> extratoLigacao(String numero, GregorianCalendar inicio) throws NotInListException {
    int indiceCelular = findCelular(numero);
    return (ArrayList<Ligacao>) this.celulares.get(indiceCelular).getLigacoes(inicio).clone();
  }

  public double valorConta(String numero) throws NotInListException, CelularInvalidoException {
    int indiceCelular = findCelular(numero);
    return this.celulares.get(indiceCelular).getConta();
  }

  public GregorianCalendar getVencimento(String numero) throws NotInListException, CelularInvalidoException {
    int indiceCelular = findCelular(numero);
    GregorianCalendar vencimento = new GregorianCalendar();
    vencimento.set(Calendar.DAY_OF_MONTH, this.celulares.get(indiceCelular).getVencimento());
    return (GregorianCalendar) vencimento.clone();
  }

  public double getCreditos(String numero) throws  CelularInvalidoException, NotInListException {
    int indiceCelular = findCelular(numero);
    return this.celulares.get(indiceCelular).getCreditos();
  }

  public GregorianCalendar getValidade(String numero) throws NotInListException, CelularInvalidoException {
    int indiceCelular = findCelular(numero);
    return (GregorianCalendar) this.celulares.get(indiceCelular).getValidade().clone();
  }

  public ArrayList<Cliente> getClientes() {
    return (ArrayList<Cliente>) this.clientes.clone();
  }

  public ArrayList<Celular> getCelulares() {
    return (ArrayList<Celular>) this.celulares.clone();
  }

  public ArrayList<Plano> getPlanos() {
    return (ArrayList<Plano>) this.planos.clone();
  }

  public ArrayList<Celular> checkValidadeVencimento() throws CelularInvalidoException{
    GregorianCalendar atual = new GregorianCalendar();
    GregorianCalendar vencimento = new GregorianCalendar();
    ArrayList<Celular> listaRetorno = new ArrayList<Celular>();
    for(int i = 0; i < this.celulares.size() ; i++) {
      if(this.celulares.get(i).getTipo() == 'A') {
        vencimento.set(Calendar.DAY_OF_MONTH, this.celulares.get(i).getVencimento());
        if(vencimento.equals(atual)) {
          listaRetorno.add(this.celulares.get(i));
        }
      }
      else if(this.celulares.get(i).getTipo() == 'C') {
        if(this.celulares.get(i).getValidade().equals(atual)) {
          listaRetorno.add(this.celulares.get(i));
        }
      }
    }
    return (ArrayList<Celular>) listaRetorno.clone();
  }

  public void gravarClientes() throws IOException, CelularInvalidoException, CloneNotSupportedException{
	  FileWriter arquivo = new FileWriter("Cliente.txt");
	  BufferedWriter escreverArq = new BufferedWriter(arquivo);

		  escreverArq.write("Clientes");
		  escreverArq.write("\n");
		  escreverArq.write("\n");

		  for(int i = 0; i < this.getClientes().size(); i++) {
			  escreverArq.write("Cliente: "+this.getClientes().get(i).getNomeCliente());
			  escreverArq.write("\n");

			  escreverArq.write("CPF/CNPJ: "+this.getClientes().get(i).getdocumento());
			  escreverArq.write("\n");

			  escreverArq.write("Endereço: "+this.getClientes().get(i).getEndereco());
			  escreverArq.write("\n");
			  
			  escreverArq.write("\n");

		  }
		  escreverArq.write("Fim");
		  escreverArq.close();
  }
  
  public void gravarCelulares() throws IOException, CelularInvalidoException, CloneNotSupportedException {
	  char tipo;
	  Locale.setDefault(new Locale("en","US"));
	  DecimalFormat df = new DecimalFormat();
	  df.applyPattern("R$ 0.00");
	  FileWriter arquivo = new FileWriter("Celulares.txt");
	  BufferedWriter escreverArq = new BufferedWriter(arquivo);
	  escreverArq.write("Celulares");
	  escreverArq.write("\n");
	  escreverArq.write("\n");
	  
	  for(int j = 0; j < this.celulares.size(); j++) {
		  
		  escreverArq.write("Titular: "+this.celulares.get(j).getCliente().getNomeCliente());
		  escreverArq.write("\n"); 
		  
		  escreverArq.write("Número: "+this.celulares.get(j).getNumero());
		  escreverArq.write("\n"); 
		  
		  tipo = this.celulares.get(j).getTipo();
		  escreverArq.write("Tipo: "+tipo);
		  escreverArq.write("\n");
		  
		  if(tipo == 'C') {
			  escreverArq.write("Saldo: "+df.format(this.celulares.get(j).getCreditos()));
			  escreverArq.write("\n");
			  GregorianCalendar validade = this.celulares.get(j).getValidade();
			  escreverArq.write("Validade: " + validade.get(Calendar.DAY_OF_MONTH) + "/" + validade.get(Calendar.MONTH) + "/" + validade.get(Calendar.YEAR));
			  escreverArq.write("\n");
		  }
		  else if (tipo == 'A') {
			  escreverArq.write("Dia de Vencimento: "+this.celulares.get(j).getVencimento());
			  escreverArq.write("\n");
		  }
		  escreverArq.write("Nome do plano: "+this.celulares.get(j).getPlano().getNome());
		  escreverArq.write("\n");
		  
		  escreverArq.write("Ligações");
		  escreverArq.write("\n");
		  escreverArq.write("\n");
		  
		  for (int i = 0; i<celulares.get(j).getLigacoes().size();i++) {
			  GregorianCalendar data = this.celulares.get(j).getLigacoes().get(i).getDataLig();
			  escreverArq.write("Data da ligação: " + data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH)+1) + "/" + data.get(Calendar.YEAR));
			  escreverArq.write("\n");
			  
			  escreverArq.write("Duração: "+this.celulares.get(j).getLigacoes().get(i).getDuracao());
			  escreverArq.write("\n");
		  }
		  
		  escreverArq.write("Fim das Ligações");
		  escreverArq.write("\n");
		  
	  }
	  escreverArq.write("Fim");
	  escreverArq.close();
	  
  }

  
  public void gravarPlanos() throws IOException {
	  Locale.setDefault(new Locale("en","US"));
	  DecimalFormat df = new DecimalFormat();
	  df.applyPattern("R$ 0.00");
	  FileWriter arquivo = new FileWriter("Planos.txt");
	  BufferedWriter escreverArq = new BufferedWriter(arquivo);

		  escreverArq.write("Planos");
		  escreverArq.write("\n");
		  escreverArq.write("\n");

		  for(int i = 0; i < this.getPlanos().size(); i++) {
			  escreverArq.write("Nome: "+this.getPlanos().get(i).getNome());
			  escreverArq.write("\n");

			  escreverArq.write("Valor por minuto: "+df.format(this.getPlanos().get(i).getValorMin()));
			  escreverArq.write("\n");
			  
			  escreverArq.write("\n");

		  }
		  escreverArq.write("Fim");
		  escreverArq.close();
  }

  public void lerClientes() throws IOException {
	  String nome, linha,documento,endereco;
	  
	  FileReader arquivo = new FileReader("Cliente.txt");
	  BufferedReader lerArq = new BufferedReader(arquivo);
	  linha = lerArq.readLine();
	  if (lerArq.readLine()!=null) {
		  if (linha.equals("Clientes")) {
	
			  while (!((linha = lerArq.readLine()).equals(new String("Fim")))) {
				  
				  nome = linha.substring(9);
				  
				  linha = lerArq.readLine();
				  documento = linha.substring(10);
	
				  linha = lerArq.readLine();
				  endereco = linha.substring(10);
	
				  this.addCliente(nome, documento, endereco);
	
				  linha = lerArq.readLine();
	
			  }
		  }
		  
	  }
	  lerArq.close();
  }

  public void lerCelulares() throws IOException, CelularInvalidoException, NotInListException, LigacaoInvalidaException  {
	  String nome, linha,numero, titular, aux;
	  GregorianCalendar validade = new GregorianCalendar();
	  double saldo= 0,duracao;
	  char tipo;
	  Plano plano;
	  int x = 0;
	  int vencimento = 0, indice,dia,mes,ano;
	  String[] data,val;
	  
	  FileReader arquivo = new FileReader("Celulares.txt");
	  BufferedReader lerArq = new BufferedReader(arquivo);
	  linha = lerArq.readLine();
		  if (linha.equals("Celulares")) {
			  linha = lerArq.readLine();
			 for(linha = lerArq.readLine(); linha==null; linha = lerArq.readLine()) {
				  
				  titular = linha.substring(9); 
				  
				  linha = lerArq.readLine();
				  numero = linha.substring(8);
	
				  linha = lerArq.readLine();
				  tipo = linha.substring(6).charAt(0);
				  
				  if (tipo == 'C') {
					  linha = lerArq.readLine();
					  saldo = Double.parseDouble(linha.substring(10));
					  
					  linha = lerArq.readLine();
					  val = linha.substring(10).split("/");
					  dia = Integer.parseInt(val[0]);
					  mes = Integer.parseInt(val[1]) - 1;
					  ano = Integer.parseInt(val[2]);
					  validade = new GregorianCalendar(ano,mes,dia);
				  }
				  
				  else if (tipo == 'A') {
					  linha = lerArq.readLine();
					  vencimento = Integer.parseInt(linha.substring(19));
				  }
				  
				  linha = lerArq.readLine();
				  nome = linha.substring(15); 
				  indice = this.findPlano(nome);
				  plano = this.planos.get(indice);
				  this.addCelular(titular, tipo, plano, vencimento,validade,saldo,numero);
				  
				  if (((linha = lerArq.readLine()).equals(new String("Ligaçoes")))) {
					  lerArq.readLine();
						 while(!((linha = lerArq.readLine()).equals(new String("Fim das ligações")))) {
							 
							  data = linha.substring(17).split("/");
							  dia = Integer.parseInt(data[0]);
							  mes = Integer.parseInt(data[1]);
							  mes = mes - 1; // Na classe GregorianCalendar o meses vão de 0 a 11
							  ano = Integer.parseInt(data[2]);
							  
							  linha = lerArq.readLine();
							  duracao = Double.parseDouble(linha.substring(9));
							  GregorianCalendar dataLig = new GregorianCalendar(ano,mes,dia);
							  
							  this.newLigacao(numero, dataLig, duracao);
							  
							  
						  }
					  }
				  
			  }
		  }
	  lerArq.close();
  }

  public void lerPlanos() throws IOException {
	  String nome, valor,linha;
	  double valorpm;
	  FileReader arquivo = new FileReader("Planos.txt");
	  BufferedReader lerArq = new BufferedReader(arquivo);
	  linha = lerArq.readLine();
	  if (lerArq.readLine()!=null) {
		  if (linha.equals("Planos")) {
	
			  while (!((linha = lerArq.readLine()).equals(new String("Fim")))) {
				  
				  nome = linha.substring(6);
				  
				  linha = lerArq.readLine();
				  valor = linha.substring(20);
				  
				  valorpm = Double.parseDouble(valor);
				  
				  linha = lerArq.readLine();
	
				  this.addPlano(nome, valorpm);
	
	
			  }
		  }
		  
	  }
	  lerArq.close();
  }
}
