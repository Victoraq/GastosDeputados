/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author victor
 */


public class THash {
    private int tam, m, count, tam_max;
    private Integer tabela[];
    private ArrayList[] tabela_encad_sep;
    private Integer indice_encad_c[];
    
    public THash(int tam) {
        this.tam = tam;
        this.count = 0;
        this.tam_max = tam * 2 + 3;
        this.encontra_primo();
        this.m = 7;
        // Problema a ser resolvido: Como usar a mesma lista para ambos métodos
        this.tabela = new Integer[this.m];
        this.tabela_encad_sep = new ArrayList[this.m];
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
    
    private int hash_multiplicacao(int k) {
        double AUREA = 0.61803399;
        
         return (int) Math.floor(m * (k*AUREA - Math.floor(k*AUREA)));
    }
    
    private int sondagem_linear(int k, int colisoes) {

        if (this.count >= this.m) {
            // Depois implementar throw de um erro
            System.out.println("Tabela Cheia");
            return -1;
        }
        
        int pos = this.hash_divisao(k + colisoes);

        while (pos < this.m && this.count < this.m) {
            if (this.tabela[pos] == null) {
                return pos;
            }
            
            colisoes++;
            this.count++;
            pos = this.hash_divisao(k+colisoes);
        }
        return -1;
    }
    
    private int sondagem_quadratica(int k, int colisoes) {
        
        if (this.count >= this.m) {
            // Depois implementar throw de um erro
            System.out.println("Tabela Cheia");
            return -1;
        }
        
        int pos = this.hash_divisao(k + colisoes*colisoes);

        while (pos < this.m && this.count < this.m) {
            if (this.tabela[pos] == null) {
                return pos;
            }
            
            colisoes++;
            this.count++;
            pos = this.hash_divisao(k+colisoes*colisoes);
        }
        return -1;
    }
    
    private int duplo_hash(int k, int colisoes) {
        if (this.count >= this.m) {
            // Depois implementar throw de um erro
            System.out.println("Tabela Cheia");
            return -1;
        }
        
        int hash1 = this.hash_divisao(k);
        int hash2 = this.hash_multiplicacao(k);
        int pos = this.hash_divisao(hash1 + colisoes * hash2);

        while (pos < this.m && this.count < this.m) {
            if (this.tabela[pos] == null) {
                return pos;
            }
            
            colisoes++;
            this.count++;
            pos = this.hash_divisao(hash1 + colisoes * hash2);
        }
        return -1;
    }
    
    private void encad_separado(int k) {
        // Posição em que sera adicionado o valor
        int pos = this.hash_divisao(k);
        
        // Se a posição está vazia criamos uma lista na mesma
        if (this.tabela_encad_sep[pos] == null) {
            ArrayList<Integer> list = new ArrayList<>();
            this.tabela_encad_sep[pos] = list;
        } 
        
        // Adicionando o valor na lista
        this.tabela_encad_sep[pos].add(k);
        
    }
    
    private int encad_coalescido(int k, int colisoes) {
        if (this.count >= this.m) {
            // Depois implementar throw de um erro
            System.out.println("Tabela Cheia");
            return -1;
        }

        int pos_inicial = this.hash_divisao(k);
        int pos = this.hash_divisao(k+colisoes);

        while (pos < this.m && this.count < this.m) {
            // Se encontrar uma posição vazia na tabela é salvo tal indice 
            // na posição anterior para servir de referencia para percorrer posteriormente
            if (this.tabela[pos] == null) {
                
                this.indice_encad_c[pos_inicial] = pos;
                
                return pos;
            }
            
            colisoes++;
            this.count++;
            // Determinando a proxima posição a ser checada com a função hash
            // Se tivesse porão seria na ultima posição vazia
            pos_inicial = pos;
            pos = this.hash_divisao(k+colisoes);
        }
        return -1;
    }
    
    public void inserir(Integer k) {
        int pos = this.hash_divisao(k);
        
        if (this.tabela[pos] == null) {
            this.tabela[pos] = k;
        } else {
            pos = this.encad_coalescido(k, 1);
            if (pos >= 0)
                this.tabela[pos] = k;
        }
//        this.encad_separado(k);
    }
    
    public void imprime() {
        for (int i = 0; i < this.m; i++)
            System.out.println(this.tabela[i]);
    }
    public void imprime_encad_pos() {
        for (int i = 0; i < this.m; i++)
            if (this.tabela_encad_sep[i] != null) {
                for (int j = 0; j < this.tabela_encad_sep[i].size(); j++) { 		      
                    System.out.print(this.tabela_encad_sep[i].get(j)+" -> "); 		
                }
                System.out.println();
            } else {
                System.out.println(this.tabela_encad_sep[i]);
            }
            
    }
    
}
