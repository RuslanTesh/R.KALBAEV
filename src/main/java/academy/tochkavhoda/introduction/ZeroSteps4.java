package academy.tochkavhoda.introduction;

public class ZeroSteps4 {

    public int min(int[][] matrix) {
        if (matrix.length == 0) {
            return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int[] row : matrix) {
            if (row.length == 0) {
                continue;
            }
            for (int num : row) {
                if (num < min) {
                    min = num;
                }
            }
        }
        return min;
    }

    public int max(int[][] matrix) {
        if (matrix.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            if (row.length == 0) {
                continue;
            }
            for (int num : row) {
                if (num > max) {
                    max = num;
                }
            }
        }
        return max;
    }

    public boolean find(int[][] matrix, int value) {
        for (int[] row : matrix) {
            for (int num : row) {
                if (num == value) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for (int[] row : matrix) {
            for (int i = 0; i < row.length - 1; i++) {
                if (row[i] <= row[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hasNoZeroRows(int[][] matrix) {
        int count = 0;
        for (int[] row : matrix) {
            boolean hasZero = false;
            for (int num : row) {
                if (num == 0) {
                    hasZero = true;
                    break;
                }
            }
            if (!hasZero) {
                count++;
            }
        }
        return count;
    }

    public boolean hasFullZeroRow(int[][] matrix) {
        for (int[] row : matrix) {
            if (row.length == 0) {
                continue;
            }
            boolean allZeros = true;
            for (int num : row) {
                if (num != 0) {
                    allZeros = false;
                    break;
                }
            }
            if (allZeros) {
                return true;
            }
        }
        return false;
    }

    public boolean isSymmetric(int[][] matrix) {
        if (matrix.length == 0) {
            return true;
        }
        int n = matrix.length;
        for (int[] row : matrix) {
            if (row.length != n) {
                return false;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int mainDiagonalMax(int[][] matrix) {
        if (matrix.length == 0) {
            return Integer.MIN_VALUE;
        }
        int n = matrix.length;
        for (int[] row : matrix) {
            if (row.length != n) {
                return Integer.MIN_VALUE;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] > max) {
                max = matrix[i][i];
            }
        }
        return max;
    }

    public int mainDiagonalPositivesSum(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        for (int[] row : matrix) {
            if (row.length != n) {
                return 0;
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] > 0) {
                sum += matrix[i][i];
            }
        }
        return sum;
    }

    public int secondaryDiagonalMax(int[][] matrix) {
        if (matrix.length == 0) {
            return Integer.MIN_VALUE;
        }
        int n = matrix.length;
        for (int[] row : matrix) {
            if (row.length != n) {
                return Integer.MIN_VALUE;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (matrix[i][n - 1 - i] > max) {
                max = matrix[i][n - 1 - i];
            }
        }
        return max;
    }

    public int secondaryDiagonalPositivesSum(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        for (int[] row : matrix) {
            if (row.length != n) {
                return 0;
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int val = matrix[i][n - 1 - i];
            if (val > 0) {
                sum += val;
            }
        }
        return sum;
    }

    public int[][] matrixSum(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length) {
            throw new IllegalArgumentException("Массивы должны иметь одинаковое количество строк");
        }
        int rows = matrix1.length;
        int[][] result = new int[rows][];

        for (int i = 0; i < rows; i++) {
            if (matrix1[i].length != matrix2[i].length) {
                throw new IllegalArgumentException("Массивы должны иметь одинаковые размеры строк");
            }
            int cols = matrix1[i].length;
            result[i] = new int[cols];
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public void transpose(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public void interchange(int[][] matrix) {
        for (int i = 0; i < matrix.length; i += 2) {
            if (i + 1 < matrix.length) {
                int[] temp = matrix[i];
                matrix[i] = matrix[i + 1];
                matrix[i + 1] = temp;
            }
        }
    }

    public int[] toLinearByRow(int[][] matrix) {
        int totalElements = 0;
        for (int[] row : matrix) {
            totalElements += row.length;
        }
        int[] result = new int[totalElements];
        int index = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                result[index] = num;
                index++;
            }
        }
        return result;
    }

    public int[] toLinearByColumn(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int n = matrix.length;
        int[] result = new int[n * n];
        int index = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                result[index] = matrix[i][j];
                index++;
            }
        }
        return result;
    }

    public int[] toLinearByRowUpperTriangle(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int n = matrix.length;
        int totalElements = n * (n + 1) / 2;
        int[] result = new int[totalElements];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                result[index] = matrix[i][j];
                index++;
            }
        }
        return result;
    }

    public int sumUntilNotFoundInRow(int[][] matrix, int barrier) {
        int totalSum = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                if (num == barrier) {
                    break;
                }
                totalSum += num;
            }
        }
        return totalSum;
    }

    public int sumUntilNotFound(int[][] matrix, int barrier) {
        int totalSum = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                if (num == barrier) {
                    return totalSum;
                }
                totalSum += num;
            }
        }
        return totalSum;
    }
}