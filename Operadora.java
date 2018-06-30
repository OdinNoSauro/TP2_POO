package operadora;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;

public class Operadora extends Object implements Cloneable {
  private ArrayList<Cliente> clientes;
  private ArrayList<Plano> planos;
  private ArrayList<Celular> celulares;
  private String nomeOp;

  public Operadora(String nome) {
	  this.clientes = new ArrayList<Cliente>();
	  this.planos = new ArrayList<Plano>();
	  this.celulares = new ArrayList<Celular>();
	  this.nomeOp = nome;
  }

  public void addCliente(String nomeCliente, String documento, String endereco) {
	  Cliente novo = new Cliente(nomeCliente, documento, endereco);
    this.clientes.add(novo);
  }

  public void addPlano(String nome, double valor) {
	  Plano novo = new Plano(nome, valor);
    this.planos.add(novo);
  }

  public int findCliente(String nomeCliente) throws NotInListException {
    for (int i = 0; i < this.clientes.size(); i++){
      if (this.clientes.get(i).getNomeCliente().equals(nomeCliente)) {
        return i;
      }
    }
    throw new NotInListException("Cliente não existe");
  }

  public int findCelular(String numeroFone) throws NotInListException {
    for (int i = 0; i < this.celulares.size(); i++){
      if (this.celulares.get(i).getNumero().equals(numeroFone)) {
        return i;
      }
    }
    throw new NotInListException("Celular não existe");
  }

  public int findPlano(String nomePlano) throws NotInListException {
    for (int i = 0; i < this.planos.size(); i++){
      if (this.planos.get(i).getNome().equals(nomePlano)) {
        return i;
      }
    }
    throw new NotInListException("Plano não existe");
  }

  public void addCelular(String nomeCliente, char tipo, Plano plano, int vencimento) throws CelularInvalidoException, NotInListException {
    int indiceCliente = findCliente(nomeCliente);
    Celular novo = this.clientes.get(indiceCliente).newCelular(tipo,plano, vencimento);
    this.celulares.add(novo);
  }

  public int deleteCelular(String numero) throws NotInListException, CelularInvalidoException {
	  int indice = findCelular(numero);
	  if(this.celulares.get(indice).deletavel() == 0) {
		  this.celulares.remove(indice);
		  return 1;
	  }
	  else {
		  return 0;
    }
  }

  public void addCreditos(String numero, double valor) throws NotInListException, CelularInvalidoException {
	  int indiceCelular = findCelular(numero);
    this.celulares.get(indiceCelular).addCreditos(valor);
  }

  public void newLigacao(String numero, GregorianCalendar data, double duracao) throws CelularInvalidoException, LigacaoInvalidaException, NotInListException {
    int indiceCelular = findCelular(numero);
    this.celulares.get(indiceCelular).realizarLigacao(data, duracao);
  }

  public ArrayList<Ligacao> extratoLigacao(String numero, GregorianCalendar inicio) throws NotInListException {
    int indiceCelular = findCelular(numero);
    return (ArrayList<Ligacao>) this.celulares.get(indiceCelular).getLigacoes(inicio).clone();
  }

  public double valorConta(String numero) throws NotInListException, CelularInvalidoException {
    int indiceCelular = findCelular(numero);
    return this.celulares.get(indiceCelular).getConta();
  }

  public GregorianCalendar getVencimento(String numero) throws NotInListException, CelularInvalidoException {
    int indiceCelular = findCelular(numero);
    GregorianCalendar vencimento = new GregorianCalendar();
    vencimento.set(Calendar.DAY_OF_MONTH, this.celulares.get(indiceCelular).getVencimento());
    return (GregorianCalendar) vencimento.clone();
  }

  public double getCreditos(String numero) throws  CelularInvalidoException, NotInListException {
    int indiceCelular = findCelular(numero);
    return this.celulares.get(indiceCelular).getCreditos();
  }

  public GregorianCalendar getValidade(String numero) throws NotInListException, CelularInvalidoException {
    int indiceCelular = findCelular(numero);
    return (GregorianCalendar) this.celulares.get(indiceCelular).getValidade().clone();
  }

  public ArrayList<Cliente> getClientes() {
    return (ArrayList<Cliente>) this.clientes.clone();
  }

  public ArrayList<Celular> getCelulares() {
    return (ArrayList<Celular>) this.celulares.clone();
  }

  public ArrayList<Plano> getPlanos() {
    return (ArrayList<Plano>) this.planos.clone();
  }

  public ArrayList<Celular> checkValidadeVencimento() throws CelularInvalidoException{
    GregorianCalendar atual = new GregorianCalendar();
    GregorianCalendar vencimento = new GregorianCalendar();
    ArrayList<Celular> listaRetorno = new ArrayList<Celular>();
    for(int i = 0; i < this.celulares.size() ; i++) {
      if(this.celulares.get(i).getTipo() == 'A') {
        vencimento.set(Calendar.DAY_OF_MONTH, this.celulares.get(i).getVencimento());
        if(vencimento.equals(atual)) {
          listaRetorno.add(this.celulares.get(i));
        }
      }
      else if(this.celulares.get(i).getTipo() == 'C') {
        if(this.celulares.get(i).getValidade().equals(atual)) {
          listaRetorno.add(this.celulares.get(i));
        }
      }
    }
    return listaRetorno.clone();
  }

  public void gravarClientes() throws CloneNotSupportedException, FileNotFoundException, IOException {
  	FileOutputStream out = new FileOutputStream("Clientes.txt");
  	ObjectOutputStream objOut = new ObjectOutputStream(out);
  	objOut.writeObject(this.clientes);
  	objOut.close();
  }

  public void gravarCelulares() throws CloneNotSupportedException, FileNotFoundException, IOException {
    FileOutputStream out = new FileOutputStream("Celulares.txt");
    ObjectOutputStream objOut = new ObjectOutputStream(out);
    objOut.writeObject(this.celulares);
    objOut.close();
  }

  public void gravarPlanos() throws CloneNotSupportedException, FileNotFoundException, IOException {
    FileOutputStream out = new FileOutputStream("Planos.txt");
    ObjectOutputStream objOut = new ObjectOutputStream(out);
    objOut.writeObject(this.planos);
    objOut.close();
  }

  public void lerClientes() throws IOException, FileNotFoundException, ClassNotFoundException {
  	if(new File("Clientes.txt").canRead() == true) {
			FileInputStream inC = new FileInputStream("Clientes.txt");
			ObjectInputStream objInc = new ObjectInputStream(inC);
			this.clientes = (ArrayList<Cliente>) objInc.readObject();
			objInc.close();
  	}
  }

  public void lerCelulares() throws IOException, FileNotFoundException, ClassNotFoundException {
    if(new File("Celulares.txt").canRead() == true) {
      FileInputStream inC = new FileInputStream("Celulares.txt");
      ObjectInputStream objInc = new ObjectInputStream(inC);
      this.celulares = (ArrayList<Cliente>) objInc.readObject();
      objInc.close();
    }
  }

  public void lerPlanos() throws IOException, FileNotFoundException, ClassNotFoundException {
    if(new File("Planos.txt").canRead() == true) {
      FileInputStream inC = new FileInputStream("Planos.txt");
      ObjectInputStream objInc = new ObjectInputStream(inC);
      this.planos = (ArrayList<Cliente>) objInc.readObject();
      objInc.close();
    }
  }
}
