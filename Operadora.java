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

  public int findCliente(String nomeCliente) {
    for (int i = 0; i < this.clientes.size(); i++){
      if (this.clientes.get(i).getNomeCliente() == nomeCliente) {
        return i;
      }
    }
    return -1;
  }

  public int findCelular(String numeroFone) {
    for (int i = 0; i < this.celulares.size(); i++){
      if (this.celulares.get(i).getNumero() == numeroFone) {
        return i;
      }
    }
    return -1;
  }

  public void addCelular(String nomeCliente) {
    int indiceCliente = findCliente(nomeCliente);
	  Celular novo = this.clientes.get(indiceCliente).newCelular();
    this.celulares.push(novo);
  }

  public int deleteCelular() {
	  return 1;
  }

  public void addCreditos(String numero, double valor) {
	  int indiceCelular = findCelular(numero);
    this.celulares.get(indiceCelular).addCreditos(valor);
  }

  public void newLigacao(String numero, GregorianCalendar data, double duracao) {
    int indiceCelular = findCelular(numero);
    this.celulares.get(indiceCelular).realizarLigacao(data, duracao);
  }

  public ArrayList<Ligacao> showConta() {
    return null;
  }

  public ArrayList<Cliente> getClientes() {
    ArrayList<Cliente> copia = this.clientes;
    return copia;
  }

  public ArrayList<Celular> getCelulares() {
    ArrayList<Celular> copia = this.celulares;
    return copia;
  }

  public ArrayList<Plano> getPlanos() {
    ArrayList<Plano> copia = this.planos;
    return copia;
  }

}
