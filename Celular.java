package operadora;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class Celular extends Object implements Cloneable  {
	protected Plano plano;
	protected Cliente cliente;
	protected ArrayList<Ligacao> ligacoes;
	protected String numero;
	protected static double proxNum = 90000000;
	
	public abstract void realizarLigacao(GregorianCalendar data, double duracao);
	public abstract Celular getCelular();
	
}
