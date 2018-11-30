/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arvores;

import GastoDeputados.Deputado;
import GastoDeputados.LeituraDados;
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
public class AnaliseNaoOrdenado {
    
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
        Splay splay;
        ArvoreAA aa;
        B b_tree;
        
        File entrada = new File("/home/victor/Documentos/ED2/Java/data/entradaArvores.txt");
        FileReader fr;
        
        FileWriter ins_avl, ins_rb, ins_b, ins_aa, ins_splay;
        FileWriter bus_avl, bus_rb, bus_b, bus_aa, bus_splay;
        FileWriter rem_avl, rem_rb, rem_b, rem_aa, rem_splay;


        BufferedReader br;
        
        File insercao_avl = new File("/home/victor/Documentos/ED2/Java/data/insercao_avl.csv");
        File insercao_rb = new File("/home/victor/Documentos/ED2/Java/data/insercao_rb.csv");
        File insercao_b = new File("/home/victor/Documentos/ED2/Java/data/insercao_b.csv");
        File insercao_splay = new File("/home/victor/Documentos/ED2/Java/data/insercao_splay.csv");
        File insercao_aa = new File("/home/victor/Documentos/ED2/Java/data/insercao_aa.csv");
        
        File busca_avl = new File("/home/victor/Documentos/ED2/Java/data/busca_avl.csv");
        File busca_rb = new File("/home/victor/Documentos/ED2/Java/data/busca_rb.csv");
        File busca_b = new File("/home/victor/Documentos/ED2/Java/data/busca_b.csv");
        File busca_splay = new File("/home/victor/Documentos/ED2/Java/data/busca_splay.csv");
        File busca_aa = new File("/home/victor/Documentos/ED2/Java/data/busca_aa.csv");
        
        File remocao_avl = new File("/home/victor/Documentos/ED2/Java/data/remocao_avl.csv");
        File remocao_rb = new File("/home/victor/Documentos/ED2/Java/data/remocao_rb.csv");
        File remocao_b = new File("/home/victor/Documentos/ED2/Java/data/remocao_b.csv");
        File remocao_splay = new File("/home/victor/Documentos/ED2/Java/data/remocao_splay.csv");
        File remocao_aa = new File("/home/victor/Documentos/ED2/Java/data/remocao_aa.csv");
        
        Deputado[] dep;
        
        
        // Variaveis para o numero de testes e tamanho de cada um
        int num_testes, tam = 0;
        
        // Lendo arquivo de entrada e inicializando os valores de teste
        
