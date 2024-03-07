
import java.awt.*;
import javax.swing.*;
import java.math.BigInteger;

public class Lamina extends JPanel {

    private JTextField Dato,resultadoText;
    private JComboBox<String> comboArray, comboArray2;
    private JButton convertir;

    private static final String[] TIPOS_DATOS =  { "Octal", "Decimal", "Hexadecimal","Binario", "Gray", "BCD"};
    private static final int[] BASES = {8,10,16,2};
    public Lamina(){
        comboArray = new JComboBox<String>(TIPOS_DATOS);
        comboArray2 = new JComboBox<String>(TIPOS_DATOS);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        Dato=new JTextField("INGRESA EL DATO",30);
        resultadoText=new JTextField("Resultado es:",30);
        resultadoText.setEditable(false);

        convertir= new JButton("convertir");
        convertir.addActionListener(e-> convertir());

        add(comboArray2);
        add(Dato);
        add(comboArray);
        add(resultadoText);
        add(convertir);

        setBackground(Color.DARK_GRAY);
    }

    private void convertir(){
        {
            int base = 0;
            BigInteger devolucion = null;
            String resultado = "", gray = "";

            String TipoSalida = (String) comboArray.getSelectedItem();
            String TipoEntrada = (String) comboArray2.getSelectedItem();

            try {
                switch (TipoEntrada) {

                    case "Gray":

                        if (Dato.getText().chars().allMatch(c -> c >= '0' && c <= '1')) {
                            gray = BinaryGray.toBinary(Dato.getText());
                            devolucion = new BigInteger(gray, 2);
                        }else
                            JOptionPane.showMessageDialog(this, "Tu codigo" +
                                    "no es valido.", "Error", JOptionPane.ERROR_MESSAGE);

                        break;

                    case "BCD":

                        if  (!(Dato.getText().chars().allMatch(c -> c >= '0' && c <= '1'))) {
                        JOptionPane.showMessageDialog(this, "Solo se " +
                                "aceptan digitos 1 o 0", "Error", JOptionPane.ERROR_MESSAGE);}
                        else if (!TipoSalida.equals("Decimal")) {
                            JOptionPane.showMessageDialog(this, "Solo se puede generar una salida" +
                                    " en decimal", "Error", JOptionPane.ERROR_MESSAGE);}
                        else if (Dato.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No has ingresado ningun dato." +
                                    "", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else if (Dato.getText().length() % 4 != 0) {
                            JOptionPane.showMessageDialog(this, "Tu Codigo BCD" +
                                    " no es multiplo de 4.", "Error", JOptionPane.ERROR_MESSAGE);

                        }

                        break;

                    default:
                        base = BASES[comboArray2.getSelectedIndex()];
                        devolucion = new BigInteger(Dato.getText(), base);

                }

                switch (TipoSalida) {
                    case "Octal":
                        resultado = devolucion.toString(BASES[comboArray.getSelectedIndex()]);
                        break;

                    case "Decimal":
                        if (!TipoEntrada.equals("BCD")) {
                            resultado = devolucion.toString(BASES[comboArray.getSelectedIndex()]);
                        }
                        else resultado = BCDecimal.toDecimal(Dato.getText());

                        break;

                    case "Hexadecimal":
                        resultado = devolucion.toString(BASES[comboArray.getSelectedIndex()]);

                        break;

                    case "Binario":
                        resultado = devolucion.toString(BASES[comboArray.getSelectedIndex()]);

                        break;

                    case "Gray":

                        resultado = BinaryGray.toGray(devolucion.toString(2));

                        break;

                    case "BCD":
                        if (!TipoEntrada.equals("Decimal")) {
                            JOptionPane.showMessageDialog(this, "Solo se acepta " +
                                    "valores en sistema decimal.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            resultado = BCDecimal.toBCD(Dato.getText());
                        }

                        break;

                    default:
                        resultado = "";

                }

                resultadoText.setText(resultado);

            } catch (NumberFormatException | StringIndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(this, "Hubo un error en la conversion" +
                        ".", "Error", JOptionPane.ERROR_MESSAGE);

            } catch (NullPointerException ex){
                devolucion = BigInteger.ZERO;
            }
        }

    }

}



