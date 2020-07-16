import java.util.Random;
import java.util.Scanner;

public class Game {

    public static int Toss(){
        Random rnd=new Random();
        return (rnd.nextInt(2));
    }

    public static void showScoreBoard(){

    }

    public static void play(){

    }

    public static void main(String[] args){

        Scanner Tname = new Scanner(System.in);
        System.out.println("Enter Team names:");

        String name1 = Tname.nextLine();  // Read user input
        System.out.println("Team one is: " + name1);  // Output user input

        String name2 = Tname.nextLine();  // Read user input
        System.out.println("Team two is: " + name2);  // Output user input


    }

}
