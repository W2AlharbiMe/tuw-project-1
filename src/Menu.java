import java.util.Scanner;

public class Menu {
    public static int initializeGameMenu(Scanner s) {
        String[] menu = {
                "Start Game\n",
                "Quit"
        };

        render(menu);

        return menu.length;
    }

    public static int createMenuInput(String message, Scanner s) {
        System.out.println(message);
        return s.nextInt();
    }

    public static void render(String[] menu) {
//        System.out.println("Choose from the menu:");
        String output = "";
        int c = 1;

        for (String item: menu){
            output += c + ". " + item;
            c++;
        }

        System.out.println(output);
    }
}
