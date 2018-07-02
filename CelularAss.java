package operadora;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CelularAss extends Celular {
	private int vencimento;

	public CelularAss(Plano plan, Cliente client, int venc) {
		this.vencimento = venc;
		this.plano = plan;
		this.cliente = client;
		this.numero = new BigDecimal(this.proxNum).toString();
		this.ligacoes = new ArrayList<Ligacao>();
		this.proxNum++;
	}

	public CelularAss(Plano plano, Cliente cliente, int vencimento, String numero) {
		this.vencimento = vencimento;
		this.plano = plano;
		this.cliente = cliente;
		this.numero = numero;
		int proximo = Integer.parseInt(numero)+1;
		if(proximo>Celular.proxNum)
			Celular.proxNum = proximo;
		this.ligacoes = new ArrayList<Ligacao>();
	}

	@Override
	public Ligacao realizarLigacao(GregorianCalendar data, double duracao) {
		Ligacao nova = new Ligacao(data, duracao,duracao*this.plano.getValorMin());
		ligacoes.add(nova);
		return nova;
	}

	public int getVencimento() {
		return new Integer(this.vencimento);
	}

	public double getConta() {
		GregorianCalendar vencimentoAtual = new GregorianCalendar();
		if (vencimentoAtual.get(Calendar.DAY_OF_MONTH)>this.vencimento) {
			vencimentoAtual.add(Calendar.MONTH, 1);
		}
		vencimentoAtual.set(Calendar.DAY_OF_MONTH, this.vencimento);
		GregorianCalendar vencimentoPassado = (GregorianCalendar) vencimentoAtual.clone();
		vencimentoPassado.add(Calendar.MONTH, -1);
		double valor = 0;
	for (int i = 0; i < this.ligacoes.size(); i++) {
      if (((vencimentoPassado.compareTo(this.ligacoes.get(i).getDataLig())) < 0) && ((vencimentoAtual.compareTo(this.ligacoes.get(i).getDataLig())) > 0)) {
        valor += this.ligacoes.get(i).getValorTotal();
      }
    }
    return valor;
	}

	@Override
	public double getCreditos() throws CelularInvalidoException {
		throw new CelularInvalidoException("Celular é do tipo assinatura");
	}

	@Override
	public void addCreditos(double valor) throws CelularInvalidoException {
		throw new CelularInvalidoException("Celular é do tipo assinatura");
	}

	@Override
	public GregorianCalendar getValidade() throws CelularInvalidoException {
		throw new CelularInvalidoException("Celular é do tipo assinatura");
	}

	@Override
	public double deletavel() {
		return getConta();
	}

	@Override
	public char getTipo() {
		return 'A';
	}
}
