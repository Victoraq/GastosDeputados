package Ordenacao;

public class MergeSort extends Ordenacao{
    
    public MergeSort() {}
    
    public void ordenar(Integer[] array){
        long inicio, fim;
        
        inicio = System.currentTimeMillis();
        
        auxMergeSort(array, 0, array.length);
        
        fim = System.currentTimeMillis();
        super.setDuracao(fim - inicio);
    }
    
    private void auxMergeSort(Integer[] array, int inicio, int fim){
        if( inicio < fim - 1) {
            int meio = ( inicio + fim)/2;
            auxMergeSort(array, inicio, meio);
            auxMergeSort(array, meio, fim);
            uniao(array, inicio, meio, fim);
        }
    }
    
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
