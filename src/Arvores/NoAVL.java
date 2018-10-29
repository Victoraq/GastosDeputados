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
public class NoAVL {
    protected int valor, fatorb;
    protected NoAVL fesq, fdir, pai;

    public NoAVL(int valor, NoAVL fesq, NoAVL fdir, NoAVL pai) {
        this.valor = valor;
        this.fesq = fesq;
        this.fdir = fdir;
        this.pai = pai;
    }

    public NoAVL(int valor) {
        this.valor = valor;
        this.fesq = null;
        this.fdir = null;
        this.pai = null;
    }

    public int getValor() {
        return valor;
    }

    public NoAVL getFesq() {
        return fesq;
    }

    public void setFesq(NoAVL fesq) {
        this.fesq = fesq;
    }

    public NoAVL getFdir() {
        return fdir;
    }

    public void setFdir(NoAVL fdir) {
        this.fdir = fdir;
    }

    public NoAVL getPai() {
        return pai;
    }

    public void setPai(NoAVL pai) {
        this.pai = pai;
    }

    
    
}
