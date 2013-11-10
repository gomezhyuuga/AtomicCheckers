
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

/*
 * Fichas.
 * 
 * @author (Fernando Gomez, Francisco Barros, Gabriel Ramos, Luis Ballinas) 
 * @version (9/13/2013)
 */
public class Ficha extends JPanel {

    private Color colorFicha;
    public final static String FICHA_A = "/Users/gomezhyuuga/Projects/java/Atomic Checkers/src/atomic/images/fichas_A.png";
    public final static String FICHA_B = "/Users/gomezhyuuga/Projects/java/Atomic Checkers/src/atomic/images/fichas_B.png";

    public Ficha(String imagenFicha) {
        super();

        setOpaque(false);
        setLayout(new BorderLayout(0, 0));

        JLabel fondo = new JLabel(new ImageIcon(imagenFicha));
        add(fondo);
    }

//  Métodos de instancia
    public void mover(int x, int y) {

    }

//  Getters & Setters
}
