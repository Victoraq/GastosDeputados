/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import Ordenacao.QuickSortMed;

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
        
        // Ordenando valores dos gastos
        QuickSortMed ord = new QuickSortMed();
        
        TagGastos[] deps = (TagGastos[]) tabela_dep.getTabela();
        TagGastos[] partidos = (TagGastos[]) tabela_partidos.getTabela();
        System.out.println(deps[0]);
        for (int i = 0; i < deps.length; i++) {
            System.out.println(deps[i].getGastos());
        }
        
        ord.ordenar(deps);
        ord.ordenar(partidos);
        
        System.out.println("Partidos que menos gastaram:");
        for (int i = 0; i < 10; i++) {
            String part = partidos[i].getTag();
            float gasto = partidos[i].getGastos();
            System.out.println("Partido: "+part+"       Gasto: "+gasto);
        }
        
        System.out.println("Deputados que menos gastaram:");
        for (int i = 0; i < 10; i++) {
            String part = deps[i].getTag();
            float gasto = deps[i].getGastos();
            System.out.println("Deputado: "+part+"      Gasto: "+gasto);
        }
        
        System.out.println("Partidos que mais gastaram:");
        int tam = partidos.length-1;
        for (int i = tam-10; i < tam; i++) {
            String part = partidos[i].getTag();
            float gasto = partidos[i].getGastos();
            System.out.println("Partido: "+part+"       Gasto: "+gasto);
        }
        
        System.out.println("Deputados que mais gastaram:");
        tam = deps.length-1;
        for (int i = tam-10; i < tam; i++) {
            String part = deps[i].getTag();
            float gasto = deps[i].getGastos();
            System.out.println("Deputado: "+part+"      Gasto: "+gasto);
        }
        
    }
}
