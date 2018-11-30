
package Arvores;

/**
 * Classe que implementa o Nó da Árvore AA.
 */
public class NoAA {
    /**
    * valor - valor inteiro armazenado no nó
    * nivel - nível em que o nó está na árvore
    * fEsq - filho a esquerda
    * fDir - filho a direita
    * pai - pai do nó
    */
    protected int valor, nivel;
    protected NoAA fEsq, fDir, pai;

    /**
     * Construtor vazio da Árvore.
     */
    public NoAA() {
    }

    /**
     * Construtor Nó da AA.
     * @param valor - valor inteiro armazenado no nó
     * @param fEsq - filho a esquerda
     * @param pai - pai do nó
     * @param fDir - filho a direita
     */
    public NoAA(int valor, NoAA fEsq, NoAA fDir, NoAA pai) {
        this.valor = valor;
        this.fEsq = fEsq;
        this.fDir = fDir;
        this.pai = pai;
        this.nivel = 1 + calculaNivel(fEsq);
    }

    /**
     * Construtor Nó da AA.
     * @param valor - valor inteiro armazenado no nó
     */
    public NoAA(int valor) {
        this.valor = valor;
        this.fEsq = null;
        this.fDir = null;
        this.pai = null;
        this.nivel = 1;
    }
    
    /**
     * Calcula nível em que o nó está
     * @param no - nó a ser avaliado o nível
     * @return Nível do nó
     */
    public int calculaNivel(NoAA no) {
        if(no == null) {
            return 0;
        }
        else {
            if(no.getfEsq() == null && no.getfDir() == null) { // Nós folha estão sempre no nível 1.
                return 1;
            }
            else {
                return 1 + calculaNivel(no.fEsq);
            }
        }
    }
    
    /**
     * Métodos que retornam atributos da classe.
     */
    
    public NoAA getfEsq() {
        return fEsq;
    }

    public void setfEsq(NoAA fEsq) {
        this.fEsq = fEsq;
    }

    public NoAA getfDir() {
        return fDir;
    }

    public void setfDir(NoAA fDir) {
        this.fDir = fDir;
    }

    public NoAA getPai() {
        return pai;
    }

    public void setPai(NoAA pai) {
        this.pai = pai;
    }

    public int getValor() {
        return valor;
    }

}
