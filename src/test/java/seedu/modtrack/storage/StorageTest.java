package seedu.modtrack.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.modtrack.module.Mod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    private static final String TEST_FILE_PATH = "./data/ModTrack.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        this.storage = new Storage();
    }

    @AfterEach
    public void tearDown() {
        // Clean up: Delete the test file after each test to ensure a fresh environment
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void saveAndLoad_validList_matchesOriginal() throws IOException {
        ArrayList<Mod> originalList = new ArrayList<>();
        Mod mod = new Mod("CS2113", 2, 1, 4);
        mod.setToDone();
        mod.addPrerequisite("CS1010");
        originalList.add(mod);

        // Save and then Reload
        this.storage.save(originalList);
        ArrayList<Mod> loadedList = this.storage.load();

        assertEquals(1, loadedList.size());
        assertEquals("CS2113", loadedList.get(0).getModName());
        assertTrue(loadedList.get(0).getIsComplete());
        assertEquals("CS1010", loadedList.get(0).getPrerequisites().get(0));
    }

    @Test
    public void load_currentSevenPartFormat_loadsCorrectly() throws IOException {
        File file = new File(TEST_FILE_PATH);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("1 | CS2113 | 2 | 1 | 4 | NORMAL | CS1010,MA1511" + System.lineSeparator());
        }

        ArrayList<Mod> loadedList = this.storage.load();

        // Verify the list size
        assertEquals(1, loadedList.size());

        // Verify specific attributes
        Mod loadedMod = loadedList.get(0);
        assertEquals("CS2113", loadedMod.getModName());
        assertTrue(loadedMod.getIsComplete());
        assertEquals(4, loadedMod.getModCredits());
        assertEquals("NORMAL", loadedMod.getCompletionType());

        // Verify prerequisites were parsed
        assertTrue(loadedMod.getPrerequisites().contains("CS1010"));
        assertTrue(loadedMod.getPrerequisites().contains("MA1511"));
    }

    @Test
    public void load_corruptedData_returnsEmptyListAndHandlesError() throws IOException {
        // Write invalid numeric data
        File file = new File(TEST_FILE_PATH);
        FileWriter fw = new FileWriter(file);
        fw.write("1 | CS2113 | NotAYear | 1 | 4" + System.lineSeparator());
        fw.close();

        ArrayList<Mod> list = this.storage.load();

        // Based on your catch(NumberFormatException) logic, it prints an error and
        // returns the list so far
        assertTrue(list.isEmpty());
    }

    @Test
    public void prepareFile_createsDirectoryAndFile() {
        // The constructor calls prepareFile()
        File folder = new File("./data/");
        File file = new File(TEST_FILE_PATH);

        assertTrue(folder.exists(), "Data directory should be created.");
        assertTrue(file.exists(), "Storage file should be created.");
    }
}
