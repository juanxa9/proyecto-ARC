/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.arc;

/**
 *
 * @author anina
 */
public class DatosPrograma {
    int n,      // numero de clientes
        v,      // numero de vecinos por grupo de trabajo
        s;      // numero de iteraciones de la simulacion

    DatosPrograma(){;}
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }
    
    
}
