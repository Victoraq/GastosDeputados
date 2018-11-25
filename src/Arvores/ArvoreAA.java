/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arvores;

/**
 *
 * @author Laura
 */
public class ArvoreAA extends Arvore {
    private NoAA raiz;

    public ArvoreAA() {
    }

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
        
        verifica_propriedades(no);
                
        for(NoAA noaux = no; noaux != null; noaux = noaux.pai) {
            verifica_propriedades(noaux);
            noaux.calculaNivel(noaux);
        }
        
    }
    
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
    
    
    public void verifica_propriedades(NoAA no) {
        super.compara();
        if(no != null) {
            NoAA fEsq = no.getfEsq();
            NoAA fDir = no.getfDir();
            super.copia();
            super.copia();
        
            super.compara();
            if(fEsq != null) {
                if(no.getfEsq().nivel == no.nivel) {
                   NoAA skew = skew(no);
                   super.copia();
                }
            }
            super.compara();
            if(fDir != null && fDir.getfDir() != null) {
                if(no.getfDir().getfDir().nivel == no.nivel) {
                   NoAA split = split(no);
                   super.copia();
                }
            }
        }
        
    }
    
    
    public NoAA skew (NoAA no) {
        super.compara();
        if(no != null && no.getfEsq() != null) {
            NoAA filho = no.getfEsq();
            NoAA pai = no.getPai();
            super.copia();
            super.copia();
            if(no.getfEsq().nivel == no.nivel) {
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
    
    
    public NoAA split(NoAA no) {
        NoAA filho = no.getfDir();
        NoAA pai = no.getPai();
        super.copia();
        super.copia();
        
        super.compara();
        if(filho != null && filho.getfDir() != null) {
            if(no.nivel == filho.getfDir().nivel) {
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
                filho.nivel++;
                
                return filho;
            }
        }
        
        return no;
    }
    
    @Override
    public NoAA busca (int valor) {
        return auxBusca(raiz, valor);
    }
    
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
                        return auxBusca(no.getfEsq(), valor);
                    }
                    else {
                        return auxBusca(no.getfDir(), valor);
                    }
                }
            }
        }
    }

    @Override
    public void remover(int valor) {
        NoAA no = busca(valor);
        
        super.compara();
        if(no != null) {
            NoAA pai = no.getPai();
            NoAA fesq = no.getfEsq();
            NoAA fdir = no.getfDir();
            super.copia();
            super.copia();
            super.copia();
            if(no.getfDir() == null && no.getfEsq() == null) {
                super.compara();
                if(pai.getfDir() == no) {
                    pai.setfDir(null);
                }
                else {
                    pai.setfEsq(null);
                }
            }
            else {
                if(no.getfEsq() == null && no.getfDir() != null) {
                    super.compara();
                    if(pai.getfDir() == no) {
                        pai.setfDir(fdir);
                        fdir.setPai(pai);
                    }
                    else {
                        pai.setfEsq(fdir);
                        fdir.setPai(pai);
                    }
                }
                else {
                    super.compara();
                    if(pai.getfDir() == no) {
                        pai.setfDir(no.getfEsq());
                        fesq.setPai(pai);
                    }
                    else {
                        pai.setfEsq(no.getfEsq());
                        fesq.setPai(pai);
                    }
                }
            }
            
            rebalancear(pai);
            }
    }
    
    public void rebalancear(NoAA no) {
       
        NoAA noRaiz = no;
        super.copia();
        for(NoAA noaux = no; noaux != null; noaux = noaux.pai) {    
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

            noRaiz = skew(noRaiz);
            super.copia();
            noRaiz.setfDir(skew(noRaiz.getfDir()));
            noRaiz.getfDir().setfDir(skew(noRaiz.getfDir().getfDir()));
            noRaiz = split(noRaiz);
            super.copia();
            noRaiz.setfDir(split(noRaiz.getfDir()));
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
