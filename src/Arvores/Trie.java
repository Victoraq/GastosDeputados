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
public class Trie {
    private NoTrie raiz;
    private final char[] alfabeto;


    public Trie() {
        raiz = new NoTrie();
        int i = 0;
        alfabeto = new char[26];
        for (char c = 'a'; c <= 'z'; c++, i++)
            alfabeto[i] = c;
    }
    
    public void insere(char[] str) {
        NoTrie no = this.raiz;
        
        for (int i = 0; i < str.length; i++) {
            int pos = 0;
            for (; pos < this.alfabeto.length; pos++) // encontrando a posição pelo alfabeto
                if (alfabeto[pos] == str[i]) break;
            
            no.setChar(str[i], pos); // insere o caracter na posição encontrada
            
            // Se for o ultimo caracter insiro a tag de caracter final
            if (i == str.length - 1) { 
                no.setFinal(pos);
                break;
            }
            
            no = no.getProx(pos);
            
        }
    }
    
    public boolean busca(char[] str) {
        NoTrie no = this.raiz;
        int pos;
        boolean encontrou;
        
        for (int i = 0; i < str.length; i++) {
            
            for (pos = 0; pos < this.alfabeto.length; pos++) // encontrando a posição pelo alfabeto
                if (alfabeto[pos] == str[i]) break;
                        
            encontrou = false;
            
            // verifica se a posição está preenchida com o caracter buscado
            if (no.getChar(pos) != null) 
                encontrou = true;
            
            if (encontrou) {
                // se for a ultima posição verificar se ela termina uma string
                if (i == str.length - 1) 
                    return no.getFinal(pos);
              
                no = no.getProx(pos);
            } else
                return false;
            
        }
        return false;
    }
    
}
