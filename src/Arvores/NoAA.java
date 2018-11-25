/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arvores;

/**
 *
 * @author Laura
 */
public class NoAA {
    protected int valor, nivel;
    protected NoAA fEsq, fDir, pai;

    public NoAA() {
    }

    public NoAA(int valor, NoAA fEsq, NoAA fDir, NoAA pai) {
        this.valor = valor;
        this.fEsq = fEsq;
        this.fDir = fDir;
        this.pai = pai;
        this.nivel = 1 + calculaNivel(fEsq);
    }

    public NoAA(int valor) {
        this.valor = valor;
        this.fEsq = null;
        this.fDir = null;
        this.pai = null;
        this.nivel = 1;
    }
    
    public int calculaNivel(NoAA no) {
        if(no == null) {
            return 0;
        }
        else {
            if(no.getfEsq() == null && no.getfDir() == null) {
                return 1;
            }
            else {
                return 1 + calculaNivel(no.fEsq);
            }
        }
    }
    
    public NoAA getfEsq() {
        return fEsq;
    }

    public void setfEsq(NoAA fEsq) {
        this.fEsq = fEsq;
    }

    public NoAA getfDir() {
        return fDir;
    }

    public void setfDir(NoAA fDir) {
        this.fDir = fDir;
    }

    public NoAA getPai() {
        return pai;
    }

    public void setPai(NoAA pai) {
        this.pai = pai;
    }

    public int getValor() {
        return valor;
    }

}
