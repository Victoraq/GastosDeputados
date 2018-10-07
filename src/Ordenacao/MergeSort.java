package Ordenacao;

/**
 * Classe que implementa o algoritmo de ordenacao MergeSort.
 */
public class MergeSort extends Ordenacao{
    
    public MergeSort() {}
    
    /**
    * Metodo que ordena em ordem crescente um vetor.
    * @param array - vetor a ser ordenado.
    */
    public void ordenar(Integer[] array){
        long inicio, fim;
        
        inicio = System.currentTimeMillis();
        
        auxMergeSort(array, 0, array.length);
        
        fim = System.currentTimeMillis();
        super.setDuracao(fim - inicio);
    }
    
    /**
    * Metodo auxiliar de ordenacao.
    * @param array - vetor a ser ordenado.
    * @param inicio - indice que indica o inicio do vetor.
    * @param fim - indice que indica o final do vetor.    
    */
    private void auxMergeSort(Integer[] array, int inicio, int fim){
        if( inicio < fim - 1) {
            int meio = ( inicio + fim)/2;
            auxMergeSort(array, inicio, meio);
            auxMergeSort(array, meio, fim);
            uniao(array, inicio, meio, fim);
        }
    }
    
    /**
    * Metodo para a uniao de dois vetores.
    * @param array - vetor a ser ordenado.
    * @param inicio - indice que indica o inicio do vetor.
    * @param meio - indice que indica o meio do vetor.
    * @param fim - indice que indica o final do vetor.    
    */
    private void uniao(Integer[] array, int inicio, int meio, int fim){
        Integer temp[] = new Integer[fim-inicio];
        super.copia(fim-inicio); // Cópia de (fim-inicio) números
        int i, j, k;
        k = 0;
        i = inicio;
        j = meio;
        
        while( i < meio  && j < fim ) {
            if( array[i] <= array[j]){
                temp[k++] = array[i++];
            } else{
                temp[k++] = array[j++];}
            super.compara();
        }
        
        while( i < meio ){
            temp[k++] = array[i++];
        }
        
        while( j < fim ){
            temp[k++] = array[j++];
        }
               
        for( i = inicio; i < fim ; ++i) {
            array[i] = temp[i-inicio];
        }
    }
}
