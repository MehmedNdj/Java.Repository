package Projects;

public class OuterLoop {
     public static void main(String[] args) {

         outer:
         for (int i = 2; i < 100; i++){
             System.out.println("i= "+i);
             for (int j = 2; j < i; j++) {
                 if (i % j == 0)
                     continue outer;

             }


             System.out.print(i);
         }
     }
}
