package ru.spbu.apcyb.svp.tasks.task1;

import java.util.*;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

import static java.lang.Long.parseLong;

/**
 * Atm
 */
public class Task1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter amount: ");
        long amount = parseLongg(sc.nextLine());

        System.out.print("Enter denominations: ");
        String denominations = sc.nextLine();
        List<Long> nums = new ArrayList<>(Arrays
                .stream(denominations.split("\\s"))
                .filter(s -> !s.isEmpty())
                .map(Task1::parseLongg)
                .toList());
        sc.close();

        List<List<Long>> ans = getCombinations(amount, nums);
        System.out.println("==================");
        System.out.print("Сombination count: ");
        System.out.println(ans.size());
        System.out.println("==================");
        System.out.println("All combinations: ");
        ans.forEach(System.out::println);
    }

    /**
     * Подпрограмма для перевода строки в массив чисел.
     *
     * @param s строка со списком доступных номиналов, записанных через пробел.
     * @return массив купюр.
     */
    public static long parseLongg(String s) {
        try {
            return parseLong(s);
        } catch (NumberFormatException e) {
            String forStr = "For string \"" + s + "\"";
            if (Pattern.compile("[^-+0-9]").matcher(s).find()) {
                throw new NumberFormatException(
                        forStr + ": a long consists only of a sign and digits from 0 to 9");
            }
            int pc= StringUtils.countMatches(s, '+');
            int mc = StringUtils.countMatches(s, '-');
            if (pc + mc > 1){
                throw new NumberFormatException(
                        forStr + ": too many sign symbols for an long");
            }
            throw new NumberFormatException(
                    forStr + ": the sign must go ahead of a number");
        }
    }

    /**
     * Алгоритм для нахождения комбинаций.
     *
     * @param amount  сумма для размена.
     * @param minDenomination  минимальная купюра из имеющихся для размена.
     * @param denominations  имеющиеся номиналы для размена.
     * @param currentCombination  массив для хранения одной (текущей) комбинации купюр.
     * @param combinations  массив массивов, в который будет записан результат всех комбинаций купюр.
     */
    public static void calcCombinations(long amount, long minDenomination, List<Long> denominations,
                                        List<Long> currentCombination,
                                        List<List<Long>> combinations) {

        if (amount == 0) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        long currentDenomination;
        int i;
        for (i = 0; i < denominations.size(); i++) {
            currentDenomination = denominations.get(i);
            if ((minDenomination >= currentDenomination) && (amount >= currentDenomination)) {
                currentCombination.add(currentDenomination);
                calcCombinations(amount - currentDenomination, currentDenomination,
                        denominations, currentCombination, combinations);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }

    /**
     * Метод, который вычисляет всевозможные варианты разложения суммы по имеющимся номиналам,
     * сортированными по возрастанию.
     *
     * @param amount  сумма, которую нужно разменять.
     * @param denominations  имеющиеся номиналы для размена.
     *
     * @return массив массивов всех вохможных комбинаций размена суммы.
     *
     */
    public static List<List<Long>> getCombinations(long amount, List<Long> denominations) {
        if (denominations == null) {
            throw new InputMismatchException("Numbers must not be null");
        }
        if (denominations.isEmpty()) {
            throw new IllegalArgumentException("Numbers must not be empty");
        }
        if (amount <= 0 || denominations.stream().anyMatch(n -> (n <= 0))) {
            throw new IllegalArgumentException("Sum and numbers must be positive");
        }

        denominations = new ArrayList<>(denominations.stream().distinct().toList());
        Collections.sort(denominations);

        List<Long> currentCombination = new ArrayList<>();
        List<List<Long>> combinations = new ArrayList<>();
        long maxDenomination = denominations.get(denominations.size()-1);

        calcCombinations(amount, maxDenomination, denominations, currentCombination, combinations);
        return combinations;
    }
}
