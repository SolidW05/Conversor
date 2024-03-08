
import java.awt.*;
import javax.swing.*;
import java.math.BigInteger;

public class Lamina extends JPanel {

    // Se crean las variables que se usaran en el programa
    private JTextField Dato,resultadoText; // campos de texto
    private JComboBox<String> comboArray, comboArray2; // Menus se seleccion
    private JButton convertir; // Boton

    // se crea un array de texto donde se guardaran los tipos de conversiones
    private static final String[] TIPOS_DATOS =  { "Octal", "Decimal", "Hexadecimal","Binario", "Gray", "BCD"};
    private static final int[] BASES = {8,10,16,2};
    // se crea un array de enteros donde se guardan las bases al sistema a convertir

    // Se crea el metodo constructor de la clase donde se inicializa las variables creadas
    public Lamina(){

        //Se inicializan los menus de seleccion con lo que queremos dentro de ellos
        comboArray = new JComboBox<String>(TIPOS_DATOS);
        comboArray2 = new JComboBox<String>(TIPOS_DATOS);

        // se cambia la plantilla del panel a Flowlayout con el autoajuste a la izquierda
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // se crea un campo de texto con un texto inicial con un tamaño predefinido
        Dato=new JTextField("INGRESA EL DATO",30);
        resultadoText=new JTextField("Resultado es:",30);
        resultadoText.setEditable(false); // se evita que el campo de texto resultado pueda ser modificado

        // se crea el boton convertir
        convertir= new JButton("convertir");
        convertir.addActionListener(e-> convertir()); // se le añade un evento al boton

        //se añaden los componentes al jpanel
        add(comboArray2);
        add(Dato);
        add(comboArray);
        add(resultadoText);
        add(convertir);

        setBackground(Color.DARK_GRAY);
    }

    // se crea el metodo convertir que es el evento del boton
    private void convertir(){
        {
            // Se crean las variables que seran usadas en el metodo
            int base = 0;
            BigInteger devolucion = null;
            String resultado = "", gray = "";

            String TipoSalida = (String) comboArray.getSelectedItem();
            String TipoEntrada = (String) comboArray2.getSelectedItem();

            // se crea un Try pra intentar realizar las conversiones
            try {
                // se crea un switch con el tipo de entrada
                switch (TipoEntrada) {

                    case "Gray":
                        // se revisa que todo el texto son ceros o unos
                        // si es asi se convierte a binario
                        if (Dato.getText().chars().allMatch(c -> c >= '0' && c <= '1')) {
                            gray = BinaryGray.toBinary(Dato.getText());
                            devolucion = new BigInteger(gray, 2);
                        } // Si no es asi, sale un mensaje de error
                        else
                            JOptionPane.showMessageDialog(this, "Tu codigo" +
                                    "no es valido.", "Error", JOptionPane.ERROR_MESSAGE);

                        break;

                    case "BCD":

                        /*
                         se revisa si el texto contiente otro tipo de caracter que no sea ceros o unos
                         si es asi, sale un mensaje de error
                        */
                        if  (!(Dato.getText().chars().allMatch(c -> c >= '0' && c <= '1'))) {
                        JOptionPane.showMessageDialog(this, "Solo se " +
                                "aceptan digitos 1 o 0", "Error", JOptionPane.ERROR_MESSAGE);}
                        // despues se revisa si el tipo de salida es decimal, y si no es asi, sale un mensaje de error
                        else if (!TipoSalida.equals("Decimal")) {
                            JOptionPane.showMessageDialog(this, "Solo se puede generar una salida" +
                                    " en decimal", "Error", JOptionPane.ERROR_MESSAGE);}
                        // despues se revisa si esta vacio el campo de texto, si es asi sale un mensaje de error
                        else if (Dato.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No has ingresado ningun dato." +
                                    "", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        // despues se revisa si el texto ingresado es un multiplo de 4, y si no es asi saldra un mensaje de error
                        else if (Dato.getText().length() % 4 != 0) {
                            JOptionPane.showMessageDialog(this, "Tu Codigo BCD" +
                                    " no es multiplo de 4.", "Error", JOptionPane.ERROR_MESSAGE);

                        }

                        break;

                        // si selecciono cualquier otro tipo de entrada, se convierte decimal
                    default:
                        base = BASES[comboArray2.getSelectedIndex()];
                        devolucion = new BigInteger(Dato.getText(), base);

                }
                // se crea un switch con el tipo de salida
                switch (TipoSalida) {

                    case "Decimal":
                        /*
                         se revisa si el tipo de entrada NO es BCD
                         si es asi se convierte la variable a STRING, ya que el valor ya esta en decimal
                        */
                        if (!TipoEntrada.equals("BCD")) {
                            resultado = devolucion.toString();
                        }
                        // SI el tipo de entrada en BCD, el texto ingresado se convierte a BCD
                        else resultado = BCDecimal.toDecimal(Dato.getText());

                        break;

                    case "Gray":
                        /*
                         El numero convertido a decimal primero se convierte a binario
                         despues se convierte a codigo Gray
                        */
                        resultado = BinaryGray.toGray(devolucion.toString(2));

                        break;

                    case "BCD":
                        /*
                        Primero se revisa si el tipo de entrada es decimal
                        si no es asi sale un mensaje de error
                        */
                        if (!TipoEntrada.equals("Decimal")) {
                            JOptionPane.showMessageDialog(this, "Solo se acepta " +
                                    "valores en sistema decimal.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        // Si si es asi, entonces se convierte a BCD
                        else {
                            resultado = BCDecimal.toBCD(Dato.getText());
                        }

                        break;


                    default:
                        // Si es cualquier otro caso se convierte al sistema seleccionado
                        resultado = devolucion.toString(BASES[comboArray.getSelectedIndex()]);

                }

                resultadoText.setText(resultado);
                // se coloca el resultado en el campo de texto

            }
            // Se crean los catch que nos ayudaran a evitar errores, si es que estos llegaran a ocurrir
            catch (NumberFormatException | StringIndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(this, "Hubo un error en la conversion" +
                        ".", "Error", JOptionPane.ERROR_MESSAGE);

            } catch (NullPointerException ex){
                devolucion = BigInteger.ZERO;
            }
        }

    }

}



