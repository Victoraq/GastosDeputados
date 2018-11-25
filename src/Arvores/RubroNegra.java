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
public class RubroNegra extends Arvore {
    private NoRubroNegra raiz;
    
    public RubroNegra() {
    }

    public RubroNegra(NoRubroNegra raiz) {
        this.raiz = raiz;
        this.raiz.setCor('p');
    }
    
    @Override
    public void inserir(int valor) {
        
        // Caso 1
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
        
      /*  if (no == null || (no.fesq == null && no.fdir == null))
            return null;
        
        if (no.valor == valor) {
            return no;
        } else if (no.valor < valor) {
            auxBusca(no.fdir, valor);
        } else {
            auxBusca(no.fesq, valor);
        }
        
        return null;*/
        
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
        NoRubroNegra no = busca(valor);
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
           
           if(no.getFdir() == null && no.getFesq() == null) {
               u = null;
           }
           else {
               if(no.getFdir() != null && no.getFesq() != null) {
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
                   if(no.getFdir() != null) {
                       u = no.getFdir();
                       super.copia();
                   }
                   else {
                       u = no.getFesq();
                       super.copia();
                   }
               }
           }
           
           if(u == null) {
               super.compara();
               if(raiz == no) {
                   raiz = null;
               }
               else {
                   if(no.cor == 'p') {
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
           
           
           if(no.getFdir() == null || no.getFesq() == null) {
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
                   
                   if(u.cor == 'v' || no.cor == 'v') {
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
        
        if(s == null) {
            eliminaDoubleBlack(p);
        }
        else {
            if(s.getCor() == 'v') {
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
            else {
                if(corSobrinhoDir == 'v' || corSobrinhoEsq == 'v') {
                    if(s.getFesq() != null && s.getFesq().getCor() == 'v') {
                        if(p.getFesq() == s) {
                            s.getFesq().cor = s.cor;
                            s.cor = p.cor;
                            rotacaoDir(p);
                        }
                        else {
                            s.getFesq().cor = p.cor;
                            rotacaoRL(p);
                        }
                    }
                    else {
                        super.compara();
                        if(p.getFesq() == s) {
                            s.getFdir().cor = p.cor;
                            rotacaoLR(p);
                        }
                        else {
                            s.getFdir().cor = s.cor;
                            s.cor = p.cor;
                            rotacaoEsq(p);
                        }
                    }
                    p.cor = 'p';
                }
                else {
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
