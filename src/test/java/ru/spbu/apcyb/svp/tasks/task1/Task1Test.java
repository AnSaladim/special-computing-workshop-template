package ru.spbu.apcyb.svp.tasks.task1;

import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.spbu.apcyb.svp.tasks.task1.Task1.getCombinations;

class Task1Test {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    @BeforeEach
    public void setUpOutput() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "8 \n",
            "5\n1,2,3\n",
            "asd\n2 1\n",
            "5\n a\n b\n",
            "3+2\n1+1 2+1\n",
            " \n",
            "\n",
            "8.5\n1 2.2 3\n",
            "+4-2\n",
            "92233720368547758077"
    })
    void testWrongFormatInput(String input) {
        provideInput(input);

        Assertions.assertThrows(NumberFormatException.class, () -> Task1.main(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "5\n \n",
            "5\n         \n"
    })
    void testEmptyNumbersInput(String input) {
        provideInput(input);

        Assertions.assertThrows(IllegalArgumentException.class, () -> Task1.main(null));
    }

    @Test
    void testCalcCombinationsSingleDenomination() {
        long sum = 8;
        List<Long> numbers = new ArrayList<>();
        numbers.add(8L);
        List<List<Long>> target = new ArrayList<>();
        target.add(new ArrayList<>(numbers));

        List<List<Long>> result = getCombinations(sum, numbers);
        assertEquals(target, result);
    }

    @Test
    void testGetCombinationsMultipleDenominations1() {
        long sum = 8;
        List<Long> numbers = Arrays.asList(1L, 2L, 3L);
        List<List<Long>> target = new ArrayList<>();
        target.add(Arrays.asList(1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L));
        target.add(Arrays.asList(2L, 1L, 1L, 1L, 1L, 1L, 1L));
        target.add(Arrays.asList(2L, 2L, 1L, 1L, 1L, 1L));
        target.add(Arrays.asList(2L, 2L, 2L, 1L, 1L));
        target.add(Arrays.asList(2L, 2L, 2L, 2L));
        target.add(Arrays.asList(3L, 1L, 1L, 1L, 1L, 1L));
        target.add(Arrays.asList(3L, 2L, 1L, 1L, 1L));
        target.add(Arrays.asList(3L, 2L, 2L, 1L));
        target.add(Arrays.asList(3L, 3L, 1L, 1L));
        target.add(Arrays.asList(3L, 3L, 2L));

        List<List<Long>> result = getCombinations(sum, numbers);
        assertEquals(target, result);
    }

    @Test
    void testGetCombinationsMultipleDenominations2() {
        long sum = 5;
        List<Long> numbers = Arrays.asList(2L, 3L);
        List<List<Long>> target = new ArrayList<>();
        target.add(Arrays.asList(3L, 2L));

        List<List<Long>> result = getCombinations(sum, numbers);
        assertEquals(target, result);
    }

    @Test
    void testGetCombinationsMultipleDenominations3() {
        long sum = 4;
        List<Long> numbers = Arrays.asList(2L, 1L);
        List<List<Long>> target = new ArrayList<>();
        target.add(Arrays.asList(1L, 1L, 1L, 1L));
        target.add(Arrays.asList(2L, 1L, 1L));
        target.add(Arrays.asList(2L, 2L));

        List<List<Long>> result = getCombinations(sum, numbers);
        assertEquals(target, result);
    }

    @Test
    void testGetCombinationsMultipleDenominations4() {
        long sum = 4;
        List<Long> numbers = Arrays.asList(1L, 2L);
        List<List<Long>> target = new ArrayList<>();
        target.add(Arrays.asList(1L, 1L, 1L, 1L));
        target.add(Arrays.asList(2L, 1L, 1L));
        target.add(Arrays.asList(2L, 2L));

        List<List<Long>> result = getCombinations(sum, numbers);
        assertEquals(target, result);
    }

    @Test
    void testGetCombinationsMultipleDenominations5() {
        long sum = 2+3;
        List<Long> numbers = Arrays.asList(1L+1L, 1L+2L);
        List<List<Long>> target = new ArrayList<>();
        target.add(Arrays.asList(3L, 2L));

        List<List<Long>> result = getCombinations(sum, numbers);
        assertEquals(target, result);
    }

    @Test
    void testCalcCombinationsSingleDenomination2() {
        long sum = 300000000;
        List<Long> numbers = new ArrayList<>();
        numbers.add(300000000L);
        List<List<Long>> target = new ArrayList<>();
        target.add(new ArrayList<>(numbers));

        List<List<Long>> result = getCombinations(sum, numbers);
        assertEquals(target, result);
    }

    @Test
    void testGetCombinationsNoCombinationsPossible() {
        long sum = 8;
        List<Long> numbers = new ArrayList<>();
        numbers.add(6L);
        List<List<Long>> target = new ArrayList<>();

        List<List<Long>> result = getCombinations(sum, numbers);
        assertEquals(target, result);
    }

    @Test
    void testGetCombinationsZeroAmount() {
        long sum = 0;
        List<Long> numbers = Arrays.asList(6L, 2L);

        assertThrows(IllegalArgumentException.class, () -> getCombinations(sum, numbers));
    }

    @Test
    void testGetCombinationsNoDenominations() {
        long sum = 8;
        List<Long> numbers = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> getCombinations(sum, numbers));
    }

    @Test
    void testGetCombinationsNegativeAmount() {
        long sum = -8;
        List<Long> numbers = Arrays.asList(6L, 2L);

        assertThrows(IllegalArgumentException.class, () -> getCombinations(sum, numbers));
    }

    @Test
    void testGetCombinationsNegativeDenominations() {
        long sum = 5;
        List<Long> numbers = Arrays.asList(0L, -2L);

        assertThrows(IllegalArgumentException.class, () -> getCombinations(sum, numbers));
    }

    @Test
    void testGetCombinationsNullDenominations() {
        long sum = 5;
        List<Long> numbers = null;

        assertThrows(InputMismatchException.class, () -> getCombinations(sum, numbers));
    }
}
