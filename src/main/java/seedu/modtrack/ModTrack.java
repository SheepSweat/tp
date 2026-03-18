package seedu.modtrack;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.modtrack.model.AddCommand;
import seedu.modtrack.model.Mod;
import seedu.modtrack.model.ReferenceList;
import seedu.modtrack.model.AddCommand;
import seedu.modtrack.ui.Ui;

public class ModTrack {

    private Ui ui;
    private ReferenceList referenceList;
    private ArrayList<Mod> taskList;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public ModTrack() {
        this.ui = new Ui();
        // 1. Initialize the object
        this.referenceList = new ReferenceList();
        this.taskList = new ArrayList<>();
        // 2. Call the method to fill it with data
        referenceList.populateReferenceList(referenceList.list);
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        Boolean isRunning = true;

        this.ui.showOpeningText();
        AddCommand commandtest = new AddCommand("CS2113",1,1,4);
        commandtest.execute(taskList);

        this.ui.showList(taskList,referenceList.list);
        while (isRunning) {

            String instruction = in.nextLine();
            if (instruction.equals("bye")) {
                isRunning = false;
                this.ui.showClosingText();
            } else {
                this.ui.showInvalidCommandError();
            }
        }
        in.close();
    }

    public static void main(String[] args) {
        ModTrack modTrack = new ModTrack();
        modTrack.run();
    }
}
