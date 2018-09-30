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
    private long duracao;
    
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

    protected void setDuracao(long duracao) {
        this.duracao = duracao;
    }
    
    public long getDuracao() {
        return duracao;
    }
    
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
    
    protected void troca(Object[] array, int index1, int index2) {
        Object aux;
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
