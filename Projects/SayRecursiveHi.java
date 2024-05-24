package Projects;

public class SayRecursiveHi {
    public static void main(String[] args) {

        sayHi(5);

    }

    private static void sayHi(int count){

        System.out.println("I think i did it!");

        if(count > 0) {
            sayHi(count - 1);
        }
        else {
            return;
        }
    }
}


