/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenacao;


/**
 *
 * @author victor
 */
public class Ordenacao {

    public Ordenacao() {}
    
    public void troca(Integer[] array, int index1, int index2) {
        int aux;
        aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
    }
}
