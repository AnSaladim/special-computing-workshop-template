package ru.spbu.apcyb.svp.tasks.task1;

import java.util.*;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

import static java.lang.Long.parseLong;
import static ru.spbu.apcyb.svp.tasks.task1.Atm.getCombinations;

public class Task1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter amount: ");
        long sum = parseLongg(sc.nextLine());

        System.out.print("Enter denominations: ");
        String numbers = sc.nextLine();
        List<Long> nums = new ArrayList<>(Arrays
                .stream(numbers.split("\\s"))
                .filter(s -> !s.isEmpty())
                .map(Task1::parseLongg)
                .toList());
        sc.close();

        List<List<Long>> ans = getCombinations(sum, nums);
        System.out.print("Сombination count: ");
        System.out.println(ans.size());
        System.out.println("All combinations: ");
        ans.forEach(System.out::println);
    }

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
}
