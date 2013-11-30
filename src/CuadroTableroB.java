
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class CuadroTableroB extends CuadroTablero {

    public CuadroTableroB(Posicion posicion) {
        super(Color.ORANGE, posicion);
        this.colorFondo = Color.ORANGE;
    }

    @Override
    public void restablecerColorFondo() {
        this.setBackground(Color.ORANGE);
    }
    
}
