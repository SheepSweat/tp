# User Guide

## Introduction

ModTrack is a Command Line Interface application designed to help Computer Engineering students from NUS track their
academic progress and graduation requirements efficiently. It provides a fast and structured way to record modules taken,
monitor Modular Credits, and identify missing graduation pillars without relying on complex spreadsheets or slow web systems.

## Quick Start

1. Environment Check: Ensure you have Java 17 or above installed.
2. Download: Obtain the latest `modtrack.jar` file.
3. Setup Folder: Place the `.jar` file in your preferred home folder.
4. Launch: Run `java -jar modtrack.jar` in your terminal.
5. Automatic Initialization: On the first run, ModTrack creates a `./data/` directory and a `ModTrack.txt` file.

---

## Features

### Adding a module: `add`

Adds a module to the tracker. By default, modules are added with 4 Modular Credits (MCs) unless specified otherwise.

Format: `add n/NAME y/YEAR s/SEMESTER [c/CREDITS]`

* **NAME**: The module code (e.g., CS2113).
* **YEAR**: The academic year (e.g., YEAR1, YEAR2). Must be between 1 and 4
* **SEMESTER**: The semester (e.g., SEM1, SEM2). Must be either 1 or 2
* **CREDITS**: (Optional) The number of modular credits. Must be either **2** or **4**. Defaults to 4 if omitted.

Examples:
* `add n/CS2113 y/YEAR2 s/SEM1` (Adds CS2113 with default 4 MCs)
* `add n/EG3301R y/YEAR3 s/SEM1 c/2` (Adds EG3301R with 2 MCs)

Example Output:
```text
----------------------------------------------------
Module added:

Name: CS2113
Year: YEAR2
Semester: SEM1
Status: Incomplete
Modular Credits: 4
Prerequisites: None

Total modules tracked: 1
----------------------------------------------------
```

### Deleting a module: `delete`
Removes a module from the tracker.
Format: `delete n/NAME`
Example: `delete n/CS2113`
Example Output:
```text
----------------------------------------------------
Noted. I've removed this mod:

Name: CS2113
Year: YEAR2
Semester: SEM1
Status: Incomplete
Modular Credits: 4
Prerequisites: None

Now you have 0 mods in the list.
----------------------------------------------------
```

### Marking a module as complete: `mark`
Mark a module as complete to update the tracker.
Format: `mark n/NAME`
Example Output:
```text
Module marked as completed:
CS2113
```

### Unmarking a module as uncompleted: `unmark`
Unmark a module as complete.
Format: `unmark n/NAME`
Example Output:
```text
Module marked as incomplete:
CS2113
```

### Handling exemptions: `exempt`
Marks a module as exempted (e.g., via polytechnic APCs). This counts as completed credits.
Format: `exempt n/NAME`
Example: `exempt n/CS1010`
Example Output:
```text
Module marked as exempted:
CS1010
```

### Handling transferred modules: `transfer`
Marks a module as transferred (e.g., from Student Exchange Program). This counts as completed credits.
Format: `transfer n/NAME`
Example: `transfer n/EE2026`
Example Output:
```text
Module marked as transferred:
EE2026
```

### Finding a module: 'find'
Searches for modules in your tracker using a keyword. The search is case-insensitive and matches partial module names.
Format: `find n/NAME`
Example: `find n/CS2113`
```text
find n/CS2113
----------------------------------------------------
Matching modules:
----------------------------------------------------
Name: CS2113
Year: YEAR2
Semester: SEM1
Status: Incomplete
Modular Credits: 4
Prerequisites: None

find n/CS1010
----------------------------------------------------
Matching modules:
No modules found in the list.
```


### Adding prerequisites: `prereq add`
Adds prerequisite modules to an existing entry in your list.
Format: `prereq add n/NAME p/PREREQ1,PREREQ2`
Example: `prereq add n/CS2113 p/CS1010,CS1231`
Example Output:
```text
Prerequisites updated for CS2113:
```

