package seedu.modtrack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
    private ArrayList<Mod> list;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        // Capture console output to verify the goodbye message
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void isExit_flag_isSetToTrue() {
        ExitCommand command = new ExitCommand();
        // Verifying the inheritance logic from the base Command class
        assertTrue(command.isExit, "ExitCommand should set the isExit flag to true upon instantiation.");
    }

    @Test
    public void execute_printsGoodbyeMessage() {
        ExitCommand command = new ExitCommand();
        command.execute(list);

        String output = outContent.toString();
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }

    @Test
    public void execute_handlesEmptyList() {
        ExitCommand command = new ExitCommand();
        // Ensure it can execute even if the list is empty without throwing exceptions
        command.execute(new ArrayList<>());

        assertTrue(command.isExit);
    }
}
