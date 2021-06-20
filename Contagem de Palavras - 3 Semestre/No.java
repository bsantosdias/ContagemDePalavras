/**
 *
 * @author bsantosdias
 */

public class No {
    private String elemento;
    private No esq;
    private No dir;
    private int recorrencia;

    public No(String elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;        
        this.recorrencia = 1;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public No getEsq() {
        return esq;
    }

    public void setEsq(No esq) {
        this.esq = esq;
    }

    public No getDir() {
        return dir;
    }

    public void setDir(No dir) {
        this.dir = dir;
    }

    public int getRecorrencia() {
        return recorrencia;
    }

    public void somaRecorrencia() {
        this.recorrencia++;
    }

    @Override
    public String toString() {
        return "No{" + "elemento=" + elemento + ", esq=" + esq + ", dir=" + dir + '}';
    }
    
    
}
