
package Arvores;

/**
 * Classe que implementa o Nó da Árvore Splay.
 */
public class NoSplay {
    /**
    * valor - valor inteiro armazenado no nó
    * fesq - filho a esquerda
    * fdir - filho a direita
    * pai - pai do nó
    */
    protected int valor;
    protected NoSplay fesq,fdir,pai;

    /**
     * Construtor do nó da Árvore Splay
     * @param valor - valor inteiro armazenado no nó
     * @param fesq - filho a esquerda
     * @param fdir - filho a direita
     * @param pai - pai do nó
     */
    public NoSplay(int valor, NoSplay fesq, NoSplay fdir, NoSplay pai) {
        this.valor = valor;
        this.fesq = fesq;
        this.fdir = fdir;
        this.pai = pai;
    }
    
    /**
     * Construtor do nó da Árvore Splay
     * @param valor - valor inteiro armazenado no nó
     */
    public NoSplay(int valor){
        this.valor=valor;
        this.fesq=null;
        this.fdir=null;
        this.pai=null;
    }

    public NoSplay() {
        this.fesq=null;
        this.fdir=null;
        this.pai=null;
       
    }
    
    /**
     * Métodos que retornam atributos da classe.
     */

    public int getValor() {
        return valor;
    }

    public NoSplay getFesq() {
        return fesq;
    }

    public NoSplay getFdir() {
        return fdir;
    }

    public NoSplay getPai() {
        return pai;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setFesq(NoSplay fesq) {
        this.fesq = fesq;
    }

    public void setFdir(NoSplay fdir) {
        this.fdir = fdir;
    }

    public void setPai(NoSplay pai) {
        this.pai = pai;
    }
        
}
