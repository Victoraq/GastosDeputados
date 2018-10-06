/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import TabelaHash.ESeparado;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class THashGastos extends ESeparado {
    
    public THashGastos(int tam) {
        super(tam);
    }
    
    public void inserir(String tag, float valor) {
        if (tag == null) {
                System.out.println("Valor nulo");
                return;
        }
        
        
        int pos = super.hashDivisao(tag); // Funcao hash de divisao
        TagGastos gasto = new TagGastos(tag,valor); // Estrutura para associar tag e valor
        
        //if ("Otavio Leite".equals(tag)) System.out.println("Pos: "+ pos);

        // Se a posição estiver com o valor null cria-se uma lista e insere o valor na lista
        if (this.tabelaESeparado[pos] == null) {
            //if ("Otavio Leite".equals(tag)) System.out.println("Primeira vez");
            ArrayList<TagGastos> list = new ArrayList<>();
            this.tabelaESeparado[pos] = list;
            this.tabelaESeparado[pos].add(gasto); // Adicionando o valor na lista
        }else{
            // Procurando se o item já foi inserido e soma o valor de gastos
            for (int i = 0; i < this.tabelaESeparado[pos].size(); i++) {
                TagGastos item = (TagGastos) this.tabelaESeparado[pos].get(i);
                //if ("Otavio Leite".equals(tag)) System.out.println(item.getTag().equals(gasto.getTag()));

                if (item.getTag().equals(gasto.getTag())) {
                    //if ("Otavio Leite".equals(tag)) System.out.println("Achou aqui: "+i + " Antes: "+ item.getGastos());
                    item.setGastos(item.getGastos() + gasto.getGastos());
                    TagGastos it = (TagGastos) this.tabelaESeparado[pos].get(i);
                    //if ("Otavio Leite".equals(tag)) System.out.println("Depois: "+it.getGastos());
                    return;
                }
            }
            //if ("Otavio Leite".equals(tag)) System.out.println("Não achou");
            // Adicionando o valor na lista se não estiver já inserido
            this.tabelaESeparado[pos].add(gasto);
        }
    }
    
    public TagGastos get(String tag) {
        int pos = super.hashDivisao(tag); // Funcao hash de divisao
        //System.out.println("Pos: "+pos+" Tag: "+tag);
        // Se a posição estiver com o valor null o item não está na tabela
        if (this.tabelaESeparado[pos] == null) {
            return null;
        } else {
            // Procurando se o item já foi inserido e o retorna
            for (int i = 0; i < this.tabelaESeparado[pos].size(); i++) {
                TagGastos item = (TagGastos) this.tabelaESeparado[pos].get(i);
                if (item.getTag().equals(tag)) {
                    return item;
                }
            }
        }
        // Se não encontra nenhum valor retorna 
        return null;
    }
    
}
