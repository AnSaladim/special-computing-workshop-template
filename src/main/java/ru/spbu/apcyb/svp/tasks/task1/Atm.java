package ru.spbu.apcyb.svp.tasks.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class Atm {

    /**Алгоритм для нахождения комбинаций.
     *
     * @param amount  сумма для размена.
     * @param numbers  номиналы для размена.
     * @param currentCombination  текущая комбинации.
     * @param combinations  всевозможные комбинации.
     */
    public static void calcCombinations(long amount, List<Long> numbers,
                                        List<Long> currentCombination,
                                        List<List<Long>> combinations, int i) {

        if (amount == 0) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        while (i < numbers.size() && amount - numbers.get(i) >= 0) {
            currentCombination.add(numbers.get(i));
            calcCombinations(amount - numbers.get(i), numbers, currentCombination, combinations, i);
            i++;
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    /**
     * Метод, который вычисляет всевозможные варианты разложения суммы по имеющимся номиналам.
     *
     * @param sum  сумма, которую нужно разменять.
     * @param numbers  имеющиеся номиналы.
     *
     * @return список разложений в сумму по номиналам. Список будет пуст, если sum
     *     невозможно разложить по данным номиналам.
     * @throws IllegalArgumentException  если среди суммы и номиналов есть
     *     неположительные.
     * @throws InputMismatchException  номиналов есть null.
     */
    public static List<List<Long>> getCombinations(long sum, List<Long> numbers) {
        if (numbers == null) {
            throw new InputMismatchException("Numbers must not be null");
        }
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("Numbers must not be empty");
        }
        if (sum <= 0 || numbers.stream().anyMatch(n -> (n <= 0))) {
            throw new IllegalArgumentException("Sum and numbers must be positive");
        }

        numbers = new ArrayList<>(numbers.stream().distinct().toList());
        Collections.sort(numbers);

        List<Long> currentCombination = new ArrayList<>();
        List<List<Long>> combinations = new ArrayList<>();

        calcCombinations(sum, numbers, currentCombination, combinations, 0);
        return combinations;
    }

}
