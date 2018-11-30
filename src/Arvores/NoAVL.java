
package Arvores;

/**
 * Classe que implementa o Nó da Árvore AVL.
 */
public class NoAVL {
    /**
    * valor - valor inteiro armazenado no nó
    * fatorb - fator de balanceamento
    * fesq - filho a esquerda
    * fdir - filho a direita
    * pai - pai do nó
    */
    protected int valor, fatorb;
    protected NoAVL fesq, fdir, pai;

    /**
     * Construtor Nó da AVL.
     * @param valor - valor inteiro armazenado no nó
     * @param fesq - filho a esquerda
     * @param pai - pai do nó
     * @param fdir - filho a direita
     */
    public NoAVL(int valor, NoAVL fesq, NoAVL fdir, NoAVL pai) {
        this.valor = valor;
        this.fesq = fesq;
        this.fdir = fdir;
        this.pai = pai;
    }

    /**
     * Construtor Nó da AVL.
     * @param valor - valor inteiro armazenado no nó
     */
    public NoAVL(int valor) {
        this.valor = valor;
        this.fesq = null;
        this.fdir = null;
        this.pai = null;
    }
    
    /**
     * Métodos que retornam atributos da classe.
     */

    public int getValor() {
        return valor;
    }

    public NoAVL getFesq() {
        return fesq;
    }

    public void setFesq(NoAVL fesq) {
        this.fesq = fesq;
    }

    public NoAVL getFdir() {
        return fdir;
    }

    public void setFdir(NoAVL fdir) {
        this.fdir = fdir;
    }

    public NoAVL getPai() {
        return pai;
    }

    public void setPai(NoAVL pai) {
        this.pai = pai;
    }

    
    
}
