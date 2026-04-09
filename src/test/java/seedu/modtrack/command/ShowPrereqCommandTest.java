package seedu.modtrack.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;
import seedu.modtrack.commands.ShowPrereqCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowPrereqCommandTest {
    private ArrayList<Mod> list;
    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
        System.setOut(new PrintStream(this.outContent));

        // Setup: Add a mod with prerequisites and one without
        Mod modWithPrereq = new Mod("CS2113", 2, 1, 4);
        modWithPrereq.addPrerequisite("CS1010");
        modWithPrereq.addPrerequisite("CS2040C");

        this.list.add(modWithPrereq);
        this.list.add(new Mod("GEA1000", 1, 1, 4));
    }

    @Test
    public void execute_modWithPrerequisites_printsList() {
        ShowPrereqCommand command = new ShowPrereqCommand("CS2113");
        command.execute(this.list, this.ui);

        String output = this.outContent.toString();
        assertTrue(output.contains("Prerequisites for CS2113:"));
        assertTrue(output.contains("CS1010, CS2040C"));
    }

    @Test
    public void execute_modWithoutPrerequisites_printsNone() {
        ShowPrereqCommand command = new ShowPrereqCommand("GEA1000");
        command.execute(this.list, this.ui);

        String output = this.outContent.toString();
        assertTrue(output.contains("Prerequisites for GEA1000:"));
        assertTrue(output.contains("None"));
    }

    @Test
    public void execute_moduleNotFound_printsErrorMessage() {
        ShowPrereqCommand command = new ShowPrereqCommand("MA1511");
        command.execute(this.list, this.ui);

        String output = this.outContent.toString();
        assertTrue(output.contains("No modules found in the list."));
    }

    @Test
    public void execute_caseInsensitivity_retrievesCorrectly() {
        // Testing with lowercase input "cs2113"
        ShowPrereqCommand command = new ShowPrereqCommand("cs2113");
        command.execute(this.list, this.ui);

        String output = this.outContent.toString();
        assertTrue(output.contains("Prerequisites for CS2113:"));
    }
}
