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
public abstract class Arvore {
    
    private long duracaoInsercao, duracaoBusca, duracaoRemocao;
    private double numComparacoes, numCopias;

    public Arvore() {
    }
    
    public void inserir(Integer[] vetor) {
        long inicio, fim;
        
        inicio = System.currentTimeMillis(); // tempo inicial
        
        for (Integer vetor1 : vetor) {
            this.inserir(vetor1);
        }
        
        fim = System.currentTimeMillis(); // tempo final
        
        this.duracaoInsercao = fim - inicio;
    }
    
    public abstract void inserir(int valor);
    
    public boolean[] busca(Integer[] vetor) {
        long inicio, fim;
        // vetor com tags se encontrou ou não os valores
        boolean[] resultado = new boolean[vetor.length]; 
        
        inicio = System.currentTimeMillis(); // tempo inicial
        
        for (int i = 0; i < vetor.length; i++) {
            Object n = this.busca(vetor[i]);
            
            resultado[i] = (n == null); // Armazenando se encontrou ou não
        }
        
        fim = System.currentTimeMillis(); // tempo final
        
        this.duracaoBusca = fim - inicio;
        
        return resultado;
    }
    
    public abstract Object busca(int valor);
    
    public void remover(Integer[] vetor) {
        long inicio, fim;
        
        inicio = System.currentTimeMillis(); // tempo inicial
        
        for (Integer vetor1 : vetor) {
            this.remover(vetor1);
        }
        
        fim = System.currentTimeMillis(); // tempo final
        
        this.duracaoRemocao = fim - inicio;
    }
    
    public abstract void remover(int valor);
    
    
    /**
     * Métodos que retornam atributos da classe.
     */
    
    
    public long getDuracaoInsercao() {
        return this.duracaoInsercao;
    }
    
    public long getDuracaoBusca() {
        return this.duracaoBusca;
    }

    public long getDuracaoRemocao() {
        return duracaoRemocao;
    }
    
    public double getNumComparacoes(){
        return this.numComparacoes;
    }
    
    public double getNumCopias(){
        return this.numCopias;
    }
    
    protected void compara(){
        numComparacoes++;
    }
    
    protected void copia(){
        numCopias ++;
    }
    
}
