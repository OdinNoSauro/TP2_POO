package operadora;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CelularAss extends Celular{
	private GregorianCalendar vencimento;
	
	public CelularAss(Plano plan, Cliente client, GregorianCalendar venc) {
		this.vencimento = venc;
		this.plano = plan;
		this.cliente = client;
		this.numero = new Double(this.proxNum).toString();
		this.proxNum++;
	}

	@Override
	public Ligacao realizarLigacao(GregorianCalendar data, double duracao) {
		Ligacao nova = new Ligacao(data, duracao);
		return nova;
		
	}
	
	public GregorianCalendar getVencimento() {
		return (GregorianCalendar) this.vencimento.clone();
	}
	
	public ArrayList<Ligacao> getConta(GregorianCalendar inicio) {
		GregorianCalendar fim = new GregorianCalendar();
	    ArrayList<Ligacao> listaRetorno = new ArrayList<Ligacao>();
	    for (int i = 0; i < this.ligacoes.size(); i++){
	      if (((inicio.compareTo(this.ligacoes.get(i).getDataLig()))< 0) && ((fim.compareTo(this.ligacoes.get(i).getDataLig()))> 0)){
	        listaRetorno.add(this.ligacoes.get(i));
	      }
	    }
	    return listaRetorno;
	}

	@Override
	public double getCreditos() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
