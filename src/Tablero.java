
/*
 * Atomic Checkers Main.
 * 
 * @author (Luis Ballinas, Gabriel Ramos Olvera, Fernando Gomez, Francisco Barros) 
 * @version (9/13/2013)
 */
import javax.swing.*;
import java.awt.*;

public class Tablero {

    //Datos 
    public static boolean tirando;
    public static boolean turnoJugador1;
    private int columnas;
    private int lineas;
    private String color1;
    private String color2;
    private JFrame ventana;
    private JPanel tablero;
    private VentanaJuego ventanaJuego;
    public static CuadroTablero[][] cuadros;
    public static Ficha fichaAMover;

    public Tablero(int cols, int rows) {
        this.columnas = cols;
        this.lineas = rows;
        this.color1 = "atomic/images/cuadro_blanco.png";
        this.color2 = "atomic/images/cuadro_cafe.png";
        cuadros = new CuadroTablero[rows][cols];
        Tablero.tirando = false;
        Tablero.turnoJugador1 = true;
    }

    public void init() {
        System.out.println("Inicializando tablero...");
        // Crear ventana principal
        ventana = new JFrame();
        ventana.setSize(520, 520);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Atomic Checkers");

        //Container panel = ventana.getContentPane();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(this.lineas, this.columnas));

        String temp;
        Color color;
        for (int i = 0; i < lineas; i++) {
            // Alternar colores del fondo del cuadro
            if (i % 2 == 0) {
                temp = this.color1;
            } else {
                temp = this.color2;
            }
            for (int j = 0; j < columnas; j++) {
                Posicion posicion = new Posicion(j + 1, lineas - i);
                CuadroTablero cuadroTablero;
                // Alternar colores del fondo del cuadro
                if (temp.equals(this.color1)) {
                    temp = this.color2;
                    //color = new Color(158, 118, 6); // Color café
                    color = Color.ORANGE; // Color café
                    cuadroTablero = new CuadroTableroB(posicion);
                } else {
                    temp = this.color1;
                    color = new Color(242, 242, 242); // Color blanco
                    cuadroTablero = new CuadroTableroA(posicion);
                }

                // Decidir si va una ficha en el cuadro o no
                if ((i == 0 || i == 2) && (j % 2) != 0) {
                    cuadroTablero.agregarFicha(new FichaA(posicion));
                } else if (i == 1 && (j % 2) == 0) {
                    cuadroTablero.agregarFicha(new FichaA(posicion));
                } else if ((i == 5 || i == 7) && (j % 2) == 0) {
                    cuadroTablero.agregarFicha(new FichaB(posicion));
                } else if (i == 6 && (j % 2) != 0) {
                    cuadroTablero.agregarFicha(new FichaB(posicion));
                }
                // Agregar el cuadro al panel del container
                this.cuadros[posicion.getX() - 1][posicion.getY() - 1] = cuadroTablero;
                cuadroTablero.setTablero(this);
                panel.add(cuadroTablero);
            }
        }
        this.tablero = panel;

        // Imprimir el tablero en la consola
//        for (int i = 0; i < this.lineas; i++) {
//            for (int j = 0; j < this.columnas; j++) {
//                CuadroTablero cuadro = cuadros[i][j];
//                System.out.print(cuadro);
//            }
//            System.out.println("");
//        }
    }

    public CuadroTablero[][] getCuadros() {
        return cuadros;
    }

    public void setCuadros(CuadroTablero[][] cuadros) {
        this.cuadros = cuadros;
    }

//    Métodos de instancia
    public void restablecerCuadros() {
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < lineas; j++) {
                cuadros[i][j].restablecerColorFondo();
            }
        }
    }

    // Getters & Setters
    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getLineas() {
        return lineas;
    }

    public void setLineas(int lineas) {
        this.lineas = lineas;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public JPanel getTablero() {
        return tablero;
    }

    public void setTablero(JPanel tablero) {
        this.tablero = tablero;
    }

    public VentanaJuego getVentanaJuego() {
        return ventanaJuego;
    }

    public void setVentanaJuego(VentanaJuego ventanaJuego) {
        this.ventanaJuego = ventanaJuego;
    }

}
