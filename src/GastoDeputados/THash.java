/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

/**
 *
 * @author victor
 */


public class THash {
    private int tam, m, count;
    private Integer tabela[];
    
    public THash(int tam) {
        this.tam = tam;
        tabela = new Integer[tam];
        this.count = 0;
    }
    
    // Pendente
    private void encontra_primo(int valor) {
        
    }
    
    private int hash_divisao(int k) {
        int pos = k % m;
        return pos;
    }
    
    private int sondagem_linear(int k, int colisoes) {
        
        if (this.count >= this.m) {
            // Depois implementar throw de um erro
            System.out.println("Tabela Cheia");
            return -1;
        }
        
        int pos = this.hash_divisao(k + colisoes);
        
        while (pos < this.m && this.count < this.m) {
            if (this.tabela[pos] != null) {
                return pos;
            }
            
            this.count++;
            pos = this.hash_divisao(pos);
 
        }
        return -1;
    }
    
    private void sondagem_quadratica(int k) {
        
        if (this.count == this.tam) {
            // Depois implementar throw de um erro
            System.out.println("Tabela Cheia");
            return;
        }
        
        int pos = this.hash_divisao(k);
        
        // Verificando se a posição está vazia
        if (this.tabela[pos] == null) {
            this.tabela[pos] = k;
        } else {
            for (int i = pos; i < this.m; i++) {
                if (this.tabela[i] != null) {
                    this.tabela[i] = k;
                    break;
                }
            }
        }
        
        this.count++;
    }
    
    public void inserir(int k) {
        int pos = this.hash_divisao(k);
        
        if (this.tabela[pos] == null) {
            this.tabela[pos] = k;
        } else {
            pos = this.sondagem_linear(k, 1);
            if (pos > 0)
                this.tabela[pos] = k;
        }
        
    }
    
//    
//    public void get_divisao(int k) {
//        int pos = k % m;
//        if (this.tabela[pos] != 0) {
//            return pos;
//        } else {
//            return 0;
//        }
//    }
    
}
