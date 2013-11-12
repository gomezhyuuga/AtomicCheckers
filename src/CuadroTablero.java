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

    public CuadroTablero(Color color) {
        super();
        this.ruta_img_fondo = ruta_img_fondo;
        
        setBackground(color);
        setOpaque(true);
        setLayout(new BorderLayout(0, 0));
    }
    
    public void agregarFicha(String imagenFicha) {
        Ficha ficha = new Ficha(imagenFicha);
        add(ficha);
    }
}