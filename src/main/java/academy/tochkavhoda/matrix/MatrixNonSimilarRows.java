package academy.tochkavhoda.matrix;

import java.util.*;

public class MatrixNonSimilarRows {
    private final int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {
        // Для каждой строки вычисляем множество уникальных чисел
        List<Set<Integer>> rowSets = new ArrayList<>();
        for (int[] row : matrix) {
            Set<Integer> set = new HashSet<>();
            for (int val : row) set.add(val);
            rowSets.add(set);
        }

        // Для каждой строки проверяем, есть ли уже похожая строка с меньшим индексом
        Set<int[]> result = new LinkedHashSet<>();
        boolean[] included = new boolean[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            if (included[i]) continue;
            // Проверяем, есть ли уже похожая строка с меньшим индексом
            boolean hasSimilarBefore = false;
            for (int j = 0; j < i; j++) {
                if (rowSets.get(i).equals(rowSets.get(j))) {
                    hasSimilarBefore = true;
                    break;
                }
            }
            if (!hasSimilarBefore) {
                result.add(matrix[i]);
                included[i] = true;
            }
        }

        return result;
    }
}