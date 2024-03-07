

import javax.swing.*;
import java.awt.*;

public class MiVentana extends JFrame{
    
    public MiVentana(){

        setSize(360,300);
        setLocationRelativeTo(null);
        setResizable(false); //redimenacinar ventana
        setTitle("Convertidor");
        ImageIcon icono = new ImageIcon("src/flechas.png");
        setIconImage(icono.getImage());


        Lamina lamina1=new Lamina();
        add(lamina1);
        
       
    }
}
