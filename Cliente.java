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

  public Cliente retornaClone() {
    try {
      return (Cliente)this.clone();
    }
    catch(CloneNotSupportedException exception) {
      exception.printStackTrace();
      return null;
    }
  }

  public String getNomeCliente(){
    return nomeCliente.retornaClone();
  }

  public String getdocumento(){
    return documento.retornaClone();
  }

  public String getEndereco(){
    return endereco.retornaClone();
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
