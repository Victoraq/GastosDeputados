
package Arvores;

/**
 * Classe que implementa o Nó da Árvore Trie Ternária.
 */
public class NoTrieTernaria {
    
    /**
     * c - Caracter do nó
     * esq - Nó a esquerda
     * centro - Nó subsequente
     * dir - Nó a direita
     * finalt - Tag para sinalizar se terminou ou não uma string
     * gasto - gasto de determinada string
     */
    private Character c;
    private NoTrieTernaria esq, centro, dir;
    private Boolean finalt;
    private double gasto = 0; // gasto para inserção de gasto de deputados

    /**
     * Construtor do Nó Trie Ternária.
     * @param c - caracter do nó
     */
    public NoTrieTernaria(Character c) {
        this.c = c;
        this.centro = null;
        this.esq = null;
        this.dir = null;
        this.finalt = false;
    }

    /**
     * Métodos que retornam atributos da classe.
     */
    
    public Character getChar() {
        return c;
    }

    public void setChar(Character c) {
        this.c = c;
    }

    public NoTrieTernaria getEsq() {
        return esq;
    }

    public void setEsq(NoTrieTernaria esq) {
        this.esq = esq;
    }

    public NoTrieTernaria getCentro() {
        return centro;
    }

    public void setCentro(NoTrieTernaria centro) {
        this.centro = centro;
    }

    public NoTrieTernaria getDir() {
        return dir;
    }

    public void setDir(NoTrieTernaria dir) {
        this.dir = dir;
    }

    public Boolean getFinal() {
        return finalt;
    }

    public void setFinal(Boolean finalt) {
        this.finalt = finalt;
    }
    
    public double getGasto() {
        return this.gasto;
    }    
    
    public void setGasto(double gasto) {
        this.gasto += gasto;
    }
    
}
