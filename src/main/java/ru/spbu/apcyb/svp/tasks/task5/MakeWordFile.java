package ru.spbu.apcyb.svp.tasks.task5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

public class MakeWordFile implements Runnable {

    private final int lowBound;
    private final String word;
    private final Integer num;
    private final String dir;
    static Logger log = Logger.getLogger(MakeWordFile.class.getName());
    /**
     * Создаем слову соответствующий файл.
     *
     * @param num - количество слов.
     * @param word - само слово.
     * @param dir дирректория, где создаем файл.
     * @param lowBound - нижняя граница количества вхождений слова для создания файла.
     */
    public MakeWordFile(Integer num, String word, String dir, Integer lowBound) {
        this.num = num;
        this.word = word;
        this.dir = dir;
        this.lowBound = lowBound;
    }

    @Override
    public void run() {
        if (!new File(dir).isDirectory()) {
            throw new IllegalArgumentException("dir не является директорией");
        }
        File out = new File(dir, word + ".txt");
        if (num >= lowBound) {
            try (FileWriter fileWriter = new FileWriter(out)) {
                fileWriter.write(String.join(" ", Collections.nCopies(num, word)));
            } catch (IOException e) {
                log.severe(e.getMessage());
            }
        }
    }
}