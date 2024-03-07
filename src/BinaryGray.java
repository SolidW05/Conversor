
public class BinaryGray{


     private static String numeroXOR(String numeroBin){
         String numeroXOR = "0";

         for (int i = 0; i < numeroBin.length() - 1; i++) {
             numeroXOR += numeroBin.charAt(i);
         }
         return numeroXOR;
     }

     public static String toGray(String numeroBin){

         String num_xor = numeroXOR(numeroBin);
         StringBuilder num_gray = new StringBuilder();
         for(int i = 0; i < numeroBin.length(); i++ ){

             if (num_xor.charAt(i) == numeroBin.charAt(i)) {
                 num_gray.append("0");
             } else num_gray.append("1");
         }

         return num_gray.toString();
     }

     public static String toBinary(String gray){

         char gn = gray.charAt(0);
         char bn = gn;

         StringBuilder binarySys = new StringBuilder();
         binarySys.append(bn);

         for (int i = 1; i < gray.length(); i++){
              gn = gray.charAt(i);
              bn = (gn == bn) ? '0' : '1';
              binarySys.append(bn);
         }
         return binarySys.toString();
     }

}
