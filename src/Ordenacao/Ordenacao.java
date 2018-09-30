package Ordenacao;

/**
 * Classe que implementa funcoes gerais dos algoritmos de ordenacao.
 */
public class Ordenacao {
    
    /**
     * numComparacoes - quantidade de comaparacoes feitas.
     * numCopias - quantidade de copias feitas.
     */
    private double numComparacoes;
    private double numCopias;
    
    /**
     * Construtor da classe Ordenacao.
     */
    public Ordenacao() {
        this.numComparacoes = 0;
        this.numCopias = 0;
    }
    
    /**
     * Métodos que retornam atributos da classe.
     */
    public double getNumComparacoes(){
        return this.numComparacoes;
    }
    
    public double getNumCopias(){
        return this.numCopias;
    }
    
    /**
     * Métodos que acrescentam caso ocorram comparacoes e/ou copias durante a ordenacao.
     */
    protected void compara(){
        numComparacoes++;
    }
    
    protected void copia(){
        numCopias ++;
    }
    
    protected void compara(int num){
        numComparacoes += num;
    }
    
    protected void copia(int num){
        numCopias += num;
    }
    
    /**
     * Método que realiza a troca de dois valores num vetor.
     */
    protected void troca(Integer[] array, int index1, int index2) {
        int aux;
        aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
        numCopias++;
    }
    
    /**
     * Método que imprime o vetor na tela.
     */
    public void imprimir(Integer[] array){
        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);
    }
}
