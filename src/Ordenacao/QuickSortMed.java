/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksortmed;

public class QuickSortMed extends Ordenacao {
  private Integer [] vetor;
  public QuickSortMed(Integer vetor[]){
      this.vetor=vetor;

  }
  private int pivoMed (int i, int f){
      int mediana;
      int pivo;
      int k=f-i+1;
      if((k)%2==0){
          mediana=(k)/2;
          int med2=mediana+1;
          pivo=((med2+mediana)/2)+i;
      }
      else{
          mediana=k/2;
          pivo=mediana+i;
      }
     return pivo;
  }
  private void troca(int i, int t){
      int aux=vetor[i];
      vetor[i]=vetor[t];
      vetor[t]=aux;
  }
  private void auxOrdenar(int i,int f,int pivo){
      if(vetor[pivo]<vetor[i]){
          troca(pivo,i);
      }
      if(vetor[i]>vetor[f]){
          troca(i,f);
      }
     if(vetor[pivo]>vetor[f]){
         troca(pivo,f);
     }
  }


  public void ordenar(int inicio,int fim){
      int i=inicio;
      int f=fim;
      int pivo=pivoMed(i,f);
      auxOrdenar(i,f,pivo);
      int k=f-i+1;
      if (i >= f) return;
      if(k>3){
          while(i<f){
                while(vetor[i]<vetor[pivo]&& i!=pivo){
                    i++;                  
                     
                }
                if(vetor[i]>vetor[pivo]){
                    troca(i,pivo);
                    pivo=i;
                }
                while(vetor[f]>vetor[pivo]&& f!=pivo){                   
                    f--;          
                }   
                if(vetor[f]<vetor[pivo]){              
                    troca(f,pivo);
                    pivo=f;
                }
          }
            
       
      } 
      if(k==3){
        auxOrdenar(i,f,pivo);
      }
      if(k==2){
          if(vetor[i]>vetor[f]){
              troca(i,f);
          }
      }

   
      if(inicio<f){ 

          ordenar(inicio,f-1);
      }
      if(i<fim){
          ordenar(i+1,fim);
      }
      
  }

  
}
