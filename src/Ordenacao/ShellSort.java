package Ordenacao;

public class ShellSort extends Ordenacao{
    
    public ShellSort() {}
    
    public void ordenar(Integer[] array){
        int i , j , value;
        int pos = 1;
        while(pos < array.length) {
            pos = 3*pos+1;
        }
        while ( pos > 1) {
            pos /= 3; // divide gap por 3
            for(i = pos; i < array.length; i++) {
                value = array[i];
                j = i;
                while (j >= pos && value < array[j - pos]) {
                    array[j] = array[j - pos];
                    j = j - pos;
                }
                array[j] = value;
            }
        }
    }
}
