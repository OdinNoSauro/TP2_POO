package operadora;

public class NotInListException extends Exception{
	public NotInListException() {

  }

  public NotInListException(String mensagem) {
    super(mensagem);
  }

  public NotInListException(Throwable causa) {
    super(causa);
  }

  public NotInListException(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }
}
