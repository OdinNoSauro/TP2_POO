package operadora;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CelularAss extends Celular {
	private GregorianCalendar vencimento;
	
	public CelularAss(Plano plan, Cliente client, GregorianCalendar venc) {
		this.vencimento = venc;
		this.plano = plan;
		this.cliente = client;
		this.numero = new Double(this.proxNum).toString();
		this.proxNum++;
	}
	
	@Override
	public void realizarLigacao(GregorianCalendar data, double duracao) {
		
	}
	@Override
	public Celular getCelular() {
		return null;
	}
	
}
