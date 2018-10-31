/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import Arvores.AVL;
import Arvores.NoAVL;
import Arvores.RubroNegra;
import Ordenacao.HeapSort;
import Ordenacao.InsertionSort;
import Ordenacao.MergeSort;
import Ordenacao.QuickSortMed;
import Ordenacao.ShellSort;
import java.util.Random;

/**
 *
 * @author ice
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//      Deputado[] dep = new LeituraDados("/home/victor/Documentos/ED2/Java/data/deputies_dataset_tratado.csv",
//                2500000).getDeputados();
//       
////       HeapSort insert = new HeapSort();
//       QuickSortMed quick = new QuickSortMed();
//       
//       int[] tamanhos = {1000, 5000, 10000, 50000, 100000, 500000};
//       
//       for (int k= 0; k < 5; k++) {
//       for (int j= 0; j < 5; j++) {
//           Integer array[] = new Integer[tamanhos[j]];
//       
//            for (int i = 0; i < tamanhos[j]; i++) {
//                int pos = new Random(System.currentTimeMillis()).nextInt(dep.length-1);
//                array[i] = dep[pos].getDeputy_id();
//            }
////
////            insert.ordenar(array);
////            
////            for (int i = 0; i < tamanhos[j]; i++) 
////                array[i] = new Random().nextInt(1000);
//            
//            quick.ordenar(array);
//            //ord.imprimir(array);
////            System.out.println("insert: "+insert.getDuracao());
//            System.out.println("quick: "+quick.getDuracao());
////            for (int i = 0; i < tamanhos[j]; i++) {
////                if (i % 100==0)System.out.println();
////                System.out.print(array[i]+" ");
////            }
//       }
//       System.out.println(ord.getNumCopias());
//       System.out.println(ord.getNumComparacoes());
//    }
        
        RubroNegra arvore = new RubroNegra();
        
        for (int i = 1; i < 16; i++) {
            System.out.println("Antes: ");
            arvore.imprime();
            arvore.inserir(i);
            System.out.println("Depois: ");
            arvore.imprime();
            System.out.println();
        }
        
        arvore.inserir(0);
        arvore.inserir(9);
        arvore.inserir(13);
        
        arvore.imprime();

    }
    
}
