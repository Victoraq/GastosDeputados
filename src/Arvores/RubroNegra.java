
package Arvores;

/**
 * Classe que implementa a Árvore Rubro Negra.
 */
public class RubroNegra extends Arvore {
    private NoRubroNegra raiz;
    
    /**
     * Construtor vazio da Árvore.
     */
    public RubroNegra() {
    }
    
    /**
     * Construtor da Árvore que recebe um nó raiz.
     * @param raiz - nó que irá ser a raiz da árvore
     */
    public RubroNegra(NoRubroNegra raiz) {
        this.raiz = raiz;
        this.raiz.setCor('p');
    }
    
    @Override
    public void inserir(int valor) {
        
        // Caso 1
        super.compara();
        if (this.raiz == null) { // Se a arvore estiver vazia é inserido na raiz.
           super.copia();
           NoRubroNegra no = new NoRubroNegra(valor);
           no.setCor('p');
           this.raiz = no;
           super.copia();
           return;
        }
        
        NoRubroNegra no = new NoRubroNegra(valor);
        
        auxInsere(raiz, no); // Chamando função auxiliar recursiva.
        
        this.verifica_propriedades(no); // verificando se ocorreu tudo bem com a inserção
        
        // Verificando as propriedades para os nós superiores
        for (NoRubroNegra noaux = no; noaux != null && noaux.pai != null; noaux = noaux.pai) 
            this.verifica_propriedades(noaux);
        
        
    }
    
    private void auxInsere(NoRubroNegra raiz, NoRubroNegra no) {
        if (raiz.valor > no.valor) { // Se valor for menor ou igual, insere a esquerda
            if (raiz.fesq == null) { // Se não tiver nenhum nó a equerda já é inserido
                super.copia();
                super.copia();
                raiz.fesq = no;
                no.pai = raiz;
            } else
                auxInsere(raiz.fesq, no); // Senão é chamado a recursão para a arvore a esquerda
        }
        if (raiz.valor <= no.valor) { // Se valor for maior, insere a direita
            if (raiz.fdir == null) { // Se não tiver nenhum nó a direita já é inserido
                super.copia();
                super.copia();
                raiz.fdir = no;
                no.pai = raiz;
            } else
                auxInsere(raiz.fdir, no); // Senão é chamado a recursão para a arvore a direita
        }
    }
    
    /**
     * Verifica propriedades da rubro negra a partir de um nó.
     * @param no - nó que irá ser verificado
     */
    private void verifica_propriedades(NoRubroNegra no) {
        // Variaveis para analise das propriedades
        NoRubroNegra pai = no.pai;
        NoRubroNegra avo = pai.pai;
        NoRubroNegra tio;
        NoRubroNegra irmao = null;
        
        // Executa medidas se quebra requisito de todo nó vermelho não tem filho vermelho
        if (avo != null && pai.cor == 'v' && no.cor == 'v') {
            super.copia();
            super.compara();
            if (avo.fdir == pai) tio = avo.fesq;
            else tio = avo.fdir;
            
            super.compara();
            if (tio != null && tio.cor == 'v') { // Caso 2
                if (avo != this.raiz) avo.cor = 'v';
                tio.cor = 'p';
                pai.cor = 'p';
            } else { //Caso 3
                
                super.compara();
                if (tio == avo.fesq) {
                    super.compara();
                    if (pai.fdir == no) {
                        super.copia();
                        super.copia();
                        this.rotacaoEsq(avo); // rotação a esquerda
                        
                        pai = no.pai;
                        irmao = pai.fesq;
                        
                        pai.cor = 'p';
                        irmao.cor = 'v';
                        
                    } else {
                        this.rotacaoRL(avo); // rotação direita esquerda
                        
                        no.cor = 'p';
                        no.getFesq().cor = 'v';
                    }
                super.compara();
                } else if (tio == avo.fdir) {
                    super.compara();
                    if (pai.fesq == no) {
                        super.copia();
                        super.copia();
                        this.rotacaoDir(avo); // rotação a direita
                        
                        pai = no.pai;
                        irmao = pai.fdir;
                        
                        pai.cor = 'p';
                        irmao.cor = 'v';
                    
                    } else {
                        this.rotacaoLR(avo); // rotação esquerda direita
                        
                        no.cor = 'p';
                        no.getFdir().cor = 'v';
                    } 
                }
            }
        }
    }
    
