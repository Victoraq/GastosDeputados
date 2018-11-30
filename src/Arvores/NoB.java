
package Arvores;

/**
 * Classe que implementa o Nó da Árvore B.
 */
public class NoB {
    /**
    * ordem - ordem do nó
    * chaves - vetor de chaves inseridas no nó
    * filhos - vetor de nós filhos
    * folha - tag para indicar se o nó é folha ou não
    * pai - pai do nó
    */
    protected int ordem;
    protected int [] chaves;
    protected NoB [] filhos;
    protected boolean folha;
    protected NoB pai;

    /**
     * Construtor do nó B
     * @param ordem - valor da ordem do nó
     */
    public NoB(int ordem) {
        this.ordem = ordem;
        chaves = new int[ordem]; // numero de chaves == ordem - 1. +1 para overflow
        filhos = new NoB[ordem+1]; // numero de filhos == ordem. +1 para overflow
        folha = true;
    }
    
}
