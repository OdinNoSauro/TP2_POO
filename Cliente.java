package operadora;


public class Cliente extends Object implements Cloneable {
  private String nomeCliente;
  private String documento;
  private String endereco;
  
  
  public Cliente(String nomeCliente, String documento, String endereco, String fone){
    this.nomeCliente = nomeCliente;
    this.documento = documento;
    this.endereco = endereco;
  }

  public Cliente retornaClone() throws CloneNotSupportedException {
      return (Cliente)this.clone();
  }

  public String getNomeCliente(){
	  String copia = this.nomeCliente;
	  return copia;
  }

  public String getdocumento(){
	  String copia = this.documento;
	  return copia;
  }

  public String getEndereco(){
	  String copia = this.endereco;
	  return copia;
  }

  public void setNomeCliente(String nomeCliente){
    this.nomeCliente = nomeCliente;
  }

  public void setdocumento(String documento){
    this.documento = documento;
  }

  public void setEndereco(String endereco){
    this.endereco = endereco;
  }
}
