/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import Ordenacao.HeapSort;
import Ordenacao.InsertionSort;
import Ordenacao.MergeSort;
import Ordenacao.ShellSort;

/**
 *
 * @author ice
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Integer array[] = {12, 4, 231, 23, 98, 34, 123, 3, 213, 1, 2, 122, 89, 7};
       InsertionSort ord = new InsertionSort();
       ord.ordenar(array);
       ord.imprimir(array);
       System.out.println(ord.getNumCopias());
       System.out.println(ord.getNumComparacoes());
    }
    
}
