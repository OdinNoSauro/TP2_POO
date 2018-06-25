package operadora;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Celular extends Object implements Cloneable  {
	protected Plano plano;
	protected Cliente cliente;
	protected ArrayList<Ligacao> ligacoes;
	protected String numero;
	protected static double proxNum = 90000000;
	
	public abstract Ligacao realizarLigacao(GregorianCalendar data, double duracao) throws Exception;
	public abstract double getCreditos() throws Exception;
	
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

	public double getProxNumero() {
		return new Double(this.proxNum);
	}
	
	public void setPlano(Plano novo) {
		// TODO Auto-generated method stub
		this.plano = novo;
	}


	public ArrayList<Ligacao> getLigacoes() {
		GregorianCalendar atual = new GregorianCalendar();
	    ArrayList<Ligacao> listaRetorno = new ArrayList<Ligacao>();
	    for (int i = 0; i < this.ligacoes.size(); i++){
	      GregorianCalendar referencia = this.ligacoes.get(i).getDataLig();
	      if ((referencia.get(Calendar.MONTH) == atual.get(Calendar.MONTH)) && (referencia.get(Calendar.YEAR) == atual.get(Calendar.YEAR))){
	        listaRetorno.add(this.ligacoes.get(i));
	      }
	    }
	    return listaRetorno;
	}

	
}
