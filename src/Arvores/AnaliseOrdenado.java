/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arvores;

import GastoDeputados.Deputado;
import GastoDeputados.LeituraDados;
import Ordenacao.MergeSort;
import Ordenacao.QuickSort;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author victor
 */
public class AnaliseOrdenado {
    
    public static void preenche_rand(Integer matrix[][], int limite) {
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < matrix.length; i++) {
            // Preenchendo com valores aleatorios
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Math.abs(rand.nextInt() % limite);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        AVL avl;
        RubroNegra rb;
        B b_tree;
        
        File entrada = new File("/home/victor/Documentos/ED2/Java/data/entradaArvores.txt");
        FileReader fr;
        FileWriter fw_avl, fw_rb, fw_b;
        BufferedReader br;
        File saida_avl = new File("/home/victor/Documentos/ED2/Java/data/saida_avl.csv");
        File saida_rb = new File("/home/victor/Documentos/ED2/Java/data/saida_rb.csv");
        File saida_b = new File("/home/victor/Documentos/ED2/Java/data/saida_b.csv");
        Deputado[] dep;
        
        
        // Variaveis para o numero de testes e tamanho de cada um
        int num_testes, tam = 0;
        
        // Lendo arquivo de entrada e inicializando os valores de teste
        
        try {
            fr = new FileReader(entrada);
            br = new BufferedReader(fr);
            
            fw_avl = new FileWriter(saida_avl);
            fw_rb = new FileWriter(saida_rb);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        String line;
        
        // Lendo primeira linha da entrada que representa a quantidade de testes
        line = br.readLine();
        num_testes = Integer.parseInt(line);
        
        // Declarando os diversos tamanhos de vetor para testes
        Integer[][] test = new Integer[num_testes][];
        
        // Construindo uma matrix com todos os dados a serem utilizados
        for (int i = 0; i < num_testes; i++) {
            // Lendo as linhas seguintes da entrada que indicam o tamanho de cada teste
            line = br.readLine();
            tam = Integer.parseInt(line);
            
            test[i] = new Integer[tam];
        }
        
        // Lendo deputados com duas vezes mais dados que o maior numero de leituras requisitadas
//        int tam_leitura = test[test.length - 1].length * 2;
        int tam_leitura = 1000000;
        
        System.out.println("Tam Leitura: "+tam_leitura);
        
        dep = new LeituraDados("/home/victor/Documentos/ED2/Java/data/deputies_gasto_id.csv", 
                            tam_leitura).getDeputados();
        
        String result; // string para armazenar resultados
        MergeSort sort = new MergeSort();
        
        //INSERÇÃO COM REPETIÇÃO E ORDENAÇÃO
        
        for (int seed = 0; seed < 5; seed++) {
            System.out.println("Seed "+seed);
            // Usando como limite de valores aleatório o tamanho do arquivo de deputados lido
            preenche_rand(test, 15);
            
            for (int i = 0; i < num_testes; i++) {
                int k = (int) (test[i].length * 0.7);
                int inicio = 0;
                // repetindo valores do inicio no fim para ter 30% de repetições
                for (; k < test[i].length; k++, inicio++) {
                    test[i][k] = test[i][inicio];
                }
                sort.ordenar(test[i]); // Ordenando os valores inseridos
            }
            
            for (int i = 0; i < num_testes; i++) {
                
                avl = new AVL();
                rb = new RubroNegra();
                
                // INSERÇÃO
                
                System.out.println("\nTam: "+test[i].length);
                
                System.out.println("\nInsercao");
                
                System.out.println("AVL");
                
                avl.inserir(test[i]);
                
                System.out.println("Duração: "+avl.getDuracaoInsercao());
//                avl.imprime();

//                System.out.println("Duração: "+avl.getDuracao());
//                System.out.println("Num. Copias: "+avl.getNumCopias());

//                result = Integer.toString(test[i].length) + ',' + Double.toString((double)avl.getDuracao())
//                        + ',' + Double.toString(avl.getNumComparacoes()) + ',' + Double.toString(avl.getNumCopias()) + '\n';

//                fw_avl.write(result);
//                fw_avl.flush();

                
                System.out.println("Rubro Negra");
                
                rb.inserir(test[i]);
                
                System.out.println("Duração: "+rb.getDuracaoInsercao());
//                rb.imprime();

//                System.out.println("Duração: "+rb.getDuracao());
//                System.out.println("Num. Copias: "+rb.getNumCopias());

//                result = Integer.toString(test[i].length) + ',' + Double.toString((double)rb.getDuracao())
//                        + ',' + Double.toString(rb.getNumComparacoes()) + ',' + Double.toString(rb.getNumCopias()) + '\n';
                
//                fw_rb.write(result);
//                fw_rb.flush();


                // BUSCA
                
                System.out.println("\nBusca");
                
                // Preenchendo valores a serem buscados
                int length = test[i].length;
                Integer[] busca = new Integer[length];
                Random rand = new Random(System.currentTimeMillis());
                
                // Preenchendo 1/3 de valores já inseridos nas arvores e 2/3 aleatorios
                int pos;
                for (int k = 0; k < length; k++) {
                    pos = rand.nextInt();
                    
                    if (k < num_testes/3) { 
                        pos = Math.abs(pos % length-1);
                        busca[k] = test[i][pos];
                    } else {
                        busca[k] = Math.abs(pos % tam_leitura-1);
                    }
                }

                System.out.println("AVL");

                avl.busca(busca);
                
                System.out.println("Duração: "+avl.getDuracaoBusca());

                
                System.out.println("Rubro Negra");

                rb.busca(busca);
                
                System.out.println("Duração: "+rb.getDuracaoBusca());
                
                //REMOÇÃO
                
            }
            
        }
    }
    
}
