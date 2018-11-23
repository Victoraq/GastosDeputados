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
public class NoB {
    protected int ordem;
    protected int [] chaves;
    protected NoB [] filhos;
    protected boolean folha;

    public NoB(int ordem) {
        this.ordem = ordem;
        chaves = new int[ordem]; // numero de chaves == ordem - 1. +1 para overflow
        filhos = new NoB[ordem+1]; // numero de filhos == ordem. +1 para overflow
        folha = true;
    }
    
}
