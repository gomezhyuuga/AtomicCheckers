
/**
 * @author (Luis Ballinas, Gabriel Ramos Olvera, Fernando Gomez, Francisco
 * Barros)
 * @version (9/13/2013)
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class CuadroTablero extends JPanel {

    private Posicion posicion;
    protected Color colorFondo;
    private Ficha ficha;
    private Tablero tablero;
    private ArrayList<Posicion> posiblesMovs;

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

    public abstract void restablecerColorFondo();

    private void cuadroPresionado(java.awt.event.MouseEvent evt) {
        CuadroTablero[][] cuadros = this.tablero.getCuadros();
        // Si hay cuadros en verde de posibles movs regresar a su estado original sólo si ya tiró
        if (!Tablero.tirando) {
            this.tablero.restablecerCuadros();
        }

//        System.out.println("# Se presionó el cuadro en:");
        // Determinar en qué posiciones se puede mover
//        System.out.println(posicion);
        // Si no está tirando apenas va a seleccionar una ficha para moverla
        if (!Tablero.tirando) {
            if (this.ficha == null) {
                System.out.println("NO HAY FICHA");
            } else {
                boolean puedeMover = false;
                System.out.println("HAY FICHA EN ESTE CUADRO");
                // Sólo puede tirar si selecciona una ficha de su propio tipo
                if (this.ficha instanceof FichaB && Tablero.turnoJugador1) {
                    // Puede mover esa ficha
                    puedeMover = true;
                } else if (this.ficha instanceof FichaA && !Tablero.turnoJugador1) {
                    // Puede mover esta ficha
                    puedeMover = true;
                } else {
                    puedeMover = false;
                    System.out.println("NO PUEDES MOVER ESTA FICHA");
                    JOptionPane.showMessageDialog(this, "NO PUEDES MOVER ESTA FICHA");
                }

                if (puedeMover) {
                    ficha.determinarPosiblesMovimientos();
                    // Sólo si tiene posibles movimientos puede tirar, sino necesita
                    // seleccionar otra ficha
                    if (ficha.getPosiblesMovs().size() > 0) {
                        Tablero.tirando = true;
                        Tablero.fichaAMover = ficha;
                        Tablero.fichaAMover.getCuadro().setBackground(Color.GRAY);
                    }
                }
            }
        } else {
            // Si ya está tirando entonces seleccionó un cuadro donde quiere mover la ficha
            System.out.println("INTENTANDO TIRAR en:");
            System.out.println(posicion);
            // Si la ficha que se quiere mover contiene la posición en posible mov 
            // de este cuadro entonces se puede mover
            boolean movValido = false;
            for (Posicion p : Tablero.fichaAMover.getPosiblesMovs()) {
                if (p.getX() == posicion.getX() && p.getY() == posicion.getY()) {
                    movValido = true;
                    break;
                }
            }
            if (movValido) {
                System.out.println("# MOVIMIENTO VÁLIDO. Tiraste en:");
                System.out.println(posicion);
                // pos antes
                int posAntesX = Tablero.fichaAMover.getPosicion().getX();
                int posAntesY = Tablero.fichaAMover.getPosicion().getY();
                // Tirar: mover ficha al nuevo cuadro y remover la antigua
                Tablero.fichaAMover.getCuadro().removeAll();
                Tablero.fichaAMover.getCuadro().revalidate();
                Tablero.fichaAMover.getCuadro().repaint();
                Tablero.fichaAMover.getCuadro().setFicha(null);
                add(Tablero.fichaAMover);
                setFicha(Tablero.fichaAMover);
                Tablero.fichaAMover.setCuadro(this);
                Tablero.fichaAMover.setPosicion(posicion);
                
                // Determinar si se convirtió en reina
                if ( Tablero.fichaAMover instanceof FichaA && !Tablero.fichaAMover.isReina() && Tablero.fichaAMover.getPosicion().getY() == 1 ) {
                    System.out.println("SE CONVIERTE EN REINA A");
                    JOptionPane.showMessageDialog(this, "LA FICHA SE HA CONVERTIDO EN REINA");
                    FichaA reina = new FichaA(posicion, true);
                    this.removeAll();
                    this.revalidate();
                    this.repaint();
                    agregarFicha(reina);
                } else if ( Tablero.fichaAMover instanceof FichaB && !Tablero.fichaAMover.isReina() && Tablero.fichaAMover.getPosicion().getY() == 8 ) {
                    System.out.println("SE CONVIERTE EN REINA B");
                    JOptionPane.showMessageDialog(this, "LA FICHA SE HA CONVERTIDO EN REINA");
                    FichaB reina = new FichaB(posicion, true);
                    this.removeAll();
                    this.revalidate();
                    this.repaint();
                    agregarFicha(reina);
                }
                
                // Determinar si comió una ficha
                ArrayList<Posicion> movsAComer = Tablero.fichaAMover.getMovsAComer();
                for (Posicion p : movsAComer) {
                    boolean comio = false;
                    if (posAntesX <= p.getX() && p.getX() <= posicion.getX()) {
                        comio = true;
                    }
                    if (posicion.getX() <= p.getX() && p.getX() <= posAntesX) {
                        comio = true;
                    }
                    if (comio) {
                        // Si comió hacia la derecha entonces:
                        // posAntesX < p.x < posicion.x
                        // SI comió hacia la derecha entonces:
                        // posicion.x < p.x < posAntesX
                        // Posición de la ficha que se comió:
                        // (posicion.x + posAntesX)/2
                        System.out.println("COMIÓ");
                        int posElimX = (posicion.getX() + posAntesX) / 2;
                        int posElimY = (posicion.getY() + posAntesY) / 2;
                        System.out.println("posElimX: " + posElimX);
                        System.out.println("posElimY: " + posElimY);
                        CuadroTablero c = Tablero.cuadros[posElimX - 1][posElimY - 1];
                        // Disminuir número de fichas del jugador
                        if (c.getFicha() instanceof FichaB) {
                            tablero.getVentanaJuego().disminuirFichasJ1();
                        } else {
                            tablero.getVentanaJuego().disminuirFichasJ2();
                        }
                        c.removeAll();
                        c.revalidate();
                        c.repaint();
                        c.setFicha(null);
                    }
                }

                // Ya tiró, establecer como tirando false
                Tablero.tirando = false;
                // Restablecer cuadros (quitar color verde)
                this.tablero.restablecerCuadros();
                // Cambiar de turno
                if (Tablero.turnoJugador1) {
                    Tablero.turnoJugador1 = false;
                    this.tablero.getVentanaJuego().cambiarLblTurno("TURNO DE JUGADOR 2");
                } else {
                    Tablero.turnoJugador1 = true;
                    this.tablero.getVentanaJuego().cambiarLblTurno("TURNO DE JUGADOR 1");
                }
            } else {
                System.out.println("MOVIMIENTO INVÁLIDO");
                JOptionPane.showMessageDialog(this, "MOVIMIENTO INVÁLIDO");
            }
        }
    }

    public void agregarFicha(Ficha ficha) {
        add(ficha);
        this.ficha = ficha;
        this.ficha.setCuadro(this);
        ficha.setPosicion(posicion);
    }

    public boolean hayFicha() {
        if (this.ficha == null) {
            return false;
        } else {
            return true;
        }
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

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
}
