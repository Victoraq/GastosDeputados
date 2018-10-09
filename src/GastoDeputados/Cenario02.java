
package GastoDeputados;

import Ordenacao.QuickSortInsercao;
import Ordenacao.QuickSort;
import Ordenacao.QuickSortMed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author gabic
 */
public class Cenario02 {
    private Deputado [] dep;
    private Integer []vet ;//será usado para armazenar os índices do vetor de deputados
    int n;// tamanho do vetor
    private long []duracao;// duracao, comparacao e copia irão armazenar esses dados para cada um dos quicks
    private double []comparacao;
    private double [] copia;
    public  Cenario02(){
        dep=new LeituraDados("/home/victor/Documentos/ED2/Java/data/deputies_dataset_tratado.csv",800000).getDeputados();
        duracao=new long [3];
        comparacao=new double [3];
        copia=new double [3];
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
               vet[i]=dep[ind].getDeputy_id(); //pega valores de indices aleatoriamente
            } catch(NullPointerException e) {
                System.out.println(e.getMessage());
                return;
            }  
        
        }      
    }
    public void sementeDif( int n){
        this.n=n;
        vet=new Integer [n];
        for(int i=1;i<=5;i++){

            preencheVetor_random();
            testaQuickSort();

            int m=10;// os valores de m foram informados na descrição do trabalho
            for(int j=0;j<2;j++){
               testaQuickSortInsertion(m);   

               m=100; 
            }

            //testaQuickSortMed();
        }
    }
    public void testaQuickSort(){// testar o QuickSort e armazenar os dados na posição 0 do vetor
        Integer []vetor1=new Integer[n];
        vetor1=vet.clone();
        QuickSort quick=new QuickSort();
        quick.ordenar(vetor1);
        duracao[0]=quick.getDuracao();       
        comparacao[0]=quick.getNumComparacoes();
        System.out.println("Copias quick: "+quick.getNumCopias());
        copia[0]=quick.getNumCopias();
    }
    public void testaQuickSortInsertion(int m){//testar o QuickSortInsertion e armazenar os dados na posição 1 do vetor
        Integer [] vetor2=new Integer[n];
        vetor2=vet.clone();

        QuickSortInsercao quick=new QuickSortInsercao();
        
        quick.ordenar(vetor2,m);
        duracao[1]=quick.getDuracao(); 
        comparacao[1]=quick.getNumComparacoes();
        System.out.println("Copias insert: "+quick.getNumCopias());

        copia[1]=quick.getNumCopias();        
    }
    public void testaQuickSortMed(){// testar o QuickSortMed e armazenar os dados na posição 2 do vetor 
        Integer [] vetor3=new Integer[n];
        vetor3=vet.clone();
        QuickSortMed quick=new QuickSortMed();
        quick.ordenar(vetor3);
        duracao[2]=quick.getDuracao();  
        comparacao[2]=quick.getNumComparacoes();
        System.out.println("Copias med: "+quick.getNumCopias());

        copia[2]=quick.getNumCopias();        
        

    }

    public static void main(String[] args) throws IOException {
        File saida1_int = new File("/home/victor/Documentos/ED2/Java/data/saidaNormal_int.csv");
        File saida3_int = new File("/home/victor/Documentos/ED2/Java/data/saidaMed_int.csv");
        File saida2_int = new File("/home/victor/Documentos/ED2/Java/data/saidaInser_int.csv");
        FileWriter fw1_int,fw2_int,fw3_int;//arquivos de saída dos dados
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
                     fw1_int.flush();
                }
                if(aux==1){
                    fw2_int.write(result);
                     fw2_int.flush();
                }
                else{
                    fw3_int.write(result);                    
                    fw3_int.flush();
                }
           }           
 
       } 
   
       
       
                
      
    }
    
}





