
package Arvores;

/**
 * Classe que implementa a Árvore AA.
 */
public class ArvoreAA extends Arvore {
    private NoAA raiz;
    
    // Árvore alternativa à rubro-negra. É mais simples, por eliminar muitas das condições que devem ser consideradas
    // para a rubro-negra.
    // Uma regra nova e importante dessa árvore é algo que seria equivalente a 'filhos à esquerda não podem ser
    // vermelhos' para uma rubro-negra. A árvore AA não utiliza o atributo de cor para os nós, trocando-o pelo atributo
    // de nível.
    
    // Árvores AA devem obedecer às seguintes condições:
    // 1) O nível de uma folha é sempre 1;
    // 2) O nível de um filho à esquerda é sempre menor que o nível de seu pai;
    // 3) O nível de um filho à direita é menor ou igual ao nível de seu pai;
    // 4) O nível de um neto à direita é sempre menor que o nível de seu avô;
    // 5) Se um nó possui nível maior que 1, ele deve ter 2 filhos.
    
    public ArvoreAA() {
    }

    /**
     * Construtor da Árvore que recebe um nó raiz.
     * @param raiz - nó que irá ser a raiz da árvore
     */
    public ArvoreAA(NoAA raiz) {
        this.raiz = raiz;
    }
    
    @Override
    public void inserir(int valor){
        NoAA no = new NoAA(valor);
        
        super.compara();
        if(this.raiz == null) {
            this.raiz = no;
            super.copia();
            no.nivel = 1;
        }
        else {
            auxInsere(raiz, no);
        }
        
        // Após a inserção do valor, verifica se as condições da árvore AA são atendidas.
        verifica_propriedades(no);
        
        // Verifica-se as condições em toda a árvore.        
        for(NoAA noaux = no; noaux != null; noaux = noaux.pai) {
            verifica_propriedades(noaux);
            noaux.calculaNivel(noaux);
        }
        
    }
    
    // Inserção comum de uma BST.
    public void auxInsere(NoAA raiz, NoAA no) {
        super.compara();
        if(no.valor > raiz.valor) {
            super.compara();
            if(raiz.getfDir() == null) {
                raiz.setfDir(no);
                no.setPai(raiz);
                no.nivel = raiz.nivel;
            }
            else {
                auxInsere(raiz.getfDir(), no);
            }
        }
        super.compara();
        if(no.valor <= raiz.valor) {
            if(raiz.getfEsq() == null) {
                raiz.setfEsq(no);
                no.setPai(raiz);
                no.nivel = raiz.nivel;
            }
            else {
                auxInsere(raiz.getfEsq(), no);                
            }
        }
    }
    
    /**
     * Método que verifica se as condições da árvore AA foram violadas ou não. Se há alguma violação, podem ser
     * chamadas as operações skew e split.
     * 
     * @param no - nó que terá suas propriedades verificadas.
     */  
    public void verifica_propriedades(NoAA no) {
        super.compara();
        if(no != null) {
            NoAA fEsq = no.getfEsq();
            NoAA fDir = no.getfDir();
            super.copia();
            super.copia();
        
            super.compara();
            if(fEsq != null) {
                if(no.getfEsq().nivel == no.nivel) { // Filho à esquerda possui mesmo nível que pai, chama skew.
                   NoAA skew = skew(no);

                }
            }
            super.compara();
            if(fDir != null && fDir.getfDir() != null) {
                if(no.getfDir().getfDir().nivel == no.nivel) { // Nó neto à direita possui mesmo nível que nó avô, chama split.
                   NoAA split = split(no);

                }
            }
        }
        
    }
    
