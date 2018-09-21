package GastoDeputados;


import TabelaHash.DuploHash;
import TabelaHash.ECoalescido;
import TabelaHash.ESeparado;
import TabelaHash.SLinear;
import TabelaHash.SQuadratica;
import java.util.Random;

/**
 *
 * @author victor
 */
public class Cenario04 {
    public static void main1(String[] args) {
        int N[] = {1000, 5000, 10000, 50000, 100000, 500000}; //Array com a quantidade de valores a serem testados
        int tam = 2*N[N.length-1]; //Quantidade de valores a serem lidos
        
        // Lendo dados dos deputados
        LeituraDados deputados = new LeituraDados("../../deputies_dataset_tratado.csv", tam);
        
        // Criando as tabelas
        DuploHash tabela_DuploHash;
        ECoalescido tabela_ECoalescido;
        ESeparado tabela_ESeparado;
        SLinear tabela_SLinear;
        SQuadratica tabela_SQuadratica;
        
        Random rand = new Random(System.currentTimeMillis());   
        int pos;
        
        // Analise sondagem linear
        System.out.println("Sondagem Linear: ");
        for (int i = 0; i < N.length; i++) {
            long ini = System.currentTimeMillis();
            tabela_SLinear = new SLinear(N[i]);
            for (int j = 0; j < N[i]; j++) {
                pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                tabela_SLinear.inserir(deputados.deputados[pos]);
            }
            System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            System.out.println("Numero de colisoes: "+tabela_SLinear.get_colisoes());
            System.out.println("Fim N = "+N[i]+'\n');
        }
        
        // Analise sondagem quadratica 
        System.out.println("Sondagem Quadratica: ");
        for (int i = 0; i < N.length; i++) {
            long ini = System.currentTimeMillis();
            tabela_SQuadratica = new SQuadratica(N[i]);
            for (int j = 0; j < N[i]; j++) {
                pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                tabela_SQuadratica.inserir(deputados.deputados[pos]);
            }
            System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            System.out.println("Numero de colisoes: "+tabela_SQuadratica.get_colisoes());
            System.out.println("Fim N = "+N[i]+'\n');
        }
        
        // Analise duplo hash
        System.out.println("Duplo Hash: ");
        for (int i = 0; i < N.length; i++) {
            long ini = System.currentTimeMillis();
            tabela_DuploHash = new DuploHash(N[i]);
            for (int j = 0; j < N[i]; j++) {
                pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                tabela_DuploHash.inserir(deputados.deputados[pos]);
            }
            System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            System.out.println("Numero de colisoes: "+tabela_DuploHash.get_colisoes());
            System.out.println("Fim N = "+N[i]+'\n');
        }
        
        // Analise encadeamento separado
        System.out.println("Encadeamento Separado: ");
        for (int i = 0; i < N.length; i++) {
            long ini = System.currentTimeMillis();
            tabela_ESeparado = new ESeparado(N[i]);
            for (int j = 0; j < N[i]; j++) {
                pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                tabela_ESeparado.inserir(deputados.deputados[pos]);
            }
            System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            System.out.println("Numero de colisoes: "+tabela_ESeparado.get_colisoes());
            System.out.println("Fim N = "+N[i]+'\n');
        }
        
        // Analise encadeamento coaslescido
        System.out.println("Encadeamento Coaslescido: ");
        for (int i = 0; i < N.length; i++) {
            long ini = System.currentTimeMillis();
            tabela_ECoalescido = new ECoalescido(N[i]);
            for (int j = 0; j < N[i]; j++) {
                pos = Math.abs(rand.nextInt() % (tam-1)); // Modulo para evitar valores maiores que o indice
                tabela_ECoalescido.inserir(deputados.deputados[pos]);
            }
            System.out.println("Duração: "+(System.currentTimeMillis() - ini));
            System.out.println("Numero de colisoes: "+tabela_ECoalescido.get_colisoes());
            System.out.println("Fim N = "+N[i]+'\n');
        }
                
    }
}
