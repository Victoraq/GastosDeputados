
package GastoDeputados;

//import Ordenacao.QuickSortInsercao;
import Ordenacao.QuickSort;
import Ordenacao.QuickSortMed;
import java.util.Random;

/**
 *
 * @author gabic
 */
public class Cenario02 {
    Deputado [] dep;
    Integer []vet ;
    int n;
    public void preencheVetor_random(){
        Random rand = new Random(System.currentTimeMillis());
        for(int i=0; i<n;i++){
            vet[i]=dep[rand.nextInt()].deputy_id;
        }      
    }
    public void sementeDif( int n){
        this.n=n;
        for(int i=1;i<=5;i++){
            preencheVetor_random();
            testaQuick();  
            //analisa();
        }
    }
    public void testaQuick(){
        Integer [] vet1=vet;
        Integer []vet2=vet;
        Integer []vet3=vet;
        QuickSort quick1=new QuickSort();
        quick1.ordenar(vet1);
        System.out.println("QuickSort");
       // imprime(vet1);
       // QuickSortInsercao quick2=new QuickSortInsercao();
        System.out.println("QuickSortInsercao:");
        QuickSortMed quick3=new QuickSortMed(vet3);
        quick3.ordenar(0,n-1);
        System.out.println("QuickSortMed:");
    }
    public void imprime(){
//        for(int i=0;i<vetor.length-1;i++){
//            System.out.println("vet indice"+" "+i+": "+ vetor[i]);
//        }
//        System.out.println("----------------------");
    }
    public void analisa(){
        
    }
    public static void main(String[] args) {
       Cenario02 c=new Cenario02();
       int []n={1000,5000,10000,50000,100000,500000};
       for(int i=0;i<6;i++){
           c.sementeDif(n[i]);
       } 
    }
    
}





