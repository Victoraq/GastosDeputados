/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import TabelaHash.ESeparado;

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
        ESeparado tabela_gastos, tabela_partidos;
        tabela_gastos = new ESeparado(tam_leitura);
        tabela_partidos = new ESeparado(tam_leitura);
        
        for (int i = 0; i < tam_leitura; i++) {
            tabela_gastos.inserir(dep[i], "receipt_value");
            tabela_partidos.inserir(dep[i], "political_party");
        }
        
    }
}
