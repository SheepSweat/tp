package seedu.modtrack.parser;

import seedu.modtrack.model.AddCommand;
import seedu.modtrack.model.Command;
import seedu.modtrack.model.DeleteCommand;
import seedu.modtrack.model.ExitCommand;
import seedu.modtrack.model.MarkCommand;
import seedu.modtrack.model.ShowGradReqCommand;
import seedu.modtrack.model.UnmarkCommand;
import seedu.modtrack.model.ListCommand;

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
        case "list":
            return new ListCommand();
        case "show":
            return this.parseShow(arguments);
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

        int credits = 4; // default
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

        String[] prefixes = { "n/", "y/", "s/", "t/" };
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
