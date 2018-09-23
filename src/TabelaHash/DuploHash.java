package TabelaHash;

import GastoDeputados.Deputado;
import java.util.ArrayList;

/**
 *
 * @author Carlos Alexandre
 */
public class DuploHash extends THash{
    
    /**
     *
     * @param tam tamalho da Tabela
     */
    public DuploHash(int tam) {
        super(tam);
    }
    
    /**
     * @param dep - objeto da classe deputado
     */
    public void inserir(Deputado dep) {
        int k;
        
        //Evita leitura de valor null
        try{
            k = dep.deputy_id;}
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(dep);
            return;
        } 
        
        // Primeira funcao hash eh o da divisao
        int pos = super.hash_divisao(k);
        
        if (this.tabela[pos] == null) {
            this.tabela[pos] = dep;
        } else {
            this.auxInsercao(k, 1);
            if (pos >= 0)
                this.tabela[pos] = dep;
        }
    }
    
    /**
     * @param k - valor da chave
     * @param numColisoes - quantidade de colisoes
     */
    private int auxInsercao(int k, int numColisoes) {
        
        int hash1 = super.hash_divisao(k);
        int hash2 = super.hash_multiplicacao(k);
        int pos = super.hash_divisao(hash1 + numColisoes * hash2);
        this.count++; // Contagem de colisões global

        while (pos < this.m && numColisoes < this.m) {
            if (this.tabela[pos] == null) {
                return pos;
            }
            
            numColisoes++;
            this.count++;
            pos = this.hash_divisao(hash1 + numColisoes * hash2);
        }
        return -1;
    }
}
