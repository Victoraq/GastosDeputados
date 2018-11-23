/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arvores;

import java.util.Objects;

/**
 *
 * @author victor
 */
public class TrieTernaria {
    private NoTrieTernaria raiz;

    public TrieTernaria() {
        this.raiz = null;
    }    
    
    public void insere(char[] str) {
        NoTrieTernaria no = this.raiz;
        
        for (int i = 0; i < str.length; i++) {
            NoTrieTernaria noaux = new NoTrieTernaria(str[i]);
            
            if (this.raiz == null) {
                this.raiz = noaux;
                no = this.raiz;
                continue;
            }

            // se os nós em sequencia forem vazios
            if ((no.getCentro() == null) && (!Objects.equals(no.getChar(), noaux.getChar()))) { 
                no.setCentro(noaux);
            } else if (no.getChar() != str[i]){ // se o nó for diferente iremos ver se irá para esquerda ou direita
                
                if (str[i] > no.getChar()) {
                    if (no.getDir() != null) no = no.getDir();
                    else {
                        no.setDir(noaux);
                        no = no.getDir();
                        continue;
                    }
                } else {
                    if (no.getEsq() != null) no = no.getEsq();
                    else {
                        no.setEsq(noaux);
                        no = no.getEsq();
                        continue;
                    }
                }
                
                i--;
                continue;
            }
                        
            if (no.getCentro() != null) no = no.getCentro();

            if (i == str.length - 1) no.setFinal(true);
            
        }
        
    }
    
    public boolean busca(char[] str) {
        NoTrieTernaria no = this.raiz;
        
        for (int i = 0; i < str.length; i++) {
            
            if (no == null) return false;
            else if (no.getChar() != str[i]){ // se o nó for diferente iremos ver se irá para esquerda ou direita
                if (str[i] > no.getChar()) no = no.getDir();
                else no = no.getEsq();
                
                i--;
                continue;
            }
            
            if (i == str.length - 1) return no.getFinal();
            
            no = no.getCentro();
            
        }
        return false;
    }
    
}
