package TabelaHash;

import GastoDeputados.Deputado;
import java.util.ArrayList;

public class ESeparado extends THash{
    protected ArrayList[] tabela_encad_sep;
    
    public ESeparado(int tam){
        super(tam);
        this.tabela_encad_sep = new ArrayList[this.m];
    }
    
    public void inserir(Deputado dep) {
        int k;
        
        //Evitando leitura de valores null
        try{
            k = dep.deputy_id;}
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(dep);
            return;
        } 
        
        int pos = super.hash_divisao(k);
        
        if (this.tabela[pos] == null) {
            this.tabela[pos] = dep;
        } else {
            this.auxInsercao(k);
            if (pos >= 0)
                this.tabela[pos] = dep;
        }
    }
    
    private void auxInsercao(int k) {
        // Posição em que sera adicionado o valor
        int pos = super.hash_divisao(k);
        
        // Se a posição está vazia criamos uma lista na mesma
        if (this.tabela_encad_sep[pos] == null) {
            ArrayList<Integer> list = new ArrayList<>();
            this.tabela_encad_sep[pos] = list;
        } 
        
        // Adicionando o valor na lista
        this.tabela_encad_sep[pos].add(k);
        this.count++; // Contagem de colisões global
    }
    
    public void imprime() {
        for (int i = 0; i < this.m; i++){
            if (this.tabela_encad_sep[i] != null) {
                for (int j = 0; j < this.tabela_encad_sep[i].size(); j++) { 		      
                    System.out.print(this.tabela_encad_sep[i].get(j)+" -> "); 		
                }
                System.out.println();
            } else {
                System.out.println(this.tabela_encad_sep[i]);
            }
        }
    }
}