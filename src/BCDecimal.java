import javax.swing.*;
import java.util.HashMap;

public class BCDecimal {

    private static HashMap<Character, String> tablaBCD = new HashMap<>();
    private static HashMap<String, Character> tablaInvertidaBCD = new HashMap<>();

    static {
        setTablaBCD();
    }
    static void setTablaBCD(){

        tablaBCD.put('0' , "0000");
        tablaBCD.put('1' , "0001");
        tablaBCD.put('2' , "0010");
        tablaBCD.put('3' , "0011");
        tablaBCD.put('4' , "0100");
        tablaBCD.put('5' , "0101");
        tablaBCD.put('6' , "0110");
        tablaBCD.put('7' , "0111");
        tablaBCD.put('8' , "1000");
        tablaBCD.put('9' , "1001");

        for(char llave : tablaBCD.keySet()){
            String valor = tablaBCD.get(llave);
            tablaInvertidaBCD.put(valor,llave);
        }

    }

    public static String toBCD(String numDecimal){

        StringBuilder Bcd = new StringBuilder();

        for(int i = 0; i < numDecimal.length(); i++){

            Bcd.append(tablaBCD.get(numDecimal.charAt(i)));
            Bcd.append("|");
        }

        return Bcd.toString();
    }

    public static String toDecimal(String BCD){

        StringBuilder numDecimal = new StringBuilder();

        String digitoBCD;
        for (int i = 0; i < BCD.length(); i += 4){

            digitoBCD = BCD.substring(i , i + 4);

            if(!tablaInvertidaBCD.containsKey(digitoBCD)){
                JOptionPane.showMessageDialog(null, "Tu numero en BCD no es valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            numDecimal.append(tablaInvertidaBCD.get(BCD.substring(i , i + 4)));
        }

        return numDecimal.toString();
    }

}
