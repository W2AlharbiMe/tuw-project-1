import java.util.ArrayList;
import java.util.HashMap;

public class Computer {
    public static ArrayList<Integer> availableComputerPositions(HashMap<Integer, Boolean> positions) {
        ArrayList<Integer> scanned_positions = new ArrayList<Integer>();

        for (int i = 0; i < positions.size(); i++) {
            if(!positions.get(i)) {
                scanned_positions.add(i);
            }
        }

        return  scanned_positions;
    }

    public static int generateRowId(ArrayList<Integer> available_positions) {
        int max = 0;
        int min = available_positions.size() - 1;
        int index = (int) ((Math.random() * (max - min)) + min);

        return available_positions.size() > 0 ? available_positions.get(index) : - 1;
    }

}