    /** 
     * Método que realiza uma rotação à direita.
     * Essa operação resolve violações na regra 2). Utiliza-se skew para eliminar um caso de um link horizontal
     * à esquerda, que não é permitido.
     * 
     * @param no - nó que passará pela operação skew.
     * @return nó que é a nova raiz da sub-árvore.
     */
    public NoAA skew (NoAA no) {
        super.compara();
        if(no != null && no.getfEsq() != null) {
            NoAA filho = no.getfEsq();
            NoAA pai = no.getPai();
            super.copia();
            super.copia();
            if(no.getfEsq().nivel == no.nivel) { // Filho à esquerda possui mesmo nível que pai.
                super.compara();
                if(raiz == no) {
                 filho.setPai(null);
                 raiz = filho;
                 super.copia();
                }
                else {
                   super.compara();
                   if(pai.getfEsq() == no) {
                      pai.setfEsq(filho);
                   }
                   else {
                      pai.setfDir(filho);
                   }
                
                   filho.setPai(pai);
                }
            
                no.setfEsq(filho.getfDir());
                filho.setfDir(no);
                no.setPai(filho);
                super.compara();
                if(no.getfEsq() != null) {
                    no.getfEsq().setPai(no);
                }
                
                return filho;
            }        
        }
        
        return no;
    }
    
    /**
     * Método que realiza uma rotação à esquerda e incrementa o nível do nó que estiver no meio (filho à direita de 'no').
     * Essa operação resolve violações na regra 4). Utiliza-se split para eliminar um caso de links horizontais à direita
     * consecutivos, que não é permitido.
     * 
     * @param no - nó que passará pela operação split.
     * @return nó que é a nova raiz da sub-árvore.
     */
    public NoAA split(NoAA no) {
        super.compara();
        if(no != null){
            NoAA filho = no.getfDir();
            NoAA pai = no.getPai();
            super.copia();
            super.copia();
        
            super.compara();
            if(filho != null && filho.getfDir() != null) {
                if(no.nivel == filho.getfDir().nivel) { // Nó neto à direita possui mesmo nível que nó avô.
                    super.compara();
                    if(raiz == no) { 
                      filho.setPai(null);
                      raiz = filho;
                      super.copia();
                    }
                    else {
                        super.compara();
                        if(pai.getfEsq() == no) {
                           pai.setfEsq(filho);
                        }
                        else {
                            pai.setfDir(filho);
                        }
                    
                        filho.setPai(pai);
                    }
            
                    no.setfDir(filho.getfEsq());
                    filho.setfEsq(no);
                    no.setPai(filho);
                    super.compara();
                    if(no.getfDir() != null) {
                        no.getfDir().setPai(no);
                    }
                    filho.nivel++; // Incrementa nível do nó do meio, que é o filho à direita de 'no'.
                
                    return filho;
                }
            }
        }
        
        return no;
    }
    
    @Override
    public NoAA busca (int valor) {
        return auxBusca(raiz, valor);
    }
    
    // Método que realiza busca por um valor em uma árvore/sub-árvore de raiz 'no'. Mesmo método usado em outras árvores.
    public NoAA auxBusca(NoAA no, int valor) {
        super.compara();
        if(no == null) {
            return null;
        }
        else {
            super.compara();
            if(no.getValor() == valor) {
                return no;
            }
            else {
                super.compara();
                if(no.getfDir() == null && no.getfEsq() == null) {
                    return null;
                }
                else {
                    super.compara();
                    if(valor <= no.getValor()) {
                        return auxBusca(no.getfEsq(), valor); // Busca valor na sub-árvore à esquerda.
                    }
                    else {
                        return auxBusca(no.getfDir(), valor); // Busca valor na sub-árvore à direita.
                    }
                }
            }
        }
    }

