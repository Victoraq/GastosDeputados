package TabelaHash;

import GastoDeputados.Deputado;
import java.util.ArrayList;

public class SLinear extends THash{
    
    public SLinear(int tam){
        super(tam);
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
            
        int pos = this.hash_divisao(k);
        
        if (this.tabela[pos] == null) {
            this.tabela[pos] = dep;
        } else {
            this.auxInsercao(k, 1);
            if (pos >= 0)
                this.tabela[pos] = dep;
        }
    }
    
    private int auxInsercao(int k, int colisoes) {
        
        int pos = super.hash_divisao(k + colisoes);
        this.count++; // Contagem de colisões global

        while (pos < this.m & colisoes < this.m) {
            if (this.tabela[pos] == null) {
                return pos;
            }
            
            colisoes++;
            this.count++;
            pos = super.hash_divisao(k+colisoes);
        }
        return -1;
    }
}
