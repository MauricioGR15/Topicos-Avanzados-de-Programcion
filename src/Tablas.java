import java.awt.*;
import javax.swing.*;
import java.util.Vector;
public class Tablas extends JFrame {
    public Tablas()  {
        super("Mi tabla");
        this.setSize(300,200);
        setLocationRelativeTo(null);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Vector columnas = new Vector();
        /* A ese vector le agrego datos, estos datos 
           vendrán a ser las columnas de la tabla.   */
        columnas.addElement("Columna A");
        columnas.addElement("Columna B");
        columnas.addElement("Columna C");
        columnas.addElement("Columna D");
/*
            Creo una instancia de la clase Vector llamada 'filas’,
            este vector tendrá todas las filas de la tabla.
        */
        Vector filas = new Vector();
        /*  objeto llamado 'fila', esto representará a           una fila en particular y cada elemento que agregue a este vector será una celda. */
              Vector fila = new Vector();
              fila.addElement("X");
              fila.addElement("Y");
              fila.addElement("Z");
              fila.addElement("E");
              
              filas.add(fila);
              
              fila=new Vector();
              fila.addElement("A");
              fila.addElement("B");
        //      fila.addElement(5);
              filas.add(fila);
           for(int i=0 ; i<20 ; i++){   
              fila=new Vector();
              fila.addElement("Z");
              fila.addElement("W");
              fila.addElement("P");
              fila.addElement("N");
              filas.add(fila);
           }   
              JTable tbl = new JTable(filas,columnas);
              /* Creo una instancia de JScrollPane y le paso como                  parametro la tabla */
              JScrollPane panel =new JScrollPane(tbl);
              /* Por ultimo agrego ese objeto de JScrollPane al contenedor de la ventana */
              add(panel);
              this.setVisible(true);
          }
          public static void main(String[] args) {
              new Tablas();
          }
      }
