package Ordenacao;

public class QuickSort extends Ordenacao{
    
    public QuickSort() {}
    
    public void ordenar(Integer[] array){
        this.auxQuickSort(array, 0, array.length -1);
    }
    
    private void auxQuickSort(Integer[] array, int inicio, int fim) {
        int pivo, i, j;
        
        pivo = array[inicio];
        int ind_pivo = inicio;
        i = inicio;
        j = fim;
        
        if (inicio >= fim) return;
        
        while(i < j) {
            // Percorre o vetor enquanto i < pivo e j > pivo
            // ind_pivo != i para evitar casos em que os valores são iguais
            while (array[i] <= pivo && ind_pivo != i)
                i++;
            while (array[j] >= pivo && ind_pivo != j)
                j--;
            
            this.troca(array, i, j);
            
            // Atualizando o indice do pivo
            if (ind_pivo == i)
                ind_pivo = j;
            else
                ind_pivo = i;
        }
        
        if (inicio < j) {
            this.auxQuickSort(array, inicio, j-1);
        }
        if (i < fim) {
            this.auxQuickSort(array, i+1, fim);
        }
    }
}
