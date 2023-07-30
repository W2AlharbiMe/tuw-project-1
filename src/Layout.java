import java.lang.reflect.Array;
import java.util.Arrays;

public class Layout {

    public static void render(int[][] game_board, int X, int O) {
        String[][] game_board_string = {
                // 0,1          1,2        2,3
                {" ", "|"},  {" ", "|"}, {" ", "\n"},
                // 3,4          4,5           5,6
                {"-", "-"},  {"-", "-"}, {"-", "\n"},
                // 6,7            7,8           8,9
                {" ", "|"},  {" ", "|"}, {" ", "\n"},
                // 9,10            10,11          11,12
                {"-", "-"},  {"-", "-"}, {"-", "\n"},
                // 12,13           13,14          14,15
                {" ", "|"},  {" ", "|"}, {" ", "\n"},
        };

        int[][] game_board_map = {
                {1, 0}, {2, 1}, {3, 2},

                {4, 6}, {5, 7}, {6, 8},

                {7, 12}, {8, 13}, {9, 14},
        };

        String output = "";

        for (int i = 0; i < game_board.length; i++) {
            int[] m = game_board_map[i];
            int[] row = game_board[i];
            String[] str_place = game_board_string[m[1]];

            if(row[0] == X) {
                str_place[0] = "X";
            }

            if (row[0] == O) {
                str_place[0] = "O";
            }
        }

//        int c = 0;
//
//        for (int i = 0; i < game_board.length; i++) {
//            int[] row = game_board[i];
//
//            if(row[0] == X) {
//                game_board_string[c][0] = "X";
//
//            } else if (row[0] == O) {
//                game_board_string[c][0] = "X";
//            }
//        }

        for (String[] row : game_board_string) {
            System.out.print(row[0] + row[1]);
        }


    }
}

