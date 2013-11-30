import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author (Luis Ballinas, Gabriel Ramos Olvera, Fernando Gomez, Francisco
 * Barros)
 * @version (9/13/2013)
 */
public class Ficha extends JLabel {

    private ImageIcon fondoFicha;
    private Posicion posicion;
    public final static String FICHA_A = "src/atomic/images/fichas_A.png";
    public final static String FICHA_B = "src/atomic/images/fichas_B.png";

    public Ficha(String imagenFicha, Posicion posicion) {
        super(new ImageIcon(imagenFicha));
        this.posicion = posicion;

        this.fondoFicha = new ImageIcon(imagenFicha);
    }

//  Métodos de instancia
    public void mover(int posicionX, int posicionY) {

    }
    
    public void comerFicha(int posicionX, int posicionY) {

    }

//  Getters & Setters
    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
}