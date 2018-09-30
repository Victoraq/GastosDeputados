package GastoDeputados;


import TabelaHash.DuploHash;
import TabelaHash.ECoalescido;
import TabelaHash.ESeparado;
import TabelaHash.SLinear;
import TabelaHash.SQuadratica;
import TabelaHash.THash;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author victor
 */
public class Cenario04 {
    
    public static void imprime_dados(long tempo_inicial, long memory_inicial, THash tabela, FileWriter fw) throws IOException {
        long tempo_final, memory_final;
        
        Runtime runtime = Runtime.getRuntime();
        
        tempo_final = System.currentTimeMillis();
        
        runtime.gc(); // Rodando o Garbage Collector
        memory_final = runtime.totalMemory() - runtime.freeMemory(); // Memoria utilizada em bytes
        
        System.out.println("Duração: "+(tempo_final - tempo_inicial));
        System.out.println("Numero de colisoes: " + tabela.getNumComparacoes());
        System.out.println("Memoria utilizada: " + (memory_final - memory_inicial));
        System.out.println("Fim N = "+tabela.getTam()+'\n');
        
        String result = Integer.toString(tabela.getTam()) + ',' + 
                Double.toString((tempo_final - tempo_inicial)) + ',' + 
                Double.toString(tabela.getNumComparacoes()) + ',' + 
                Double.toString(memory_final - memory_inicial) + '\n';
                
        fw.write(result);
        fw.flush();
        
    }
    
    public static void main(String[] args) throws IOException {
        int N[] = {1000, 5000, 10000, 50000, 100000, 500000}; //Array com a quantidade de valores a serem testados
        int tam = 2*N[N.length-1]; //Quantidade de valores a serem lidos
        
        // Lendo dados dos deputados
        LeituraDados reader = new LeituraDados("/home/victor/Documentos/ED2/Java/data/deputies_dataset_tratado.csv", tam);
        Deputado[] deputados = reader.getDeputados();
        
        // Criando as tabelas
        DuploHash tabela_DuploHash;
        ECoalescido tabela_ECoalescido;
        ESeparado tabela_ESeparado;
        SLinear tabela_SLinear;
        SQuadratica tabela_SQuadratica;
        
        // Arquivos de saida e seus respectivos writters
        FileWriter fw_SLinear, fw_SQuad, fw_DuploHash, fw_ESeparado, fw_ECoalescido;
        File saida_SLinear = new File("/home/victor/Documentos/ED2/Java/data/saida_SLinear.csv");
        File saida_SQuad = new File("/home/victor/Documentos/ED2/Java/data/saida_SQuad.csv");
        File saida_DuploHash = new File("/home/victor/Documentos/ED2/Java/data/saida_DuploHash.csv");
        File saida_ESeparado = new File("/home/victor/Documentos/ED2/Java/data/saida_ESeparado.csv");
        File saida_ECoalescido = new File("/home/victor/Documentos/ED2/Java/data/saida_ECoalescido.csv");
        
        Runtime runtime = Runtime.getRuntime();
        long memory_inicial, ini;
        
        Random rand = new Random(System.currentTimeMillis());   
        int pos;
        
        // Inicializando variaveis de escrita
        try {
            fw_SLinear = new FileWriter(saida_SLinear);
            fw_SQuad = new FileWriter(saida_SQuad);
            fw_DuploHash = new FileWriter(saida_DuploHash);
            fw_ESeparado = new FileWriter(saida_ESeparado);
            fw_ECoalescido = new FileWriter(saida_ECoalescido);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        String columns = "tam,duracao,num_comparacao,memoria\n";
        
        fw_SLinear.write(columns);
        fw_SQuad.write(columns);
        fw_DuploHash.write(columns);
        fw_ESeparado.write(columns);
        fw_ECoalescido.write(columns);
    
        for (int seed = 0; seed < 5; seed++) {
            
            System.out.println("Seed "+seed);

            
            // Analise Sondagem Linear
            System.out.println("Sondagem Linear: ");
                    
            for (int i = 0; i < N.length; i++) {
                ini = System.currentTimeMillis();   // Inicio da execução

                runtime.gc(); // Rodando o Garbage Collector
                memory_inicial = runtime.totalMemory() - runtime.freeMemory();  // Memoria utilizada em bytes

                tabela_SLinear = new SLinear(N[i]);

                for (int j = 0; j < N[i]; j++) {
                    pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                    tabela_SLinear.inserir(deputados[pos]);
                }

                imprime_dados(ini,memory_inicial,tabela_SLinear, fw_SLinear);
            }
        
        
            // Analise sondagem quadratica 
            System.out.println("Sondagem Quadratica: ");
                    
            for (int i = 0; i < N.length; i++) {
                ini = System.currentTimeMillis();   // Inicio da execução
                
                runtime.gc(); // Rodando o Garbage Collector
                memory_inicial = runtime.totalMemory() - runtime.freeMemory();  // Memoria utilizada em bytes

                tabela_SQuadratica = new SQuadratica(N[i]);

                for (int j = 0; j < N[i]; j++) {
                    pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                    tabela_SQuadratica.inserir(deputados[pos]);
                }

                imprime_dados(ini,memory_inicial,tabela_SQuadratica, fw_SQuad);

            }
       
        
            // Analise duplo hash
            System.out.println("Duplo Hash: ");
                    
            for (int i = 0; i < N.length; i++) {
                ini = System.currentTimeMillis();   // Inicio da execução
                
                runtime.gc(); // Rodando o Garbage Collector
                memory_inicial = runtime.totalMemory() - runtime.freeMemory();  // Memoria utilizada em bytes

                tabela_DuploHash = new DuploHash(N[i]);

                for (int j = 0; j < N[i]; j++) {
                    pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                    tabela_DuploHash.inserir(deputados[pos]);
                }

                imprime_dados(ini,memory_inicial,tabela_DuploHash, fw_DuploHash);

            }
        
            // Analise encadeamento separado
            System.out.println("Encadeamento Separado: ");
                    
            for (int i = 0; i < N.length; i++) {
                ini = System.currentTimeMillis();   // Inicio da execução
                
                runtime.gc(); // Rodando o Garbage Collector
                memory_inicial = runtime.totalMemory() - runtime.freeMemory();  // Memoria utilizada em bytes

                tabela_ESeparado = new ESeparado(N[i]);

                for (int j = 0; j < N[i]; j++) {
                    pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                    tabela_ESeparado.inserir(deputados[pos]);
                }

                imprime_dados(ini,memory_inicial,tabela_ESeparado, fw_ESeparado);

            }
        
            // Analise encadeamento coaslescido
            System.out.println("Encadeamento Coaslescido: ");
                    
            for (int i = 0; i < N.length; i++) {
                ini = System.currentTimeMillis();   // Inicio da execução
                
                runtime.gc(); // Rodando o Garbage Collector
                memory_inicial = runtime.totalMemory() - runtime.freeMemory();  // Memoria utilizada em bytes

                tabela_ECoalescido = new ECoalescido(N[i]);

                for (int j = 0; j < N[i]; j++) {
                    pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                    tabela_ECoalescido.inserir(deputados[pos]);
                }

                imprime_dados(ini,memory_inicial,tabela_ECoalescido, fw_ECoalescido);
            }
        }             
    }
}
