package seedu.modtrack.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import seedu.modtrack.commands.UnmarkCommand;
import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;

class UnmarkCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ArrayList<Mod> modList;
    private Ui ui;
    private Mod testMod;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(this.outContent));
        this.modList = new ArrayList<>();
        this.ui = new Ui();

        // Setup: Add a module and mark it as 'Done' first
        this.testMod = new Mod("CS2113", 2, 1, 4);
        this.testMod.setToDone();
        this.modList.add(this.testMod);
    }

    @AfterEach
    void tearDown() {
        System.setOut(this.originalOut);
    }

    @Test
    void execute_moduleFound_updatesStateAndPrintsCorrectFormat() {
        UnmarkCommand command = new UnmarkCommand("CS2113");

        command.execute(this.modList, this.ui);

        String output = this.outContent.toString().replace("\r\n", "\n").trim();

        assertTrue(output.contains("Module marked as incomplete:\nCS2113"),
                "Output format should match the requirement exactly");

        assertFalse(this.testMod.getIsComplete(), "The module state must be updated to incomplete");
    }

    @Test
    void execute_caseInsensitive_findsAndUnmarks() {
        UnmarkCommand command = new UnmarkCommand("cs2113");
        command.execute(this.modList, this.ui);

        assertFalse(this.testMod.getIsComplete(), "Should be case-insensitive when unmarking");
    }

    @Test
    void execute_moduleNotFound_printsErrorMessage() {
        UnmarkCommand command = new UnmarkCommand("NonExistentMod");
        command.execute(this.modList, this.ui);

        String output = this.outContent.toString().trim();
        assertTrue(output.contains("No modules found in the list."), "Should handle missing modules gracefully");
    }
    @Test
    void execute_emptyList_printsGracefulError() {
        this.modList.clear();
        UnmarkCommand command = new UnmarkCommand("CS2113");

        command.execute(this.modList, this.ui);

        String output = this.outContent.toString().trim();
        assertTrue(output.contains("No modules found") , "Should handle empty list without crashing");
    }
}
