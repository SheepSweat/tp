package seedu.modtrack.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.modtrack.commands.ListCommand;
import seedu.modtrack.module.Mod;

class ListCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // This runs before EVERY test - sets up the "bucket" automatically
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    // This runs after EVERY test - puts the console back to normal
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_singleModule_printsFullFormattedOutput() {
        // 1. Setup
        ArrayList<Mod> modInterests = new ArrayList<>();
        // Assuming constructor: Mod(name, year, sem, credits)
        modInterests.add(new Mod("CS1010", 1, 1, 4));

        ListCommand command = new ListCommand();

        // 2. Act
        command.execute(modInterests);

        // 3. Assert - We get the output and normalize line endings
        String output = outContent.toString().replace("\r\n", "\n").trim();

        // Check for the Header
        assertTrue(output.contains("===== Your Tracked Modules ====="), "Header missing!");

        // Check for exact formatting of the fields
        assertTrue(output.contains("Name: CS1010"), "Module name formatted incorrectly");
        assertTrue(output.contains("Year: YEAR1"), "Year formatting incorrect");
        assertTrue(output.contains("Semester: SEM1"), "Semester formatting incorrect");
        assertTrue(output.contains("Modular Credits: 4"), "Credits missing or wrong");
        assertTrue(output.contains("Status: Incomplete"), "Default status should be Incomplete");
    }

    @Test
    void execute_emptyList_printsCleanMessage() {
        ListCommand command = new ListCommand();
        command.execute(new ArrayList<>());

        String output = outContent.toString().trim();

        // Using assertEquals is more robust than assertTrue(contains) for short messages
        String expected = "===== Your Tracked Modules =====" + System.lineSeparator() + "No modules tracked yet.";
        assertEquals(expected.trim(), output);
    }
}
