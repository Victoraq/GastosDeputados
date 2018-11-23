/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arvores;

/**
 *
 * @author gabic
 */
public class Splay {
    private NoSplay raiz;
    public Splay(){
        raiz=null;
    }
    public Splay(NoSplay no){
        raiz=no;
    }
    public void inserir(int valor){
        if(raiz==null){// caso da arvore vazia
            NoSplay no=new NoSplay(valor);
            raiz=no;
            return ;
        }
         NoSplay no =raiz;
         NoSplay ant = null;
         while (no != null)//até que não tenha que seja folha
         {
             ant=no;//recebe a raiz a primeira vez
             //o ant sempre estará atrás do no
             if (valor > no.getValor())//coloca os maiores valores a direita raiz ou do no e os menores a esquerda
                 no = no.getFdir();
             else
                 no = no.getFesq();
         }
         no = new NoSplay(valor);
         no.setPai(ant);
         if (ant == null)
             raiz = no;
         else if (valor > ant.getValor())// conserta os ponteiros do anterior(pai)
             ant.setFdir(no);
         else
             ant.setFesq(no);
         Splay(no); //joga pra colocar esse valor encima-- zig e zag       
    }
   
    public void zig(NoSplay no, NoSplay pai)// rotaciona o no da esquerda pra direita
     {

         if (pai.getPai() != null)
         {
             if (pai == pai.getPai().getFesq())
                 pai.getPai().setFesq(no);
             else 
                 pai.getPai().setFdir(no);
         }
         if (no.getFdir() != null)
             no.getFdir().setPai(pai);
 
         no.setPai(pai.getPai());
         pai.setPai(no);
         pai.setFesq(no.getFesq());
         no.setFdir(pai);
     }
    public void zag(NoSplay f, NoSplay p)// roda o nó da direita pra esquerda, invertendo os valores
     {//coloca o valor inserido encima
         if (p.getPai() != null)
         {
             if (p == p.getPai().getFesq())
                 p.getPai().setFesq(f);
             else
                 p.getPai().setFdir(f);
         }
         if (f.getFesq() != null)
             f.getFesq().setPai(p);
         f.setPai(p.getPai());
         p.setPai(f);
         p.setFdir(f.getFesq());
         f.setFesq(p);
     } 
    
     private void Splay(NoSplay no)
     {
         while (no.pai != null)//roda até o valor que acabamos de inserir ficar na raiz
         {
             NoSplay pai = no.getPai();
             NoSplay avo = pai.getPai();
             if (avo == null)// filho da raiz... caso base
             {
                 if (no == pai.getFesq())
                     zig(no, pai);
                 else
                     zag(no, pai);                 
             } 
             else
             {

                 if (no == pai.getFesq())//vê se o no está na esquerda pra dos casos: zig-zig e zig-zag
                 {

                     if (pai == avo.getFesq())//2 caso: filho a esquerda de um filho a esquerda
                     {//só vai entrar nessa condição a primeira vez, quando acabamos de inserir o no
                         zig(pai, avo);
                         zig(no, pai);
                     }
                     else // o pai está a direita do avo
                     {//1 caso: filho a esquerda de um pai a direita
                         zig(no, no.getPai());
                         zag(no, no.getPai());
                     }
                 }
                 else 
                 {
                     if (pai ==avo.getFesq())
                     {//1 caso: no a direita de um pai a esquerda
                         zag(no, no.getPai());
                         zig(no, no.getPai());
                     } 
                     else //2 caso: no a direita de um pai a direita
                     {//só vai entrar nessa condição a primeira vez, quando acabamos de inserir o no
                         zag(pai, avo);
                         zag(no, pai);
                     }
                 }
             }
         }
         raiz = no;
     }


     public void remove(int ele)
     {
         NoSplay no = procuraNo(ele);
         remove(no);
     }
     private void remove(NoSplay no)
     {
         if (no == null)
             return;
 
         Splay(no);// vai separar o no procurado
         if( (no.getFesq() != null) && (no.getFdir() !=null))
         { //caso tenha filho na direita e na esquerda
             NoSplay auxMin = no.getFesq();
             while(auxMin.getFdir()!=null)
                 auxMin = auxMin.getFdir();
 
             auxMin.setFdir(no.getFdir());
             no.getFdir().setPai(auxMin);
             if(auxMin.getFesq()!=null)
                 no.getFesq().setFdir(auxMin.getFesq());
             no.getFesq().setPai(auxMin);
             raiz = auxMin;
         }
         else if (no.getFdir() != null)
         {
             no.getFdir().setPai(null);
             raiz = no.getFdir();
         } 
         else if( no.getFesq() !=null)
         {
             no.getFesq().setPai(null);
             raiz = no.getFesq();
         }
         else
         {
             raiz = null;
         }
         no.setPai(null);
         no.setFesq(null);
         no.setFdir(null);
         no = null;
     } 
    
     
     public boolean pesquisa(int val)
     {
         return procuraNo(val) != null;
     }
 
     private NoSplay procuraNo(int ele)
     {
    	 NoSplay prox = null;// o prox vai ser o no mais próximo do no procurado.Caso não encontre o no, o prox é jogado pra cima
         //o prox vai estar atrás de z
         NoSplay z = raiz;
         while (z != null)
         {//z vai até encontrar o no ou até a folha
             prox = z;
             if (ele > z.getValor())
                 z = z.getFdir();
             else if (ele < z.getValor())
                 z = z.getFesq();
             else if(ele == z.getValor()) {
                 Splay(z);
                 return z;
             }
 
         }
         if(prox != null)
         {
             Splay(prox);
             return null;
         }
         return null;
     }
  
     
     
    private void printArvore(NoSplay raiz, int level){
        if(raiz==null)
             return;
        printArvore(raiz.fdir, level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            System.out.println("|-------"+raiz.getValor()+" ");
        }
        else
            System.out.println(raiz.getValor()+" ");
        printArvore(raiz.fesq, level+1);
    }     
    public void imprime() {
        printArvore(raiz, 0);
    }    
    
    
}
