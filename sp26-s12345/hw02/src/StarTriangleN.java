public class StarTriangleN {
   /**
     * Prints a right-aligned triangle of stars ('*') with N lines.
     * The first row contains 1 star, the second 2 stars, and so on. 
     */
   public static void starTriangle(int N) {
      // TODO: Fill in this function
      for (int i = 1; i <= N; i += 1) {
         String space = " ".repeat(N-i);
         String star = "*".repeat(i);
         System.out.println(space + star);
      }
   }
   
   public static void main(String[] args) {
      starTriangle(7);
   }
}