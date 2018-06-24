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
  
  public void addCliente() {
	  
  }
  
  public void addPlano() {
	  
  }
  
  public void addCelular() {
	  
  }
  
  public int deleteCelular() {
	  return 1;
  }
  
  public void addCreditos(String numero) {
	  
  }
  
  public void newLigacao(String numero, GregorianCalendar data, double duracao) {
	  
  }
  
  public ArrayList<Ligacao> showConta(){
	return null;
	  
  }
  
  public Celular getCelular (String numero) {
	  return null;
  }
  
  public ArrayList<Cliente> getClientes(){
	  return null;
  }
  
  public ArrayList<Celular> getCelulares(){
	  return null;
  }
  
  public ArrayList<Plano> getPlanos(){
	  return null;
  }
  
  
  
}
