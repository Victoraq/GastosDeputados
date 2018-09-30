package TabelaHash;
import GastoDeputados.Deputado;
import java.util.Arrays;

/**
 * Classe que implementa funcoes gerais dos algoritmos de
 * tratamento de colisão de tabela hash.
 */
public class THash {

    /**
     * tam - tamanho original dos dados
     * m - tamanho da tabela.
     * numComparacoes - quantidade de comparacoes.
     * numPosPreenchidas - quantidade de posicoes preenchidas na tabela.
     * tabela[] - vetor dos valores desejados.
     */
    protected int tam, m, numComparacoes, numPosPreenchidas;
    protected Object tabela[];
    
    /**
    * Construtor da classe THash.
    * @param tam - Tamanho da tabela
    */
    public THash(int tam) {
        this.tam = tam;
        //Tamanho da tabela será o primeiro primo maior que this.tam
        // A fim de reduzir o número de colisões
        this.m = this.encontraPrimo(this.tam);
        System.out.println("Primo encontrado: "+this.m);

        this.numPosPreenchidas = 0;
        this.numComparacoes = 0;
        this.tabela = new Deputado[this.m];
    }
    
    /**
     * Método que retorna o numero de comparacoes da tabela em todas as insercoes.
     * @return Quantidade de comparacoes das insercoes. 
    */
    public int getNumComparacoes() {
        return this.numComparacoes;
    }
    
    /**
     * Método que retorna o tamanho inicial da tabela.
     * @return tamanho inicial da tabela. 
    */
    public int getTam() {
        return tam;
    }
    
    /**
     * Método que retorna o primeiro primo maior que o parametro passado
     * Algoritmo modificado de :
     *      https://www.geeksforgeeks.org/sieve-sundaram-print-primes-smaller-n/
     * @param x - valor determinante para encontrar primo
     * @return Primeiro primo maior que x
     */
    private int encontraPrimo(int x) {
        System.out.println("Buscando primo");
        // In general Sieve of Sundaram, produces 
        // primes smaller than (2*x + 2) for a number
        // given number x. Since we want primes 
        // smaller than n, we reduce n to half
        int nNew = ((x * 2 + 3) - 2) / 2;

        // This array is used to separate numbers of the 
        // form i+j+2ij from others where 1 <= i <= j
        boolean marked[] = new boolean[nNew + 1];

        // Initalize all elements as not marked
        Arrays.fill(marked, false);

        // Main logic of Sundaram. Mark all numbers of the
        // form i + j + 2ij as true where 1 <= i <= j
        int primo = x;
        loop:
        for (int i = 1; i <= nNew; i++) 
            for (int j = i; (i + j + 2 * i * j) <= nNew; j++){
                try { 
                    marked[i + j + 2 * i * j] = true;
                } catch(Exception e) {
//                    System.out.println(e.getMessage());
                    break loop;
                }
            }
        for (int k=x/2; k < nNew+1; k++) {
            if (marked[k] == false && k * 2 + 1 > this.tam) {
                primo = k * 2 + 1;
                break;
            }
        }
        
        
        return primo;
    }
    
    /**
     * Método que retorna o valor do hash da divisao para as classes do pacote.
     * @param k - valor da chave.
     * @return Valor da funcao hash de acordo com o parametro k.
    */
    protected int hashDivisao(int k) {
        int pos = k % this.m;
        return pos;
    }
    
    /**
     * Método que retorna o valor do hash da multiplicacao para as classes do pacote.
     * @param k - valor da chave.
     * @return Valor da funcao hash de acordo com o parametro k.
    */
    protected int hashMultiplicacao(int k) {
        double AUREA = 0.61803399;
        
        return (int) Math.floor(m * (k*AUREA - Math.floor(k*AUREA)));
    }
    
    /**
     * Método que imprime a tabela na tela.
     */
    public void imprime() {
        for (int i = 0; i < this.m; i++)
            System.out.println(this.tabela[i]);
    }    
}