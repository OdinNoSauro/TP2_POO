package operadora;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CelularCartao extends Celular {
	private double creditos;
	private GregorianCalendar validade;

	public CelularCartao(Cliente client, Plano plan) {
		this.plano = plan;
		this.cliente = client;
		this.numero = new BigDecimal(Celular.proxNum).toString();
		this.proxNum++;
		this.creditos = 0;
		this.ligacoes = new ArrayList<Ligacao>();
		this.validade = new GregorianCalendar();
	}
	
	public CelularCartao(Cliente client, Plano plan, GregorianCalendar validade, double saldo, String numero) {
		this.plano = plan;
		this.cliente = client;
		this.numero = numero;
		int proximo = Integer.parseInt(numero)+1;
		if(proximo>Celular.proxNum)
			Celular.proxNum = proximo;
		this.creditos = saldo;
		this.ligacoes = new ArrayList<Ligacao>();
		this.validade = validade;
	}

	@Override
	public Ligacao realizarLigacao(GregorianCalendar data, double duracao) throws LigacaoInvalidaException {
		double custo = this.plano.getValorMin() * duracao;
		if (this.creditos < custo) {
			throw new LigacaoInvalidaException("Crédito insufuciente");
		}
		this.creditos -= custo;
		GregorianCalendar hoje = new GregorianCalendar();
		if(this.validade.compareTo(hoje) < 0) {
			throw new LigacaoInvalidaException("Créditos vencidos");
		}
		Ligacao nova = new Ligacao(data, duracao,duracao*this.plano.getValorMin());
		return nova;
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

	@Override
	public double getConta() throws CelularInvalidoException {
		throw new CelularInvalidoException("Celular é do tipo Cartão");
	}

	@Override
	public int getVencimento() throws CelularInvalidoException {
		throw new CelularInvalidoException("Celular é do tipo Cartão");
	}

	@Override
	public double deletavel() {
		return getCreditos();
	}

	@Override
	public char getTipo() {
		return 'C';
	}
}
