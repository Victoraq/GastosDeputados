package TabelaHash;

import GastoDeputados.Deputado;
import java.util.ArrayList;

public class SQuadratica extends THash{
    
    public SQuadratica(int tam){
        super(tam);
    }
    
    public void inserir(Deputado dep) {
        int k;
        
        // Verifica se ha local vazio na tabela, caso contrario não realiza a insercao.
        if(this.numPosPreenchidas >= this.tam){
            System.out.println("Tabela cheia!");
            System.out.println(dep);
            return;
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

            int pos = super.hashDivisao(k); // utiliza a hash de divisao

            if (this.tabela[pos] == null) {
                // Se estiver vazio, apenas insere e atualiza o contador de insercoes
                this.tabela[pos] = dep;
                this.numPosPreenchidas++;
            } else {
                // Caso contario, localiza-se a proxima posicao vazia
                this.numColisoes++; // A medida que for ocorrendo colisoes, aumenta uma. 
                while(this.tabela[pos] != null){
                    this.numColisoes++;
                    pos++;
                    
                    // Caso chegue na ultima posicao, reinicia a contagem;
                    if(pos >= this.tam){
                        pos = 0;
                    }
                }
                this.tabela[pos] = dep; // Caso encontrada posicao livre, inserir valor na tabela
                numPosPreenchidas++;  // Atualizar posicoes posOcupadas
            }
        }
    }
}
