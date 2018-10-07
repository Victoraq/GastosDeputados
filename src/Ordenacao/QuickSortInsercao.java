package Ordenacao;

public class QuickSortInsercao extends Ordenacao {

    public QuickSortInsercao() {}    
    
    // Algoritmo de inserção para ordenar partições do vetor de tamanho menor/igual a m.
    public void insertionSort(Integer[] array, int inicio, int fim) {
        for(int i = inicio + 1; i <= fim; i++) {
            int pivo = array[i];
            int j = i - 1;
            while(j >= inicio && array[j] > pivo) {
                super.compara();
                array[j + 1] = array[j];
                j--;
                super.copia();
            }
            
            super.compara();
            
            array[j + 1] = pivo;
            super.copia();
        }
    }
        
    public int particao (Integer[] array, int inicio, int fim, int pivo) {
        
        int i = inicio;
        int j = fim - 1; // Desconsidera-se, por enquanto, a posição em que o pivô se encontra.
        
        while(true) {
            while(array[i] <= pivo && i <= fim - 1) { // Percorre-se o vetor da esquerda para a direita.
                super.compara();
                i++;
            }
            super.compara();
            while(array[j] >= pivo && j > inicio) { // Percorre-se o vetor da direita para a esquerda.
                super.compara();
                j--;
            }
            super.compara();
            if(i >= j) {
                // Quando i e j se cruzam, todas as trocas necessárias foram feitas e existe um
                // subarray à esquerda com valores menores que o pivô 
                // e outro à direita com valores maiores que o pivô.
                break;
            }
            else {
                // Efetua-se a troca de dois valores do array.
                this.troca(array, i, j);
            }
        }
        
        // Troca-se o valor da posição i, que corresponde à primeira posição do subarray direito,
        // com a última posição do array, que corresponde ao pivô.
        this.troca(array, i, fim);
        // Retorna-se a nova posição do pivô.
        return i;
    }
    
    public void ordenar(Integer[] array, int m){
        long inicio, fim;
        
        inicio = System.currentTimeMillis();

        this.quickSortIns(array, 0, array.length -1, m);
        
        fim = System.currentTimeMillis();

        super.setDuracao(fim - inicio);
    }
           
    public void quickSortIns (Integer[] array, int inicio, int fim, int m) {
        
        int tam = (fim - inicio) + 1;
        
        if (tam <= 1){ // Se tam for igual a 1, o vetor já está ordenado.
            return;
        }
        else {
            if(tam <= m) { // Utiliza-se o algoritmo de inserção se tam é menor/igual a m.
                insertionSort(array, inicio, fim);
            }
            else { // Utiliza-se o algoritmo QuickSort para ordenar o vetor se tam é maior que m.            
                int pivo = array[fim]; // Escolhe-se o elemento mais à direita do vetor como pivô.
                int part = particao(array, inicio, fim, pivo);
                // Utiliza-se o algoritmo QuickSort para cada subarray, excluindo-se da ordenação
                // o pivô, que já se encontra em sua posição correta.
                quickSortIns(array, inicio, part - 1, m);
                quickSortIns(array, part + 1, fim, m);
            }
        }
    }
    
    
}
