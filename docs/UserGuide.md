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
Adds a module to the tracker.
Format: `add n/NAME y/Year s/SEMESTER`
Example: `add n/CS2113 y/YEAR2 s/SEM1`

### Deleting a module: `delete`
Removes a module from the tracker.
Format: `delete n/NAME`
Example: `delete n/CS2113`

### Marking a module as complete: `mark`
Mark a module as complete to update the tracker.
Format: `mark n/NAME`

### Unmarking a module as uncompleted: `unmark`
Unmark a module as complete.
Format: `unmark n/NAME`

### Handling exemptions: `exempt`
Marks a module as exempted (e.g., via polytechnic APCs). This counts as completed credits.
Format: `exempt n/NAME`
Example: `exempt n/CS1010`

### Handling transferred modules: `transfer`
Marks a module as transferred (e.g., from Student Exchange Program). This counts as completed credits.
Format: `transfer n/NAME`
Example: `transfer n/EE2026`

### Adding prerequisites: `prereq add`
Adds prerequisite modules to an existing entry in your list.
Format: `prereq add n/NAME p/PREREQ1,PREREQ2`
Example: `prereq add n/CS2113 p/CS1010,CS1231`

### Showing prerequisites: `prereq show`
Displays the list of prerequisites for a specific module.
Format: `prereq show n/NAME`
Example: `prereq show n/CS2113`

### List all modules: `list`
Displays all modules in your personal tracker.
Format: `list`

### Compare with Graduation Requirements: `list c/`
Compares your current tracked list against the official Computer Engineering graduation requirements to show what is missing.
Format: `list c/`

### Show graduation requirements: `show grad req`
Show general graduation requirement pillars for CE students.
Format: `show grad req`

### Exiting the program: `exit`
Saves your data and exits.
Format: `exit`

---

## Command Summary

| Action | Format | Example |
|--------|--------|---------|
| **Add Module** | `add n/NAME y/YEAR s/SEM` | `add n/CS2113 y/2 s/SEM1` |
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
| **Exit** | `exit` | `exit` |