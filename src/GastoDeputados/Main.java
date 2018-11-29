
package GastoDeputados;

import Arvores.AVL;
import Arvores.ArvoreAA;
import Arvores.B;
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
        
        Splay arvore = new Splay();
        Random rand = new Random(System.currentTimeMillis());
        Integer a[];
//        Integer ex[] = {5, 1, 2, 3, 10, 7, 8, 9, 30, 13, 18, 19, 40, 46, 49, 80, 89};
//        
//        for (int i = 0; i < ex.length; i++)
//            arvore.inserir(ex[i]);
//        
//        for (int i = 0; i < ex.length; i++){
//            Object test = arvore.busca(ex[i]);
//            if (test == null) System.out.println("Não encontrou: "+ex[i]);
//        }

        
        System.out.println("Teste sem repeticao");
        for (int k = 0; k < 200; k++) {
            arvore = new Splay();
            a = new Integer[50];

            // Preenchendo com valores aleatorios não repetidos
            for (int i = 0; i < a.length; i++) {
                int num = Math.abs(rand.nextInt() % 150)+1;
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
            arvore = new Splay();
            a = new Integer[50];

            // Preenchendo com valores aleatorios não repetidos
            for (int i = 0; i < a.length; i++) {
                int num = Math.abs(rand.nextInt() % 100)+1;
                a[i] = num;
            }
            
            for (int i = 0; i < a.length; i++) {
                arvore.inserir(a[i]);
            }
            
            for (int i = 0; i < a.length; i++){
                Object test = arvore.busca(a[i]);
                if (test == null) System.out.println("Não encontrou: "+a[i]);
            }
//            System.out.println("Iniciou busca");
//            arvore.busca(a);
        }
        System.out.println("Acabou");
        
        System.out.println("Teste com remoção");
        for (int k = 0; k < 200; k++) {
            arvore = new Splay();
            a = new Integer[200];
            Integer[] r = new Integer[200];

            // Preenchendo com valores aleatorios não repetidos
            for (int i = 0; i < a.length; i++) {
                int num = Math.abs(rand.nextInt() % 50)+1;
                a[i] = num;
            }
            for (int i = 0; i < a.length; i++) {
                int num = Math.abs(rand.nextInt() % 100)+1;
                r[i] = num;
            }
            
            for (int i = 0; i < a.length; i++) {
                arvore.inserir(a[i]);
            }
            
            arvore.remover(r);
            
            for (int i = 0; i < a.length; i++){;
                Object test = arvore.busca(a[i]);
                if (test == null) System.out.println("Não encontrou: "+a[i]);
            }
            System.out.println("Iniciou busca");
            arvore.busca(a);
        }
        System.out.println("Acabou");
        
    }
    
}
