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
     * @param coluna - campo que irá servir de chave para a inserção
     */
    public void inserir(Deputado dep, String coluna) {
        if (null != coluna) // Inserindo por id do deputado se a sua coluna for passada ou for uma string vazia.
        switch (coluna) {
            case "deputy_id":
            case " ":
                inserirAux(dep, dep.getDeputy_id());
                break;
            case "receipt_value":
                inserirAux(dep, (int) dep.getReceipt_value());
                break;
            case "political_party":
                inserirAux(dep, super.stringToNum(dep.getPolitical_party()));
                break;
            default:
                break;
        }
    }
    
    /**
     * Metodo que faz a insercao de objetos na tabela.
     * @param dep - objeto da classe Deputado a ser inserido na tabela.
     * @param k - valor chave para inserção
     */
    private void inserirAux(Deputado dep, Integer k) {
        
        if (k == null) {
                System.out.println("Valor nulo");
                return;
            }  
        
        int pos = super.hashMultiplicacao(k); // Funcao hash de multiplicacao
        
        // Se a posição estiver com o valor null cria-se uma lista e insere o valor na lista
        if (this.tabelaESeparado[pos] == null) {
            ArrayList<Integer> list = new ArrayList<>();
            this.tabelaESeparado[pos] = list;
            this.tabelaESeparado[pos].add(dep); // Adicionando o valor na lista
        }else{
            this.numComparacoes += this.tabelaESeparado[pos].size();
            this.tabelaESeparado[pos].add(dep);
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