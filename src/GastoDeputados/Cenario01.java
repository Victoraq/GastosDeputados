package GastoDeputados;

import Ordenacao.QuickSort;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Cenario01 {
    
    public static void preenche_rand(Integer matrix[][], int limite) {
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < matrix.length; i++) {
            // Preenchendo com valores aleatorios
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Math.abs(rand.nextInt() % limite);
            }
        }
    }
    
    public static void imprime(Integer[] array) {
        for (Integer array1 : array) {
            System.out.println(array1);
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        File entrada = new File("/home/victor/Documentos/ED2/Java/data/entrada.txt");
        FileReader fr;
        FileWriter fw_int, fw_dep;
        BufferedReader br;
        File saida_int = new File("/home/victor/Documentos/ED2/Java/data/saida_int.csv");
        File saida_dep= new File("/home/victor/Documentos/ED2/Java/data/saida_deputados.csv");
        Deputado[] dep;
        
        
        // Variaveis para o numero de testes e tamanho de cada um
        int num_testes, tam = 0;
        
        // Lendo arquivo de entrada e inicializando os valores de teste
        
        try {
            fr = new FileReader(entrada);
            br = new BufferedReader(fr);
            
            fw_int = new FileWriter(saida_int);
            fw_dep = new FileWriter(saida_dep);
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
        
        System.out.println("Tam Leitura: "+tam_leitura);
        
        dep = new LeituraDados("/home/victor/Documentos/ED2/Java/data/deputies_dataset_tratado.csv", 
                            tam_leitura).getDeputados();
        
        // Analise para o array de int
        
        System.out.println("Ordenando Array de Inteiros");
        
        fw_int.write("tam,duracao,num_comparacao,num_copia\n");
        fw_int.flush();
        
        for (int seed = 0; seed < 5; seed++) {
            System.out.println("Seed "+seed);
            // Usando como limite de valores aleatório o tamanho do arquivo de deputados lido
            preenche_rand(test, tam_leitura-1);
            QuickSort ord = new QuickSort();

            
            for (int i = 0; i < num_testes; i++) {
                ord.ordenar(test[i]);
                
                System.out.println("Tam: "+test[i].length);
                System.out.println("Duração: "+ord.getDuracao());
                System.out.println("Num. Copias: "+ord.getNumCopias());

                String result = Integer.toString(test[i].length) + ',' + Double.toString((double)ord.getDuracao())
                        + ',' + Double.toString(ord.getNumComparacoes()) + ',' + Double.toString(ord.getNumCopias()) + '\n';
                
                fw_int.write(result);
                fw_int.flush();

            }
        }
        
        // Analise para o array de deputados
        
        System.out.println("Ordenando Array de Deputados");
        
        fw_dep.write("tam,duracao,num_comparacao,num_copia \n");
        fw_dep.flush();
        
        for (int seed = 0; seed < 5; seed++) {
            System.out.println("Seed "+seed);
            // Usando como limite de valores aleatório o tamanho do arquivo de deputados lido
            preenche_rand(test, tam_leitura-1);
            QuickSort ord = new QuickSort();
            
            for (int i = 0; i < num_testes; i++) {
                
                // Gerando vetor de deputados com os indices aleatorios anteriormente gerados
                Deputado[] auxDep = new Deputado[test[i].length];
                for (int j = 0; j < test[i].length; j++) {
                    auxDep[j] = dep[test[i][j]];
                }
                
                ord.ordenar(auxDep);
                
                System.out.println("Tam: "+test[i].length);
                System.out.println("Duração: "+ord.getDuracao());
                System.out.println("Num. Comparacoes: "+ord.getNumComparacoes());
                System.out.println("Num. Copias: "+ord.getNumCopias());

                String result = Integer.toString(test[i].length) + ',' + Double.toString((double)ord.getDuracao())
                        + ',' + Double.toString(ord.getNumComparacoes()) + ',' + Double.toString(ord.getNumCopias()) + '\n';
                
                fw_dep.write(result);
                fw_dep.flush();

            }
        }
    }
}
