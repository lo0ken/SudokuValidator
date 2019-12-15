import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {
    public static final int FIELD[][] =  {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 4, 5, 6, 7, 8, 9, 1},
            {5, 6, 7, 8, 9, 1, 2, 3, 4},
            {8, 9, 1, 2, 3, 4, 5, 6, 7},
            {3, 4, 5, 6, 7, 8, 9, 1, 2},
            {6, 7, 8, 9, 1, 2, 3, 4, 5},
            {9, 1, 2, 3, 4, 5, 6, 7, 8}
    };

    public static void main(String[] args) {
        System.out.println(isFieldCorrect() ? "корректно" : "некорректно");
    }

    public static boolean isFieldCorrect() {
        return checkSizes() && checkValues() && isColumnsCorrect() && isRowsCorrect() && isSquaresCorrect();
    }

    private static boolean isLineCorrect(int[] line) {
        Set<Integer> elements = new HashSet<>();

        for (int i = 0; i < line.length; i++) {
            if (elements.contains(line[i]))
                return false;
            else
                elements.add(line[i]);
        }
        return true;
    }

    private static boolean checkSizes() {
        if (FIELD == null)
            return false;

        if (FIELD.length == 0)
            return false;

        if (FIELD.length != 9)
            return false;

        for (int i = 0; i < FIELD.length; i++) {
            if (FIELD.length != FIELD[i].length)
                return false;
        }
        return true;
    }

    private static boolean checkValues() {
        for (int i = 0; i < FIELD.length; i++) {
            for (int j = 0; j < FIELD[i].length; j++) {
                if (FIELD[i][j] <= 0 || FIELD[i][j] > 9)
                    return false;
            }
        }
        return true;
    }

    private static boolean isSquaresCorrect() {
        int[] square1 = new int[FIELD.length];
        int[] square2 = new int[FIELD.length];
        int[] square3 = new int[FIELD.length];
        int index = 0;

        for (int i = 0; i < FIELD.length; i++) {
            for (int j = 0; j < FIELD.length; j++) {
                if (j < 3)
                    square1[index] = FIELD[i][j];
                else if (j < 6)
                    square2[index -3] = FIELD[i][j];
                else
                    square3[index - 6] = FIELD[i][j];
                index++;
            }

            if (index == 15)
                index = 0;
            else
                index -= 6;

            if (i == 2 || i == 5) {
                boolean valid = isLineCorrect(square1) && isLineCorrect(square2) && isLineCorrect(square3);
                if (!valid)
                    return false;

                square1 = new int[FIELD.length];
                square2 = new int[FIELD.length];
                square3 = new int[FIELD.length];
            }
        }
        return isLineCorrect(square1) && isLineCorrect(square2) && isLineCorrect(square3);
    }

    private static boolean isRowsCorrect() {
        for (int i = 0; i < FIELD.length; i++) {
            if (!isLineCorrect(FIELD[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColumnsCorrect() {
        int[] line;

        for (int i = 0; i < FIELD.length; i++) {
            line = new int[FIELD.length];
            for (int j = 0; j < FIELD.length; j++) {
                line[j] = FIELD[j][i];
            }

            if (!isLineCorrect(line)) {
                return false;
            }
        }
        return true;
    }
}