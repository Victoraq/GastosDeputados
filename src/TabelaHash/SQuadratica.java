package TabelaHash;
import GastoDeputados.Deputado;

public class SQuadratica extends THash{
    
    public SQuadratica(int tam){
        super(tam);
    }
    
    private int hashQuadratica(int k, int colisoes) {
        int hk = super.hashDivisao(k); // Usa a hash de divisao
        double auxPos = (hk + (0.5*colisoes) + (0.5*colisoes*colisoes)) % this.m;
        int pos = (int) auxPos;
        return pos;
    }
    
    public void inserir(Deputado dep) {
        int k;
        
        // Verifica se ha local vazio na tabela, caso contrario não realiza a insercao.
        if(this.numPosPreenchidas >= this.tam){
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
            int pos = this.hashQuadratica(k, numColisoes); // Funcao de hash de sondagem linear

            if (this.tabela[pos] == null) {
                // Se estiver vazio, apenas insere e atualiza o contador de insercoes
                this.tabela[pos] = dep;
                this.numPosPreenchidas++;
            } else {
                // Caso contario, calcula-se a proxima posicao a medida que ocorre as colisoes
                this.numComparacoes++; // A medida que for ocorrendo comparacoes, aumenta uma. 
                while(this.tabela[pos] != null){
                    this.numComparacoes++;
                    numColisoes++;
                    pos = this.hashQuadratica(k, numColisoes);
                }
                
                this.tabela[pos] = dep; // Caso encontrada posicao livre, inserir valor na tabela
                numPosPreenchidas++;  // Atualizar posicoes posOcupadas
            }
        }
    }
}
