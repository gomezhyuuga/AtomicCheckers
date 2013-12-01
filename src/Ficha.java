
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
    private ArrayList<Posicion> movsAComer;
    private boolean reina;
    CuadroTablero cuadro;
    private ArrayList<Posicion> posiblesMovs;
    public final static String FICHA_A = "src/atomic/images/fichas_A.png";
    public final static String FICHA_B = "src/atomic/images/fichas_B.png";
    public final static String FICHA_A_REINA = "src/atomic/images/fichas_A_Reina.png";
    public final static String FICHA_B_REINA = "src/atomic/images/fichas_B_Reina.png";

    public Ficha(String imagenFicha, Posicion posicion) {
        super(new ImageIcon(imagenFicha));
        this.posicion = posicion;

        this.fondoFicha = new ImageIcon(imagenFicha);
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
        posiblesMovs = new ArrayList<Posicion>();
        movsAComer = new ArrayList<Posicion>();
        ArrayList<Posicion> movsAEliminar = new ArrayList<Posicion>();
        if (this instanceof FichaA && !reina && this.posicion.getY() == 1) {
            // ya llegó hasta abajo, se convierte en reina
            posiblesMovs.clear();
        } else if (this instanceof FichaB && !reina && this.posicion.getY() == 8) {
            // ya llegó hasta arriba, se convierte en reina
            posiblesMovs.clear();
        } else if (this.reina) {
            System.out.println("SE PUEDE MOVER COMO REINA");
            int x = posicion.getX();
            int y = posicion.getY();
            // Movimiento en las cuatro diagonales
            Posicion pos1 = new Posicion(x + 1, y + 1);
            Posicion pos2 = new Posicion(x + 1, y - 1);
            Posicion pos3 = new Posicion(x - 1, y + 1);
            Posicion pos4 = new Posicion(x - 1, y - 1);
            ArrayList<Posicion> movs = new ArrayList<Posicion>();
            movs.add(pos1);
            movs.add(pos2);
            movs.add(pos3);
            movs.add(pos4);
            for (Posicion pos : movs) {
                // Posición válida no se sale del tablero
                if (pos.getX() != 0 && pos.getX() != 9 && pos.getY() != 9 && pos.getY() != 0) {
                    posiblesMovs.add(pos);
                }
            }
            // Determinar posibles movsAcomer
            for (Posicion pos : posiblesMovs) {
                CuadroTablero c = Tablero.cuadros[pos.getX() - 1][ pos.getY() - 1];
                if (c.hayFicha()) {
                    movsAEliminar.add(pos);
                    // Hay ficha, tal vez pueda comer. Determinar si es de su mismo
                    // tipo la ficha o no
                    if (c.getFicha().getClass() != this.getClass()) {
                        int x2 = c.getPosicion().getX();
                        int y2 = c.getPosicion().getY();
                        // No son del mismo tipo. Determinar si puede comer o no
                        // Para bordes
                        if (x2 == 8 || x2 == 1 || y2 == 1 || y2 == 8) {
                            // No puede comer porque ya está en el borde y no hay dónde saltar
                            movsAEliminar.add(pos);
                        } else {
                            // PUede comer
                            // Comiendo hacia la derecha. El incremento es +1
                            int incrY = 0;
                            int incrX = 0;
                            if (x2 > this.getPosicion().getX()) {
                                incrX = 1;
                            } else {
                                // Comiendo hacia la izquierda
                                incrX = -1;
                            }
                            if (y2 > this.getPosicion().getY()) {
                                // Come hacia arriba a la derecha
                                incrY = 1;
                            } else {
                                incrY = -1;
                            }
                            // Si está vació el cuadro puede mover y comer
                            Posicion posComer = new Posicion(x2 + incrX, y2 + incrY);
                            if (!Tablero.cuadros[posComer.getX() - 1][posComer.getY() - 1].hayFicha()) {
                                c.setBackground(Color.blue);
                                movsAComer.add(posComer);
                            }
                        }
                    } else {
                        // Son del mismo tipo, se elimina el posible mov
                        movsAEliminar.add(pos);
                    }
                }
            }
        } else {
            // Posibles movimientos por defecto (aún sin validar si hay fichas en las posiciones posibles
            // a mover ni si puede comer o no)
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

            // Determinar si hay ficha dentro de los posibles movimientos
            for (Posicion p : posiblesMovs) {
                int x = p.getX();
                int y = p.getY();
                CuadroTablero cuadro = Tablero.cuadros[x - 1][y - 1];
                // Si hay ficha entonces determinar si se puede comer o no
                if (cuadro.hayFicha()) {
                    if (cuadro.getFicha().getClass() == this.getClass()) {
                        // Son del mismo tipo, no se puede comer. Se eliminan los posibles movs
                        movsAEliminar.add(p);
                    } else {
                        // No son del mismo tipo, tal vez puede comer
                        // Determinar si existe un cuadro vacío en diagonal a la ficha
                        // que tal vez que pueda comer
                        // Determinar si es que puede comer hacia la derecha o izquierda
                        int incrementoX2 = 0;
                        if (x < posicion.getX()) {
                            incrementoX2 = -1;
                        } else {
                            incrementoX2 = 1;
                        }
                        int x2 = x + incrementoX2;
                        int y2 = y + incrementoY;
                        // Validación para bordes
                        if (x2 >= 1 && x2 <= 8 && y2 >= 1 && y2 <= 8) {
                            // Puede comer hacia la izquierda
                            if (!Tablero.cuadros[x2 - 1][y2 - 1].hayFicha()) {
                                // Puede comer, poner de color azul el fondo de la ficha
                                System.out.println("ESTÁ VACÍA. PUEDE COMER");
                                cuadro.setBackground(Color.blue);
                                movsAEliminar.add(p);
                                movsAComer.add(new Posicion(x2, y2));
                            } else {
                                // No puede comer, eliminar posible mov
                                movsAEliminar.add(p);
                            }
                        } else {
                            // No puede comer, eliminar posible mov
                            movsAEliminar.add(p);
                        }

                    }
                }
            }
        }
        // Añadir los movimientos posibles a comer ficha
        for (Posicion p : movsAComer) {
            posiblesMovs.add(p);
        }

        // Eliminar movimientos inválidos
        for (Posicion p : movsAEliminar) {
            posiblesMovs.remove(p);
        }

        // Resaltar cuadros a los que puede mover
        resaltarPosiblesMovimientos();
    }

    public void resaltarPosiblesMovimientos() {
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

    public ArrayList<Posicion> getMovsAComer() {
        return movsAComer;
    }

    public void setMovsAComer(ArrayList<Posicion> movsAComer) {
        this.movsAComer = movsAComer;
    }

    public boolean isReina() {
        return reina;
    }

    public void setReina(boolean reina) {
        this.reina = reina;
    }

}
