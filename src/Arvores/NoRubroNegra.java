
package Arvores;

/**
 * Classe que implementa o Nó da Árvore Rubro Negra.
 */
public class NoRubroNegra {
    /**
    * valor - valor inteiro armazenado no nó
    * cor - cor do nó
    * fesq - filho a esquerda
    * fdir - filho a direita
    * pai - pai do nó
    */
    protected char cor;
    protected int valor;
    protected NoRubroNegra fesq, fdir, pai;

    /**
     * Construtor Nó da Rubro Negra.
     * @param valor - valor inteiro armazenado no nó
     * @param fesq - filho a esquerda
     * @param pai - pai do nó
     * @param fdir - filho a direita
     */
    public NoRubroNegra(int valor, NoRubroNegra fesq, NoRubroNegra fdir, NoRubroNegra pai) {
        this.valor = valor;
        this.fesq = fesq;
        this.fdir = fdir;
        this.pai = pai;
        this.cor = 'v';
    }

    /**
     * Construtor Nó da AVL.
     * @param valor - valor inteiro armazenado no nó
     */
    public NoRubroNegra(int valor) {
        this.valor = valor;
        this.fesq = null;
        this.fdir = null;
        this.pai = null;
        this.cor = 'v';
    }
    
    /**
     * Métodos que retornam atributos da classe.
     */
    
    public int getValor() {
        return valor;
    }

    public char getCor() {
        return cor;
    }

    public void setCor(char cor) {
        this.cor = cor;
    }

    public NoRubroNegra getFesq() {
        return fesq;
    }

    public void setFesq(NoRubroNegra fesq) {
        this.fesq = fesq;
    }

    public NoRubroNegra getFdir() {
        return fdir;
    }

    public void setFdir(NoRubroNegra fdir) {
        this.fdir = fdir;
    }

    public NoRubroNegra getPai() {
        return pai;
    }

    public void setPai(NoRubroNegra pai) {
        this.pai = pai;
    }
    
    
}
