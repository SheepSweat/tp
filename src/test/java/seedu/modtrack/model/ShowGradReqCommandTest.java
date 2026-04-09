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

class ShowGradReqCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_printsAllGraduationSections() {

        ShowGradReqCommand command = new ShowGradReqCommand();
        ArrayList<Mod> emptyList = new ArrayList<>();


        command.execute(emptyList);

        String output = outContent.toString().replace("\r\n", "\n");

        assertTrue(output.contains("===== Computer Engineering Graduation Requirements ====="),
                "Main header is missing");

        assertTrue(output.contains("Core Modules:"), "Core Modules section header missing");
        assertTrue(output.contains("Project/Internship:"), "Project section header missing");
        assertTrue(output.contains("Engineering Common Curriculum:"), "Common Curriculum header missing");

        assertTrue(output.contains("- CS2113"), "CS2113 should be listed in core");
        assertTrue(output.contains("- CG4002/CG4001"), "Final Year Project should be listed");
        assertTrue(output.contains("- DTK1234"), "DTK1234 should be listed in CDE curriculum");
    }
}