### Showing prerequisites: `prereq show`
Displays the list of prerequisites for a specific module.
Format: `prereq show n/NAME`
Example: `prereq show n/CS2113`
Example Output:
```text
Prerequisites for CS2113:
CS1010, CS1231
```
If there are no prerequisites:
```text
Prerequisites for CS2113:
None
```

### List all modules: `list`
Displays all modules in your personal tracker.
Format: `list`
Example Output:
```text
===== Your Tracked Modules =====
Name: CS2113
Year: YEAR2
Semester: SEM1
Status: Incomplete
Modular Credits: 4
Prerequisites: None
```
If the list is empty:
```text
===== Your Tracked Modules =====
No modules tracked yet.
```

### Compare with Graduation Requirements: `list c/`
Compares your current tracked list against the official Computer Engineering graduation requirements to show what is missing.
Format: `list c/`
Example Output:
```text
____________________________________________________________
Comparison with Graduation Requirements (CE):

✔ COMPLETED MODULES:
Name: CS1010
Year: YEAR1
Semester: SEM1
Status: Completed
Modular Credits: 4
Prerequisites: None

✘ MISSING/UNCOMPLETED MODULES:
Name: CS1231
Year: YEAR1
Semester: SEM2
Status: Incomplete
Modular Credits: 4
Prerequisites: None
____________________________________________________________
```
If there are no completed modules:
```text
____________________________________________________________
Comparison with Graduation Requirements (CE):

✔ COMPLETED MODULES:
  (None yet)

✘ MISSING/UNCOMPLETED MODULES:
Name: CS1231
Year: YEAR1
Semester: SEM2
Status: Incomplete
Modular Credits: 4
Prerequisites: None
____________________________________________________________
```
If all the graduation requirements are met:
```text
____________________________________________________________
Comparison with Graduation Requirements (CE):

✔ COMPLETED MODULES:
Name: CS1010
Year: YEAR1
Semester: SEM1
Status: Completed
Modular Credits: 4
Prerequisites: None

✘ MISSING/UNCOMPLETED MODULES:
  Congratulations! All requirements met.
____________________________________________________________
```

### Show graduation requirements: `show grad req`
Show general graduation requirement pillars for CE students.
Format: `show grad req`
Example Output:
```text
===== Computer Engineering Graduation Requirements =====
Total MCs Required: 160
Core Modules:
- CS1010
- CG1111A
- MA1511
```

### Clearing all data: `clear`

Deletes all currently tracked modules from the list.

> [!WARNING]
> This action is irreversible. All module data, including completion status and prerequisites, will be permanently removed.

**Format:** `clear`

**Confirmation Step:**
Upon entering the command, the application will prompt you for confirmation to prevent accidental data loss. You must type `yes` and press Enter to proceed. Any other input will cancel the operation.

**Example Interaction:**
```text
> clear
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
WARNING: This will delete ALL tracked modules.
Are you sure? (type 'yes' to confirm): yes
----------------------------------------------------
Noted. All modules have been cleared. Now you have an empty list.
----------------------------------------------------
```
### Exiting the program: `exit` or `bye`

Saves your current module list and terminates the application.

**Format:** `exit` or `bye`

**Example Output:**
```text
Bye. Hope to see you again soon!
```

---

## Potential Errors Encountered

This section aims to list potential errors faced when using the application and guide users in resolving the issues or changing subsequent inputs.

### Errors with inputs
This section consists of errors that are faced with incorrect or incomplete inputs.

**Empty input**
```text

----------------------------------------------------
Input cannot be empty.
----------------------------------------------------
```
Resolution:
* Please enter an input

**Missing required prefix**:
``` text
add cs2113
----------------------------------------------------
Missing field: n/
----------------------------------------------------
```
Resolution: 
* Ensure the required prefixes shown (`n/`, `y/`, `s/`) is included.

**Empty fields**:
``` text
add cs2113
----------------------------------------------------
Missing field: n/
----------------------------------------------------
```
Resolution:
* Ensure that the field is not blank

