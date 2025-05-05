# To-Do List Console Application

## Description
A simple console-based to-do list application written in Java. This program allows users to manage their tasks with various features including adding, removing, editing, sorting, and saving tasks to a file.

## Features
- **Add a task**: Create new tasks with name, description, priority, and due date
- **Remove a task**: Delete tasks from the list
- **Display task details**: View complete information about a specific task
- **Edit a task**: Modify existing task details
- **Sort tasks**: Organize tasks by due date or priority
- **Display list**: View all task names in the list
- **Clear list**: Remove all tasks at once
- **Save list to file**: Export tasks to a text file for later reference

## Requirements
- Java Development Kit (JDK) 8 or later

## How to Run
1. Compile the program:
   ```
   javac Main.java
   ```
2. Run the program:
   ```
   java Main
   ```

## Usage
1. Follow the on-screen menu to select an operation
2. Enter the required information when prompted
3. Tasks are saved in memory until the program exits (use "Save list to a file" to persist your tasks)

## File Format
When saving to a file, tasks are stored in the following format:
```
Task Name: [name]
Task Description: [description]
Task Priority: [Low/Average/High]
Due Date: [day-month-year]
----------------
```

## Author
Nagham Wael Mohamed Elsayed  
Version: V1.0  

## Modification Dates
- First Modified: 22 Feb 2025
- Last Modified: 24 Feb 2025

## Notes
- The program uses console input/output only
- All data is lost when the program exits unless saved to a file
- Date format must be entered as day-month-year (e.g., 5-11-2025)
- Priorities are: 1 = Low, 2 = Average, 3 = High
