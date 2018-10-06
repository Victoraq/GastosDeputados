/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GastoDeputados;

/**
 *
 * @author victor
 */
public class TagGastos {
    private String tag;
    private float gastos;

    public TagGastos(String tag, float gastos) {
        this.tag = tag;
        this.gastos = gastos;
    }
    

    public String getTag() {
        return tag;
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }
    
}
