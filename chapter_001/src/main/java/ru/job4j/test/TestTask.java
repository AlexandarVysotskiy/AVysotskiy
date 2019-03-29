package ru.job4j.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashSet;

/**
 * Class TestTask
 * <p>
 * <p>
 * Задание по Java
 * В сборниках занимательных математических задач встречается такой тип задач, когда надо из заданных чисел и арифметических знаков составить выражение, дающее какое-то заданное число. Пример такой задачи: "Составьте из чисел 4, 1, 8, 7 и арифметических знаков выражение, равное 24". Ответом может служить такое выражение: "(8-4) * (7-1)". К сожалению, некоторые авторы допускают ошибки и предлагают нерешаемую задачу, поэтому их начальник обратился к программистам.
 * <p>
 * Необходимо написать такую программу, которая определяет, можно ли построить из заданного набора выражение, равное числу N или нет. Так как это только прототип, то достаточно написать программу, которая работает только с наборами из 4-х чисел, а число N всегда равно 24.
 * <p>
 * Допустимые арифметические операторы: сложение, вычитание, умножение, деление, скобки.
 * <p>
 * На входе: массив из 4-х целых чисел от 1 до 9.
 * На выходе: true (если из заданного набора можно построить выражение, равное 24) или false (если из заданного набора такого выражения построить нельзя).
 * <p>
 * Пример 1.
 * На входе: [4, 1, 8, 7]
 * На выходе: true
 * Пояснение: (8-4) * (7-1) = 24
 * <p>
 * Пример 2.
 * На входе: [1, 2, 1, 2]
 * На выходе: false
 * Пояснение: из данного набора чисел невозможно составить выражение, равное 24.
 * <p>
 * Примечание: убедитесь, что в вашем решении деление корректно работает с дробями, т.е. например 4 / (1 - 2/3) = 12.
 * <p>
 * Представьте ваше решение в виде java-функции вида:
 * public boolean canBeEqualTo24(int[] nums) {
 * ...
 * }
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class TestTask {

    private ScriptEngineManager mgr = new ScriptEngineManager();
    private ScriptEngine engine = mgr.getEngineByName("JavaScript");
    private int count = 0;

    /**
     * Метод делает все возможные комбинации из чисел и проверят выражение их них на равенство.
     *
     * @param nums - массив из чисел.
     * @param num  - число равно которому будет выражение (можно использовать не только 24).
     * @return true - если выражение равно num;
     */
    public boolean canBeEqualTo24(int[] nums, int num) {

        if (nums.length == 0) {
            System.out.println("Массив пуст");
        }

        boolean result = false;
        String[] operators = {"/", "-", "*", "+"};
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (j != i) {
                    for (int k = 0; k < nums.length; k++) {
                        if (k != i && k != j) {
                            for (int l = 0; l < nums.length; l++) {
                                if (l != i && l != j && l != k) {
                                    if (combineAndPrint(convertFromIntToString(nums[i], nums[j], nums[k], nums[l]), operators, num) >= 1) {
                                        result = true;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        return result;
    }

    /**
     * Вспомогательный метод. Конвертирует массив типа int в String
     */
    private String[] convertFromIntToString(int... nums) {
        String[] result = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = String.valueOf(nums[i]);
        }
        return result;
    }

    /**
     * Вспомогательный метод. Комбинирует числа и логические операторы.
     *
     * @param pieces    массив входных чисел.
     * @param operators массив арифметических знаков.
     * @param num       - заданное число, которому должно быть равно выражение.
     * @return true если выражение равно числу.
     */
    private int combineAndPrint(String[] pieces, String[] operators, int num) {
        if (pieces.length == 1) {
            // если не пустой то идет проверка на равенство.
            if (parsingExpressionWithoutBrackets(pieces[0], num)) {
                count++;
                System.out.println(pieces[0]);
            }
            HashSet temp = addBracketsAndParsing(pieces[0], num);
            if (!temp.isEmpty()) {
                count++;
                for (Object s : temp) {
                    System.out.println(s);
                }
            }
        } else {
            String[] newPieces = new String[pieces.length - 1];
            // скопировать в него все, кроме первых двух
            for (int i = 2; i < pieces.length; i++) {
                newPieces[i - 1] = pieces[i];
            }
            // объединить первые две части и рекурсировать
            for (int i = 0; i < operators.length; i++) {
                newPieces[0] = pieces[0] + operators[i] + pieces[1];
                combineAndPrint(newPieces, operators, num);
            }
        }
        return count;
    }

    /**
     * Вспомогательный метод.
     * Распарсивает строковое выражение и проверет равен ли результат вычесленией заданному числу.
     */
    private boolean parsingExpressionWithoutBrackets(String in, int number) {
        boolean result = false;
        try {
            if ((int) engine.eval(in) == number) {
                result = true;
            }
        } catch (ScriptException s) {
            s.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * Вспомогательный метод.
     * Добавляет скобки, распарсивает строковое выражение и проверет равен ли результат вычесленией заданному числу.
     */
    private HashSet addBracketsAndParsing(String in, int number) {
        HashSet result = new HashSet();
        char[] cn = in.toCharArray();
        char[] first = {'(', cn[0], cn[1], cn[2], ')', cn[3], cn[4], cn[5], cn[6]};
        char[] second = {cn[0], cn[1], cn[2], cn[3], '(', cn[4], cn[5], cn[6], ')'};
        char[] third = {'(', cn[0], cn[1], cn[2], cn[3], cn[4], ')', cn[5], cn[6]};
        char[] fourth = {cn[0], cn[1], '(', cn[2], cn[3], cn[4], ')', cn[5], cn[6]};
        char[] fifth = {cn[0], cn[1], '(', cn[2], cn[3], cn[4], cn[5], cn[6], ')'};
        char[] sixth = {cn[0], cn[1], cn[2], cn[3], '(', cn[4], cn[5], cn[6], ')'};
        char[] seventh = {'(', cn[0], cn[1], cn[2], ')', cn[3], '(', cn[4], cn[5], cn[6], ')'};
        String firstFromCharArray = new String(first);
        String secondFromCharArray = new String(second);
        String thirdFromCharArray = new String(third);
        String fourthFromCharArray = new String(fourth);
        String fifthFromCharArray = new String(fifth);
        String sixthFromCharArray = new String(sixth);
        String seventhFromCharArray = new String(seventh);
        String[] temp = {firstFromCharArray, secondFromCharArray, thirdFromCharArray, fourthFromCharArray,
                fifthFromCharArray, sixthFromCharArray, seventhFromCharArray};
        try {
            for (String s : temp) {
                if ((int) engine.eval(s) == number) {
                    result.add(s);
                }
            }
        } catch (ScriptException s) {
            s.printStackTrace();
        } finally {
            return result;
        }
    }
}