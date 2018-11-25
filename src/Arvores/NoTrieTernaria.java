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
public class NoTrieTernaria {
    private Character c;
    private NoTrieTernaria esq, centro, dir;
    private Boolean finalt;
    private double gasto = 0; // gasto para inserção de gasto de deputados

    public NoTrieTernaria(Character c) {
        this.c = c;
        this.centro = null;
        this.esq = null;
        this.dir = null;
        this.finalt = false;
    }

    public Character getChar() {
        return c;
    }

    public void setChar(Character c) {
        this.c = c;
    }

    public NoTrieTernaria getEsq() {
        return esq;
    }

    public void setEsq(NoTrieTernaria esq) {
        this.esq = esq;
    }

    public NoTrieTernaria getCentro() {
        return centro;
    }

    public void setCentro(NoTrieTernaria centro) {
        this.centro = centro;
    }

    public NoTrieTernaria getDir() {
        return dir;
    }

    public void setDir(NoTrieTernaria dir) {
        this.dir = dir;
    }

    public Boolean getFinal() {
        return finalt;
    }

    public void setFinal(Boolean finalt) {
        this.finalt = finalt;
    }
    
    public double getGasto() {
        return this.gasto;
    }    
    
    public void setGasto(double gasto) {
        this.gasto += gasto;
    }
    
}
