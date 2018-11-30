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
    
    /**
     * duracaoInsercao - Duração em segundos da inserção 
     * duracaoBusca - Duração em segundos da busca
     * duracaoRemocao - Duração em segundos da remoção
     * numComparacoes - quantidade de comparacoes feitas.
     * numCopias - quantidade de copias feitas.
     */
    private long duracaoInsercao, duracaoBusca, duracaoRemocao;
    private double numComparacoes, numCopias;
    
    /**
     * Construtor da classe Ordenacao.
     */
    public Arvore() {
        this.duracaoBusca = 0;
        this.duracaoInsercao = 0;
        this.duracaoRemocao = 0;
        this.numComparacoes = 0;
        this.numCopias = 0;
    }
    
    /**
     * Insere um vetor de inteiros na arvore e contabiliza métricas
     * @param vetor - vetor de valores a serem insseridos
     */
    public void inserir(Integer[] vetor) {
        long inicio, fim;
        this.numComparacoes = 0;
        this.numCopias = 0;
        
        inicio = System.currentTimeMillis(); // tempo inicial
        
        // Inserindo valores
        for (Integer vetor1 : vetor) {
            this.inserir(vetor1);
        }
        
        fim = System.currentTimeMillis(); // tempo final
        
        this.duracaoInsercao = fim - inicio;
    }
    
    /**
     * Insere um valor inteiro na arvore
     * @param valor - valor inteiro a ser inserido
     */
    public abstract void inserir(int valor);
    
    /**
     * Busca um vetor de inteiros na arvore e contabiliza métricas
     * @param vetor - vetor de valores a serem buscados
     */
    public boolean[] busca(Integer[] vetor) {
        long inicio, fim;
        // vetor com tags se encontrou ou não os valores
        boolean[] resultado = new boolean[vetor.length]; 
        this.numComparacoes = 0;
        this.numCopias = 0;
        
        inicio = System.currentTimeMillis(); // tempo inicial
        
        // Buscando valores
        for (int i = 0; i < vetor.length; i++) {
            Object n = this.busca(vetor[i]);
            
            resultado[i] = (n == null); // Armazenando se encontrou ou não
        }
        
        fim = System.currentTimeMillis(); // tempo final
        
        this.duracaoBusca = fim - inicio;
        
        return resultado;
    }
    
    /**
     * Busca um valor inteiro na arvore
     * @param valor - valor inteiro a ser buscado
     * @return Object Boolean se foi encontrado ou não
     */
    public abstract Object busca(int valor);
    
    /**
     * Remove valores de um vetor de inteiros na arvore e contabiliza métricas
     * @param vetor - vetor de valores a serem removidos
     */
    public void remover(Integer[] vetor) {
        long inicio, fim;
        this.numComparacoes = 0;
        this.numCopias = 0;
        
        inicio = System.currentTimeMillis(); // tempo inicial
        
        // Removendo valores
        for (Integer vetor1 : vetor) {
            this.remover(vetor1);
        }
        
        fim = System.currentTimeMillis(); // tempo final
        
        this.duracaoRemocao = fim - inicio;
    }
    
    /**
     * Remove um valor inteiro na arvore
     * @param valor - valor inteiro a ser removido
     */
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
