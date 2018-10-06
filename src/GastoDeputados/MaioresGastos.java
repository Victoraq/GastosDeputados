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
public class MaioresGastos {
    public static void main(String[] args) {
        int tam_leitura = 2500000;
        Deputado[] dep;
    
        dep = new LeituraDados("/home/victor/Documentos/ED2/Java/data/deputies_dataset_tratado.csv", 
                                tam_leitura).getDeputados();
        
        /* Pelas análises obtidas do cenário 4 foi concluído que o encadeamento
           separado detém de um melhor desempenho em relação a duração e número 
           de comparações, assim será usado para essa análise.
        */
        THashGastos  tabela_dep, tabela_partidos;
        tabela_dep = new THashGastos(tam_leitura);
        tabela_partidos = new THashGastos(tam_leitura);

        for (int i = 0; i < tam_leitura; i++) {         
            if (i % 100000 == 0) System.out.println(i);
            tabela_dep.inserir(dep[i].getName(), dep[i].getReceipt_value());
            tabela_partidos.inserir(dep[i].getPolitical_party(), dep[i].getReceipt_value());
        }
        
        // Testes
        Random rand = new Random();
        
        for (int i = 0; i < 20; i++) {
            int pos = Math.abs(rand.nextInt() % (tam_leitura -1));
            String part = (String) tabela_partidos.get(dep[pos].getPolitical_party()).getTag();
            float gasto = tabela_partidos.get(dep[pos].getPolitical_party()).getGastos();
            System.out.println("Partido: "+part+" Gasto: "+gasto);
        }
        for (int i = 0; i < 50; i++) {
            int pos = Math.abs(rand.nextInt() % (tam_leitura -1));
            String part = (String) tabela_dep.get(dep[pos].getName()).getTag();
            float gasto = tabela_dep.get(dep[pos].getName()).getGastos();
            System.out.println("Deputado: "+part+" Gasto: "+gasto);
        }
        
    }
}