        try {
            fr = new FileReader(entrada);
            br = new BufferedReader(fr);
            
            ins_avl = new FileWriter(insercao_avl);
            ins_rb = new FileWriter(insercao_rb);
            ins_splay = new FileWriter(insercao_splay);
            ins_aa = new FileWriter(insercao_aa);
            ins_b = new FileWriter(insercao_b);
            
            bus_avl = new FileWriter(busca_avl);
            bus_rb = new FileWriter(busca_rb);
            bus_splay = new FileWriter(busca_splay);
            bus_aa = new FileWriter(busca_aa);
            bus_b = new FileWriter(busca_b);
            
            rem_avl = new FileWriter(remocao_avl);
            rem_rb = new FileWriter(remocao_rb);
            rem_splay = new FileWriter(remocao_splay);
            rem_aa = new FileWriter(remocao_aa);
            rem_b = new FileWriter(remocao_b);
            
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
        int tam_leitura = test[test.length - 1].length * 2;
//        int tam_leitura = 1000000;
        
        System.out.println("Tam Leitura: "+tam_leitura);
        
        dep = new LeituraDados("/home/victor/Documentos/ED2/Java/data/deputies_gasto_id.csv", 
                            tam_leitura).getDeputados();
        
        String result; // string para armazenar resultados
        
        for (int seed = 0; seed < 5; seed++) {
            System.out.println("Seed "+seed);
            // Usando como limite de valores aleatório o tamanho do arquivo de deputados lido
            preenche_rand(test, 15);

            for (int i = 0; i < num_testes; i++) {
                
                avl = new AVL();
                rb = new RubroNegra();
                splay = new Splay();
                aa = new ArvoreAA();
                b_tree = new B(5);
                
                // INSERÇÃO
                
                System.out.println("\nTam: "+test[i].length);
                
                System.out.println("\nInsercao");
                
                System.out.println("AVL");
                
                avl.inserir(test[i]);
                
                System.out.println("Duração: "+avl.getDuracaoInsercao());

                System.out.println("Duração: "+avl.getDuracaoInsercao());
                System.out.println("Num. Copias: "+avl.getNumCopias());

                result = Integer.toString(test[i].length) + ',' + Double.toString((double)avl.getDuracaoInsercao())
                        + ',' + Double.toString(avl.getNumComparacoes()) + ',' + Double.toString(avl.getNumCopias()) + '\n';

                ins_avl.write(result);
                ins_avl.flush();

                
                System.out.println("Rubro Negra");
                
                rb.inserir(test[i]);
                
                System.out.println("Duração: "+rb.getDuracaoInsercao());

                System.out.println("Duração: "+rb.getDuracaoInsercao());
                System.out.println("Num. Copias: "+rb.getNumCopias());

                result = Integer.toString(test[i].length) + ',' + Double.toString((double)rb.getDuracaoInsercao())
                        + ',' + Double.toString(rb.getNumComparacoes()) + ',' + Double.toString(rb.getNumCopias()) + '\n';
                
                ins_rb.write(result);
                ins_rb.flush();
                
                System.out.println("Splay");
                
                splay.inserir(test[i]);
                
                System.out.println("Duração: "+splay.getDuracaoInsercao());

                System.out.println("Duração: "+splay.getDuracaoInsercao());
                System.out.println("Num. Copias: "+splay.getNumCopias());

                result = Integer.toString(test[i].length) + ',' + Double.toString((double)splay.getDuracaoInsercao())
                        + ',' + Double.toString(splay.getNumComparacoes()) + ',' + Double.toString(splay.getNumCopias()) + '\n';

                ins_splay.write(result);
                ins_splay.flush();
                
                System.out.println("AA");
                
                aa.inserir(test[i]);
                
                System.out.println("Duração: "+aa.getDuracaoInsercao());

                System.out.println("Duração: "+aa.getDuracaoInsercao());
                System.out.println("Num. Copias: "+aa.getNumCopias());

                result = Integer.toString(test[i].length) + ',' + Double.toString((double)aa.getDuracaoInsercao())
                        + ',' + Double.toString(aa.getNumComparacoes()) + ',' + Double.toString(aa.getNumCopias()) + '\n';

                ins_aa.write(result);
                ins_aa.flush();
                
                System.out.println("B");
                
                b_tree.inserir(test[i]);
                
                System.out.println("Duração: "+b_tree.getDuracaoInsercao());

                System.out.println("Duração: "+b_tree.getDuracaoInsercao());
                System.out.println("Num. Copias: "+b_tree.getNumCopias());

                result = Integer.toString(test[i].length) + ',' + Double.toString((double)b_tree.getDuracaoInsercao())
                        + ',' + Double.toString(b_tree.getNumComparacoes()) + ',' + Double.toString(b_tree.getNumCopias()) + '\n';

                ins_b.write(result);
                ins_b.flush();


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
                
                result = Integer.toString(test[i].length) + ',' + Double.toString((double)avl.getDuracaoBusca())
                        + ',' + Double.toString(avl.getNumComparacoes()) + ',' + Double.toString(avl.getNumCopias()) + '\n';

                bus_avl.write(result);
                bus_avl.flush();

                
                System.out.println("Rubro Negra");

                rb.busca(busca);
                
                System.out.println("Duração: "+rb.getDuracaoBusca());
                
                result = Integer.toString(test[i].length) + ',' + Double.toString((double)rb.getDuracaoBusca())
                        + ',' + Double.toString(rb.getNumComparacoes()) + ',' + Double.toString(rb.getNumCopias()) + '\n';

                bus_rb.write(result);
                bus_rb.flush();
                
                
                System.out.println("Splay");

                splay.busca(busca);
                
                System.out.println("Duração: "+splay.getDuracaoBusca());
                
                result = Integer.toString(test[i].length) + ',' + Double.toString((double)splay.getDuracaoBusca())
                        + ',' + Double.toString(splay.getNumComparacoes()) + ',' + Double.toString(splay.getNumCopias()) + '\n';

                bus_splay.write(result);
                bus_splay.flush();
                
                
                System.out.println("AA");

                aa.busca(busca);
                
                System.out.println("Duração: "+aa.getDuracaoBusca());
                
                result = Integer.toString(test[i].length) + ',' + Double.toString((double)aa.getDuracaoBusca())
                        + ',' + Double.toString(aa.getNumComparacoes()) + ',' + Double.toString(aa.getNumCopias()) + '\n';

                bus_aa.write(result);
                bus_aa.flush();
                
                
                System.out.println("B");

                b_tree.busca(busca);
                
                System.out.println("Duração: "+b_tree.getDuracaoBusca());
                
                result = Integer.toString(test[i].length) + ',' + Double.toString((double)b_tree.getDuracaoBusca())
                        + ',' + Double.toString(b_tree.getNumComparacoes()) + ',' + Double.toString(b_tree.getNumCopias()) + '\n';

                bus_b.write(result);
                bus_b.flush();
                
                // REMOÇÃO
                
                System.out.println("AVL");

                avl.remover(busca);
                
                System.out.println("Duração: "+avl.getDuracaoBusca());
                
                result = Integer.toString(test[i].length) + ',' + Double.toString((double)avl.getDuracaoBusca())
                        + ',' + Double.toString(avl.getNumComparacoes()) + ',' + Double.toString(avl.getNumCopias()) + '\n';

                rem_avl.write(result);
                rem_avl.flush();

                
                System.out.println("Rubro Negra");

                rb.remover(busca);
                
                System.out.println("Duração: "+rb.getDuracaoBusca());
                
                result = Integer.toString(test[i].length) + ',' + Double.toString((double)rb.getDuracaoBusca())
                        + ',' + Double.toString(rb.getNumComparacoes()) + ',' + Double.toString(rb.getNumCopias()) + '\n';

                rem_rb.write(result);
                rem_rb.flush();
                
                
                System.out.println("Splay");

                splay.remover(busca);
                
                System.out.println("Duração: "+splay.getDuracaoBusca());
                
                result = Integer.toString(test[i].length) + ',' + Double.toString((double)splay.getDuracaoBusca())
                        + ',' + Double.toString(splay.getNumComparacoes()) + ',' + Double.toString(splay.getNumCopias()) + '\n';

                rem_splay.write(result);
                rem_splay.flush();
                
                
                System.out.println("AA");

                aa.remover(busca);
                
                System.out.println("Duração: "+aa.getDuracaoBusca());
                
                result = Integer.toString(test[i].length) + ',' + Double.toString((double)aa.getDuracaoBusca())
                        + ',' + Double.toString(aa.getNumComparacoes()) + ',' + Double.toString(aa.getNumCopias()) + '\n';

                rem_aa.write(result);
                rem_aa.flush();
                
                
                System.out.println("B");

                b_tree.remover(busca);
                
                System.out.println("Duração: "+b_tree.getDuracaoBusca());
                
                result = Integer.toString(test[i].length) + ',' + Double.toString((double)b_tree.getDuracaoBusca())
                        + ',' + Double.toString(b_tree.getNumComparacoes()) + ',' + Double.toString(b_tree.getNumCopias()) + '\n';

                rem_b.write(result);
                rem_b.flush();
                
                
            }
        }
        
    }
    
}
