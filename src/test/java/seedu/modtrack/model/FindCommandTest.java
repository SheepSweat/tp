package seedu.modtrack.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import seedu.modtrack.commands.*;
import seedu.modtrack.module.Mod;

class FindCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ArrayList<Mod> modList;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        modList = new ArrayList<>();
        // Adding a module that matches your described format
        modList.add(new Mod("CS2113", 2, 1, 4));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_foundModule_printsCorrectFormat() {
        FindCommand command = new FindCommand("CS2113");
        command.execute(modList);

        String output = outContent.toString().replace("\r\n", "\n");

        assertTrue(output.contains("Matching modules:"), "Header is missing");
        assertTrue(output.contains("--------------------"), "Separator line is missing");

        // 2. Verify all specific fields from your format
        assertTrue(output.contains("Name: CS2113"), "Module name not found");
        assertTrue(output.contains("Year: YEAR2"), "Year field formatted incorrectly");
        assertTrue(output.contains("Semester: SEM1"), "Semester field missing");
        assertTrue(output.contains("Status: Incomplete"), "Status field missing");
        assertTrue(output.contains("Progress: 0%"), "Progress field missing");
        assertTrue(output.contains("Modular Credits: 4"), "Credits field missing");
    }

    @Test
    void execute_caseInsensitiveSearch_findsModule() {
        FindCommand command = new FindCommand("cs2113");
        command.execute(modList);

        String output = outContent.toString();
        assertTrue(output.contains("Name: CS2113"), "Should find module regardless of case");
    }

    @Test
    void execute_noMatchingModule_printsNotFoundMessage() {
        FindCommand command = new FindCommand("History");
        command.execute(modList);

        String output = outContent.toString().trim();

        assertTrue(output.contains("Matching modules:"),
                "The header 'Matching modules:' was not printed.");

        assertTrue(output.contains("No matching module found."),
                "The 'not found' feedback message was missing for a non-existent keyword.");
    }
}