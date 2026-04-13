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
            if (!arguments.isEmpty()) {
                throw new InvalidCommandException("The 'clear' command does not take any arguments.");
            }
            return new ClearCommand();
        case "exit":
        case "bye":
            if (!arguments.isEmpty()) {
                throw new InvalidCommandException("The '" + commandWord + "' command does not take any arguments.");
            }
            return new ExitCommand();
        default:
            throw new InvalidCommandException("Invalid command.");
        }
    }

    private Command parseAdd(String arguments) throws InvalidCommandException {
        String modName = this.extractValue(arguments, "n/");
        String yearText = this.extractValue(arguments, "y/");
        String semText = this.extractValue(arguments, "s/");

        // Check if c/ is present, otherwise default to 4
        int credits = 4;
        if (arguments.contains("c/")) {
            try {
                credits = Integer.parseInt(this.extractValue(arguments, "c/"));
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("Module credits must be a number (2, 4, 8 or 10).");
            }
        }

        if (credits != 2 && credits != 4 && credits != 8 && credits != 10) {
            throw new InvalidCommandException("Module credits must be 2, 4, 8 or 10.");
        }

        int year = this.parseYear(yearText);
        int semester = this.parseSemester(semText);

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
                String trimmedPrereq = prereq.trim().toUpperCase();
                if (trimmedPrereq.isEmpty()) {
                    continue;
                }
                if (trimmedPrereq.equals(modName)) {
                    throw new InvalidCommandException("Illogical dependency: " + modName
                            + " cannot be a prerequisite for itself.");
                }

                prereqs.add(trimmedPrereq);
            }

            if (prereqs.isEmpty()) {
                throw new InvalidCommandException("Please provide at least one valid prerequisite code.");
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

        // If no arguments, return the standard list
        if (trimmedArgs.isEmpty()) {
            return new ListCommand();
        }

        // Check if the argument is EXACTLY "c/" (case-insensitive)
        if (trimmedArgs.equalsIgnoreCase("c/")) {
            return new ListCompareCommand();
        }

        // If there is anything else (e.g., "list c/abc" or "list random"), throw error
        throw new InvalidCommandException("Invalid list command. Use 'list' or 'list c/'.");
    }

    private Command parseShow(String arguments) throws InvalidCommandException {
        String normalizedArgs = arguments.trim().replaceAll("\\s+", " ");

        if (normalizedArgs.equalsIgnoreCase("grad req")) {
            return new ShowGradReqCommand();
        }

        throw new InvalidCommandException("Unknown show command. Did you mean 'show grad req'?");
    }

    private String extractValue(String input, String prefix) throws InvalidCommandException {
        int start = input.indexOf(prefix);
        if (start == -1) {
            throw new InvalidCommandException("Missing field: " + prefix);
        }

        start += prefix.length();
        assert start >= prefix.length() && start <= input.length()
                : "Invalid start index after prefix extraction";

        int nextPrefixIndex = input.length();

        String[] prefixes = {"n/", "y/", "s/", "t/", "p/", "c/"};
        for (String p : prefixes) {
            if (p.equals(prefix)) {
                continue;
            }
            int index = input.indexOf(p, start);
            if (index != -1 && index < nextPrefixIndex) {
                nextPrefixIndex = index;
            }
        }

        assert nextPrefixIndex >= start && nextPrefixIndex <= input.length()
                : "Invalid next prefix index while extracting value";

        String value = input.substring(start, nextPrefixIndex).trim();
        if (value.isEmpty()) {
            throw new InvalidCommandException("Empty value for: " + prefix);
        }

        return value;
    }

    private int parseYear(String text) throws InvalidCommandException {
        String normalized = text.toUpperCase().replace("YEAR", "").trim();
        try {
            int year = Integer.parseInt(normalized);
            if (year < 1 || year > 4) {
                throw new InvalidCommandException("Year must be between 1 and 4 (e.g., YEAR1, YEAR2...).");
            }
            return year;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Year must be in the form YEAR1, YEAR2, YEAR3 or YEAR4.");
        }
    }

    private int parseSemester(String text) throws InvalidCommandException {
        String normalized = text.toUpperCase().replace("SEM", "").trim();
        try {
            int semester = Integer.parseInt(normalized);
            if (semester < 1 || semester > 2) {
                throw new InvalidCommandException("Semester must be 1 or 2 (e.g., SEM1, SEM2).");
            }
            return semester;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Semester must be in the form SEM1 or SEM2.");
        }
    }
}

