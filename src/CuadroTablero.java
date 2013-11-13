/**
 * @author (Luis Ballinas, Gabriel Ramos Olvera, Fernando Gomez, Francisco Barros) 
 * @version (9/13/2013)
 */
import javax.swing.*;
import java.awt.*;

public class CuadroTablero extends JPanel {
    private Color colorFondo;

    public CuadroTablero(Color color) {
        super();
        this.colorFondo = color;
        setBackground(color);
        setOpaque(true);
        setLayout(new BorderLayout(0, 0));
    }
    
    public void agregarFicha(String imagenFicha) {
        Ficha ficha = new Ficha(imagenFicha);
        add(ficha);
    }
}