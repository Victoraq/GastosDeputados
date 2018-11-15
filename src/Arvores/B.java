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
public class B {
    
    private NoB raiz;
    private int ordem;

    public B(NoB raiz, int ordem) {
        this.raiz = raiz;
        this.ordem = ordem;
    }

    public B(int ordem) {
        this.ordem = ordem;
        this.raiz = null;
    }
    
    // classe para auxiliar na obtenção de valores uteis na busca
    private class Busca { 
        NoB no = null; // no contendo o valor buscado
        NoB pai = null; // no contendo o pai do no encontrado
        int pos; // posicao no vetor de chaves do valor
        int valida; // validação se encontrou ou não o valor
    }
    
    private Busca buscaB(int valor) {
        Busca busca = new Busca();
        
        NoB n = busca.pai = this.raiz;
        busca.valida = 0;
        
        while (n != null) {
            int i = busca.pos = 0;
            
            busca.pai = busca.no;
            busca.no = n;
            
            while (i < this.ordem) {
                if (valor > n.chaves[i]) {
                    if (n.chaves[i] == 0) { // caso em que esta vazio, ai desce para filho 
                        n = n.filhos[i];
                        break;
                    }
                    
                    i = busca.pos = i+1;
                
                } else { 
                    
                    if (valor == n.chaves[i]) { // caso valor esteja na arvore
                        busca.valida = 1;
                        
                        // se a próxima posição estiver vazia é inserido no próximo
                        if (n.chaves[i+1] == 0)
                            i = busca.pos = i+1;
                        
                        n = null;
                        
                    } else { // se for menor desce para filho a esquerda
                        n = n.filhos[i];
                    }
                    break;
                }
                
                // ultima posição livre para inserção
                if (i == ordem) n = n.filhos[ordem]; 
            }
            
        }
        
        return busca;
        
    }
    
    public boolean busca(int valor) {
        Busca b = buscaB(valor);
        return b.valida == 1;
    }
    
    public void inserir(int valor) {
        NoB no, pai;
        int pos, valida;
        
        Busca busca = this.buscaB(valor); // busca posição para inserção
        
        no = busca.no;
        pai = busca.pai;
        pos = busca.pos;
        valida = busca.valida;
        
        // Arvore vazia
        if (this.raiz == null) {
            this.raiz = new NoB(ordem);
            this.raiz.chaves[0] = valor;
            return;
        }
        
        this.insere_vetor(no, valor); // inserindo no nó encontrado
        
        if (no.chaves[ordem-1] != 0) { // Overflow na folha inserida

            busca = this.buscaB(valor);

            no = busca.no;  
            pai = busca.pai; 

            while (no != this.raiz) { // verifica para os nós superiores se ocorreu overflow
                
                // Corrigir depois!!! Pois não posso comparar null com int e o 0 pode ser valor inserido
                if (no.chaves[ordem-1] != 0) { // se a ulima posição estiver preenchida ocorreu overflow
                    this.split(no, pai);
                } 

                busca = this.buscaB(pai.chaves[0]); // Busco um valor do pai para subir na arvore

                no = busca.no;  
                pai = busca.pai; 

            }

            if (this.raiz.chaves[ordem-1] != 0) { // Overflow na raiz
                    NoB nRaiz = new NoB(ordem);
                    split(this.raiz, nRaiz);
                    this.raiz = nRaiz;
                    this.raiz.folha = false;
                }

        }
        
    }
    
    private void split(NoB no, NoB pai) {
       int pos = this.insere_vetor(pai, no.chaves[(ordem/2)-1]);
       
       // Novos nós que surgiram a partir do split
       NoB no_esq = new NoB(ordem);
       NoB no_dir = new NoB(ordem);
       int pos_dir = 0;
       
       for (int i = 0; i <= ordem; i++) { // Preenchendo nós separados
           // Preenchendo chaves e filhos
           if (i <= (ordem/2)-1) {
               
               // Pulando valor que subiu para o nó superior
               if (i != (ordem/2)-1) no_esq.chaves[i] = no.chaves[i];
               
               no_esq.filhos[i] = no.filhos[i];
               
           } else {
               // evitando null pointer exception
               if (i < ordem) no_dir.chaves[pos_dir] = no.chaves[i];
               
               no_dir.filhos[pos_dir] = no.filhos[i];
               
               pos_dir++;
           }
       }
       
       //Verificando se filhos irão ser folhas
       for (int i = 0; i <= ordem; i++) {
           if (no_esq.filhos[i] != null) 
               no_esq.folha = false;
           if (no_dir.filhos[i] != null) 
               no_dir.folha = false;
       }
       
       // Assinalando os seus respectivos ponteiros
       pai.filhos[pos] = no_esq;
       pai.filhos[pos+1] = no_dir;
       pai.folha = false;

       
    }

    private int insere_vetor(NoB pai, int chave) {
        int i = ordem-1;
        
        // Irá deslocando as chaves para inserir ordenado
        while(i >= 0 && (chave < pai.chaves[i] || pai.chaves[i] == 0)) {
            if (i+1 < ordem) pai.chaves[i+1] = pai.chaves[i];
            pai.filhos[i+1] = pai.filhos[i];
            i--;
        }
        
        pai.chaves[i+1] = chave;
        
        return i+1;
        
    }


    
    
}
