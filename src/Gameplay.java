import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Gameplay {
    // this will generate a random number between 20 and 100.
    public static double generateScoreRate() {
        int max = 100;
        int min = 20;
        return ((Math.random() * (max - min)) + min);
    }


    // each column will have the following data:
    // [MARK, PLAYER]
    // example:
    // {X, p1} -> {1, 0}
    // -1, -1 means that position is available.
    // by default all positions are available.
    public static int[][] initializeGameBoard() {
        return new int[][]{
                // 0,1         1,2         2,3
                {-1, -1}, {-1, -1}, {-1, -1},
                // 3,4         4,5         5,6
                {-1, -1}, {-1, -1}, {-1, -1},
                // 6,7         7,8         8,9
                {-1, -1}, {-1, -1}, {-1, -1},
        };
    }

    public static void scanAvailablePositions(int[][] game_board, HashMap<Integer, Boolean> positions) {
        for (int i = 0; i < game_board.length; i++) {
            int[] row = game_board[i];

            if(row[0] > -1) {
                positions.put(i, true);
            }
        }

    }

    // this will update the game board and return it
    // the position parameter is a simple array that have:
    public static void acquirePosition(int[][] game_board, int row, final int MARK, final int PLAYER) {
        int[] column = {MARK, PLAYER};
        game_board[row] = column;
    }

    public static int[] createInput(Scanner s, int player, String[] names, boolean computer_play) {
        if(!computer_play) {
            System.out.println("Turn: " + names[player]);
            System.out.print("Enter a Position from 1 to 9: ");
        } else {
            System.out.print("Enter a Position from 1 to 9 or\nEnter (b) to go back to the menu or\nEnter (q) to quit: ");
        }

        int[] response = {0, -1};

        String data = s.nextLine();

        if(data.isEmpty() || data.isBlank()) {
            response[0] = -3;
            return response;
        }

        if(data.equalsIgnoreCase("q") && computer_play) {
            response[0] = -1;
            return response;
        }

        if(data.equalsIgnoreCase("b") && computer_play) {
            response[0] = -2;
            return response;
        }


        int[] parsed = Validate.parse(data);

        if(parsed[0] == -1) {
            response[0] = -1;
            return response;
        }

        response[1] = parsed[1];

        return response;
    }


    // this is used to view the game board data.
//    public static void _debugGameBoard(int[][] game_board) {
//        String output = "{\n";
//        int counter = 1;
//
//        for (int[] p : game_board) {
//            output += " {" + p[0] + ", " + p[1] + "}";
//            counter++;
//            if(counter == 4) {
//                output += "\n";
//                counter = 1;
//            }
//        }
//
//        output += "];";
//
//        System.out.println(output);
//    }

    // this takes 3 positions and scan them
    // finally returns the winner.
    public static int[] scanPosition(int[][] positions, int P1, int P2, int C) {
        int[] response = {-1, -1};
        // destruct
        int[] p1 = positions[0];
        int[] p2 = positions[1];
        int[] p3 = positions[2];

        if(p1[1] == P1 && p2[1] == P1 && p3[1] == P1) {
            response[0] = 0;
            response[1] = P1;
            return response;
        }

        if(p1[1] == C && p2[1] == C && p3[1] == C) {
            response[0] = 0;
            response[1] = C;
            return response;
        }

        if(p1[1] == P2 && p2[1] == P2 && p3[1] == P2) {
            response[0] = 0;
            response[1] = P2;
            return response;
        }


        return response;
    }

    public static int[] detectWinner(int[][] game_board, int P1, int P2, int C) {
        int[] response = {0, 9};
        int[][] positions = {
                {-1, -1}, {-1, -1}, {-1, -1}
        };

        // I need to try to detect the winner within every loop
        // before anything else.
        // The scanning for winner will be in the following order:
        // 4. check right diagonal rows
        // 3. check left diagonal rows
        // 1. check by rows
        // 2. check by columns


        int last_row_id = 2;
        int second_column_id = 3;
        int last_column_id = 6;

        int middle_position_id = 4;
        int last_position_id = game_board.length - 1; // 8

        // optimization tick, scan diagonals first.
        for (int i = 0; i < 4; i+=2) {
            int[] current_position = game_board[i];
            int[] middle_position = game_board[middle_position_id];
            int[] last_position = game_board[last_position_id];

            positions[0] = current_position;
            positions[1] = middle_position;
            positions[2] = last_position;

            int[] result = scanPosition(positions, P1, P2, C);

            if(result[0] == 0) {
                response[0] = result[1];
                response[1] = 5;
                return response;
            }


            last_position_id -= 2;
        }


        // scan rows then scan columns.
        // a single row consists of:
        // [MARK, PLAYER]
        for (int i = 0; i < game_board.length; i+=3) {
            int[] row = game_board[i];
            int[] middle_row = game_board[last_row_id - 1];
            int[] last_row = game_board[last_row_id];

            positions[0] = row;
            positions[1] = middle_row;
            positions[2] = last_row;

            int[] result = scanPosition(positions, P1, P2, C);

            if(result[0] == 0) {
                response[0] = result[1];
                response[1] = 5;
                return response;
            }

            last_row_id += 3;

            if(last_row_id > 8) {
                // scan columns.
                for (int v = 0; v < 3; v++) {
                    int[] column = game_board[v];
                    int[] second_column = game_board[second_column_id];
                    int[] last_column = game_board[last_column_id];

                    positions[0] = column;
                    positions[1] = second_column;
                    positions[2] = last_column;

                    result = scanPosition(positions, P1, P2, C);

                    if(result[0] == 0) {
                        response[0] = result[1];
                        response[1] = 5;
                        return response;
                    }

                    second_column_id += 1;
                    last_column_id += 1;
                }


                break;
            }

        }


        return response;
    }




    public static int[] start(Scanner s) {
        int[] response = {0, 0};

        // Names
        String computer_name = "Computer";
        String p_one_name = "Player 1";
        String p_two_name = "Player 2";

        String[] names = {p_one_name, p_two_name, computer_name};

        // Marks
        // annotated with final to make sure that I don't change them by accident.
        final int X = 0;
        final int O = 1;

        // by default play with computer
        // when false play with player 2
        boolean computer_play = true;

        // Score rate
        // initialized at startup.
        int score_rate = (int) Gameplay.generateScoreRate() * 2;

        // players scores -> runtime score.
//        int computer_score = 0;
//        int p_one_score = 0;
//        int p_two_score = 0;

//        int board_style = 0;

        // players
        final int P1 = 0; // player 1
        final int P2 = 1; // player 2
        final int C = 2; // computer

        // create game board on startup.
        int[][] game_board = Gameplay.initializeGameBoard();

        int left_positions = 9;

        HashMap<Integer, Boolean> positions = new HashMap<>();

        int turn = P1;

        do {
            String[] menu = {
                    "Play VS Computer\n",
                    "Play Vs Another Player (Same Laptop)"
            };

            Menu.render(menu);

            int choice = Menu.createMenuInput("Select Mode: ", s);
            int result = Validate.selection(choice, menu.length, s);

            s.nextLine(); // intercept

            if(result == -1) {
                continue;
            }

            if(choice == 2) {
                computer_play = false;
            }

            break;
        } while (true);

        // build hash map for available positions
        // { 0:false, 1:true .... }
        for (int i = 0; i < game_board.length; i++) {
            positions.put(i, false);
        }

        while (true) {
            System.out.println();
            System.out.println();



            // print game board here
//            _debugGameBoard(game_board);
            Layout.render(game_board, X, O);

            int[] winner = detectWinner(game_board, P1, P2, C);

            if(winner[1] == 5) {
                System.out.println("Winner is: " + names[winner[0]] + " Scored: " + score_rate + " Points.");

                GameSystem.hold(s);
                return response;
            }

            // I need to check for left positions
            if(left_positions == 0) {
                System.out.println("TIE. No one won.");
                System.out.println("No Positions left.");

                GameSystem.hold(s);
                break;
            }

            // ask for input
            int[] data = createInput(s, turn, names, computer_play);

            if(data[0] == -1) {
                response[0] = -5;
                return response;
            }

            if(data[0] == -2) {
                break;
            }

            if(data[0] == -3) {
                continue;
            }

            int choice = data[1];

            int result = Validate.gamePlay(choice, positions, s);


            if(result == -1) {
                continue;
            }

            if (computer_play) {
                acquirePosition(game_board, (choice - 1), X, P1);
                scanAvailablePositions(game_board, positions);

                ArrayList<Integer> computer_available_positions = Computer.availableComputerPositions(positions);

                int computer_row_acq = Computer.generateRowId(computer_available_positions);

                if(computer_row_acq == -1) {
                    left_positions = 0;
                    continue;
                }

                acquirePosition(game_board, computer_row_acq, O, C);
                scanAvailablePositions(game_board, positions);

                continue;
            }

            if(turn == P1 && !computer_play) {
                acquirePosition(game_board, (choice - 1), X, P1);
                scanAvailablePositions(game_board, positions);
                turn = P2;
            } else if(turn == P2 && !computer_play) {
                acquirePosition(game_board, (choice - 1), O, P2);
                scanAvailablePositions(game_board, positions);
                turn = P1;
            }

            left_positions -= 1;
        }

        return response;
    }
}

