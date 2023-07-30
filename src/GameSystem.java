import java.io.IOException;
import java.util.Scanner;

public class GameSystem {
    public static void start() {
        Scanner s = new Scanner(System.in);

        Layout.printWelcomeMessage();

        while (true) {
            // show menu and return length
            int menu_length = Menu.initializeGameMenu(s);

            int choice = Menu.createMenuInput("Choose from the menu:", s);

            s.nextLine(); // intercept newline.


            // validate choice
            int result = Validate.selection(choice, menu_length, s);

            if(result == -1) {
                continue;
            }

            // optimization tick, quickly quit form the game.
            if(choice == 2) {
                System.out.println("Quitting the game ..");
                break;
            }

            int[] response = handleMethod(choice, s);

            if(response[0] == -5) {
                System.out.println("Quitting the game ..");
                break;
            }

            System.out.println();
            System.out.println();
        }
    }

//    public static void spaceTerminal() {
//        for (int i = 0; i < 50; ++i) System.out.println();
//    }

    public static void hold(Scanner s) {
        System.out.println("[PRESS ENTER TO CONTINUE].");
        s.nextLine();
    }


//    public static void clearTerminal() {
//        try {
//            final String os = System.getProperty("os.name");
//
//            if (os.contains("Windows")) {
//                Runtime.getRuntime().exec("cls");
//            } else {
//                Runtime.getRuntime().exec("clear");
//            }
//
//        } catch (IOException e) {
//            System.out.println("Could not clear terminal.");
//        }
//    }

    public static int[] handleMethod(int choice, Scanner s) {
        int[] response = {0, 0};

        switch (choice) {
            case 1:
                // start GamePlay Loop
                return Gameplay.start(s);
        }

        return response;
    }
}
