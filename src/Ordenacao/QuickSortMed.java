
package Ordenacao;

import GastoDeputados.TagGastos;


/**
 * Classe que implementa o algoritmo de ordenacao QuickSort mediana.
 */
public class QuickSortMed extends Ordenacao {
    
    public QuickSortMed() {
        
    }
    
  private int pivoMed (int i, int f){ //Serve como auxiliar para ordenar.Só encontra o pivo, recendo o indice do inicio e do final
      int mediana;
      int pivo;
      int k=f-i+1;
      super.compara();
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

  private void auxOrdenar(Integer[] vetor,int i,int f,int pivo){ // ordena os tres valores adquiridos do meu vetor de inteiros
      super.compara();
      if(vetor[pivo]<vetor[i]){
          this.troca(vetor,pivo,i);
      }
      super.compara();
      if(vetor[i]>vetor[f]){
          this.troca(vetor,i,f);
      }
     super.compara();
     if(vetor[pivo]>vetor[f]){
         this.troca(vetor,pivo,f);
     }

  }
  
  private void auxOrdenar(TagGastos[] vetor,int i,int f,int pivo){ // ordena os tres valores adquiridos do meu vetor de gastos
      super.compara();
      if(vetor[pivo].getGastos()<vetor[i].getGastos()){
          this.troca(vetor,pivo,i);
      }
      super.compara();
      if(vetor[i].getGastos()>vetor[f].getGastos()){
          this.troca(vetor,i,f);
      }
     super.compara();
     if(vetor[pivo].getGastos()>vetor[f].getGastos()){
         this.troca(vetor,pivo,f);
     }

  }


    public void ordenarAjudando(Integer[] vetor, int inicio,int fim){
        int i=inicio;

        int f=fim;
        int pivo=pivoMed(i,f);

        int k=f-i+1;
        super.compara();
        if (i >= f){
            return ;
        }// verifica quando possuir uma partição de tamanho 1
        super.compara();
        if(k>3){ //Condição mínima para conseguirmos adquirir tres valores do vetor, não sendo escolher se o vetor for de tamanho 3 ou menor 
          auxOrdenar(vetor, i,f,pivo);

            while(i<f){

                  super.compara();

                  while(vetor[i]<=vetor[pivo]&& i!=pivo&&i<=fim){

                      i++;                  
                      super.compara();
                  }
                  super.compara(); 

                  if(vetor[i]>vetor[pivo]){
                      this.troca(vetor,i,pivo);
                      pivo=i;
                  }             
                  while(vetor[f]>=vetor[pivo]&& f!=pivo&&f>=inicio){     
                      f--;   
                      super.compara();
                  }   
                  super.compara();

                  if(vetor[f]<vetor[pivo]){   
                      this.troca(vetor,f,pivo);
                      pivo=f;
                  }
            }


        } 

        if(k==3){ // tratando os casos quando o vetor for de tamanho igual a tres
          auxOrdenar(vetor, i,f,pivo); //Portanto, posso pular a escolha do pivo direto para a ordenação. Isso ocorre também para k=2.
        }
        if(k==2){
            if(vetor[i]>vetor[f]){
                this.troca(vetor,i,f);
            }
        }


        if(inicio<f){ 
            ordenarAjudando(vetor,inicio,f-1);
        }
        if(i<fim){
            ordenarAjudando(vetor,i+1,fim);
        }

    }
  
    public void ordenarAjudando(TagGastos[] vetor, int inicio,int fim){
        int i=inicio;

        int f=fim;
        int pivo=pivoMed(i,f);

        int k=f-i+1;
        super.compara();
        if (i >= f){
            return ;
        }// verifica quando possuir uma partição de tamanho 1
        super.compara();
        if(k>3){ //Condição mínima para conseguirmos adquirir tres valores do vetor, não sendo escolher se o vetor for de tamanho 3 ou menor 
          auxOrdenar(vetor, i,f,pivo);

            while(i<f){

                  super.compara();

                  while(vetor[i].getGastos()<=vetor[pivo].getGastos()&& i!=pivo&&i<=fim){

                      i++;                  
                      super.compara();
                  }
                  super.compara(); 

                  if(vetor[i].getGastos()>vetor[pivo].getGastos()){
                      this.troca(vetor,i,pivo);
                      pivo=i;
                  }             
                  while(vetor[f].getGastos()>=vetor[pivo].getGastos()&& f!=pivo&&f>=inicio){     
                      f--;   
                      super.compara();
                  }   
                  super.compara();

                  if(vetor[f].getGastos()<vetor[pivo].getGastos()){   
                      this.troca(vetor,f,pivo);
                      pivo=f;
                  }
            }


        } 

        if(k==3){ // tratando os casos quando o vetor for de tamanho igual a tres
          auxOrdenar(vetor, i,f,pivo); //Portanto, posso pular a escolha do pivo direto para a ordenação. Isso ocorre também para k=2.
        }
        if(k==2){
            if(vetor[i].getGastos()>vetor[f].getGastos()){
                this.troca(vetor,i,f);
            }
        }


        if(inicio<f){ 
            ordenarAjudando(vetor,inicio,f-1);
        }
        if(i<fim){
            ordenarAjudando(vetor,i+1,fim);
        }

    }  
  
    /**
    * Metodo que ordena em ordem crescente um vetor de inteiros.
    * @param vetor - vetor de inteiros a ser ordenado.
    */
    public void ordenar(Integer[] vetor){
        long inicio, fim;
        inicio = System.currentTimeMillis();
        ordenarAjudando(vetor,0,vetor.length-1);
        fim = System.currentTimeMillis();
        super.setDuracao(fim - inicio);
        
    }
    
    /**
    * Metodo que ordena em ordem crescente um vetor de gastos.
    * @param vetor - vetor de gastos a ser ordenado.
    */
    public void ordenar(TagGastos[] vetor){
        long inicio, fim;
        inicio = System.currentTimeMillis();
        ordenarAjudando(vetor,0,vetor.length-1);
        fim = System.currentTimeMillis();
        super.setDuracao(fim - inicio);
        
    }
  
}