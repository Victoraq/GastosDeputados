/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;


/**
 *
 * @author victor
 */
public class Ordenacao {

    public Ordenacao() {
    }
    
    public void troca(int[] array, int index1,int index2) {
        int aux;
        aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
    }
    
    public void bubbleSort(int[] array) {
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
    
    public void insertionSort(int[] array) {
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
    
    public void maxHeapfy(int[] array, int fim) {
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
    
    public void HeapSort(int[] array) {
        int tam = array.length;
        
        for (int i = tam-1; i > 1; i--) {
            
            maxHeapfy(array, i);
            troca(array, 0, i);
            for(int j = 0; j < 5; j++)
                System.out.println(array[j]);
            System.out.println("\nIndice: "+i+"\n");
        }
        
    }
    
}
