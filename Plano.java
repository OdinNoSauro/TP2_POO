package operadora;

public class Plano extends Object implements Cloneable{
  private String nomePlano;
  private double valorMin;

  public Plano(String nome, double valor) {
    this.nomePlano = nome;
    this.valorMin = valor;
  }

  public String getNome() {
	  String copia = this.nomePlano;
	  return copia;
  }

  public double getValorMin() {
	  double copia = new Double(this.valorMin);
	  return copia;
  }

  public Plano retornaClone() throws CloneNotSupportedException {
	  return (Plano) this.clone();
  }
}
