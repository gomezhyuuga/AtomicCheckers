
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class CuadroTableroA extends CuadroTablero {

    public CuadroTableroA(Posicion posicion) {
        super(new Color(242, 242, 242), posicion);
        this.colorFondo = new Color(242, 242, 242);
    }

    @Override
    public void restablecerColorFondo() {
        this.setBackground(new Color(242, 242, 242));
    }
    
}
