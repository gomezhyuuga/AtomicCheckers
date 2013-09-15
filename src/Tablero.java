
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

    public Tablero(int cols, int rows) {
        this.columnas = cols;
        this.lineas = rows;
        this.color1 = "/atomic/images/cuadro_blanco.png";
        this.color2 = "/atomic/images/cuadro_cafe.png";
    }
    
    

    public void init() {
        // Crear ventana principal
        ventana = new JFrame();
        ventana.setSize(600, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Atomic Checkers");
        
        Container panel = ventana.getContentPane();
        panel.setLayout(new GridLayout(this.lineas, this.columnas));
        
        String temp;
        for (int i = 0; i < lineas; i++) {
            if (i % 2 == 0) {
                temp = this.color1;
            } else {
                temp = this.color2;
            }
            for (int j = 0; j < columnas; j++) {
                CuadroTablero ficha_panel = new CuadroTablero(temp);
//              usar para fondo http://jc-mouse.blogspot.mx/2009/12/jframe-con-imagen-de-fondo-en-netbeans.html
                if (temp.equals(this.color1)) {
                    temp = this.color2;
                } else {
                    temp = this.color1;
                }
                panel.add(ficha_panel);
            }
        }
        ventana.setVisible(true);
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
}
