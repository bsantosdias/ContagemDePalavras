
/**
 *
 * @author bsantosdias
 */

public class FreqPalavra {
    private No raiz;

    // Cria uma ABB vazia
    public FreqPalavra() {
        this.raiz = null;
    }

    // Verifica se a árvore está vazia
    public boolean vazia() {
        return this.raiz == null;
    }

    public void insere(String elemento) {
        No p = raiz;
        No pai = null;
        No novo = new No(elemento, null, null);

        if (elemento.equals("as")) {
            String teste = "teste";
        }

        while (p != null) {
            pai = p;
            if (elemento.compareTo(p.getElemento()) < 0) {
                p = p.getEsq();
            } else if (elemento.equals(p.getElemento())) {
                p.somaRecorrencia();
                return;
            } else {
                p = p.getDir();
            }
        }
        if (vazia()) {
            raiz = novo;
            return;
        }
        if (elemento.compareTo(pai.getElemento()) < 0) {
            pai.setEsq(novo);
        } else if (elemento.equals(pai.getElemento())) {
            pai.somaRecorrencia();
        } else {
            pai.setDir(novo);
        }
    }

    public void insereR(String elemento) {
        if (vazia()) {
            raiz = new No(elemento, null, null);
            ;
            return;
        }
        No novo = new No(elemento, null, null);
        insereR(raiz, novo);
    }

    public void insereR(No p, No novo) {
        if (novo.getElemento().compareTo(p.getElemento()) < 0)
            if (p.getEsq() == null)
                p.setEsq(novo);
            else
                insereR(p.getEsq(), novo);
        else if (p.getDir() == null)
            p.setDir(novo);
        else
            insereR(p.getDir(), novo);
    }

    public void preOrdem() {
        preOrdem(raiz);
    }

    public void preOrdem(No p) {
        if (p != null) {
            System.out.print(p.getElemento() + " ");
            preOrdem(p.getEsq());
            preOrdem(p.getDir());
        }
    }

    public void inOrdem() {
        inOrdem(raiz);
    }

    public void inOrdem(No p) {
        if (p != null) {
            inOrdem(p.getEsq());
            System.out.println("Palavra: " + p.getElemento() + ", Numero de ocorrências: " + p.getRecorrencia());
            inOrdem(p.getDir());
        }
    }

    public void posOrdem() {
        posOrdem(raiz);
    }

    public void posOrdem(No p) {
        if (p != null) {
            posOrdem(p.getEsq());
            posOrdem(p.getDir());
            System.out.print(p.getElemento() + " ");
        }
    }

    // Melhor caso, ABB está balanceada ou completa, O(logn)
    // Melhor pior, ABB totalmente desbalanceada, linear, O(n)
    public No buscaIt(String elemento) {
        No p = raiz;

        while (p != null) {
            if (elemento.compareTo(p.getElemento()) == 0)
                return p; // Achou

            if (elemento.compareTo(p.getElemento()) < 0)
                p = p.getEsq();
            else
                p = p.getDir();
        }
        return null; // Não achou
    }

    public No buscaR(String elemento) {
        return buscaR(raiz, elemento);
    }

    public No buscaR(No p, String elemento) {
        if (p == null)
            return null; // NÃO achou

        if (elemento.compareTo(p.getElemento()) == 0)
            return p; // Achou

        if (elemento.compareTo(p.getElemento()) < 0)
            return buscaR(p.getEsq(), elemento);
        else
            return buscaR(p.getDir(), elemento);
    }

    public No menorIt() {
        No p = raiz;

        while (p.getEsq() != null) {
            p = p.getEsq();
        }
        return p;
    }

    public No menor() {
        return menor(raiz);
    }

    public No menor(No p) {
        if (p.getEsq() == null)
            return p;

        return menor(p.getEsq());
    }

    public No maior() {
        return maior(raiz);
    }

    public No maior(No p) {
        if (p.getDir() == null)
            return p;

        return maior(p.getDir());
    }

    // Método que remove um nó na ABB
    public No remove(String elemento) {
        return remove(raiz, elemento);
    }

    public No remove(No p, String elemento) {
        if (p == null)
            return null; // Não achei

        if (elemento.compareTo(p.getElemento()) < 0)
            p.setEsq(remove(p.getEsq(), elemento));
        else if (elemento.compareTo(p.getElemento()) > 0)
            p.setDir(remove(p.getDir(), elemento));
        else {
            // elemento==p.getElemento()
            // Verifica se o elemento será removido tem subárvore esquerda
            if (p.getEsq() != null) {
                // busca o maior na subárvore esquerda
                No aux = maior(p.getEsq());
                // Copia o elemento maior da subarv esq para p
                p.setElemento(aux.getElemento());
                // Remove elemento copiado recursivamente
                p.setEsq(remove(p.getEsq(), aux.getElemento()));
            }
            // Verifica se o elemento será removido tem subárvore direita
            else if (p.getDir() != null) {
                // busca o menor na subárvore direita
                No aux = menor(p.getDir());
                // Copia o elemento menor da subarv dir para p
                p.setElemento(aux.getElemento());
                // Remove elemento copiado recursivamente
                p.setDir(remove(p.getDir(), aux.getElemento()));
            } else
                // Verifica se é folha
                return null;
        }
        return p;
    }

    public int contaNos() {
        return contaNos(raiz);
    }

    public int contaNos(No p) {
        if (p == null)
            return 0;

        return 1 + contaNos(p.getEsq()) + contaNos(p.getDir());
    }

    public int altura() {
        return altura(raiz);
    }

    public int altura(No p) {
        if (p == null)
            return -1;

        int esq = altura(p.getEsq());
        int dir = altura(p.getDir());

        if (esq > dir)
            return 1 + esq;

        return 1 + dir;

    }

    public void inOrdemFolhas() {
        inOrdemFolhas(raiz);
    }

    public void inOrdemFolhas(No p) {
        if (p != null) {
            inOrdemFolhas(p.getEsq());
            if (p.getEsq() == null && p.getDir() == null) // Nó folha
                System.out.print(p.getElemento() + " ");
            inOrdemFolhas(p.getDir());
        }
    }

    public No sucessor(String elemento) {
        No p = buscaIt(elemento);

        if (p.getDir() != null)
            // Sucessor é o menor da subárvore da direita
            return menor(p.getDir());

        return ancestralDireita(p);
    }

    /**
     * Para determinar o ancestral pode-se proceder por dois métodos: (a) Subir na
     * árvore por meio de um atributo pai. Como não temos esse atributo no nosso
     * modelo, usaremos o segundo método. (b) Ou descer a partir da raiz procurando
     * esse sucessor;
     **/

    public No ancestralDireita(No p) {
        No pai = null;
        No temp = raiz;

        while (temp.getElemento().compareTo(p.getElemento()) != 0) {

            if (temp.getElemento().compareTo(p.getElemento()) > 0) {
                pai = temp;
                temp = temp.getEsq();
            } else
                temp = temp.getDir();
        }
        return pai;
    }

    @Override
    public String toString() {
        return "ABB{" + "raiz=" + raiz + '}';
    }

}
