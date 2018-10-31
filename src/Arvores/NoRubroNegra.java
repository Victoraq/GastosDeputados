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
public class NoRubroNegra {
    protected char cor;
    protected int valor;
    protected NoRubroNegra fesq, fdir, pai;

    public NoRubroNegra(int valor, NoRubroNegra fesq, NoRubroNegra fdir, NoRubroNegra pai) {
        this.valor = valor;
        this.fesq = fesq;
        this.fdir = fdir;
        this.pai = pai;
        this.cor = 'v';
    }

    public NoRubroNegra(int valor) {
        this.valor = valor;
        this.fesq = null;
        this.fdir = null;
        this.pai = null;
        this.cor = 'v';
    }
    
    public int getValor() {
        return valor;
    }

    public char getCor() {
        return cor;
    }

    public void setCor(char cor) {
        this.cor = cor;
    }

    public NoRubroNegra getFesq() {
        return fesq;
    }

    public void setFesq(NoRubroNegra fesq) {
        this.fesq = fesq;
    }

    public NoRubroNegra getFdir() {
        return fdir;
    }

    public void setFdir(NoRubroNegra fdir) {
        this.fdir = fdir;
    }

    public NoRubroNegra getPai() {
        return pai;
    }

    public void setPai(NoRubroNegra pai) {
        this.pai = pai;
    }
    
    
}
