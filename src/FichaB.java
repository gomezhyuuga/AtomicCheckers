/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gomezhyuuga
 */
public class FichaB extends Ficha {

    public FichaB(Posicion posicion) {
        super(Ficha.FICHA_B, posicion);
    }

    public FichaB(Posicion posicion, boolean reina) {
        super(Ficha.FICHA_B_REINA, posicion);
        setReina(true);
    }
}
