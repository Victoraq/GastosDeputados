/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenacao;


/**
 *
 * @author victor
 */
public class Ordenacao {
    private double numComparacoes;
    private double numCopias;
    private long duracao;
    
    public Ordenacao() {
        this.numComparacoes = 0;
        this.numCopias = 0;
    }
    
    public double getNumComparacoes(){
        return this.numComparacoes;
    }
    
    public double getNumCopias(){
        return this.numCopias;
    }

    protected void setDuracao(long duracao) {
        this.duracao = duracao;
    }
    
    public long getDuracao() {
        return duracao;
    }
    
    protected void compara(){
        numComparacoes++;
    }
    
    protected void copia(){
        numCopias ++;
    }
    
    protected void compara(int num){
        numComparacoes += num;
    }
    
    protected void copia(int num){
        numCopias += num;
    }
    
    protected void troca(Object[] array, int index1, int index2) {
        Object aux;
        aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
        numCopias++;
    }
    
    public void imprimir(Integer[] array){
        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);
    }
}
