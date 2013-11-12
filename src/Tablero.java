
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
    private int columnas;
    private int lineas;
    private String color1;
    private String color2;
    private JFrame ventana;
    private JPanel tablero;
    private Ficha[][] fichas;

    public Tablero(int cols, int rows) {
        this.columnas = cols;
        this.lineas = rows;
        this.color1 = "atomic/images/cuadro_blanco.png";
        this.color2 = "atomic/images/cuadro_cafe.png";
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
//              usar para fondo http://jc-mouse.blogspot.mx/2009/12/jframe-con-imagen-de-fondo-en-netbeans.html
                // Alternar colores del fondo del cuadro
                if (temp.equals(this.color1)) {
                    temp = this.color2;
                    //color = new Color(158, 118, 6); // Color café
                    color = Color.ORANGE; // Color café
                } else {
                    temp = this.color1;
                    color = new Color(242, 242, 242); // Color blanco
                }
                
                // Decidir si va una ficha en el cuadro o no
                CuadroTablero cuadroTablero = new CuadroTablero(color);
                if ( (i == 0 || i == 2) && (j%2) == 0 ) {
                    cuadroTablero.agregarFicha(Ficha.FICHA_A);
                } else if ( i== 1  && (j%2) != 0 ) {
                    cuadroTablero.agregarFicha(Ficha.FICHA_A);
                } else if ( (i == 5 || i == 7) && (j%2) == 0 ) {
                    cuadroTablero.agregarFicha(Ficha.FICHA_B);
                } else if ( i== 6  && (j%2) != 0 ) {
                    cuadroTablero.agregarFicha(Ficha.FICHA_B);
                }
                
                // Agregar el cuadro al panel del container
                panel.add(cuadroTablero);
            }
        }
        this.tablero = panel;
    }
    
//    Métodos de instancia
    public void restablecerTablero() {
        
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
}
