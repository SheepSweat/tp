package seedu.modtrack.model;

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
        prepareFile();
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
                    // backward compatibility with old save format
                    String status = parts[0];
                    String name = parts[1];
                    int year = Integer.parseInt(parts[2]);
                    int semester = Integer.parseInt(parts[3]);
                    int credits = Integer.parseInt(parts[4]);

                    Mod mod = new Mod(name, year, semester, credits);
                    if (status.equals("1")) {
                        mod.setToDone();
                    }
                    list.add(mod);
                } else if (parts.length == 8) {
                    String status = parts[0];
                    String name = parts[1];
                    int year = Integer.parseInt(parts[2]);
                    int semester = Integer.parseInt(parts[3]);
                    int credits = Integer.parseInt(parts[4]);
                    int progress = Integer.parseInt(parts[5]);
                    String completionType = parts[6];
                    String prereqText = parts[7];

                    Mod mod = new Mod(name, year, semester, credits);

                    if (status.equals("1")) {
                        mod.setToDone();
                    }
                    mod.setProgressPercentage(progress);
                    mod.setCompletionType(completionType);
                    mod.setPrerequisites(Mod.parsePrerequisites(prereqText));

                    list.add(mod);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Storage file not found.");
        } catch (NumberFormatException e) {
            System.out.println("Storage file contains invalid data.");
        }

        return list;
    }
}