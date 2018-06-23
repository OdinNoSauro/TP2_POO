package operadora;

import java.util.GregorianCalendar;

public class Ligacao extends Object implements Cloneable {
  private GregorianCalendar dataLig;
  private double duracao;

  public Ligacao(GregorianCalendar data, double duracao) {
    this.dataLig = data;
    this.duracao = duracao;
  }

  public Ligacao retornaClone() {
    try {
      return (Ligacao)this.clone();
    }
    catch(CloneNotSupportedException exception) {
      exception.printStackTrace();
      return null;
    }
  }

  public GregorianCalendar getDataLig() {
    return dataLig.retornaClone();
  }

  public double getDuracao() {
    return duracao.retornaClone();
  }

  public void setDataLig(GregorianCalendar data) {
    this.dataLig = data;
  }

  public void setDuracao(double duracao) {
    this.duracao = duracao;
  }
}
