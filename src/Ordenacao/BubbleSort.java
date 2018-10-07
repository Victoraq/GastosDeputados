package Ordenacao;

/**
 * Classe que implementa o algoritmo de ordenacao BubbleSort.
 */
public class BubbleSort extends Ordenacao{
    
    public BubbleSort() {}
    
    /**
    * Metodo que ordena em ordem crescente um vetor.
    * @param array - vetor a ser ordenado.
    */
    public void ordenar(Integer[] array){
        boolean trocou = true;
        int tam = array.length;
        int atual, proximo;
        
        while(trocou) {
            trocou = false;
            for (int i = 0; i < tam - 1; i++) {
                
                atual = array[i];
                proximo = array[i+1];
                
                if (atual > proximo) {
                    troca(array, i, i+1);
                    trocou = true;
                }
            }
        }
    }
}