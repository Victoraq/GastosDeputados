/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TabelaHash;

import GastoDeputados.Deputado;
import java.util.ArrayList;
import java.util.Arrays;

public class THash {
    protected int tam, m, numComparacoes, numPosPreenchidas;
    protected Object tabela[];
    
    public THash(int tam) {
        this.tam = tam;
        this.numPosPreenchidas = 0;
        this.numColisoes = 0;
        this.numComparacoes = 0;
        this.m = tam * 2 + 3;
        this.tabela = new Deputado[this.m];
    }
    
    public int getnumComparacoes() {
        return this.numComparacoes;
    }
    
    protected void encontraPrimo() {
        
        /* Algoritmo modificado de :
            https://www.geeksforgeeks.org/sieve-sundaram-print-primes-smaller-n/
        */
        
        // In general Sieve of Sundaram, produces 
        // primes smaller than (2*x + 2) for a number
        // given number x. Since we want primes 
        // smaller than n, we reduce n to half
        int nNew = (this.m - 2) / 2;

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
    
    protected int hashDivisao(int k) {
        int pos = k % this.m;
        return pos;
    }
    
    protected int hashMultiplicacao(int k) {
        double AUREA = 0.61803399;
        
         return (int) Math.floor(m * (k*AUREA - Math.floor(k*AUREA)));
    }
    
    public void imprime() {
        for (int i = 0; i < this.m; i++)
            System.out.println(this.tabela[i]);
    }    
}