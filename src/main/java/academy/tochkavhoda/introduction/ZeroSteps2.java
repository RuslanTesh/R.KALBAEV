package academy.tochkavhoda.introduction;

import java.util.Random;

public class ZeroSteps2 {

    // 1. Сумма квадратов целых чисел от 1 до count
    public int sumSquares(int count) {
        int result = 0;
        for (int i = 1; i <= count; i++) {
            result += i * i;
        }
        return result;
    }

    // 2. Сумма нечётных целых чисел от 1 до count
    public int sumOdds(int count) {
        int result = 0;
        for (int i = 1; i <= count; i += 2) {
            result += i;
        }
        return result;
    }

    // 3. Сумма обратных величин целых чисел от 1 до count
    public double sumInverses(int count) {
        double result = 0.0;
        for (int i = 1; i <= count; i++) {
            result += 1.0 / i;
        }
        return result;
    }

    // 4. Факториал числа count
    public long factorial(int count) {
        if (count < 0) throw new IllegalArgumentException("Отрицательные аргументы недопустимы");
        long result = 1L;
        for (int i = 2; i <= count; i++) {
            result *= i;
        }
        return result;
    }

    // 5. Произведение цифр числа value
    public int prodDigits(int value) {
        int product = 1;
        while (value > 0) {
            product *= value % 10;
            value /= 10;
        }
        return product;
    }

    // 6. Числа Фибоначчи (без рекурсии)
    public int fibonacci(int number) {
        if (number == 0 || number == 1) return number;
        int prevPrev = 0;
        int prev = 1;
        int current = 0;
        for (int i = 2; i <= number; i++) {
            current = prev + prevPrev;
            prevPrev = prev;
            prev = current;
        }
        return current;
    }

    // 7. Сумма степеней двойки от 2^0 до 2^max
    public long sum2Powers(int max) {
        if (max < 0) {
            return 0;
        }
        if (max >= 63) {
            long result = 0;
            for (int i = 0; i <= max; i++) {
                long power = 1L << i;
                if (result > Long.MAX_VALUE - power) {
                    return (1L << (max + 1)) - 1;
                }
                result += power;
            }
            return result;
        }
        return (1L << (max + 1)) - 1;
    }

    // 8. Сумма квадратов целых чисел от 1 до count с барьером
    public int sumSquaresWithBarrier(int count, int barrier) {
        int result = 0;
        for (int i = 1; i <= count; i++) {
            int square = i * i;
            if (square > barrier) {
                break;
            }
            if (result + square > barrier) {
                break;
            }
            result += square;
        }
        return result;
    }

    // 9. Сумма произведений пар чисел (от 1 до count1 и от 1 до count2)
    public int sumPairProd(int count1, int count2) {
        int result = 0;
        for (int i = 1; i <= count1; i++) {
            for (int j = 1; j <= count2; j++) {
                result += i * j;
            }
        }
        return result;
    }

    // 10. Сумма произведений пар чисел с ограничением по барьеру
    public int sumPairProdWithBarrier(int count1, int count2, int barrier) {
        int result = 0;
        for (int i = 1; i <= count1; i++) {
            for (int j = 1; j <= count2; j++) {
                int prod = i * j;
                if (prod >= barrier) continue;
                result += prod;
            }
        }
        return result;
    }

    // 11. Сумма обратных значений произведений пар чисел
    public double sumInversePairProd(int count1, int count2) {
        double result = 0.0;
        for (int i = 1; i <= count1; i++) {
            for (int j = 1; j <= count2; j++) {
                result += 1.0 / (i * j);
            }
        }
        return result;
    }

    // 12. Сумма тройных произведений (i * j * k)
    public int sumTripleProd(int count1, int count2, int count3) {
        int result = 0;
        for (int i = 1; i <= count1; i++) {
            for (int j = 1; j <= count2; j++) {
                for (int k = 1; k <= count3; k++) {
                    result += i * j * k;
                }
            }
        }
        return result;
    }

    // 13. Приближённое значение экспоненты e
    public double calculateE() {
        double result = 1.0;
        double term = 1.0;
        int denominator = 1;
        do {
            term /= denominator++;
            result += term;
        } while (term > 1E-6);
        return result;
    }

    // 14. Приближённое значение числа pi методом Лейбница
    public double calculatePi() {
        double result = 0.0;
        double sign = 1.0;
        int denominator = 1;
        double term = 1.0;
        do {
            result += sign * term;
            sign *= -1;
            denominator += 2;
            term = 1.0 / denominator;
        } while (Math.abs(term) > 1E-8);
        return 4 * result;
    }

    // 15. Оценка площади круга методом Монте-Карло
    public double calculateCircleSquare(double length, int count) {
        Random random = new Random();
        int insideCount = 0;
        double centerX = length / 2.0;
        double centerY = length / 2.0;
        double radius = length / 2.0;

        for (int i = 0; i < count; i++) {
            double x = random.nextDouble() * length;
            double y = random.nextDouble() * length;
            if (Math.hypot(centerX - x, centerY - y) <= radius) {
                insideCount++;
            }
        }
        return length * length * insideCount / (double) count;
    }
}