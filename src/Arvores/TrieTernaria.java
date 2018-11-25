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
    
    public void insere(String string, double valor) {
        NoTrieTernaria no = this.raiz;
        
        char[] str = string.toCharArray();
        
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

            if (i == str.length - 1) {
                no.setFinal(true);
                no.setGasto(valor);
            }
            
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
    
    public double buscaGasto(String string) {
        NoTrieTernaria no = this.raiz;
        NoTrieTernaria ant = null;
        
        char[] str = string.toCharArray();
        
        int i;
        for (i = 0; i < str.length; i++) {
            
            if (no == null) {
                // Se a string foi digitada errado voltamos um caracter e procuramos a nova string
                string = string.substring(0, i);
                double gasto = buscaGasto(string);
                
                return gasto;
            }
            else if (no.getChar() != str[i]){ // se o nó for diferente iremos ver se irá para esquerda ou direita
                if (str[i] > no.getChar()) no = no.getDir();
                else no = no.getEsq();
                
                i--;
                continue;
            }
            
            ant = no;
            no = no.getCentro();
            
        }
        
        if (no == null) no = ant; // caso chegou ao fim da string

        if (no.getFinal()) // Se a string foi encontrada já retorna o gasto
            return no.getGasto();
        else { // Senão imprime sugestões
            System.out.println("Gasto não encontrado. Sugestões: ");
            print(no,string);
            return -1;
        }
        
    }
    
    // imprime todas as strings em sequencia
    private void print(NoTrieTernaria no, String str) {
        NoTrieTernaria origem = no;
        
        while (no != null) {
            
            // imprime as strings alternativas
            if (no.getEsq() != null) print(no.getEsq(), str);
            if (no.getDir() != null) print(no.getDir(), str);
            
            // Vai acumulando a string a ser imprimida
            str = str.concat(Character.toString(no.getChar()));
            
            // imprime string final
            if (no.getFinal())
                System.out.println(str);
            
            
            no = no.getCentro();
        }
        
    }
    
    public void imprime() {
        print(this.raiz,"");
    }
    
}
