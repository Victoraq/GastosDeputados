package Ordenacao;

/**
 * Classe que implementa o algoritmo de ordenacao InsertionSort.
 */
public class InsertionSort extends Ordenacao{
    
    public InsertionSort() {}
    
    /**
    * Metodo que ordena em ordem crescente um vetor.
    * @param array - vetor a ser ordenado.
    */
    public void ordenar(Integer[] array){
        int tam = array.length;
        int ind_aux;
        int ordenado, proximo;
        long inicio, fim;
        
        inicio = System.currentTimeMillis();
        

        for (int i = 1; i < tam; i++) {
            
            ind_aux = i-1;
            ordenado = array[ind_aux];
            proximo = array[i];
            
            while(ordenado > proximo) {
                super.compara();
                array[ind_aux+1] = array[ind_aux];
                ind_aux--;
                super.copia();
                
                if (ind_aux == -1){
                    break;
                }
                
                ordenado = array[ind_aux];
                super.copia();
            }
            super.compara();
            
            array[ind_aux+1] = proximo;
            super.copia();
        }
        
        fim = System.currentTimeMillis();
        super.setDuracao(fim - inicio);
    }
}