### Adding a module: `add`

**Duplicate module**:
``` text
This module is already in the list!
```
Resolution:
* Use `list` to check existing modules before adding.

**Existing but incomplete module**
```text
This module already exists in the list, but is currently incomplete.
To mark this module as complete, use: mark n/CS2113
```
Resolution: 
* Use the `mark` command instead of adding again.


### Deleting a module: delete

**Missing Module**
```text
Module not found
Module does not exist. Choose another number.
```
Resolution:
- Ensure the module exists by checking with list.

### Marking a module: mark

**Missing Module**
```text
Module not found
Module does not exist. Choose another number.
```
Resolution:
- Ensure the module has been added before marking.

### Unmarking a module: unmark

**Missing Module**
```text
Module not found
Module does not exist. Choose another number.
```
Resolution:
- Ensure the module exists before attempting to unmark.

### Handling exemptions: exempt

**Missing Module**
```text
Module not found
Module does not exist. Choose another number.
```
Resolution:
- Ensure the module exists before marking it as exempted.

### Finding a module: 'find'

Errors have been covered ontop with **Missing prefixes** and **Empty Fields**

### Handling transferred modules: transfer

**Missing Module**
```text
Module not found
Module does not exist. Choose another number.
```
Resolution:
- Ensure the module exists before transferring it.

### Adding prerequisites: prereq add

**Missing Module**
```text
Module not found
Module does not exist. Choose another number.
```
Resolution:
- Ensure the module exists before adding prerequisites.

**Missing Prefix**
```text
----------------------------------------------------
Missing field: n/
----------------------------------------------------
```
Resolution:
- Ensure you include the prerequisite prefix and provide at least one prerequisite module.

### Showing prerequisites: prereq show

**Missing Module**
```text
Module not found
Module does not exist. Choose another number.
```
Resolution:
- Ensure the module exists before viewing prerequisites.

### List all modules: list

**No modules tracked**
```text
===== Your Tracked Modules =====
No modules tracked yet.
```
Resolution:
- Add modules using the add command before running list.

### Compare with Graduation Requirements: list c/

**No completed modules**
```text
____________________________________________________________
Comparison with Graduation Requirements (CE):

✔ COMPLETED MODULES:
  (None yet)

✘ MISSING/UNCOMPLETED MODULES:
...
____________________________________________________________
```
Resolution:
- Mark modules using mark or transfer before inputing the command.

### Show graduation requirements: show grad req

```text
Unknown show command. Did you mean 'show grad req'?
```
Resolution:
- Ensure the command is input correctly with no missing or extra spaces.

### Clearing all data: clear
```text
The 'clear' command does not take any arguments.
```
Resolution:
- Key in 'clear' with no other words

### Exiting: exit / bye

```text
The 'bye' command does not take any arguments.
```
Resolution:
- Key in 'bye' with no other words

---

## Command Summary

| Action | Format | Example |
|--------|--------|---------|
| **Add Module** | `add n/NAME y/YEAR s/SEM [c/CREDITS]` | `add n/CS2113 y/YEAR2 s/SEM1 c/4` |
| **Delete Module** | `delete n/NAME` | `delete n/CS2113` |
| **Mark Complete** | `mark n/NAME` | `mark n/CS2113` |
| **Unmark** | `unmark n/NAME` | `unmark n/CS2113` |
| **Exempt** | `exempt n/NAME` | `exempt n/MA1511` |
| **Transfer** | `transfer n/NAME` | `transfer n/CS2040C` |
| **Add Prereq** | `prereq add n/NAME p/LIST` | `prereq add n/CG2023 p/CG1111` |
| **Show Prereq** | `prereq show n/NAME` | `prereq show n/CG2023` |
| **List All** | `list` | `list` |
| **Compare Grad** | `list c/` | `list c/` |
| **Show Requirements**| `show grad req` | `show grad req` |
| **Clear All** | `clear` | `clear` |
| **Exit** | `exit` | `exit` |
