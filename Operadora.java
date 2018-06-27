package operadora;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Operadora extends Object implements Cloneable{
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
    this.clientes.push(novo);
  }

  public void addPlano(String nome, double valor) {
	  Plano novo = new Plano(nome, valor);
    this.planos.push(novo);
  }

  public int findCliente(String nomeCliente) throws NotInListException {
    for (int i = 0; i < this.clientes.size(); i++){
      if (this.clientes.get(i).getNomeCliente() == nomeCliente) {
        return i;
      }
    }
    return -1;
  }

  public int findCelular(String numeroFone) throws NotInListException {
    for (int i = 0; i < this.celulares.size(); i++){
      if (this.celulares.get(i).getNumero() == numeroFone) {
        return i;
      }
    }
  }

  public void addCelular(String nomeCliente) {
    int indiceCliente = findCliente(nomeCliente);
	  Celular novo = this.clientes.get(indiceCliente).newCelular();
    this.celulares.push(novo);
  }

  public int deleteCelular() throws {
	  return 1;
  }

  public void addCreditos(String numero, double valor) throws {
	  int indiceCelular = findCelular(numero);
    this.celulares.get(indiceCelular).addCreditos(valor);
  }

  public void newLigacao(String numero, GregorianCalendar data, double duracao) {
    int indiceCelular = findCelular(numero);
    this.celulares.get(indiceCelular).realizarLigacao(data, duracao);
  }

  public ArrayList<Ligacao> extratoLigacao(String numero, GregorianCalendar inicio) {
    int indiceCelular = findCelular(numero);
    return this.celulares.get(indiceCelular).getLigacoes(inicio).clone();
  }

  public double valorConta(String numero) {
    int indiceCelular = findCelular(numero);
    return this.celulares.get(indiceCelular).getConta();
  }

  public GregorianCalendar getVencimento(String numero) {
    int indiceCelular = findCelular(numero);
    GregorianCalendar vencimento = new GregorianCalendar();
    vencimento.set(DAY_OF_MONTH, this.celulares.get(indiceCelular).getVencimento);
    return vencimento.clone();
  }

  public double getCreditos(String numero) {
    int indiceCelular = findCelular(numero);
    return this.celulares.get(indiceCelular).getCreditos();
  }

  public GregorianCalendar getValidade(String numero) {
    int indiceCelular = findCelular(numero);
    return this.celulares.get(indiceCelular).getValidade().clone();
  }

  public ArrayList<Cliente> getClientes() {
    return this.clientes.clone();
  }

  public ArrayList<Celular> getCelulares() {
    return this.celulares.clone();
  }

  public ArrayList<Plano> getPlanos() {
    return this.planos.clone();
  }

}
