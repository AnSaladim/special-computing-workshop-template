package ru.spbu.apcyb.svp.tasks.task1;

import java.util.*;

/**
 * Atm
 */
public class Task1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter amount: ");
        String stringAmount = in.nextLine();
        System.out.print("Enter denominations: ");
        String stringBanknotes = in.nextLine();
        in.close();

        List<List<Long>> ans = getCombinations(stringAmount, stringBanknotes);
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
     * @param stringBanknotes строка со списком доступных номиналов, записанных через пробел.
     * @return массив купюр.
     */
    public static long[] inputLongList(String stringBanknotes) {

        String[] splitStr = stringBanknotes.split("\\D+");

        long[] banknotes = new long[splitStr.length];

        for (int i = 0; i < banknotes.length; i++) {
            try {
                banknotes[i] = Long.parseLong(splitStr[i]);
                if (banknotes[i] <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.print( "incorrect input denominations");
                throw e;
            }
        }
        if (banknotes.length == 0) {
            System.out.print( "denominations must not be empty");
            throw new NumberFormatException();
        }
        return banknotes;
    }

    /**
     * Метод, который вычисляет всевозможные варианты разложения суммы по имеющимся номиналам,
     * сортированными по возрастанию.
     *
     * @param stringAmount  сумма, которую нужно разменять.
     * @param stringBanknotes  имеющиеся номиналы для размена.
     *
     * @return массив массивов всех вохможных комбинаций размена суммы.
     *
     */
    public static List<List<Long>> getCombinations(String stringAmount, String stringBanknotes) {

        long amount;
        try {
            amount = Long.parseLong(stringAmount);
        } catch (NumberFormatException e) {
            System.out.print( "incorrect input amount");
            throw e;
        }
        if (amount <= 0) {
            System.out.print("amount must be positive");
            throw new NumberFormatException();
        }
        long[] nums = inputLongList(stringBanknotes);
        nums = Arrays.stream(nums).distinct().toArray();

        List<Long> denominations = new ArrayList<>();
        int i;
        for (i = 0; i < nums.length; i++) {
            denominations.add(nums[i]);
        }
        Collections.sort(denominations);

        List<Long> currentCombination = new ArrayList<>();
        List<List<Long>> combinations = new ArrayList<>();
        long maxDenomination = denominations.get(denominations.size()-1);

        calcCombinations(amount, maxDenomination, denominations, currentCombination, combinations);
        return combinations;
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
}
