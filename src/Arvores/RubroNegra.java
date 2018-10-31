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
public class RubroNegra {
    private NoRubroNegra raiz;
    
    public RubroNegra() {
    }

    public RubroNegra(NoRubroNegra raiz) {
        this.raiz = raiz;
        this.raiz.setCor('p');
    }
    
    public void inserir(int valor) {
        
        // Caso 1
        if (this.raiz == null) { // Se a arvore estiver vazia é inserido na raiz.
           NoRubroNegra no = new NoRubroNegra(valor);
           no.setCor('p');
           this.raiz = no;
           return;
        }
        
        NoRubroNegra no = new NoRubroNegra(valor);
        
        auxInsere(raiz, no); // Chamando função auxiliar recursiva.
        
        // Variaveis para analise das propriedades
        NoRubroNegra pai = no.pai;
        NoRubroNegra avo = pai.pai;
        NoRubroNegra tio;
        NoRubroNegra irmao = null;
        
        while (avo != null) {
            System.out.println("entrou");
            if (avo.fdir == pai) tio = avo.fesq;
            else tio = avo.fdir;
        
        
            // Caso 2
            if (tio != null && tio.cor == 'v') {
                if (avo != this.raiz) avo.cor = 'v';
                tio.cor = 'p';
                pai.cor = 'p';
            } else { //Caso 3

                if (tio == avo.fesq) {
                    if (pai.fdir == no) this.rotacaoEsq(avo); // rotação a esquerda
                    else this.rotacaoRL(avo); // rotação direita esquerda
                    pai = no.pai;
                    irmao = pai.fesq;
                } else if (tio == avo.fdir) {
                    if (pai.fesq == no) this.rotacaoDir(avo); // rotação a direita
                    else this.rotacaoLR(avo); // rotação esquerda direita
                    pai = no.pai;
                    irmao = pai.fdir;
                }
                pai.cor = 'p';
                irmao.cor = 'v';
            }
            System.out.println("Antes");
            System.out.println("No: "+no);
            System.out.println("Pai: "+pai);
            System.out.println("Avo: "+avo);
            no = pai;
            pai = no.pai;
            avo = pai.pai;
            System.out.println("Depois");
            System.out.println("No: "+no);
            System.out.println("Pai: "+pai);
            System.out.println("Avo: "+avo);
        }
    }
    
    private void auxInsere(NoRubroNegra raiz, NoRubroNegra no) {
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
    
    private void rotacaoEsq(NoRubroNegra x0) {
        NoRubroNegra x1= x0.fdir;
        
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
    
    private void rotacaoDir(NoRubroNegra x0) {
        NoRubroNegra x1 = x0.fesq;
        
        if (x0 == raiz) {
            raiz = x1;
            x1.pai = null;
        } else {
            x1.pai = x0.pai;
            x0.pai.fesq = x1;
        }
        
        x0.fesq = x1.fdir;
        x1.fdir = x0;
        x0.pai = x1;
    }
    
    private void rotacaoLR(NoRubroNegra x) {
        this.rotacaoEsq(x.fesq);
        this.rotacaoDir(x);
    }
    
    private void rotacaoRL(NoRubroNegra x) {
        this.rotacaoDir(x.fdir);
        this.rotacaoEsq(x);
    }
    
    public void imprime() {
        printArvore(raiz, 0);
    }
    
    private void printArvore(NoRubroNegra raiz, int level){
        if(raiz==null)
             return;
        printArvore(raiz.fdir, level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            System.out.println("|-------"+raiz.getValor()+" "+raiz.getCor());
        }
        else
            System.out.println(raiz.getValor()+" "+raiz.getCor());
        printArvore(raiz.fesq, level+1);
    }
    
}
