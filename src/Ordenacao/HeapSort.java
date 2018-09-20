package Ordenacao;

import GastoDeputados.Ordenacao;

public class HeapSort extends Ordenacao{
    
    public HeapSort() {}
    
    public void ordenar(Integer[] array){
        int tam = array.length;
        for (int i = tam-1; i > 1; i--) {
            maxHeapfy(array, i);
            troca(array, 0, i);
        }
    }
    
    private void maxHeapfy(Integer[] array, int fim) {
        int tam = fim;
        int p = (int) tam/2 - 1; // Ponteiro para percorrer o array
        boolean existe2 = true;
        
        while(p > -1) {

            int pai = array[p];
            int filho1 = array[2*p];
            int filho2 = array[2*p+1];
            
            if (2*p+1 >= tam) 
                existe2 = false;

            if (filho1 < filho2 && existe2) {
                if (pai < filho2) {
                    troca(array, p, 2*p+1);
                }
            } else {
                if (pai < filho1) {
                    troca(array, p, 2*p);
                }
            }
            p--;
        }
    }
}
