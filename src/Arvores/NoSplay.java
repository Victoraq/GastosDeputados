/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arvores;

/**
 *
 * @author gabic
 */
public class NoSplay {
    protected int valor;
    protected NoSplay fesq,fdir,pai;

    public NoSplay(int valor, NoSplay fesq, NoSplay fdir, NoSplay pai) {
        this.valor = valor;
        this.fesq = fesq;
        this.fdir = fdir;
        this.pai = pai;
    }
    public NoSplay(int valor){
        this.valor=valor;
        this.fesq=null;
        this.fdir=null;
        this.pai=null;
    }

    public int getValor() {
        return valor;
    }

    public NoSplay getFesq() {
        return fesq;
    }

    public NoSplay getFdir() {
        return fdir;
    }

    public NoSplay getPai() {
        return pai;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setFesq(NoSplay fesq) {
        this.fesq = fesq;
    }

    public void setFdir(NoSplay fdir) {
        this.fdir = fdir;
    }

    public void setPai(NoSplay pai) {
        this.pai = pai;
    }
        
}
