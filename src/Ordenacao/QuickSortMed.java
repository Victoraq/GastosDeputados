
package Ordenacao;

public class QuickSortMed extends Ordenacao {
  private Integer [] vetor;
  public QuickSortMed(Integer vetor[]){
      this.vetor=vetor;

  }
  private int pivoMed (int i, int f){ //Serve como auxiliar para ordenar.Só encontra o pivo, recendo o indice do inicio e do final
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
          pivo=mediana+i;// O indice i serve para localizar qual partição estou me referindo
      }
     return pivo;
  }
  private void troca(int i, int t){ //troca os valores de lugar
      int aux=vetor[i];
      vetor[i]=vetor[t];
      vetor[t]=aux;
  }
  private void auxOrdenar(int i,int f,int pivo){ // ordena os tres valores adquiridos do meu vetor
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
      if (i >= f) return;// verifica quando possuir uma partição de tamanho 1
      if(k>3){ //Condição mínima para conseguirmos adquirir tres valores do vetor, não sendo escolher se o vetor for de tamanho 3 ou menor 
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
      if(k==3){ // tratando os casos quando o vetor for de tamanho igual a tres
        auxOrdenar(i,f,pivo); //Portanto, posso pular a escolha do pivo direto para a ordenação. Isso ocorre também para k=2.
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