    @Override
    public NoRubroNegra busca(int valor) {
        return this.auxBusca(this.raiz, valor);
    }
    
    private NoRubroNegra auxBusca(NoRubroNegra no, int valor) {
                
        super.compara();
        if (no == null) {
            return null;
        }
        else{
            super.compara();
            if (no.valor == valor) {
                return no;
            } else if (no.fesq == null && no.fdir == null) {
                return null;
            } else if (no.valor < valor) {
                return auxBusca(no.fdir, valor);
            } else {
                return auxBusca(no.fesq, valor);
            }
        }
        
    }
    
    private void rotacaoEsq(NoRubroNegra x0) {
        NoRubroNegra x1= x0.fdir;
        
        super.copia();
        super.compara();
        if (x0 == raiz) {
            raiz = x1;
            x1.setPai(null);
        } else {
            x1.setPai(x0.getPai());
            if (x0.getPai().getFdir() == x0)
                x0.getPai().setFdir(x1);
            else
                x0.getPai().setFesq(x1);
        }
        
        super.copia();
        x0.setFdir(x1.getFesq());
        if (x0.getFdir()!= null) x0.getFdir().setPai(x0);
        x1.setFesq(x0);
        x0.setPai(x1);
        
    }
    
    private void rotacaoDir(NoRubroNegra x0) {
        NoRubroNegra x1 = x0.fesq;
        
        super.copia();
        super.compara();
        if (x0 == raiz) {
            raiz = x1;
            x1.setPai(null);
        } else {
            x1.setPai(x0.getPai());
            if (x0.getPai().getFdir() == x0)
                x0.getPai().setFdir(x1);
            else
                x0.getPai().setFesq(x1);
        }
        
        super.copia();
        x0.setFesq(x1.getFdir());
        if (x0.getFesq()!= null) x0.getFesq().setPai(x0);
        x1.setFdir(x0);
        x0.setPai(x1);
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

    @Override
    public void remover(int valor) {
        NoRubroNegra no = busca(valor); // Busca na árvore o valor que vai ser removido.
        auxRemover(no);
    }
    
    private void auxRemover(NoRubroNegra no) {        
        super.compara();
        if(no != null) {
           NoRubroNegra p = no.getPai();
           super.copia();
           NoRubroNegra u;
           NoRubroNegra s;
           
           if(p != null) {
               super.compara();
               if(p.getFesq() == no) {
                    s = p.getFdir();
                    super.copia();
                }
                else {
                    s = p.getFesq();
                    super.copia();
                }
           }
           else {
               s = null;
           }
           
           if(no.getFdir() == null && no.getFesq() == null) { // Nó é folha.
               u = null;
           }
           else {
               if(no.getFdir() != null && no.getFesq() != null) { // Nó é interno. 'u' será o maior valor da sub-árvore 
                                                                  // à esquerda.
                   NoRubroNegra maiorEsq = no.getFesq();
                   super.copia();
                   super.compara();
                   while(maiorEsq.getFdir() != null) {
                       maiorEsq = maiorEsq.getFdir();
                       super.copia();
                   }
                   u = maiorEsq;
                   super.copia();
               }
               else {
                   if(no.getFdir() != null) { // Nó tem somente um filho à direita.
                       u = no.getFdir();
                       super.copia();
                   }
                   else { // Nó tem somente um filho à esquerda.
                       u = no.getFesq();
                       super.copia();
                   }
               }
           }
           
           if(u == null) { // 'u' é preto.
               super.compara();
               if(raiz == no) {
                   raiz = null;
               }
               else {
                   if(no.cor == 'p') { // Caso: tanto 'u' quanto 'no' são pretos.
                       eliminaDoubleBlack(no);
                   }
                   else {
                       if(s != null) {
                           s.cor = 'v';
                       }
                   }
                   
                   super.compara();
                   if(p.getFesq() == no) {
                       p.setFesq(null);
                   }
                   else {
                       p.setFdir(null);
                   }
               }
               
               return;
           }
           
           
           if(no.getFdir() == null || no.getFesq() == null) { // 'no' só pussui um filho.
               super.compara();
               if(raiz == no) {
                   no.valor = u.valor;
                   no.setFesq(null);
                   no.setFdir(null);
               }
               else {
                   super.compara();
                   if(p.getFesq() == no) {
                       p.setFesq(u);
                   }
                   else {
                       p.setFdir(u);
                   }
                   u.setPai(p);
                   
                   if(u.cor == 'v' || no.cor == 'v') { // Caso: ou 'u' é vermelho ou 'no' é vermelho.
                       u.cor = 'p';
                   }
                   else {
                       eliminaDoubleBlack(u);
                   }
               }
               
               return;
           }
           
           int aux;
           aux = u.valor;
           u.valor = no.valor;
           no.valor = aux;
           
           auxRemover(u);
        }
    }
    
    // Método que elimina o double black adquirido por um nó durante a remoção, reestruturando, assim, a árvore.
    private void eliminaDoubleBlack(NoRubroNegra no) {
        super.compara();
        if(no == raiz) {
            return;
        }
        
        NoRubroNegra p = no.getPai();
        super.copia();
        NoRubroNegra s;
        char corSobrinhoDir, corSobrinhoEsq;
        
        super.compara();
        if(p.getFesq() == no) {
            s = p.getFdir();
            super.copia();
        }
        else {
            s = p.getFesq();
            super.copia();
        }
        
        if(s != null) {
            if(s.getFesq() != null) {
                corSobrinhoEsq = s.getFesq().getCor();
            }
            else {
                corSobrinhoEsq = 'p';
            }
                    
            if(s.getFdir() != null) {
                corSobrinhoDir = s.getFdir().getCor();
            }
            else {
                corSobrinhoDir = 'p';
            }
        }
        else {
            corSobrinhoDir = 'p';
            corSobrinhoEsq = 'p';
        }
        
        if(s == null) { // 's' é preto.
            eliminaDoubleBlack(p);
        }
        else {
            if(s.getCor() == 'v') { // 's' é vermelho. Realiza-se rotação e recolore-se nós.
                p.cor = 'v';
                s.cor = 'p';
                
                if(p.getFesq() == s) {
                    rotacaoDir(p);
                }
                else {
                    rotacaoEsq(p);
                }
                
                eliminaDoubleBlack(no);
            }
            else { // 's' é preto.
                if(corSobrinhoDir == 'v' || corSobrinhoEsq == 'v') { // Ou um dos filhos de 's' é vermelho, ou ambos o são.
                                                                     // Ocorre(m) rotação(ões).
                    if(s.getFesq() != null && s.getFesq().getCor() == 'v') { // Filho à esquerda de 's' é vermelho.
                        if(p.getFesq() == s) { // 's' é filho à esquerda.
                            s.getFesq().cor = s.cor;
                            s.cor = p.cor;
                            rotacaoDir(p);
                        }
                        else { // 's' é filho à direita.
                            s.getFesq().cor = p.cor;
                            rotacaoRL(p);
                        }
                    }
                    else { // Filho à direita de 's' é vermelho.
                        super.compara();
                        if(p.getFesq() == s) { // 's' é filho à esquerda.
                            s.getFdir().cor = p.cor;
                            rotacaoLR(p);
                        }
                        else { // 's' é filho à direita.
                            s.getFdir().cor = s.cor;
                            s.cor = p.cor;
                            rotacaoEsq(p);
                        }
                    }
                    p.cor = 'p';
                }
                else { // Filhos de 's' são pretos. Ocorre recoloração.
                    s.cor = 'v';
                    if(p.cor == 'p') {
                        eliminaDoubleBlack(p);
                    }
                    else {
                        p.cor = 'p';
                    }
                }
            }
        }
        
    }
    
}
