package ru.spbu.apcyb.svp.tasks.task3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.Task3.Task3;

/**
 * Тесты для задания 3.
 */
class Task3Test {
    @Test
    void dirNotExists() {
        boolean thrown = false;

        try {
            FileWriter file = new FileWriter("testfile");
            File dir = new File("skgsvlnsxckmvnxclv").getAbsoluteFile();
            Task3.displayDirectoryContents(dir, file);
        } catch (IOException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    void fileInExistingFolder() {
        assertThrows(
                FileNotFoundException.class,
                () -> {
                    FileWriter file = new FileWriter("ggsgsgsgsdgssdgs");
                    File dir = new File("/afaf/afaf").getAbsoluteFile();
                    Task3.displayDirectoryContents(dir, file);
                }
        );
    }

    @Test
    void normalRun() throws IOException {
        FileWriter file = new FileWriter("testfile");
        File dir = new File(".").getAbsoluteFile();
        Task3.displayDirectoryContents(dir, file);
    }

    @Test
    void normalRun2() throws IOException {
        FileWriter file = new FileWriter("testfile2");
        File dir = new File("C:\\Users\\share\\Downloads").getAbsoluteFile();
        Task3.displayDirectoryContents(dir, file);
    }

}
