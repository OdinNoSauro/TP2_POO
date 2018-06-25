package operadora;

public class LigacaoInvalidaException extends Exception{
	public LigacaoInvalidaException () {

    }

    public LigacaoInvalidaException (String menssagem) {
        super (menssagem);
    }

    public LigacaoInvalidaException (Throwable causa) {
        super (causa);
    }

    public LigacaoInvalidaException (String menssagem, Throwable causa) {
        super (menssagem, causa);
    }
}
