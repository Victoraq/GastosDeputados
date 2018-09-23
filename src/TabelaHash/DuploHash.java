package TabelaHash;
import GastoDeputados.Deputado;

public class DuploHash extends THash{
    
    public DuploHash(int tam) {
        super(tam);
    }
    
    private int hashDuplo(int k, int colisoes) {
        int hk1 = super.hashDivisao(k); // Usa a hash da divisao comum como primeira funcao
        int hk2 = 1 + (k % (this.m - 1)); // Usa a hash de divisao com m' como segunda funcao
        
        // Importante que hk2 e m sejam primos entre si para que a funcao seja uniforme
        int pos = (hk1 + colisoes*hk2) % this.m;
        return pos;
    }
    
    public void inserir(Deputado dep) {
        int k;
        
        // Verifica se ha local vazio na tabela, caso contrario não realiza a insercao.
        if(this.numPosPreenchidas >= this.m){
            System.out.println("Tabela cheia!");
            System.out.println(dep);
        }else{
            try{
                k = dep.deputy_id;
            }catch(Exception ex) {
                // Evita a inserção de valor null.
                // Se acontecer, imprime a mensagem e retorna.
                System.out.println(ex.getMessage());
                System.out.println(dep);
                return;
            }    
            
            int numColisoes = 0; // Auxiliar para contagem de colisoes 
            int pos = this.hashDuplo(k, numColisoes); // Funcao de hash duplo

            if (this.tabela[pos] == null) {
                // Se estiver vazio, apenas insere e atualiza o contador de insercoes
                this.tabela[pos] = dep;
            } else {
                // Caso contario, calcula-se a proxima posicao a medida que ocorre as colisoes 
                while(this.tabela[pos] != null){
                    this.numComparacoes++; // A medida que for ocorrendo comparacoes, aumenta uma.
                    numColisoes++;
                    pos = this.hashDuplo(k, numColisoes);
                }
                
                this.tabela[pos] = dep; // Caso encontrada posicao livre, inserir valor na tabela
            }
            
            numPosPreenchidas++;  // Atualizar posicoes posOcupadas
        }
    }
}
