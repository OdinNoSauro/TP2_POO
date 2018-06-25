package operadora;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CelularCartao extends Celular{
	private double creditos;
	private GregorianCalendar validade;
	
	public CelularCartao(Cliente client, Plano plan) {
		this.plano = plan;
		this.cliente = client;
		this.numero = new Double(this.proxNum).toString();
		this.proxNum++;
		this.creditos = 0;
		this.validade = new GregorianCalendar();
	}

	@Override
	public Ligacao realizarLigacao(GregorianCalendar data, double duracao) throws LigacaoInvalidaException {
		double custo = this.plano.getValorMin()*duracao;
		if (this.creditos<custo)
			throw new LigacaoInvalidaException("Crédito insufuciente");
		GregorianCalendar hoje = new GregorianCalendar();
		if(this.validade.compareTo(hoje)<0)
			throw new LigacaoInvalidaException("Créditos vencidos");
		Ligacao nova = new Ligacao(data, duracao);
		return nova;
		// TODO Auto-generated method stub
		
	}
	
	public double getCreditos() {
		double copia = new Double(this.creditos);
		return copia;
	}
	
	public GregorianCalendar getValidade() {
		return (GregorianCalendar) this.validade.clone();
	}
	
	public void addCreditos(double valor) {
		this.creditos += valor;
		this.validade.add(Calendar.DAY_OF_MONTH, 180);
	}
	
	
}
