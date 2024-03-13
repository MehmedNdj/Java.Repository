package Projects;

public class RandomNum {
    //generates a random number from 0 to 100
    int randomNum = (int)(Math.random() * 101);

    public static void main(String[] args){

        RandomNum random = new RandomNum();
        System.out.print(random.randomNum);
    }
}
