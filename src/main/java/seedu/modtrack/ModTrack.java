package seedu.modtrack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import seedu.modtrack.parser.InvalidCommandException;
import seedu.modtrack.commands.Command;
import seedu.modtrack.module.Mod;
import seedu.modtrack.parser.Parser;
import seedu.modtrack.referencelist.ReferenceList;
import seedu.modtrack.storage.Storage;
import seedu.modtrack.ui.Ui;

public class ModTrack {

    private Ui ui;
    private ReferenceList referenceList;
    private ArrayList<Mod> taskList;
    private Parser parser;
    private Storage storage;

    public ModTrack() {
        this.ui = new Ui();
        this.referenceList = new ReferenceList();
        this.parser = new Parser();
        this.storage = new Storage();

        this.referenceList.populateReferenceList(ReferenceList.getReferenceList());
        this.taskList = this.storage.load();
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        this.ui.showOpeningText();

        while (isRunning) {
            String instruction = in.nextLine();

            try {
                Command command = this.parser.parse(instruction);
                command.execute(this.taskList, this.ui);

                this.storage.save(this.taskList);

                if (command.isExit()) {
                    isRunning = false;
                }
            } catch (InvalidCommandException e) {
                this.ui.showInvalidCommandError();
            } catch (IOException e) {
                this.ui.showSaveError();
            }
        }

        in.close();
    }

    public static void main(String[] args) {
        Logger.getLogger("seedu.modtrack").setLevel(Level.WARNING);
        ModTrack modTrack = new ModTrack();
        modTrack.run();
    }
}
