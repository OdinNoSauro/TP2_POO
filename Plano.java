package operadora;

public class Plano {
  private String nomePlano;
  private double valorMin;

  public Plano(String nome, double valor) {
    this.nomePlano = nome;
    this.valorMin = valor;
  }
  
  public String getNome() {
	  String s = this.nomePlano;
	  return s;
  }
  
  public double getValorMin() {
	  double copia = new Double(this.valorMin);
	  return copia;
  }
  
  public Plano retornaClone() throws CloneNotSupportedException {
	  return (Plano) this.clone();
  }
}
