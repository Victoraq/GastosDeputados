package Arvores;

import GastoDeputados.Deputado;
import GastoDeputados.LeituraDados;
import java.util.Scanner;

/**
 *
 * @author victor
 */
public class BuscaGasto {
    
    public static void main(String[] args) {
        
        Deputado[] dep;
        TrieTernaria trie = new TrieTernaria();
        Scanner sc = new Scanner(System.in);
        String str = "s";
        
        int tam_leitura = 10000;
        
        double gasto = -1;
        
        // Lendo dados de deputados
        System.out.println("Tam Leitura: "+tam_leitura);
        
        dep = new LeituraDados("/home/victor/Documentos/ED2/Java/data/deputies_gasto_id.csv", 
                            tam_leitura).getDeputados();
        
        // Inserindo gastos na arvore
        for (int i = 0; i < tam_leitura; i++) {
            trie.insere(dep[i].getReceipt_descript().toLowerCase(), dep[i].getReceipt_value());
        }
        
//        trie.imprime();
        
        // Loop de busca
        while(str.equals("s")) {

            while(gasto < 0) {
                System.out.println("Insira o gasto: ");
                str = sc.nextLine();

                gasto = trie.buscaGasto(str.toLowerCase());
            }

            System.out.println("Gasto: "+gasto);

            System.out.println("Fazer outra pesquisa? [S/N]");
            str = sc.nextLine().toLowerCase();
            gasto = -1;
        }
        
    }
        
}
