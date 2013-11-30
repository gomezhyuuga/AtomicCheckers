
/**
 * @author (Luis Ballinas, Gabriel Ramos Olvera, Fernando Gomez, Francisco
 * Barros)
 * @version (9/13/2013)
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CuadroTablero extends JPanel {

    private Posicion posicion;
    private Color colorFondo;
    private Ficha ficha;

    public CuadroTablero(Color color, Posicion posicion) {
        super();
        this.posicion = posicion;
        this.colorFondo = color;
        setBackground(color);
        setOpaque(true);
        setLayout(new BorderLayout(0, 0));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadroPresionado(evt);
            }
        });
    }
    
    private void cuadroPresionado(java.awt.event.MouseEvent evt) {
        System.out.println("Se presionó el cuadro en:");
        // Determinar en qué posiciones se puede mover
        System.out.println(posicion);
        if (this.ficha == null) {
            System.out.println("NO HAY FICHA");
        } else {
            System.out.println("HAY FICHA EN ESTE CUADRO");
            // Posibles movimientos por defecto (aún sin validar si hay fichas en las posiciones posibles
            // a mover ni si puede comer o no)
            ArrayList<Posicion> posiblesMovs = new ArrayList<Posicion>();
        }
    }

    public void agregarFicha(Ficha ficha) {
        add(ficha);
        this.ficha = ficha;
        ficha.setPosicion(posicion);
    }

    @Override
    public String toString() {
        if (this.ficha != null) {
            return "o";
        } else {
            return "x";
        }
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
}
