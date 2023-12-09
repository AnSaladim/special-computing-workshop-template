package ru.spbu.apcyb.svp.tasks.task4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Double.parseDouble;
import static java.lang.String.format;

/**
 * Задание 4.
 */
class Task4 {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        int size = 10000;
        generateNumbersFile("num.txt", size);
        oneThreadTan("num.txt", "num_tan.txt", size);
        multithreadedTan("num.txt", "num_tan.txt", size, 8);
    }
    public static void generateNumbersFile(String fileWriteName, int size) throws IOException {
        FileWriter fileWriter = new FileWriter(fileWriteName, false);
        for (int i = 0; i < size; i++) {
            fileWriter.write(Float.toString(ThreadLocalRandom.current().nextFloat(-1000, 1000)) + " ");
        }
        fileWriter.flush();
    }

    public static boolean oneThreadTan(String fileReadName, String fileWriteName, int size) throws IOException {
        FileReader fileReader = new FileReader(fileReadName);
        FileWriter fileWriter = new FileWriter(fileWriteName, false);
        fileWriter.write("Количество вычисленных значений: " + size + "\n");

        BufferedReader fileBReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder("").append(fileBReader.readLine());
        String[] tmp = sb.toString().split(" ");

        long start = System.nanoTime();
        for (String integer : tmp) {
            final double i = parseDouble(integer);
            fileWriter.write(Math.tan(i) + " ");
        }
        fileReader.close();
        fileWriter.flush();
        System.out.println(format("One thread Executed by %d ns, size : %d",
                (System.nanoTime() - start), size));
        return true;
    }

    public static boolean multithreadedTan(String fileReadName, String fileWriteName, int size, int nThreads) throws IOException, ExecutionException, InterruptedException {
        FileReader fileReader = new FileReader(fileReadName);
        FileWriter fileWriter = new FileWriter(fileWriteName, false);
        fileWriter.write("Количество вычисленных значений: " + size + "\n");

        BufferedReader fileBReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder("").append(fileBReader.readLine());
        String[] tmp = sb.toString().split(" ");

        ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
        long start = System.nanoTime();

        List<CompletableFuture<Double>> futures = new ArrayList<>();
        for (String integer : tmp) {
            final double i = parseDouble(integer);
            futures.add(CompletableFuture.supplyAsync(() -> Math.tan(i), threadPool));
        }
        fileReader.close();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        for (Future<Double> future : futures) {
            fileWriter.write(String.valueOf(future.get()) + " ");
        }
        fileWriter.flush();
        System.out.println(format("Multi thread Executed by %d ns, size : %d",
                (System.nanoTime() - start), size));
        threadPool.shutdown();
        return true;
    }

}