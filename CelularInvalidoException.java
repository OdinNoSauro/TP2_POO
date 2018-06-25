package operadora;

public class CelularInvalidoException extends Exception{
	public CelularInvalidoException () {

    }

    public CelularInvalidoException (String menssagem) {
        super (menssagem);
    }

    public CelularInvalidoException (Throwable causa) {
        super (causa);
    }

    public CelularInvalidoException (String menssagem, Throwable causa) {
        super (menssagem, causa);
    }
}