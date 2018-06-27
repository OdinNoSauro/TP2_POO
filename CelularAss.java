package operadora;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CelularAss extends Celular {
	private int vencimento;

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

	public int getVencimento() {
		return new Int(this.vencimento);
	}

	public double getConta() {
		GregorianCalendar vencimentoAtual = new GregorianCalendar();
		vencimentoAtual.set(DAY_OF_MONTH, this.vencimento);
		GregorianCalendar vencimentoPassado = vencimentoAtual.clone();
		vencimentoPassado.add(DAY_OF_MONTH, -1);
		double valor = 0;
		ArrayList<Ligacao> listaRetorno = new ArrayList<Ligacao>();
    for (int i = 0; i < this.ligacoes.size(); i++) {
      if (((vencimentoPassado.compareTo(this.ligacoes.get(i).getDataLig())) < 0) && ((vencimentoAtual.compareTo(this.ligacoes.get(i).getDataLig())) > 0)) {
        valor += this.ligacoes.get(i).getDuracao()*this.plano.getValorMin();
      }
    }
    return valor;
	}

	@Override
	public double getCreditos() throws CelularInvalidoException {
		throw new CelularInvalidoException("Celular é do tipo assinatura");
	}
}
