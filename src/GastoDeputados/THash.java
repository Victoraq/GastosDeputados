/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import java.util.Arrays;

/**
 *
 * @author victor
 */


public class THash {
    private int tam, m, count, tam_max;
    private Integer tabela[];
    
    public THash(int tam) {
        this.tam = tam;
        this.count = 0;
        this.tam_max = tam * 2 + 3;
        this.encontra_primo();
        this.tabela = new Integer[this.m];

    }
    
    private void encontra_primo() {
        // In general Sieve of Sundaram, produces 
        // primes smaller than (2*x + 2) for a number
        // given number x. Since we want primes 
        // smaller than n, we reduce n to half
        int nNew = (this.tam_max - 2) / 2;

        // This array is used to separate numbers of the 
        // form i+j+2ij from others where 1 <= i <= j
        boolean marked[] = new boolean[nNew + 1];

        // Initalize all elements as not marked
        Arrays.fill(marked, false);

        // Main logic of Sundaram. Mark all numbers of the
        // form i + j + 2ij as true where 1 <= i <= j
        int last = nNew;
        loop:
        for (int i = 1; i <= nNew; i++)
            for (int j = i; (i + j + 2 * i * j) <= nNew; j++){
                try { 
                    last = i + j + 2 * i * j;
                    marked[i + j + 2 * i * j] = true;
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                    break loop;
                }
            }
        for (int k=this.tam/2; k < nNew+1; k++) {
            if (marked[k] == false && k * 2 + 1 > this.tam) {
                this.m = k * 2 + 1;
                break;
            }
        }
        System.out.println("Primo: "+this.m);
    }
    
    private int hash_divisao(int k) {
        int pos = k % this.m;
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
