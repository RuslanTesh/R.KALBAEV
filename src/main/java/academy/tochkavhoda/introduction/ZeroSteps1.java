package academy.tochkavhoda.introduction;

public class ZeroSteps1 {
    // Сумма двух целых чисел
    public int sum(int x, int y) { return x + y; }

    // Произведение двух целых чисел
    public int mul(int x, int y) { return x * y; }

    // Частное от деления двух целых чисел
    public int div(int x, int y) { return x / y; }

    // Остаток от деления двух целых чисел
    public int mod(int x, int y) { return x % y; }

    // Проверка равенства двух целых чисел
    public boolean isEqual(int x, int y) { return x == y; }

    // Проверка, является ли первое число большим второго
    public boolean isGreater(int x, int y) { return x > y; }

    // Площадь прямоугольного треугольника по двум катетам
    public double calculateTriangleSquare(int side1, int side2) { return 0.5 * side1 * side2; }

    // Периметр прямоугольного треугольника по двум катетам
    public double calculateTrianglePerimeter(int side1, int side2) {
        double hypotenuse = Math.sqrt(side1*side1 + side2*side2);
        return side1 + side2 + hypotenuse;
    }

    // Число, записанное цифрами наоборот
    public int reverseNumber(int number) {
        return Integer.parseInt(new StringBuilder(String.valueOf(number)).reverse().toString());
    }

    // Быстрое возведение в пятнадцатую степень
    public long calculate15Degree(int number) {
        long n = number;
        long pow2 = n * n;      // n²
        long pow4 = pow2 * pow2; // n⁴
        long pow8 = pow4 * pow4; // n⁸
        long pow3 = pow2 * n;     // n³
        return pow8 * pow4 * pow3; // n¹⁵ = n⁸ × n⁴ × n³
    }

    // Находится ли точка внутри прямоугольника?
    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        return x >= xLeft && x <= xRight && y >= yTop && y <= yBottom;
    }

    // Квадратный корень модуля числа
    public double sqrtAbs(int number) { return Math.sqrt(Math.abs((double)number)); }

    // Существует ли треугольник с указанными длинами сторон?
    public boolean isTriangleExist(int side1, int side2, int side3) {
        return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1;
    }

    // Произведение всех цифр четырёхзначного числа
    public int getDigitsProduction(int number) {
        int prod = 1;
        while (number > 0) {
            prod *= number % 10;
            number /= 10;
        }
        return prod;
    }

    // Может ли круг вписаться внутрь квадрата без касания границ?
    public boolean isCircleInsideSquare(int radius, int side) { return radius * 2 < side; }

    // Возвращение символа по его ASCII-коду
    public char getCharByCode(short code) { return (char)(code & 0xffff); }

    // Получение ASCII-кода символа
    public short getCodeByChar(char character) { return (short)character; }

    // Смещение символа на указанное количество позиций
    public char getCharByCodeAndOffset(char ch, short offset) { return (char)((ch & 0xffff) + offset); }

    // Больше или равно код символа
    public boolean isGreaterOrEqual(char ch1, char ch2) { return ((int)ch1) >= ((int)ch2); }

    // Среднее значение символов по их коду
    public char getAverageChar(char ch1, char ch2) { return (char)(((int)ch1 + (int)ch2)/2); }
}
