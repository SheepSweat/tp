package seedu.modtrack.storage;

import seedu.modtrack.model.Mod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/ModTrack.txt";
    private static final String DIRECTORY_PATH = "./data/";

    public Storage() {
        this.prepareFile();
    }

    private void prepareFile() {
        try {
            File folder = new File(DIRECTORY_PATH);
            if (!folder.exists()) {
                folder.mkdir();
            }

            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating storage: " + e.getMessage());
        }
    }

    public void save(ArrayList<Mod> list) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Mod mod : list) {
            fw.write(mod.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<Mod> load() {
        ArrayList<Mod> list = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s*\\|\\s*");

                if (parts.length == 5) {
                    list.add(parseLegacyFivePart(parts));
                } else if (parts.length == 7) {
                    list.add(parseCurrentSevenPart(parts));
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Storage file not found.");
        } catch (NumberFormatException e) {
            System.out.println("Storage file contains invalid numeric data.");
        } catch (Exception e) {
            System.out.println("Error loading storage: " + e.getMessage());
        }
        return list;
    }

    private Mod parseLegacyFivePart(String[] parts) {
        String status = parts[0];
        String name = parts[1];
        int year = Integer.parseInt(parts[2]);
        int semester = Integer.parseInt(parts[3]);
        int credits = Integer.parseInt(parts[4]);

        Mod mod = new Mod(name, year, semester, credits);
        if (status.equals("1")) {
            mod.setToDone();
        }
        return mod;
    }

    private Mod parseCurrentSevenPart(String[] parts) {
        String status = parts[0];
        String name = parts[1];
        int year = Integer.parseInt(parts[2]);
        int semester = Integer.parseInt(parts[3]);
        int credits = Integer.parseInt(parts[4]);
        String completionType = parts[5];
        String prereqText = parts[6];

        Mod mod = new Mod(name, year, semester, credits);

        // Set status based on the first column
        if (status.equals("1")) {
            mod.setToDone();
        }

        // Set the specific type (NORMAL, EXEMPTED, TRANSFERRED)
        mod.setCompletionType(completionType);

        // Set prerequisites
        mod.setPrerequisites(Mod.parsePrerequisites(prereqText));

        return mod;
    }
}
