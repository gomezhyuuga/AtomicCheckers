
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
            int incrementoY = 0;
            // Si es una FichaA, entonces sólo se puede mover hacia abajo
            if (this.ficha instanceof FichaB) {
                incrementoY = 1;
            } else if (this.ficha instanceof FichaA) {
                // Si es una FichaB, entonces sólo se puede mover hacia arriba
                incrementoY = -1;
            }
            // Movimientos en diagonal de sólo un brinco
            Posicion posFicha = ficha.getPosicion();
            int incrementoX = 0;
            if( posFicha.getX() > 1 && posFicha.getX() < 8 ) {
                // Se puede mover tanto en diagonal izquierda como diagonal derecha
                incrementoX = 1;
                posiblesMovs.add(new Posicion(ficha.getPosicion().getX() - 1, ficha.getPosicion().getY() + incrementoY));
            } else if( posFicha.getX() == 1 ) {
                // Sólo se puede mover en diagonal derecha
                incrementoX = 1;
            } else if( posFicha.getX() == 8 ) {
                // Sólo se puede mover en diagonal izquierda
                incrementoX = -1;
            }
            posiblesMovs.add(new Posicion(ficha.getPosicion().getX() + incrementoX, ficha.getPosicion().getY() + incrementoY));
            
            // Determinar si hay ficha dentro de los posibles movimientos
            
            
            System.out.println("POSIBLES MOVS:");
            for(Posicion p : posiblesMovs) {
                System.out.println(p);
            }
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
