package operadora;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Celular extends Object implements Cloneable {
	protected Plano plano;
	protected Cliente cliente;
	protected ArrayList<Ligacao> ligacoes;
	protected String numero;
	protected static int proxNum = 900000000;

	public abstract Ligacao realizarLigacao(GregorianCalendar data, double duracao) throws CelularInvalidoException, LigacaoInvalidaException;
	public abstract double getCreditos() throws CelularInvalidoException;
	public abstract void addCreditos(double valor) throws CelularInvalidoException;
	public abstract double getConta() throws CelularInvalidoException;
	public abstract int getVencimento() throws CelularInvalidoException;
	public abstract double deletavel();
	public abstract GregorianCalendar getValidade() throws CelularInvalidoException;
	public abstract char getTipo();

	public Celular getCelular() throws CloneNotSupportedException {
		return (Celular) this.clone();
	}

	public Cliente getCliente() throws CloneNotSupportedException {
		return this.cliente.retornaClone();
	}

	public Plano getPlano() throws CloneNotSupportedException {
		return this.plano.retornaClone();
	}

	public String getNumero() {
		// TODO Auto-generated method stub
		String s = this.numero;
		return s;
	}

	public BigDecimal getProxNumero() {
		return new BigDecimal(this.proxNum);
	}

	public void setPlano(Plano novo) {
		// TODO Auto-generated method stub
		this.plano = novo;
	}

	public ArrayList<Ligacao> getLigacoes(GregorianCalendar inicio) {
    ArrayList<Ligacao> listaRetorno = new ArrayList<Ligacao>();
    for (int i = 0; i < this.ligacoes.size(); i++) {
      if ((inicio.compareTo(this.ligacoes.get(i).getDataLig())) < 0) {
        listaRetorno.add(this.ligacoes.get(i));
      }
    }
    return listaRetorno;
	}
	
	public ArrayList<Ligacao> getLigacoes(){
		return (ArrayList<Ligacao>) this.ligacoes.clone();
	}
	
	
}
