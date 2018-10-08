package Ordenacao;

/**
 * Classe que implementa o algoritmo de ordenacao ShellSort.
 */
public class ShellSort extends Ordenacao{
    
    public ShellSort() {}
    
    /**
    * Metodo que ordena em ordem crescente um vetor.
    * @param array - vetor a ser ordenado.
    */
    public void ordenar(Integer[] array){
        int i , j , value;
        int pos = 1;
        long inicio, fim;

        inicio = System.currentTimeMillis();
        
        while(pos < array.length) {
            pos = 3*pos+1;
        }
        while ( pos > 1) {
            pos /= 3; // divide pos por 3
            for(i = pos; i < array.length; i++) {
                value = array[i];
                j = i;
                while (j >= pos && value < array[j - pos]) {
                    super.compara();
                    array[j] = array[j - pos];
                    super.copia();
                    j = j - pos;
                }
                super.compara();
                array[j] = value;
                super.copia();
            }
        }
        
        fim = System.currentTimeMillis();
        super.setDuracao(fim - inicio);
    }
}
