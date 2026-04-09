package seedu.modtrack.parser;

import java.util.ArrayList;

import seedu.modtrack.commands.AddCommand;
import seedu.modtrack.commands.AddPrereqCommand;
import seedu.modtrack.commands.ClearCommand;
import seedu.modtrack.commands.Command;
import seedu.modtrack.commands.DeleteCommand;
import seedu.modtrack.commands.ExemptCommand;
import seedu.modtrack.commands.ExitCommand;
import seedu.modtrack.commands.FindCommand;
import seedu.modtrack.commands.ListCommand;
import seedu.modtrack.commands.ListCompareCommand;
import seedu.modtrack.commands.MarkCommand;
import seedu.modtrack.commands.SetProgressCommand;
import seedu.modtrack.commands.ShowGradReqCommand;
import seedu.modtrack.commands.ShowPrereqCommand;
import seedu.modtrack.commands.TransferCommand;
import seedu.modtrack.commands.UnmarkCommand;

public class Parser {

    public Command parse(String input) throws InvalidCommandException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidCommandException("Input cannot be empty.");
        }

        String trimmedInput = input.trim();
        String[] parts = trimmedInput.split("\\s+", 2);

        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
            case "add":
                return this.parseAdd(arguments);
            case "delete":
                return this.parseDelete(arguments);
            case "mark":
                return this.parseMark(arguments);
            case "unmark":
                return this.parseUnmark(arguments);
            case "progress":
                return this.parseProgress(arguments);
            case "exempt":
                return this.parseExempt(arguments);
            case "transfer":
                return this.parseTransfer(arguments);
            case "find":
                return this.parseFind(arguments);
            case "prereq":
                return this.parsePrereq(arguments);
            case "list":
                return this.parseList(arguments);
            case "show":
                return this.parseShow(arguments);
            case "clear":
                return new ClearCommand();
            case "exit":
            case "bye":
                return new ExitCommand();
            default:
                throw new InvalidCommandException("Invalid command.");
        }
    }

    private Command parseAdd(String arguments) throws InvalidCommandException {
        String modName = this.extractValue(arguments, "n/");
        String yearText = this.extractValue(arguments, "y/");
        String semText = this.extractValue(arguments, "s/");

        int year = this.parseYear(yearText);
        int semester = this.parseSemester(semText);

        int credits = 4;
        return new AddCommand(modName, year, semester, credits);
    }

    private Command parseDelete(String arguments) throws InvalidCommandException {
        String modName = this.extractValue(arguments, "n/");
        return new DeleteCommand(modName);
    }

    private Command parseMark(String arguments) throws InvalidCommandException {
        String modName = this.extractValue(arguments, "n/");
        return new MarkCommand(modName);
    }

    private Command parseUnmark(String arguments) throws InvalidCommandException {
        String modName = this.extractValue(arguments, "n/");
        return new UnmarkCommand(modName);
    }

    private Command parseProgress(String arguments) throws InvalidCommandException {
        String modName = this.extractValue(arguments, "n/");
        String progressText = this.extractValue(arguments, "p/");

        int percentage;
        try {
            percentage = Integer.parseInt(progressText);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Progress must be an integer from 0 to 100.");
        }

        if (percentage < 0 || percentage > 100) {
            throw new InvalidCommandException("Progress must be between 0 and 100.");
        }

        return new SetProgressCommand(modName, percentage);
    }

    private Command parseExempt(String arguments) throws InvalidCommandException {
        String modName = this.extractValue(arguments, "n/");
        return new ExemptCommand(modName);
    }

    private Command parseTransfer(String arguments) throws InvalidCommandException {
        String modName = this.extractValue(arguments, "n/");
        return new TransferCommand(modName);
    }

    private Command parseFind(String arguments) throws InvalidCommandException {
        String keyword = this.extractValue(arguments, "n/");
        return new FindCommand(keyword);
    }

    private Command parsePrereq(String arguments) throws InvalidCommandException {
        String[] parts = arguments.split("\\s+", 2);
        if (parts.length < 2) {
            throw new InvalidCommandException("Use 'prereq add ...' or 'prereq show ...'");
        }

        String action = parts[0].toLowerCase();
        String rest = parts[1];

        if (action.equals("add")) {
            String modName = this.extractValue(rest, "n/");
            String prereqText = this.extractValue(rest, "p/");
            String[] prereqArray = prereqText.split(",");
            ArrayList<String> prereqs = new ArrayList<>();

            for (String prereq : prereqArray) {
                prereqs.add(prereq.trim().toUpperCase());
            }

            return new AddPrereqCommand(modName, prereqs);
        } else if (action.equals("show")) {
            String modName = this.extractValue(rest, "n/");
            return new ShowPrereqCommand(modName);
        }

        throw new InvalidCommandException("Unknown prereq command.");
    }

    private Command parseList(String arguments) throws InvalidCommandException {
        String trimmedArgs = arguments.trim();
        if (trimmedArgs.contains("c/")) {
            return new ListCompareCommand();
        }
        if (trimmedArgs.isEmpty()) {
            return new ListCommand();
        }
        throw new InvalidCommandException("Unknown list command.");
    }

    private Command parseShow(String arguments) throws InvalidCommandException {
        if (arguments.equalsIgnoreCase("grad req")) {
            return new ShowGradReqCommand();
        }
        throw new InvalidCommandException("Unknown show command.");
    }

    private String extractValue(String input, String prefix) throws InvalidCommandException {
        int start = input.indexOf(prefix);
        if (start == -1) {
            throw new InvalidCommandException("Missing field: " + prefix);
        }

        start += prefix.length();
        int nextPrefixIndex = input.length();

        String[] prefixes = { "n/", "y/", "s/", "t/", "p/" };
        for (String p : prefixes) {
            if (p.equals(prefix)) {
                continue;
            }
            int index = input.indexOf(p, start);
            if (index != -1 && index < nextPrefixIndex) {
                nextPrefixIndex = index;
            }
        }

        String value = input.substring(start, nextPrefixIndex).trim();
        if (value.isEmpty()) {
            throw new InvalidCommandException("Empty value for: " + prefix);
        }

        return value;
    }

    private int parseYear(String text) throws InvalidCommandException {
        String normalized = text.toUpperCase().replace("YEAR", "").trim();
        try {
            return Integer.parseInt(normalized);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Year must be in the form YEAR1, YEAR2, YEAR3...");
        }
    }

    private int parseSemester(String text) throws InvalidCommandException {
        String normalized = text.toUpperCase().replace("SEM", "").trim();
        try {
            return Integer.parseInt(normalized);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Semester must be in the form SEM1 or SEM2.");
        }
    }
}
