# User Guide

## Introduction

ModTrack is a Command Line Interface application designed to help Computer Engineering students from NUS track their
academic progress and graduation requirements efficiently. It provides a fast and structured way to record modules taken, 
monitor Modular Credits, and identify missing graduation pillars without relying on complex spreadsheets or slow web systems.

Unlike traditional academic portals, ModTrack focuses on clear requirement tracking and gap analysis. 
By comparing your completed modules against predefined graduation requirements, 
the program helps you quickly understand what you have fulfilled and what still needs attention.

ModTrack is especially useful for students managing heavy academic workloads, planning specializations,
or ensuring that prerequisite and graduation requirements are not accidentally missed.

## Key Goals 
### ModTrack aims to help users:
1. Keep a structured record of modules taken
2. Track completed and pending requirements
3. Monitor total MCs earned and remaining
4. Identify unmet graduation criteria early
5. Plan future semesters more confidently

## Additional Details
ModTrack is optimized for students who prefer fast keyboard-based interaction.

All features are accessible through simple text commands, 
like `mark` for completed modules, making it lightweight and efficient to use even during busy CourseReg periods. 
ModTrack does not connect directly to EduRec or external NUS systems.

All module information is entered manually and stored locally on your device to keep your academic data private.

## Quick Start

1. Environment Check: Ensure you have Java 17 or above installed on your computer.
2. Download: Obtain the latest modtrack.jar file from our repository.
3. Setup Folder: Copy the .jar file to the folder you wish to use as the home folder for your module data.
4. Launch:
   * Open your command terminal (Command Prompt or Terminal).
   * cd into the folder containing the jar file.
   * Run the app using: java -jar modtrack.jar.
5. Automatic Initialization: On the first run, 
ModTrack will automatically create a ./data/ directory and a ModTrack.txt file in your home folder to store your progress.
6. Type the command in the command box of the command line terminal and press Enter to execute it. 
e.g. typing `help` and pressing Enter will open the help window.

### Some example commands you can try:
* add n/CS2113 y/YEAR2 S/SEM1: Records a new module you are taking.
* list: Displays all completed modules currently in your tracker. 
* mark n/CS2113: Marks the CS2113 module in your list as completed to track academic progress.
* delete n/CS2113: Removes the CS2113 module entry if it was added by mistake.
* show grad req: View your total earned MCs and remaining credits required for graduation.
* exit: Saves your data and exits the application.

## Features

### Adding a module: `add`
Adds a module to the tracker.

Format: `add n/NAME y/Year s/SEMESTER`

* The `SEMESTER` must be SEM1/SEM2 only.  

Example of usage:
`add n/CS2113 y/YEAR2 s/SEM1`
`add n/CS1010 y/YEAR1 s/SEM1`

### Deleting a module: `delete`
Removes a module from the tracker.

Format: `delete n/NAME`

Example of usage:
`delete n/CS2113`

### Marking a module as complete: `mark `
Mark a module as complete to update the tracker.

Format: `mark n/NAME`

Example of usage:
`mark n/CS2113`

### Unmarking a module as uncompleted: `unmark  `
Unmark a module as complete to update the tracker.

Format: `unmark n/NAME`

Example of usage:
`unmark n/CS2113`

### List all completed and uncompleted modules: `list  `
* List all modules marked as completed and uncompleted modules throughout the course.
* Shows number of completed modular credits and remaining number needed for graduation.

Format: `list`

Example of usage:
`list`

### Show graduation requirements:: `show grad req  `
Show graduation requirements for Computer Engineering students.

Format: `show grad req`

Example of usage:
`show grad req`

### Saving the data
Course credits data are saved in the hard disk automatically after any command. There is no need to save manually.

### Exiting the program: `exit`
Exits the program
Format: `exit`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**:  Install the app in the other computer and overwrite the empty data file 
it creates with the file that contains the data of your previous Modtracking home folder.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`

| Actions                           | Format, Examples                                                       | 
|-----------------------------------|------------------------------------------------------------------------| 
| `add`                             | `add n/NAME y/Year s/SEMESTER`<br/>`e.g., add n/CS2113 y/YEAR2 s/SEM1` |
| `delete`                          | `delete n/NAME`<br/>`e.g., delete n/CS2113`                            |
| `mark`                            | `mark n/NAME`<br/>  `e.g., mark n/CS2113`                              | 
| `unmark`                          | `unmark n/NAME`<br/>`e.g., unmark n/CS2113`                            |    
| `list`                            | `list`                                                                 |
| `Showing Graduation requirements` | `show grad req`                                                        |
| `exiting the app`                 | `exit`                                                                 |
|                                   |                                                                        |
|                                   |                                                                        |
|                                   |                                                                        |


