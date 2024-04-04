package Projects;

public class OuterLoop {


     public static void main(String[] args) {

         Person p = new Person();
         p.age = 35;
         p.name = "Josef Novak";
         int a = p.age;
         a += 1;

         Person p2 = new Person();
         p2.age = 13;
         System.out.println(a);
     }
}

class Person {
   int age;
   String name;
}


