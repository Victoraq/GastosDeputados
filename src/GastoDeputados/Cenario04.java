/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import java.util.Random;

/**
 *
 * @author victor
 */
public class Cenario04 {
    public static void main(String[] args) {
        int N[] = {1000, 5000, 10000, 50000, 100000, 500000}; //Array com a quantidade de valores a serem testados
        int tam = 2*N[N.length-1]; //Quantidade de valores a serem lidos
        
        // Lendo dados dos deputados
        LeituraDados deputados = new LeituraDados("/home/victor/Documentos/ED2/Java/data/deputies_dataset_tratado.csv", tam);
        THash tabela;
        
        Random rand = new Random(System.currentTimeMillis());   
        int pos;
        
        // Analise sondagem linear
        System.out.println("Sondagem Linear: ");
        for (int i = 0; i < N.length; i++) {
            long ini = System.currentTimeMillis();
            tabela = new THash(N[i]);
            for (int j = 0; j < N[i]; j++) {
                pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                tabela.inserir_sondagem_linear(deputados.deputados[pos]);
            }
            System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            System.out.println("Numero de colisoes: "+tabela.get_colisoes());
            System.out.println("Fim N = "+N[i]+'\n');
        }
        
        // Analise sondagem quadratica 
        System.out.println("Sondagem Quadratica: ");
        for (int i = 0; i < N.length; i++) {
            long ini = System.currentTimeMillis();
            tabela = new THash(N[i]);
            for (int j = 0; j < N[i]; j++) {
                pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                tabela.inserir_sondagem_quad(deputados.deputados[pos]);
            }
            System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            System.out.println("Numero de colisoes: "+tabela.get_colisoes());
            System.out.println("Fim N = "+N[i]+'\n');
        }
        
        // Analise duplo hash
        System.out.println("Duplo Hash: ");
        for (int i = 0; i < N.length; i++) {
            long ini = System.currentTimeMillis();
            tabela = new THash(N[i]);
            for (int j = 0; j < N[i]; j++) {
                pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                tabela.inserir_duplo_hash(deputados.deputados[pos]);
            }
            System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            System.out.println("Numero de colisoes: "+tabela.get_colisoes());
            System.out.println("Fim N = "+N[i]+'\n');
        }
        
        // Analise encadeamento separado
        System.out.println("Encadeamento Separado: ");
        for (int i = 0; i < N.length; i++) {
            long ini = System.currentTimeMillis();
            tabela = new THash(N[i]);
            for (int j = 0; j < N[i]; j++) {
                pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                tabela.inserir_encad_separado(deputados.deputados[pos]);
            }
            System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            System.out.println("Numero de colisoes: "+tabela.get_colisoes());
            System.out.println("Fim N = "+N[i]+'\n');
        }
        
        // Analise encadeamento coaslescido
        System.out.println("Encadeamento Coaslescido: ");
        for (int i = 0; i < N.length; i++) {
            long ini = System.currentTimeMillis();
            tabela = new THash(N[i]);
            for (int j = 0; j < N[i]; j++) {
                pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                tabela.inserir_encad_coalescido(deputados.deputados[pos]);
            }
            System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            System.out.println("Numero de colisoes: "+tabela.get_colisoes());
            System.out.println("Fim N = "+N[i]+'\n');
        }
                
    }
}
