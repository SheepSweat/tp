package seedu.modtrack.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import seedu.modtrack.commands.*;
import seedu.modtrack.module.Mod;

class UnmarkCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ArrayList<Mod> modList;
    private Mod testMod;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        modList = new ArrayList<>();

        // Setup: Add a module and mark it as 'Done' first
        testMod = new Mod("CS2113", 2, 1, 4);
        testMod.setToDone();
        modList.add(testMod);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_moduleFound_updatesStateAndPrintsCorrectFormat() {
        UnmarkCommand command = new UnmarkCommand("CS2113");

        command.execute(modList);

        String output = outContent.toString().replace("\r\n", "\n").trim();

        assertTrue(output.contains("Module marked as incomplete:\nCS2113"),
                "Output format should match the requirement exactly");

        assertFalse(testMod.getIsComplete(), "The module state must be updated to incomplete");
    }

    @Test
    void execute_caseInsensitive_findsAndUnmarks() {
        UnmarkCommand command = new UnmarkCommand("cs2113");
        command.execute(modList);

        assertFalse(testMod.getIsComplete(), "Should be case-insensitive when unmarking");
    }

    @Test
    void execute_moduleNotFound_printsErrorMessage() {
        UnmarkCommand command = new UnmarkCommand("NonExistentMod");
        command.execute(modList);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Module not found."), "Should handle missing modules gracefully");
    }
}