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
public class Ficha extends JPanel {

    private ImageIcon fondoFicha;
    private int posicionX;
    private int posicionY;
    public final static String FICHA_A = "src/atomic/images/fichas_A.png";
    public final static String FICHA_B = "src/atomic/images/fichas_B.png";

    public Ficha(String imagenFicha) {
        super();

        setOpaque(false);
        setLayout(new BorderLayout(0, 0));
        this.fondoFicha = new ImageIcon(imagenFicha);

        JLabel ficha = new JLabel(this.fondoFicha);
        add(ficha);
    }

//  Métodos de instancia
    public void mover(int posicionX, int posicionY) {

    }
    
    public void comerFicha(int posicionX, int posicionY) {

    }

//  Getters & Setters
    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
}
