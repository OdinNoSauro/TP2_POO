package operadora;

import java.util.GregorianCalendar;

public class Ligacao extends Object implements Cloneable {
  private GregorianCalendar dataLig;
  private double duracao;
  private double valorTotal;

  public Ligacao(GregorianCalendar data, double duracao, double valor) {
    this.dataLig = data;
    this.duracao = duracao;
    this.valorTotal = valor;
  }

  public Ligacao retornaClone() throws CloneNotSupportedException {
    return (Ligacao)this.clone();
  }

  public GregorianCalendar getDataLig() {
    return (GregorianCalendar) dataLig.clone();
  }

  public double getDuracao() {
    double dur = new Double(duracao);
    return dur;
  }

  public double getValorTotal() {
	double dur = new Double(valorTotal);
	return dur;
  }

  public void setDataLig(GregorianCalendar data) {
    this.dataLig = data;
  }

  public void setDuracao(double duracao) {
    this.duracao = duracao;
  }
}
