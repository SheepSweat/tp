package seedu.modtrack.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.modtrack.commands.MarkCommand;
import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;


class MarkCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ArrayList<Mod> modList;
    private Mod testMod;
    private Ui ui;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(this.outContent));
        this.modList = new ArrayList<>();
        this.ui = new Ui();
        // Setup: Create a module that is currently incomplete
        this.testMod = new Mod("CS2113", 2, 1, 4);
        this.modList.add(this.testMod);
    }

    @AfterEach
    void tearDown() {
        System.setOut(this.originalOut);
    }

    @Test
    void execute_moduleFound_marksAsDoneAndPrintsCorrectFormat() {
        // 1. Setup: Use the correct name
        MarkCommand command = new MarkCommand("CS2113");

        // 2. Act
        command.execute(this.modList, this.ui);

        // 3. Assert
        String output = this.outContent.toString().replace("\r\n", "\n").trim();

        // Verify output format exactly as requested
        assertTrue(output.contains("Module marked as completed:\nCS2113"),
                "Output should show the success message followed by the module name on a new line");

        // Verify the logic/state change
        assertTrue(this.testMod.getIsComplete(), "The module's internal state should be marked as done");
    }

    @Test
    void execute_caseInsensitive_marksSuccessfully() {
        // Test with lowercase input
        MarkCommand command = new MarkCommand("cs2113");
        command.execute(this.modList, this.ui);

        assertTrue(this.testMod.getIsComplete(), "Marking should be case-insensitive");
    }

    @Test
    void execute_moduleNotFound_printsErrorMessage() {
        MarkCommand command = new MarkCommand("NonExistentMod");
        command.execute(this.modList, this.ui);

        String output = this.outContent.toString().trim();
        assertTrue(output.contains("No modules found in the list."), "Should handle missing modules gracefully");
    }
}
