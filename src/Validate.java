import java.util.HashMap;
import java.util.Scanner;

public class Validate {

    public static int[] parse(String s) {
        int[] response = {0, 0};

        try {
            int data = Integer.parseInt(s);
            response[1] = data;

            return response;
        } catch(NumberFormatException e) {
            System.out.println("The choice you entered is invalid. choose from 1 to ");
            response[0] = -1;

            return response;
        }
    }


    public static int selection(int choice, int menu_length, Scanner s) {
        if(choice > menu_length || choice <= 0) {
            System.out.println("The choice you entered is invalid. choose from 1 to " + menu_length);
            GameSystem.hold(s);

            return -1;
        }

        return 0;
    }

    public static int gamePlay(int choice, HashMap<Integer, Boolean> positions, Scanner s) {
        if(choice > 9 || choice <= 0) {
            System.out.println("You can only choose a position 1 to 9.");
            GameSystem.hold(s);

            return -1;
        }

        if(positions.get(choice - 1)) {
            System.out.println("The position you want is already acquired.");
            GameSystem.hold(s);

            return -1;
        }

        return 0;
    }
}
