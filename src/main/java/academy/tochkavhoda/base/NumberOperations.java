package academy.tochkavhoda.base;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class NumberOperations {

    public static Integer find(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return null;
    }

    public static Integer find(double[] array, double value, double eps) {
        for (int i = 0; i < array.length; i++) {
            if (Math.abs(array[i] - value) <= eps) {
                return i;
            }
        }
        return null;
    }

    public static Double calculateDensity(double weight, double volume, double min, double max) {
        if (volume == 0) {
            return null;
        }
        double density = weight / volume;
        if (density >= min && density <= max) {
            return density;
        }
        return null;
    }

    public static Integer find(BigInteger[] array, BigInteger value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return null;
    }

    public static BigDecimal calculateDensity(BigDecimal weight, BigDecimal volume, BigDecimal min, BigDecimal max) {
        if (volume.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        BigDecimal result = weight.divide(volume, 10, RoundingMode.HALF_UP);
        result = result.stripTrailingZeros();
        if (result.compareTo(min) >= 0 && result.compareTo(max) <= 0) {
            return result;
        }
        return null;
    }
}