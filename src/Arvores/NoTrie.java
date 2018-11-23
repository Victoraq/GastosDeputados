/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arvores;

/**
 *
 * @author victor
 */
public class NoTrie {
    private Character[] caracter;
    private Boolean[] tfinal;
    private NoTrie[] prox;

    public NoTrie() {
        this.caracter = new Character[26];
        this.tfinal = new Boolean[26];
        
        for (int i = 0; i < this.tfinal.length; i++) // Preenchendo o vetor com false
            this.tfinal[i] = false;
        
        this.prox = new NoTrie[26];
    }
    
    public void setChar(char c, int pos) {
        this.caracter[pos] = c;
    }
    
    public Character getChar(int pos) {
        return this.caracter[pos];
    }
    
    public Boolean getFinal(int pos) {
        return this.tfinal[pos];
    }
    
    public void setFinal(int pos) {
        this.tfinal[pos] = true;
    }
    
    public NoTrie getProx(int pos) {
        // Se está vazio inicializa com novo no
        if (this.prox[pos] == null) this.prox[pos] = new NoTrie();
        return this.prox[pos];
    }
    
}