    @Override
    public void remover(int valor) {
        NoAA no = busca(valor); // Busca na árvore o valor que vai ser removido.
        
        super.compara();
        if(no != null) {
            NoAA pai = no.getPai();
            NoAA fesq = no.getfEsq();
            NoAA fdir = no.getfDir();
            super.copia();
            super.copia();
            super.copia();
            if(no.getfDir() == null && no.getfEsq() == null) { // Nó é folha. Basta remover o nó.
                super.compara();
                if(pai != null) {
                    if(pai.getfDir() == no) {
                       pai.setfDir(null);
                    }
                    else {
                       pai.setfEsq(null);
                    }
                }
                else { // 'no' é raiz.
                    raiz = null;
                }
            }
            else {
                if(no.getfEsq() == null && no.getfDir() != null) { // Nó tem um filho. Coloca filho à direita no lugar.
                    super.compara();
                    if(pai != null) {
                       if(pai.getfDir() == no) {
                           pai.setfDir(fdir);
                       }
                       else {
                           pai.setfEsq(fdir);
                       }
                       fdir.setPai(pai);
                    }
                    else { // 'no' é raiz.
                        raiz = fdir;
                        super.copia();
                        fdir.setPai(null);
                    }
                }
                else { // Nó interno.
                    super.compara();
                    
                    NoAA maiorEsq = no.getfEsq();
                    super.copia();
                    super.compara();
                    while(maiorEsq.getfDir() != null) {
                        maiorEsq = maiorEsq.getfDir();
                    }
                    int aux = maiorEsq.valor;
                    remover(maiorEsq.valor);
                    no.valor = aux;
                }
            }
            
            // Rebalancear a árvore.
            rebalancear(pai);
            }
    }
    
    /** Método que reestrutura a árvore depois de uma remoção.
     *  @param no - nó a partir do qual a reestruturação será feita.
     */
    public void rebalancear(NoAA no) {
       
        NoAA noRaiz = no;
        super.copia();
        for(NoAA noaux = no; noaux != null; noaux = noaux.pai) { // Decrementa o nível dos nós para que 
                                                                 // as condições da árvore sejam satisfeitas.
            super.compara();
            if(noaux.getfDir() == null || noaux.getfEsq() == null) {
                if(noaux.nivel > 1) {
                noaux.nivel--;
                }
                super.compara();
                if(noaux.getfDir() != null && noaux.getfDir().nivel > noaux.nivel) {
                  noaux.getfDir().nivel--;
                }
            }    
            else { 
               if((noaux.getfEsq().nivel < noaux.nivel - 1) || (noaux.getfDir().nivel < noaux.nivel - 1)) {
                   noaux.nivel--;
                   if(noaux.getfDir().nivel > noaux.nivel) {
                      noaux.getfDir().nivel--;
                    }
                }
               else {
                   if(noaux.getfDir().nivel > noaux.nivel) {
                      noaux.getfDir().nivel--;
                      if(noaux.getfDir().nivel > noaux.nivel) {
                         noaux.getfDir().nivel--;
                      }
                    }
                }
            }
            
            noRaiz = noaux;
            super.copia();
            super.compara();
            if(noaux.getfEsq() != null && noaux.getfEsq().nivel == noaux.nivel) {
                break;
            }
            super.compara();
            if(noaux.getfDir().getfDir() != null && noaux.getfDir().getfDir().nivel == noaux.nivel) {
                break;
            }
            
        }
            
            // Após o decremento do nível dos nós, são realizadas operações de skew e split para reorganizar a árvore.
            noRaiz = skew(noRaiz);
            super.compara();
            if(noRaiz != null) {
                noRaiz.setfDir(skew(noRaiz.getfDir()));
                noRaiz.getfDir().setfDir(skew(noRaiz.getfDir().getfDir()));
            }
            noRaiz = split(noRaiz);
            if(noRaiz != null) {
                noRaiz.setfDir(split(noRaiz.getfDir()));
            }
    }
    
    public void imprime() {
        printArvore(raiz, 0);
    }
    
    private void printArvore(NoAA raiz, int level){
        if(raiz==null)
             return;
        printArvore(raiz.fDir, level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            System.out.println("|-------"+raiz.getValor()+" "+raiz.nivel);
        }
        else
            System.out.println(raiz.getValor()+" "+raiz.nivel);
        printArvore(raiz.fEsq, level+1);
    }
}
