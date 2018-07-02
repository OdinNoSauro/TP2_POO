package operadora;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Cliente extends Object implements Cloneable {
  private String nomeCliente;
  private String documento;
  private String endereco;
  private ArrayList<Celular> celulares = new ArrayList<Celular>();

  public Cliente(String nomeCliente, String documento, String endereco) {
    this.nomeCliente = nomeCliente;
    this.documento = documento;
    this.endereco = endereco;
  }

  public Cliente retornaClone() throws CloneNotSupportedException {
    return (Cliente)this.clone();
  }

  public String getNomeCliente() {
	  String copia = this.nomeCliente;
	  return copia;
  }

  public String getDocumento() {
	  String copia = this.documento;
	  return copia;
  }

  public String getEndereco() {
	  String copia = this.endereco;
	  return copia;
  }

  public ArrayList<Celular> getCelulares() {
    ArrayList<Celular> copia = new ArrayList<Celular>(this.celulares);
    return copia;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  public void setdocumento(String documento) {
    this.documento = documento;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public Celular newCelular(char tipo, Plano plano, int vencimento) throws CelularInvalidoException {
    if (tipo == 'C' || tipo == 'c') {
    	Celular novo = new CelularCartao(this,plano);
    	this.celulares.add(novo);
    	return novo;
    }
    else if(tipo == 'A' || tipo == 'a') {
    	Celular novo = new CelularAss(plano,this,vencimento);
    	this.celulares.add(novo);
    	return novo;
    }
    throw new CelularInvalidoException("Tipo invalido");
  }

  public Celular newCelular(char tipo, Plano plano, int vencimento, GregorianCalendar validade, double saldo, String numero) throws CelularInvalidoException {
	    if (tipo == 'C' || tipo == 'c') {
	    	Celular novo = new CelularCartao(this,plano,validade,saldo,numero);
	    	this.celulares.add(novo);
	    	return novo;
	    }
	    else if(tipo == 'A' || tipo == 'a') {
	    	Celular novo = new CelularAss(plano,this,vencimento, numero);
	    	this.celulares.add(novo);
	    	return novo;
	    }
	    throw new CelularInvalidoException("Tipo invalido");
	  }

}
