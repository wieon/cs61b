public class DoubleUp {
   /**
     * Returns a new string where each character of the given string is repeated twice.
     * Example: doubleUp("hello") -> "hheelllloo"
     */
   public static String doubleUp(String s) {
      // TODO: Fill in this function
      int sLength = s.length();
      String result = "";

      if (sLength != 0) {
         for (int i = 0; i < sLength; i += 1) {
            char letter = s.charAt(i);
            result += letter;
            result += letter;
         }
         return result;
      } else {
         return null;
      }
   }
   
   public static void main(String[] args) {
      String s = doubleUp("hello");
      System.out.println(s);
      
      System.out.println(doubleUp("cat"));
   }
}