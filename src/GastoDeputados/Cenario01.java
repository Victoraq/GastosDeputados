package GastoDeputados;

import Ordenacao.Ordenacao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class Cenario01 {
    
    public static void preenche_rand(Integer matrix[][]) {
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < matrix.length; i++) {
            // Preenchendo com valores aleatorios
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = rand.nextInt();
            }
        }
    }
    
    public static void imprime(Integer[] array) {
        for (Integer array1 : array) {
            System.out.println(array1);
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        Ordenacao ord = new Ordenacao();
        File entrada = new File("/home/victor/Documentos/ED2/Java/data/entrada.txt");
        FileReader fr;
        BufferedReader br;
        
        // Variaveis para o numero de testes e tamanho de cada um
        int num_testes, tam = 0;
        
        // Lendo arquivo de entrada e inicializando os valores de teste
        
        try {
            fr = new FileReader(entrada);
            br = new BufferedReader(fr);
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
        
//        preenche_rand(test);
        
        // Testes de ordenação
        
//        System.out.println("Antes:");
//        imprime(test[3]);
//        ord.bubbleSort(test[3]);
//        System.out.println("Depois:");
//        imprime(test[3]);
        
        for (int seed = 0; seed < 5; seed++) {
            System.out.println("Seed "+seed);
            preenche_rand(test);
            for (int i = 0; i < num_testes; i++) {
                long ini = System.currentTimeMillis();
                //ord.QuickSort(test[i]);
                System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            }
        }
    }
}
