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
//                        if (n.folha && n.chaves[i] != 0) {
                            i = busca.pos = i+1;
                            
//                            if (i == this.ordem) {
                                n = n.filhos[i];
                                if (n != null && n.chaves[0] != valor)
                                    n = null;
//                            }
                            
//                            continue;
//                        }
                        // Problema ao buscar valores repetidos, sempre iremos ao mais inferior
//                        else if (n.filhos[i+1]!= null && n.filhos[i+1].chaves[0] == valor){
//                            n = n.filhos[i+1];
//                            break;
//                        }
                        
//                        n = null;
                        break;
                    } else { // se for menor desce para filho a esquerda
                        n = n.filhos[i];
                        break;
                    }
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
        
        // para numeros iguais a busca encontra o no em que eles está
        // mas se ele estiver cheio irei forçar a ida para o filho a direita
//        if (valida == 1 && no.chaves[this.ordem-1] != 0) { 
//            if(no.filhos[this.ordem] == null) no.filhos[this.ordem] = new NoB(this.ordem);
//            no = no.filhos[this.ordem];
//        }
        
        if (no.folha)
            this.insere_vetor(no, valor); // inserindo no nó encontrado
        else {
            NoB n = new NoB(this.ordem);
            this.insere_vetor(n, valor);
            no.filhos[pos] = n;
        }
        
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

    private int insere_vetor(NoB no, int chave) {
//        int i = this.ultima_posicao(no.chaves);
        int i = this.ordem-1;
        
        // Irá deslocando as chaves para inserir ordenado
        while(i >= 0 && (chave < no.chaves[i] || no.chaves[i] == 0)) {
            if (i+1 < ordem) no.chaves[i+1] = no.chaves[i];
            no.filhos[i+1] = no.filhos[i];
            i--;
        }
        
        no.chaves[i+1] = chave;
        
        return i+1;
        
    }
    
    private void remove_vetor(NoB no, int pos) {
        int i = pos;
            
        // Desloco as chaves para remover o valor
        while (i < ordem-1) { 
            no.chaves[i] = no.chaves[i+1];
            i++;
        }
    }

    public boolean remover(int valor) {
        
        NoB no, pai, maior, irmao, pai_maior;
        int pos, ult_maior, pos_maior;
        
        Busca busca = buscaB(valor); // Buscando valor
        
        if (busca.valida == 0) {// não encontrou o valor
            System.out.println("Valor não encontrado");
            return false;
        }
        
        no = busca.no;
        pai = busca.pai;
        pos = busca.pos;
        ult_maior = this.ordem;
        pos_maior = 0;
        
        // Pela forma que foi implementada a busca se a posição seguinte estiver
        // vazia a pos irá ser a seguinte, sendo assim necessário recuar para encontrar o valor
        if (no.chaves[pos] != valor) pos--;
        
        if (no.folha) { // Se for folha
            
            this.remove_vetor(no, pos); // removendo o valor do nó folha
            
        } else { // se não for folha
            
            // Procuro a maior chave dentre as menores que o valor a ser retirado
            pai = no;
            maior = no.filhos[pos];
            
            while(maior.folha == false) {
                
                for (pos_maior = 0; pos_maior < ordem; pos_maior++) {
                    if (maior.chaves[pos_maior] == 0)
                        break;
                }
                
                pai = maior;
                
                maior = maior.filhos[pos_maior];
            }
            
            // Encontrando posição do maior valor
            
            ult_maior = this.ultima_posicao(maior.chaves);
            
            no.chaves[pos] = maior.chaves[ult_maior]; // Substituindo valor
            
            this.remove_vetor(maior, ult_maior); // removendo o valor do nó folha
            
            no = maior;
            
        }
        
        this.verifica_remocao(pai, no);
        
        
        return true;
    }
    
    private int ultima_posicao(int[] vetor) {
        int i;
        for (i = vetor.length-1; i >= 0; i--) {
            if (vetor[i] != 0)
                break;
        }
        
        return i;
    }
    
    private void verifica_remocao(NoB pai, NoB no) {
        NoB irmao = null;
        
        int tam_no = this.ultima_posicao(no.chaves)+1;

        
        if (tam_no < ordem/2) {
            // Descobrindo o irmao
            for (int i = 0; i < ordem; i++) {
                // Se o nó for estiver na primeira posicao o irmao é o subsequente
                if (no == pai.filhos[0]) { 
                    
                    irmao = pai.filhos[1];
                    
                    break;
                    
                } // Senao, o irmão é o anterior ao nó
                else if(no == pai.filhos[i]) {
                    
                    no = pai.filhos[i-1];
                    irmao = pai.filhos[i];
                    
                    break;
                }
            
            }
            
            // Coletando numero de chaves do irmao e do nó
            int tam_irmao = this.ultima_posicao(irmao.chaves)+1;
            
            tam_no = this.ultima_posicao(no.chaves)+1;

            if (tam_no + tam_irmao < ordem) {
                this.concatenacao(pai,no,irmao);
            } else {
                this.redistribuicao(pai,no,irmao);
            }
            
        }
        
    }
    
    private void concatenacao(NoB pai, NoB no, NoB irmao) {
        NoB novo_no, avo;
        int pos, pos_no, ult_no, ult_irmao, tam_pai, i;
        Busca busca;
                 
        novo_no = new NoB(this.ordem);
        avo = this.buscaB(pai.chaves[0]).pai; // adquirindo avo para futuras operações

        //Descobrindo posição do filho a esquerda e da chave do pai a ser concatenado
        for (pos_no = 0; pos_no < ordem; pos_no++) {
            if (no == pai.filhos[pos_no]) {
                break;
            }
        }

        // Preenchendo o novo nó com os valores do filho a esquerda
        ult_no = this.ultima_posicao(no.chaves);
        
        i = 0;
        pos = -1;
        
        for (i = 0; i <= ult_no && no.chaves[i] != 0; i++) {
            pos = this.insere_vetor(novo_no, no.chaves[i]);
            novo_no.filhos[pos] = no.filhos[i];
        }
        if (no.filhos[i] != null) { // inserindo ultimo filho que pode ser pulado
            pos++;
            novo_no.filhos[pos] = no.filhos[i];
        }

        // inserindo chave do pai a ser concatenada
        if (pai.chaves[pos_no] != 0)
            pos = this.insere_vetor(novo_no, pai.chaves[pos_no]);
            

        // Preenchendo o novo nó com os valores do filho a direita
        ult_irmao = this.ultima_posicao(irmao.chaves);
        for (i = 0; i <= ult_irmao && irmao.chaves[i] != 0; i++) {
            pos = this.insere_vetor(novo_no, irmao.chaves[i]);
            novo_no.filhos[pos] = irmao.filhos[i];
        }
        if (irmao.filhos[i] != null) { // inserindo ultimo filho que pode ser pulado
            pos++;
            novo_no.filhos[pos] = irmao.filhos[i];
        }

        // verificando se é folha
        for (int j = 0; j <= this.ordem; j++)
            if (novo_no.filhos[j] != null) novo_no.folha = false;
        
        //removendo valor que desceu para nó inferior
        this.remove_vetor(pai, pos_no);
        // realocando filhos
        pai.filhos[pos_no] = novo_no;
        for (i = pos_no + 1; i < ordem; i++) {
            pai.filhos[i] = pai.filhos[i + 1];
        }
        
        if (this.raiz.chaves[0] == 0) {
            this.raiz = novo_no;
            return;
        }

        tam_pai = this.ultima_posicao(pai.chaves)+1;


        // verificando se será necessário continuar a concatenação
        if (tam_pai < this.ordem/2 && pai != this.raiz) { 
            
            // Subindo na arvore
            no = pai;
            pai = avo;

            // encontrando irmao
            for (i = 0; i < ordem; i++) {
                // Se o nó for estiver na primeira posicao o irmao é o subsequente
                if (no == pai.filhos[0]) { 
                    irmao = pai.filhos[1];
                    break;
                }// Senao, o irmão é o anterior ao nó
                else if (no == pai.filhos[i]) {
                    no = pai.filhos[i-1];
                    irmao = pai.filhos[i];
                    break;
                }
            }
            int tam_no = this.ultima_posicao(no.chaves)+1;
            
            int tam_irmao = this.ultima_posicao(irmao.chaves)+1;
            
            if(tam_no + tam_irmao < ordem) this.concatenacao(pai, no, irmao);
        }
 
        
        
    }
    
    private void redistribuicao(NoB pai, NoB no, NoB irmao) {
        int pos;
        // Inserindo todos as chaves do nó no irmão
        for (int i = 0; i < ordem; i++) {
            pos = this.insere_vetor(irmao, no.chaves[i]);
            irmao.filhos[pos] = no.filhos[i];
        } 

        this.split(irmao, pai);
    }
    
}
