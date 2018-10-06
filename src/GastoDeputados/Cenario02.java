
package GastoDeputados;

import Ordenacao.QuickSortInsercao;
import Ordenacao.QuickSort;
import Ordenacao.QuickSortMed;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author gabic
 */
public class Cenario02 {
    private Deputado [] dep;
    private Integer []vet ;
    int n;
    private long []duracao;
    private double []comparacao;
    private double [] copia;
    public  Cenario02(){
        dep=new LeituraDados("C:\\Users\\gabic\\Downloads\\Engenharia computacional\\ED2\\GastosDeputados\\deputies_dataset_tratado.csv",500000).getDeputados();
    }
    public Integer[] getVet() {
        return vet;
    }

    public long []getDuracao() {
        return duracao;
    }

    public double []getComparacao() {
        return comparacao;
    }

    public double []getCopia() {
        return copia;
    }
    
    public void preencheVetor_random(){
        Random rand = new Random(System.currentTimeMillis());
        
        for(int i=0; i<n;i++){

            int ind=Math.abs(rand.nextInt())%(dep.length-1);
            try{
               vet[i]=dep[ind].getDeputy_id(); 
            } catch(NullPointerException e) {
                System.out.println(e.getMessage());
                return;
            }  
            System.out.println("vet[i]:"+vet[i]);
        }      
    }
    public void sementeDif( int n){
        this.n=n;
        vet=new Integer [n];
        for(int i=1;i<=5;i++){
            System.out.println("vai imprmir semente");
            preencheVetor_random();
            System.out.println("passou random");
            testaQuickSort();
            System.out.println("passou testa quicksort");
            int m=10;
            for(int j=0;j<2;j++){
               testaQuickSortInsertion(m);               
               m=100; 
            }

            testaQuickSortMed();
              
        }
    }
    public void testaQuickSort(){
        Integer [] vetor=vet;
        System.out.println("vetor:"+vetor[0]);
        QuickSort quick=new QuickSort();
        quick.ordenar(vetor);
 

       
        duracao[0]=quick.getDuracao();
        System.out.println("Duração: "+duracao[0]);        
        comparacao[0]=quick.getNumComparacoes();
        System.out.println("Comparacao: "+comparacao[0]);
        copia[0]=quick.getNumCopias();
        System.out.println("Copias: "+copia[0]);
    }
    public void testaQuickSortInsertion(int m){
        Integer [] vetor=vet;
        QuickSortInsercao quick=new QuickSortInsercao();
        try {
            quick.ordenar(vetor,m);
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
            return;
        }    
        try{
            duracao[1]=quick.getDuracao();
        }catch(NullPointerException e) {
            System.out.println(e.getMessage());
            return;
        }    
        comparacao[1]=quick.getNumComparacoes();
        copia[1]=quick.getNumCopias();        
    }
    public void testaQuickSortMed(){
        Integer [] vetor=vet;
        QuickSortMed quick=new QuickSortMed(vetor);
        try{
            quick.ordenar(0,n-1);
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
            return;
        }   
        System.out.println("duração: "+ quick.getDuracao());
        try{
            duracao[2]=quick.getDuracao();
        }catch(NullPointerException e) {
            System.out.println(e.getMessage());
            return;
        }    
        comparacao[2]=quick.getNumComparacoes();
        copia[2]=quick.getNumCopias();        
        //k=3 e k=5
        

    }

    public static void main(String[] args) throws IOException {
        File saida1_int = new File("C:\\Users\\gabic\\Downloads\\Engenharia computacional\\ED2\\GastosDeputados\\saidaNormal_int.csv");
        File saida2_int = new File("C:\\Users\\gabic\\Downloads\\Engenharia computacional\\ED2\\GastosDeputados\\saidaMed_int.csv");
        File saida3_int = new File("C:\\Users\\gabic\\Downloads\\Engenharia computacional\\ED2\\GastosDeputados\\saidaInser_int.csv");
        FileWriter fw1_int,fw2_int,fw3_int;
        try {
            
            fw1_int = new FileWriter(saida1_int);
            fw2_int = new FileWriter(saida2_int);
            fw3_int = new FileWriter(saida3_int);
 
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }        

        fw1_int.write("tam,duracao,num_comparacao,num_copia\n");
        fw1_int.flush();
        fw2_int.write("tam,duracao,num_comparacao,num_copia\n");
        fw2_int.flush();
        fw3_int.write("tam,duracao,num_comparacao,num_copia\n");
        fw3_int.flush();
       Cenario02 c=new Cenario02();
       int []n={1000,5000,10000,50000,100000,500000};
       for(int i=0;i<6;i++){
           c.sementeDif(n[i]);
           double []vetComp=c.getComparacao();
           long []vetDura=c.getDuracao();
           double []vetCopias=c.getCopia();
           for(int aux=0;aux<3;aux++){
               String result;
               try{
                  
                   result = Integer.toString(n[i])+ ',' +vetDura[aux] + ',' + vetComp[aux]+ ',' + vetCopias[aux] + '\n';
               } catch(NullPointerException e) {
                    System.out.println(e.getMessage());
                    return;
               }    
                if(aux==0){
                    fw1_int.write(result);
                }
                if(aux==1){
                    fw2_int.write(result);
                }
                else{
                    fw3_int.write(result);                    
                }
           }           
 
       } 
   
       
       
                
      
    }
    
}





