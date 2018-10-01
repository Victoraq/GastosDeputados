package TabelaHash;
import GastoDeputados.Deputado;
import java.util.ArrayList;

/**
 * Classe que implementa o algoritmo de tratamento de colisao Encadeamento Separado.
 */
public class ESeparado extends THash{
    private ArrayList[] tabelaESeparado; // Estrutura de lista utilizada no algoritmo.
    
    /**
     * Construtor da classe ESeparado.
     * @param tam - tamanho da tabela
     */
    public ESeparado(int tam){
        super(tam);
        this.tabelaESeparado = new ArrayList[this.m];
    }
    
    /**
     * Metodo que faz a insercao de objetos na tabela.
     * @param dep - objeto da classe Deputado a ser inserido na tabela.
     */
    public void inserir(Deputado dep) {
        int k;
        
        try{
            k = dep.getDeputy_id();
        } catch(Exception ex) {
            // Evita a inserção de valor null.
            // Se acontecer, imprime a mensagem e retorna.
            System.out.println(ex.getMessage());
            System.out.println(dep);
            return;
        } 
        
        int pos = super.hashMultiplicacao(k); // Funcao hash de multiplicacao
        
        // Se a posição estiver com o valor null cria-se uma lista e insere o valor na lista
        if (this.tabelaESeparado[pos] == null) {
            ArrayList<Integer> list = new ArrayList<>();
            this.tabelaESeparado[pos] = list;
            this.tabelaESeparado[pos].add(k); // Adicionando o valor na lista
        }else{
            this.numComparacoes += this.tabelaESeparado[pos].size();
            this.tabelaESeparado[pos].add(k);
        }
    }
    
    /**
    * Método que imprime a tabela na tela, sobrepondo com o descrito na superclasse.
    */
    @Override
    public void imprime() {
        for (int i = 0; i < this.m; i++){
            if (this.tabelaESeparado[i] != null) {
                for (int j = 0; j < this.tabelaESeparado[i].size(); j++) { 		      
                    System.out.print(this.tabelaESeparado[i].get(j)+" -> "); 		
                }
                System.out.println();
            } else {
                System.out.println(this.tabelaESeparado[i]);
            }
        }
    }
}