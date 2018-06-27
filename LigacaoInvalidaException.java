package operadora;

public class LigacaoInvalidaException extends Exception{
	public LigacaoInvalidaException() {

  }

  public LigacaoInvalidaException(String mensagem) {
      super (mensagem);
  }

  public LigacaoInvalidaException(Throwable causa) {
      super (causa);
  }

  public LigacaoInvalidaException(String mensagem, Throwable causa) {
      super (mensagem, causa);
  }
}
