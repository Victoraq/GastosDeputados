package Ordenacao;

import GastoDeputados.Ordenacao;

public class InsertionSort extends Ordenacao{
    
    public InsertionSort() {}
    
    public void ordenar(Integer[] array){
        int tam = array.length;
        int ind_aux;
        int ordenado, proximo;

        for (int i = 1; i < tam; i++) {
            
            ind_aux = i-1;
            ordenado = array[ind_aux];
            proximo = array[i];
            
            while(ordenado > proximo) {
                array[ind_aux+1] = array[ind_aux];                    
                ind_aux--;
                if (ind_aux == -1)
                    break;
                ordenado = array[ind_aux];
            }
            array[ind_aux+1] = proximo;
        }   
    }
}

