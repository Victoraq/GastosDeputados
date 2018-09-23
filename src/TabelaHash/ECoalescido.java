package TabelaHash;

import GastoDeputados.Deputado;
import java.util.ArrayList;

/**
 * Classe que implementa o algoritmo de tratamento de colisao Encadeamento Coalescido.
 */
public class ECoalescido extends THash{
    private Integer indiceECoalescido[]; // vetor que define os indices dos valores na tabela.
    
    /**
     * Construtor da classe ECoalescido.
     * @param tam - tamanho da tabela
     */
    public ECoalescido(int tam){
        super(tam);
        this.indiceECoalescido = new Integer[this.m];
        this.inicializarTabelaIndices();
    }
    
    /**
     * Inicializa os valores da tabela de indice com a FLAG de posicao vazia (-2).
     */
    private void inicializarTabelaIndices(){
        for(int i=0; i<this.indiceECoalescido.length; i++){
            this.indiceECoalescido[i] = -2;
        }
    } 
    
    /**
     * Metodo que faz a insercao de objetos na tabela.
     * @param dep - objeto da classe Deputado a ser inserido na tabela.
     */
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
            
            int pos = super.hashDivisao(k); // Funcao de hash de divisao

            if (this.indiceECoalescido[pos] == -2) {
                // Se estiver vazio: insere o valor e a FLAG, e atualiza o contador de insercoes;
                this.tabela[pos] = dep;
                this.indiceECoalescido[pos] = -1;
            } else {
                // Caso contario, verifica se teve mais numeros colididos ou não.
                int novaPosicao; // atributo auxiliar para a nova posicao
		int posAnterior = pos; // auxiliar para colocar a posicao anterior, caso ja tenha colidido
                
		if(this.indiceECoalescido[pos] != -1){
                    // Caso seja (-1), e o final daquela posicao. Marca a nova posicao a partir do final da tabela.
                    novaPosicao = this.m-1; // Indica a possivel nova posicao
                    this.numComparacoes++; // A medida que for ocorrendo comparacoes, aumenta uma.
		}else{ 
                    // Caso contrario, percorrer a cadeia e verificar até chegar a FLAG (-1).
                    // E comecar a verificar a partir daquela posicao para cima.
                    int posAtual = pos; // Auxiliar da posicao atual
		    int posProximo; // Auxiliar da proxima posicao
                    
                    while(this.indiceECoalescido[posAtual] != -1){
                        posAnterior = posAtual;
			posProximo = this.indiceECoalescido[posAtual];
                        posAtual = posProximo;
			this.numComparacoes++;
                    }
                    novaPosicao = posAtual-1; // Indica a possivel nova posicao
                    
		}
                
                // Percorre de baixo para cima ate achar uma posicao vazia.
		while(this.indiceECoalescido[novaPosicao] != -2){
                    novaPosicao--;
                    this.numComparacoes++;
		}
                
                // Na posicao vazia, atualiza os valores e os indices
                this.indiceECoalescido[posAnterior] = novaPosicao;
		this.indiceECoalescido[novaPosicao] = -1;
		this.tabela[novaPosicao] = dep;
            }
            
            numPosPreenchidas++;  // Atualizar posicoes ocupadas
        }
    }
}
