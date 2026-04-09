package seedu.modtrack.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;
import seedu.modtrack.commands.ShowGradReqCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowGradReqCommandTest {
    private ArrayList<Mod> list;
    private Ui ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
        this.originalOut = System.out;
        System.setOut(new PrintStream(this.outputStream));
    }

    @Test
    public void execute_printsGraduationRequirements() {
        ShowGradReqCommand command = new ShowGradReqCommand();

        command.execute(this.list, this.ui);

        String output = this.outputStream.toString();

        assertTrue(output.contains("Computer Engineering Graduation Requirements"));
        assertTrue(output.contains("Total MCs Required: 160"));
        assertTrue(output.contains("Core Modules:"));
        assertTrue(output.contains("CS1010"));
        assertTrue(output.contains("CS2113"));
        assertTrue(output.contains("Project/Internship:"));
        assertTrue(output.contains("EG3611A/CP3880"));
        assertTrue(output.contains("Engineering Common Curriculum:"));
        assertTrue(output.contains("EG1311"));
        assertTrue(output.contains("General Education Curriculum:"));
        assertTrue(output.contains("GENXXXX"));
        assertTrue(output.contains("GECXXXX"));

        System.setOut(this.originalOut);
    }
}
