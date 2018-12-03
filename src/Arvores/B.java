
package Arvores;

/**
 * Classe que implementa a Árvore B.
 */
public class B extends Arvore {
    
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
    
    /**
    * Busca interna por um valor
    */
    private Busca buscaB(int valor) {
        Busca busca = new Busca();
        
        super.copia();
        super.copia();
        NoB n = busca.pai = this.raiz;
        busca.valida = 0;
        
        super.compara();
        while (n != null) {
            int i = busca.pos = 0;
            
            super.copia();
            super.copia();
            busca.pai = busca.no;
            busca.no = n;
            
            super.compara();
            while (i < this.ordem) {
                super.compara();
                if (valor > n.chaves[i]) {
                    super.compara();
                    if (n.chaves[i] == 0) { // caso em que esta vazio, ai desce para filho 
                        n = n.filhos[i];
                        break;
                    }
                    super.copia();
                    i = busca.pos = i+1;
                
                } else { 
                    
                    if (valor == n.chaves[i]) { // caso valor esteja na arvore
                        busca.valida = 1;
                                                
                        n = null;
                        break;
                    } else { // se for menor desce para filho a esquerda
                        super.copia();
                        n = n.filhos[i];
                        break;
                    }
                }
                
                // ultima posição livre para inserção
                if (i == ordem) n = n.filhos[ordem]; 
                super.copia();
            }
            
        }
        
        return busca;
        
    }
    
    @Override
    public Boolean busca(int valor) {
        Busca b = buscaB(valor);
        return b.valida == 1;
    }
    
    @Override    
    public void inserir(int valor) {
        
        super.compara();
        if(this.raiz == null){ //Verifica se a arvore esta vazia, se sim, apenas cria a raiz

            this.raiz = new NoB(this.ordem);
            this.insere_vetor(this.raiz, valor);

        } else {///Caso contrario, chama a insercao recursiva

            this.auxInserir(raiz,valor);
            
            super.compara();
            if(raiz.chaves[this.ordem-1] != 0){ //Verifica se a raiz esta em overflow, se estiver faz o split

                NoB nRaiz = new NoB(ordem);
                split(this.raiz, nRaiz);
                this.raiz = nRaiz;
                this.raiz.folha = false;

            }

        }
    }
    
