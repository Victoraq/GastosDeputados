/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import Arvores.AVL;
import Arvores.NoAVL;
import Arvores.RubroNegra;
import Arvores.Splay;
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
        
        AVL arvore = new AVL();
        Random rand = new Random(System.currentTimeMillis());
        
        System.out.println("Teste sem repeticao");
        for (int k = 0; k < 200; k++) {
            Integer a[] = new Integer[100];

            // Preenchendo com valores aleatorios não repetidos
            for (int i = 0; i < a.length; i++) {
                int num = Math.abs(rand.nextInt() % 250);
                boolean test = true;
                for (int j = 0; j < i; j++){
                    if (num == a[j]) {
                        i--;
                        test = false;
                    }
                }
                if (test) a[i] = num;
            }
            
            arvore.inserir(a);
            
            for (int i = 0; i < a.length; i++){
                Object test = arvore.busca(a[i]);
                if (test == null) System.out.println("Não encontrou: "+a[i]);
            }
            
        }
        System.out.println("Acabou");
        
        System.out.println("Teste com repeticao");
        for (int k = 0; k < 200; k++) {
            Integer a[] = new Integer[100];

            // Preenchendo com valores aleatorios não repetidos
            for (int i = 0; i < a.length; i++) {
                int num = Math.abs(rand.nextInt() % 50);
                a[i] = num;
            }
            
            arvore.inserir(a);
            
            for (int i = 0; i < a.length; i++){
                Object test = arvore.busca(a[i]);
                if (test == null) System.out.println("Não encontrou: "+a[i]);
            }
            
        }
        System.out.println("Acabou");
        
    }
    
}
