package TabelaHash;

import GastoDeputados.Deputado;
import java.util.ArrayList;

public class ECoalescido extends THash{
    protected Integer indice_encad_c[];
    
    public ECoalescido(int tam){
        super(tam);
        this.indice_encad_c = new Integer[this.m];
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
            this.auxInsercao(k, 1);
            if (pos >= 0)
                this.tabela[pos] = dep;
        }
    }
    
    private int auxInsercao(int k, int colisoes) {

        int pos_inicial = super.hash_divisao(k);
        int pos = super.hash_divisao(k+colisoes);
        this.count++; // Contagem de colisões global

        while (pos < this.m && colisoes < this.m) {
            // Se encontrar uma posição vazia na tabela é salvo tal indice 
            // na posição anterior para servir de referencia para percorrer posteriormente
            if (this.tabela[pos] == null) {
                
                this.indice_encad_c[pos_inicial] = pos;
                
                return pos;
            }
            
            colisoes++;
            this.count++;
            // Determinando a proxima posição a ser checada com a função hash
            // Se tivesse porão seria na ultima posição vazia
            pos_inicial = pos;
            pos = super.hash_divisao(k+colisoes);
        }
        return -1;
    }
}
