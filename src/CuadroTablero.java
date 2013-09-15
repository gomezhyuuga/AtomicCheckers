/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gomezhyuuga
 */
import javax.swing.*;
import java.awt.*;

public class CuadroTablero extends JPanel {
    private String ruta_img_fondo;

    public CuadroTablero(String ruta_img_fondo) {
        super();
        this.ruta_img_fondo = ruta_img_fondo;
    }

    @Override
    public void paint(Graphics g) {
        Dimension tamanio = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource( this.ruta_img_fondo ));
        g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
        setOpaque(false);
        super.paintComponent(g);
    }
}
