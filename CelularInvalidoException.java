package operadora;

public class CelularInvalidoException extends Exception{
	public CelularInvalidoException() {

  }

  public CelularInvalidoException(String mensagem) {
    super(mensagem);
  }

  public CelularInvalidoException(Throwable causa) {
    super(causa);
  }

  public CelularInvalidoException(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }
}
