package GastoDeputados;

import Ordenacao.QuickSort;
import Ordenacao.InsertionSort;
import Ordenacao.MergeSort;
import Ordenacao.HeapSort;
import Ordenacao.ShellSort;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author Laura
 */
public class Cenario03 {
    
    Integer[] vetor;
    Deputado[] array;

    public Cenario03() { // Cria o vetor com os dados de deputados.
        array = new LeituraDados("C:\\Users\\Laura\\Documents\\deputies_dataset_tratado.csv", 10 /*500000*/).getDeputados();
    }
          
    // Preenche o vetor com o id de posições aleatórias do vetor de deputados.
    private void preencheVetor(int tam) {
        vetor = new Integer[tam];
        for(int i = 0; i < tam; i++) {
            int random = new Random().nextInt(array.length - 1);
            vetor[i] = array[random].getDeputy_id();
        }
    }
    
    
    public void compOrdenacao(double[] numCopias, double[] numComp, long[] tempoExec, int indice) {
        // Cada um dos vetores passará por um método de ordenação diferente.
        Integer[] vetor1 = vetor;
        Integer[] vetor2 = vetor;
        Integer[] vetor3 = vetor;
        Integer[] vetor4 = vetor;
        Integer[] vetor5 = vetor;
        
        // Métodos de ordenação a serem comparados.

        QuickSort comp1 = new QuickSort();
        InsertionSort comp2 = new InsertionSort();        
        MergeSort comp3 = new MergeSort();
        HeapSort comp4 = new HeapSort();
        ShellSort comp5 = new ShellSort();
        
        comp1.ordenar(vetor1);
        comp2.ordenar(vetor2);
        comp3.ordenar(vetor3);
        comp4.ordenar(vetor4);
        comp5.ordenar(vetor5);
        
        // Preenche-se os vetor numCopias, numComp e tempoExec com os
        // valores numCopias, numComparacoes e duracao, obtidos pelas diferentes ordenações.
       
        numCopias[indice] += comp1.getNumCopias();
        numComp[indice] += comp1.getNumComparacoes();
        tempoExec[indice] += comp1.getDuracao();
        indice++;       
        
        numCopias[indice] += comp2.getNumCopias();
        numComp[indice] += comp2.getNumComparacoes();
        tempoExec[indice] += comp2.getDuracao();
        indice++;
        
        numCopias[indice] += comp3.getNumCopias();
        numComp[indice] += comp3.getNumComparacoes();
        tempoExec[indice] += comp3.getDuracao();
        indice++;
        
        numCopias[indice] += comp4.getNumCopias();
        numComp[indice] += comp4.getNumComparacoes();
        tempoExec[indice] += comp4.getDuracao();
        indice++;
        
        numCopias[indice] += comp5.getNumCopias();
        numComp[indice] += comp5.getNumComparacoes();
        tempoExec[indice] += comp5.getDuracao();
        
    }
    
    
    public static void main(String[] args) throws IOException {
        File saida1_int = new File("C:\\Users\\Laura\\Documents\\saidaQuickSort.csv");
        File saida2_int = new File("C:\\Users\\Laura\\Documents\\saidaInsertionSort.csv");
        File saida3_int = new File("C:\\Users\\Laura\\Documents\\saidaMergeSort.csv");
        File saida4_int = new File("C:\\Users\\Laura\\Documents\\saidaHeapSort.csv");
        File saida5_int = new File("C:\\Users\\Laura\\Documents\\saidaShellSort.csv");
        FileWriter fw1_int, fw2_int, fw3_int, fw4_int, fw5_int;
        try {
            
            fw1_int = new FileWriter(saida1_int);
            fw2_int = new FileWriter(saida2_int);
            fw3_int = new FileWriter(saida3_int);
            fw4_int = new FileWriter(saida4_int);
            fw5_int = new FileWriter(saida5_int);
 
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        fw1_int.write("tam,duracao,num_comparacao,num_copia\n");
        fw1_int.flush();
        fw2_int.write("tam,duracao,num_comparacao,num_copia\n");
        fw2_int.flush();
        fw3_int.write("tam,duracao,num_comparacao,num_copia\n");
        fw3_int.flush();
        fw4_int.write("tam,duracao,num_comparacao,num_copia\n");
        fw4_int.flush();
        fw5_int.write("tam,duracao,num_comparacao,num_copia\n");
        fw5_int.flush();
        
        
        Cenario03 cenario = new Cenario03();
        double[] numCopias = new double[30];
        double[] numComp = new double[30];
        long[] tempoExec = new long[30];
        int indice = 0;
        
        int[] tamanhos = {1000, 5000, 10000, 50000, 100000, 500000};
        for(int i = 0; i < 6; i++) { // Cada i corresponde a um tamanho diferente de vetor.
            for(int j = 0; j < 5; j++) {// Cada j corresponde a um vetor diferente. Serão utilizados 5
                                        //   vetores gerados aleatoriamente para cada tamanho.
                cenario.preencheVetor(tamanhos[i]);
                cenario.compOrdenacao(numCopias, numComp, tempoExec, indice);
            }
            indice = indice + 5;
        }
        
        // Termina-se o cálculo das médias, dividindo pelo número de vetores que foram utilizados.
        for(int i = 0; i < 30; i++) {
            numCopias[i] = numCopias[i]/5.0;
            numComp[i] = numComp[i]/5.0;
            tempoExec[i] = (long) (tempoExec[i]/5.0);
        }
        
        indice = 0;
        
        // Imprime-se os dados obtidos por cada ordenação.
        System.out.println("Dados: ");
        for(int i = 0; i < 6; i++) {
            System.out.println("Vetor de tamanho " + tamanhos[i] + ": ");
            System.out.println("");
            for(int j = 0; j < 5; j++) {
                switch(j) {
                    case 0: System.out.println("QuickSort: ");
                    break;
                    case 1: System.out.println("InsertionSort: ");
                    break;
                    case 2: System.out.println("MergeSort: ");
                    break;
                    case 3: System.out.println("HeapSort: ");
                    break;
                    default: System.out.println("ShellSort: ");
                }
                
                System.out.println("Numero medio de copias: " + numCopias[indice]);
                System.out.println("Numero medio de comparacoes: " + numComp[indice]);
                System.out.println("Tempo medio de execucao em milissegundos: " + tempoExec[indice]);
                System.out.println("");
                indice++;
            }
             System.out.println("");
        }
        
        double[] numCompQuick = new double[6];
        double[] numCompInsertion = new double[6];
        double[] numCompMerge = new double[6];
        double[] numCompHeap = new double[6];
        double[] numCompShell = new double[6];
        
        double[] numCopiasQuick = new double[6];
        double[] numCopiasInsertion = new double[6];
        double[] numCopiasMerge = new double[6];
        double[] numCopiasHeap = new double[6];
        double[] numCopiasShell = new double[6];
        
        long[] tempoExecQuick = new long[6];
        long[] tempoExecInsertion = new long[6];
        long[] tempoExecMerge = new long[6];
        long[] tempoExecHeap = new long[6];
        long[] tempoExecShell = new long[6];
        
        for(int i = 0; i < 6; i++) {
            numCompQuick[i] = numComp[5*i];
            numCopiasQuick[i] = numCopias[5*i];
            tempoExecQuick[i] = tempoExec[5*i];
        }
        
        for(int i = 0; i < 6; i++) {
            numCompInsertion[i] = numComp[1 + 5*i];
            numCopiasInsertion[i] = numCopias[1 + 5*i];
            tempoExecInsertion[i] = tempoExec[1 + 5*i];
        }
        
        for(int i = 0; i < 6; i++) {
            numCompMerge[i] = numComp[2 + 5*i];
            numCopiasMerge[i] = numCopias[2 + 5*i];
            tempoExecMerge[i] = tempoExec[2 + 5*i];
        }
        
        for(int i = 0; i < 6; i++) {
            numCompHeap[i] = numComp[3 + 5*i];
            numCopiasHeap[i] = numCopias[3 + 5*i];
            tempoExecHeap[i] = tempoExec[3 + 5*i];
        }
        
        for(int i = 0; i < 6; i++) {
            numCompShell[i] = numComp[4 + 5*i];
            numCopiasShell[i] = numCopias[4 + 5*i];
            tempoExecShell[i] = tempoExec[4 + 5*i];
        }
        
        long[] vetDuracao;
        double[] vetComp;
        double[] vetCopias;
        
        for(int i = 0; i < 6; i++) {
            for(int aux = 0; aux < 5; aux++){
               
            switch(aux){
                case 0: vetDuracao = tempoExecQuick;
                        vetComp = numCompQuick;
                        vetCopias = numCopiasQuick;
                break;
                case 1: vetDuracao = tempoExecInsertion;
                        vetComp = numCompInsertion;
                        vetCopias = numCopiasInsertion;
                break; 
                case 2: vetDuracao = tempoExecMerge;
                        vetComp = numCompMerge;
                        vetCopias = numCopiasMerge;
                break;  
                case 3: vetDuracao = tempoExecHeap;
                        vetComp = numCompHeap;
                        vetCopias = numCopiasHeap;
                break; 
                default: vetDuracao = tempoExecShell;
                         vetComp = numCompShell;
                         vetCopias = numCopiasShell;
            }
            
               String result;
               try{
                  
                   result = Integer.toString(tamanhos[i])+ ',' +vetDuracao[i] + ',' + vetComp[i]+ ',' + vetCopias[i] + '\n';
               } catch(NullPointerException e) {
                    System.out.println(e.getMessage());
                    return;
               }    
                if(aux == 0){
                    fw1_int.write(result);
                     fw1_int.flush();
                }
                if(aux == 1){
                    fw2_int.write(result);
                     fw2_int.flush();
                }
                if(aux == 2){
                    fw3_int.write(result);
                     fw3_int.flush();
                }
                if(aux == 3){
                    fw4_int.write(result);
                     fw4_int.flush();
                }
                else{
                    fw5_int.write(result);                    
                     fw5_int.flush();
                }
           }
        }
        
    }
    
}

