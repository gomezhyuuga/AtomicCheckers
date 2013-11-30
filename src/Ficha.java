
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author (Luis Ballinas, Gabriel Ramos Olvera, Fernando Gomez, Francisco
 * Barros)
 * @version (9/13/2013)
 */
public class Ficha extends JLabel {

    private ImageIcon fondoFicha;
    private Posicion posicion;
    CuadroTablero cuadro;
    private ArrayList<Posicion> posiblesMovs;
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

    public ArrayList<Posicion> getPosiblesMovs() {
        return posiblesMovs;
    }

    public void setPosiblesMovs(ArrayList<Posicion> posiblesMovs) {
        this.posiblesMovs = posiblesMovs;
    }

    void determinarPosiblesMovimientos() {
        // Posibles movimientos por defecto (aún sin validar si hay fichas en las posiciones posibles
        // a mover ni si puede comer o no)
        ArrayList<Posicion> posiblesMovs = new ArrayList<Posicion>();
        this.posiblesMovs = posiblesMovs;
        int incrementoY = 0;
        // Si es una FichaA, entonces sólo se puede mover hacia abajo
        if (this instanceof FichaB) {
            incrementoY = 1;
        } else if (this instanceof FichaA) {
            // Si es una FichaB, entonces sólo se puede mover hacia arriba
            incrementoY = -1;
        }
        // Movimientos en diagonal de sólo un brinco
        int incrementoX = 0;
        if (posicion.getX() > 1 && posicion.getX() < 8) {
            // Se puede mover tanto en diagonal izquierda como diagonal derecha
            incrementoX = 1;
            posiblesMovs.add(new Posicion(posicion.getX() - 1, posicion.getY() + incrementoY));
        } else if (posicion.getX() == 1) {
            // Sólo se puede mover en diagonal derecha
            incrementoX = 1;
        } else if (posicion.getX() == 8) {
            // Sólo se puede mover en diagonal izquierda
            incrementoX = -1;
        }
        posiblesMovs.add(new Posicion(posicion.getX() + incrementoX, posicion.getY() + incrementoY));
        ArrayList<Posicion> movsAEliminar = new ArrayList<Posicion>();

        // Determinar si hay ficha dentro de los posibles movimientos
        for (Posicion p : posiblesMovs) {
            CuadroTablero cuadro = Tablero.cuadros[p.getX() - 1][p.getY() - 1];
            // Si hay ficha entonces determinar si se puede comer o no
            if (cuadro.hayFicha()) {
                if (cuadro.getFicha().getClass() == this.getClass()) {
                    // Son del mismo tipo, no se puede comer. Se eliminan los posibles movs
                    movsAEliminar.add(p);
                } else {
                    // No son del mismo tipo, tal vez puede comer
                }
            }
        }

        // Eliminar movimientos inválidos
        for (Posicion p : movsAEliminar) {
            posiblesMovs.remove(p);
        }

        // Resaltar cuadros a los que puede mover
        if (posiblesMovs.size() > 0) {
            System.out.println("POSIBLES MOVS:");
            for (Posicion p : posiblesMovs) {
                System.out.println(p);
                CuadroTablero cuadro = Tablero.cuadros[p.getX() - 1][p.getY() - 1];
                cuadro.setBackground(Color.GREEN);
            }
        } else {
            System.out.println("NO HAY POSIBLES MOVS, ENVIAR AVISO");
            JOptionPane.showMessageDialog(this, "NO HAY POSIBLES MOVIMIENTOS PARA ESTA FICHA");
        }
    }

    public CuadroTablero getCuadro() {
        return cuadro;
    }

    public void setCuadro(CuadroTablero cuadro) {
        this.cuadro = cuadro;
    }

}
