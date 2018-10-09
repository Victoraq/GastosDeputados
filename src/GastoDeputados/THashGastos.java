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
        

        // Se a posição estiver com o valor null cria-se uma lista e insere o valor na lista
        if (this.tabelaESeparado[pos] == null) {
            ArrayList<TagGastos> list = new ArrayList<>();
            this.tabelaESeparado[pos] = list;
            this.tabelaESeparado[pos].add(gasto); // Adicionando o valor na lista
        }else{
            // Procurando se o item já foi inserido e soma o valor de gastos
            for (int i = 0; i < this.tabelaESeparado[pos].size(); i++) {
                TagGastos item = (TagGastos) this.tabelaESeparado[pos].get(i);

                if (item.getTag().equals(gasto.getTag())) {
                    item.setGastos(item.getGastos() + gasto.getGastos());
                    return;
                }
            }
            // Adicionando o valor na lista se não estiver já inserido
            this.tabelaESeparado[pos].add(gasto);
        }
    }
    
    /**
     * Método para retornar o valor a partir de uma tag
     * @param tag
     * @return TagGastos procurada
     */
    public TagGastos get(String tag) {
        int pos = super.hashDivisao(tag); // Funcao hash de divisao
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
    
    /**
     * Método que retorna a Tabela em formato de vetor 
     * @return tags - array de todas as tags
     */
    public Object[] getTabela() {
        ArrayList<TagGastos> tags = new ArrayList<>();
        
        for (int i = 0; i < this.m; i++){
            if (this.tabelaESeparado[i] != null) {
                for (int j = 0; j < this.tabelaESeparado[i].size(); j++) {
                    if (this.tabelaESeparado[i].get(j) != null){
                        tags.add((TagGastos) this.tabelaESeparado[i].get(j));
                    }
                }
            }
        }
        
        // Passando a lista para array
        TagGastos[] array = tags.toArray(new TagGastos[tags.size()]);
        
        return array;
    }
    
}
