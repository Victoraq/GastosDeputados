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
public class AVL {
    private NoAVL raiz;
    
    public AVL() {
    }

    public AVL(NoAVL raiz) {
        this.raiz = raiz;
    }
    
    public void inserir(int valor) {
        
        if (this.raiz == null) { // Se a arvore estiver vazia é inserido na raiz.
           NoAVL no = new NoAVL(valor);
           raiz = no;
           return;
        }
        
        
        NoAVL no = new NoAVL(valor);
        auxInsere(raiz, no); // Chamando função auxiliar recursiva.
        
        for (NoAVL noaux = no; noaux != null; noaux = noaux.pai) {
            noaux.fatorb = this.calculaFatorBalanceamento(noaux);
            if (noaux.fatorb == 2) {
                if (noaux.fesq.fatorb == -1) {
                    this.rotacaoLR(noaux);
                    continue;
                }
                this.rotacaoDir(noaux);
                
            } else if(noaux.fatorb == -2) {
                if (noaux.fdir.fatorb == 1) {
                    this.rotacaoRL(noaux);
                    continue;
                }
                this.rotacaoEsq(noaux);
            }
            if (noaux == raiz) {
                break;
            }
        }
    }
    
    private void auxInsere(NoAVL raiz, NoAVL no) {
        if (raiz.valor >= no.valor) { // Se valor for menor ou igual, insere a esquerda
            if (raiz.fesq == null) { // Se não tiver nenhum nó a equerda já é inserido
                raiz.fesq = no;
                no.pai = raiz;
            } else
                auxInsere(raiz.fesq, no); // Senão é chamado a recursão para a arvore a esquerda
        }
        if (raiz.valor < no.valor) { // Se valor for maior, insere a direita
            if (raiz.fdir == null) { // Se não tiver nenhum nó a direita já é inserido
                raiz.fdir = no;
                no.pai = raiz;
            } else
                auxInsere(raiz.fdir, no); // Senão é chamado a recursão para a arvore a direita
        }
    }
    
    public NoAVL busca(int valor) {
        return this.auxBusca(this.raiz, valor);
    }
    
    private NoAVL auxBusca(NoAVL no, int valor) {
        
        if (no == null || (no.fesq == null && no.fdir == null))
            return null;
        
        if (no.valor == valor) {
            return no;
        } else if (no.valor < valor) {
            auxBusca(no.fdir, valor);
        } else {
            auxBusca(no.fesq, valor);
        }
        
        return null;
        
    }
    
    public void remove() {
        
    }
    
    private int calculaFatorBalanceamento(NoAVL no) {
        
        int fator = this.auxFator(no.getFesq()) - this.auxFator(no.getFdir());
        
        return fator;
    }
    
    private int auxFator(NoAVL no) {
        if (no == null)
            return 0;
        else 
            return 1 + Math.max(auxFator(no.getFesq()), auxFator(no.getFdir()));
    }
    
    private void rotacaoEsq(NoAVL x0) {
        NoAVL x1= x0.fdir;
        
        if (x0 == raiz) {
            raiz = x1;
        } else {
            x1.pai = x0.pai;
            x0.pai.fdir = x1;
        }
        
        x0.fdir = x1.fesq;
        x1.fesq = x0;
        x0.pai = x1;
        
    }
    
    private void rotacaoDir(NoAVL x0) {
        NoAVL x1 = x0.fesq;
        
        if (x0 == raiz) {
            raiz = x1;
        } else {
            x1.pai = x0.pai;
            x0.pai.fesq = x1;
        }
        
        x0.fesq = x1.fdir;
        x1.fdir = x0;
        x0.pai = x1;
    }
    
    private void rotacaoLR(NoAVL x) {
        this.rotacaoEsq(x.fesq);
        this.rotacaoDir(x);
    }
    
    private void rotacaoRL(NoAVL x) {
        this.rotacaoDir(x.fdir);
        this.rotacaoEsq(x);
    }
    
    public void imprime() {
        printArvore(raiz, 0);
    }
    
    private void printArvore(NoAVL raiz, int level){
        if(raiz==null)
             return;
        printArvore(raiz.fdir, level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            System.out.println("|-------"+raiz.getValor());
        }
        else
            System.out.println(raiz.getValor());
        printArvore(raiz.fesq, level+1);
    } 
    
}
