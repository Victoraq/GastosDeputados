/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import Ordenacao.HeapSort;
import Ordenacao.InsertionSort;
import Ordenacao.MergeSort;
import Ordenacao.QuickSortInsercao;
import Ordenacao.QuickSortMed;
import Ordenacao.ShellSort;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author victor
 */
public class Cenario03 {
    
    public static void saida(int tamanho, long duracao, double comparacao, double copias, FileWriter fw) throws IOException {
        String result = Integer.toString(tamanho) + ',' + Double.toString((double)duracao) + ',' + 
                Double.toString(comparacao) + ',' + Double.toString(copias) + '\n';
                
        fw.write(result);
        fw.flush();
    }
    
    public static Integer[] preencheVetor(Deputado[] dep, int tam) {
        Integer[] vetor = new Integer[tam];
        for(int i = 0; i < tam; i++) {
            int random = new Random().nextInt(dep.length - 1);
            vetor[i] = dep[random].getDeputy_id();
        }
        return vetor;
    }
    
    public static void main(String[] args) throws IOException {
        
        Deputado[] dep = new LeituraDados("/home/victor/Documentos/ED2/Java/data/deputies_dataset_tratado.csv",
                2500000).getDeputados(); 
        
        int[] tamanhos = {1000, 5000, 10000, 50000, 100000, 500000};
                
//        QuickSortMed quick = new QuickSortMed();
//        InsertionSort insert = new InsertionSort();        
//        MergeSort merge = new MergeSort();
//        HeapSort heap = new HeapSort();
//        ShellSort shell = new ShellSort();
        
        File saida1_int = new File("/home/victor/Documentos/ED2/Java/data/saidaQuickSortMed.csv");
        File saida2_int = new File("/home/victor/Documentos/ED2/Java/data/saidaInsertionSort.csv");
        File saida3_int = new File("/home/victor/Documentos/ED2/Java/data/saidaMergeSort.csv");
        File saida4_int = new File("/home/victor/Documentos/ED2/Java/data/saidaHeapSort.csv");
        File saida5_int = new File("/home/victor/Documentos/ED2/Java/data/saidaShellSort.csv");
        FileWriter fw_quick, fw_insert, fw_merge, fw_heap, fw_shell;
        try {
            
            fw_quick = new FileWriter(saida1_int);
            fw_insert = new FileWriter(saida2_int);
            fw_merge = new FileWriter(saida3_int);
            fw_heap = new FileWriter(saida4_int);
            fw_shell = new FileWriter(saida5_int);
 
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        fw_quick.write("tam,duracao,num_comparacao,num_copia\n");
        fw_quick.flush();
        fw_insert.write("tam,duracao,num_comparacao,num_copia\n");
        fw_insert.flush();
        fw_merge.write("tam,duracao,num_comparacao,num_copia\n");
        fw_merge.flush();
        fw_heap.write("tam,duracao,num_comparacao,num_copia\n");
        fw_heap.flush();
        fw_shell.write("tam,duracao,num_comparacao,num_copia\n");
        fw_shell.flush();
        
        for (int seed = 0; seed < 5; seed++) {
            System.out.println("Seed: "+seed);
            QuickSortMed quick = new QuickSortMed();
            InsertionSort insert = new InsertionSort();        
            MergeSort merge = new MergeSort();
            HeapSort heap = new HeapSort();
            ShellSort shell = new ShellSort();
            
            
            for (int i = 0; i < tamanhos.length; i++) {
                
                
                Integer[] vetor = preencheVetor(dep,tamanhos[i]);
                System.out.println("ttam; "+tamanhos[i]);
                System.out.println("quick");
                quick.ordenar(vetor);

                System.out.println("insert");
                vetor = preencheVetor(dep,tamanhos[i]);

                insert.ordenar(vetor);
                System.out.println("merge");
                vetor = preencheVetor(dep,tamanhos[i]);

                merge.ordenar(vetor);
                System.out.println("heap");
                vetor = preencheVetor(dep,tamanhos[i]);

                heap.ordenar(vetor);
                System.out.println("shell");
                vetor = preencheVetor(dep,tamanhos[i]);

                shell.ordenar(vetor);
                
                saida(tamanhos[i],quick.getDuracao(),quick.getNumComparacoes(),
                        quick.getNumCopias(),fw_quick);
                saida(tamanhos[i],insert.getDuracao(),insert.getNumComparacoes(),
                        insert.getNumCopias(),fw_insert);
                saida(tamanhos[i],merge.getDuracao(),merge.getNumComparacoes(),
                        merge.getNumCopias(),fw_merge);
                saida(tamanhos[i],heap.getDuracao(),heap.getNumComparacoes(),
                        heap.getNumCopias(),fw_heap);
                saida(tamanhos[i],shell.getDuracao(),shell.getNumComparacoes(),
                        shell.getNumCopias(),fw_shell);
                
            }
            
        }
        
        
        
    }
}