    private void auxInserir(NoB no, int valor){
        
        int pos = 0;
        super.compara();
        while(pos < this.ordem) {
            
            if (no.chaves[pos] < valor) pos++;
            else break;
            
        }
        super.compara();
        if (no.filhos[pos] == null) {
            this.insere_vetor(no, valor);
        } else {
            this.auxInserir(no.filhos[pos], valor);
            
            super.compara();
            if (no.filhos[pos].chaves[this.ordem-1] != 0) {
                this.split(no.filhos[pos], no);
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
           super.compara();
           super.copia();
           // Preenchendo chaves e filhos
           if (i <= (ordem/2)-1) {
               
               super.compara();
               // Pulando valor que subiu para o nó superior
               if (i != (ordem/2)-1) no_esq.chaves[i] = no.chaves[i];
               
               super.copia();
               no_esq.filhos[i] = no.filhos[i];
               if (no.filhos[i] != null) no.filhos[i].pai = no_esq;
               
           } else {
               super.compara();
               // evitando null pointer exception
               if (i < ordem) no_dir.chaves[pos_dir] = no.chaves[i];
               
               super.copia();
               no_dir.filhos[pos_dir] = no.filhos[i];
               if (no.filhos[i] != null) no.filhos[i].pai = no_dir;

               
               pos_dir++;
           }
       }
       
       //Verificando se filhos irão ser folhas
       for (int i = 0; i <= ordem; i++) {
           super.compara();
           if (no_esq.filhos[i] != null) 
               no_esq.folha = false;
           if (no_dir.filhos[i] != null) 
               no_dir.folha = false;
       }
       
       // Assinalando os seus respectivos ponteiros
       
       super.copia();
       super.copia();
       pai.filhos[pos] = no_esq;
       pai.filhos[pos+1] = no_dir;
       pai.folha = false;
       no_esq.pai = pai;
       no_dir.pai = pai;

       
    }

    private int insere_vetor(NoB no, int chave) {
        int pos = 0;
        
        // encontra posição a ser inserido
        while(pos < this.ordem-1 && no.chaves[pos] < chave && no.chaves[pos] != 0) {
            super.compara();
            pos++;

        }
        
        // deslocando chaves
        for(int i = this.ordem-2; i >= pos; i--){
            super.compara();
            super.copia();
            no.chaves[i+1] = no.chaves[i];
        }
        // deslocando filhos a partir da ultima posição inserida a fim de evitar duplicações
        int ult = this.ultima_posicao(no.chaves);
        for(int i = ult; i >= pos; i--){
            super.compara();
            super.copia();
            no.filhos[i+1] = no.filhos[i];

        }
        
        no.chaves[pos] = chave;
        
        return pos;
        
    }
    
    private void remove_vetor(NoB no, int pos) {
        int i = pos;
            
        // Desloco as chaves para remover o valor
        while (i < ordem-1) { 
            super.compara();
            super.copia();
            no.chaves[i] = no.chaves[i+1];
            i++;
        }
    }

    @Override
    public void remover(int valor) {
        
        NoB no, pai, maior = null, irmao, pai_maior;
        int pos, ult_maior, pos_maior;
        
        super.copia();
        Busca busca = buscaB(valor); // Buscando valor
        
        if (busca.valida == 0) {// não encontrou o valor
//            System.out.println("Valor não encontrado");
            return;
//            return false;
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
            for (int i = 0; i < this.ordem; i++){
                if (no.filhos[i] != null)
                    maior = no.filhos[i];
            }
            while(maior != null && !maior.folha) {
                
                pos_maior = this.ultima_posicao(maior.chaves);
                
//                for (pos_maior = 0; pos_maior < ordem; pos_maior++) {
//                    if (maior.chaves[pos_maior] == 0)
//                        break;
//                }
                
                if (maior.filhos[pos_maior] != null) {
                    maior = maior.filhos[pos_maior];
                } else
                    break;
            }
            
            // Encontrando posição do maior valor
            
            ult_maior = this.ultima_posicao(maior.chaves);
            
            no.chaves[pos] = maior.chaves[ult_maior]; // Substituindo valor
            
            this.remove_vetor(maior, ult_maior); // removendo o valor do nó folha
            
            int i = ult_maior;
            while (i < ordem) {
                no.filhos[i] = no.filhos[i+1];
                i++;
            }
            
            no = maior;
            
        }
        if (no != this.raiz)
            this.verifica_remocao(no.pai, no);
        
        
        
//        return true;
    }
        
    private int ultima_posicao(int[] vetor) {
        int i;
        for (i = vetor.length-1; i >= 0; i--) {
            super.compara();
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
            for (int i = 0; i <= ordem; i++) {
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
            int tam_irmao;
            if (irmao != null) tam_irmao = this.ultima_posicao(irmao.chaves)+1;
            else {
                tam_irmao = 0;
                irmao = new NoB(this.ordem);
                irmao.pai = pai;
                pai.filhos[1] = irmao;
            }
            
            tam_no = this.ultima_posicao(no.chaves)+1;

            if (tam_no + tam_irmao < ordem) {
//                if (irmao == null) {
//                    irmao = new NoB(this.ordem);
//                    irmao.pai = no.pai;
//                }
                this.concatenacao(no.pai,no,irmao);
            } else {
//                if (irmao == null) {
//                    irmao = new NoB(this.ordem);
//                    irmao.pai = no.pai;
//                }
                this.redistribuicao(no.pai,no,irmao);
            }
            
        }
        
    }
    
    private void concatenacao(NoB pai, NoB no, NoB irmao) {
        NoB novo_no;
        int pos, pos_no, ult_no, ult_irmao, tam_pai, i;
        Busca busca;
                 
        novo_no = new NoB(this.ordem);
        pai = no.pai;

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
            if (no.filhos[i] != null) no.filhos[i].pai = novo_no;
        }
        if (no.filhos[i] != null) { // inserindo ultimo filho que pode ser pulado
            pos++;
            novo_no.filhos[pos] = no.filhos[i];
            no.filhos[i].pai = novo_no;
        }
        // inserindo chave do pai a ser concatenada
        if (pai.chaves[pos_no] != 0)
            pos = this.insere_vetor(novo_no, pai.chaves[pos_no]);
            

        // Preenchendo o novo nó com os valores do filho a direita
        ult_irmao = this.ultima_posicao(irmao.chaves);
        for (i = 0; i <= ult_irmao && irmao.chaves[i] != 0; i++) {
            pos = this.insere_vetor(novo_no, irmao.chaves[i]);
            novo_no.filhos[pos] = irmao.filhos[i];
            if (irmao.filhos[i] != null) irmao.filhos[i].pai = novo_no;
        }
        if (irmao.filhos[i] != null) { // inserindo ultimo filho que pode ser pulado
            pos++;
            novo_no.filhos[pos] = irmao.filhos[i];
            irmao.filhos[i].pai = novo_no;
        }

        // verificando se é folha
        for (int j = 0; j <= this.ordem; j++)
            if (novo_no.filhos[j] != null) novo_no.folha = false;
        
        //removendo valor que desceu para nó inferior
        this.remove_vetor(pai, pos_no);
        // realocando filhos
        pai.filhos[pos_no] = novo_no;
        novo_no.pai = pai;
        for (i = pos_no + 1; i < ordem; i++) {
            pai.filhos[i] = pai.filhos[i + 1];
        }
        
        if (this.raiz.chaves[0] == 0) {
            this.raiz = novo_no;
            novo_no.pai = null;
            return;
        }

        tam_pai = this.ultima_posicao(pai.chaves)+1;


        // verificando se será necessário continuar a concatenação
        if (tam_pai < this.ordem/2 && pai != this.raiz) { 
            
            // Subindo na arvore
            no = pai;
            pai = pai.pai;

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
            
            int tam_irmao;
            if (irmao != null) tam_irmao = this.ultima_posicao(irmao.chaves)+1;
            else {
                tam_irmao = 0;
                irmao = new NoB(this.ordem);
                irmao.pai = pai;
                pai.filhos[1] = irmao;
            }
            
            if(tam_no + tam_irmao < ordem) this.concatenacao(no.pai, no, irmao);
        }
 
        
        
    }
    
    private void redistribuicao(NoB pai, NoB no, NoB irmao) {
        int pos;
        // Inserindo todos as chaves do nó no irmão
        for (int i = 0; i < ordem; i++) {
            pos = this.insere_vetor(irmao, no.chaves[i]);
            irmao.filhos[pos] = no.filhos[i];
            if (no.filhos[i] != null) no.filhos[i].pai = irmao;
        } 

        this.split(irmao, pai);
    }
    
}
