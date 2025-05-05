//Author: Nagham Wael Mohamed Elsayed
//ID: 20231189
//version: V1.0
//First Modified: 22 Feb 2025
//Last Modified: 24 Feb 2025
//Purpose: a to-do list console program
//Features: Add a task , Remove a task , Display task details , Edit a task , Sort tasks , Display list , Clear list , Save list to a file


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;



public class Main {

    // Task class definition
    static class Task {
        private String name;
        private String description;
        private int priority; // 1 = low, 2 = average, 3 = high
        private LocalDate dueDate;

        public Task(String name, String description, int priority, LocalDate dueDate) {
            this.name = name;
            this.description = description;
            this.priority = priority;
            this.dueDate = dueDate;
        }


        //getters
        public String getName() {
            return name;
        }


        //this is not used yet but is here for furher development in the future
        public String getDescription() {
            return description;
        }


        public int getPriority() {
            return priority;
        }


        public LocalDate getDueDate() {
            return dueDate;
        }


        public void setName(String name) {
            this.name = name;
        }


        public void setDescription(String description) {
            this.description = description;
        }


        public void setPriority(int priority) {
            this.priority = priority;
        }


        public void setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
        }


        @Override
        public String toString() {
            String priorityString;
            switch(priority) {
                case 1: priorityString = "Low"; break;
                case 2: priorityString = "Average"; break;
                case 3: priorityString = "High"; break;
                default: priorityString = "Unknown"; break;
            }
            return "Task Name: " + name + "\n" +
                    "Task Description: " + description + "\n" +
                    "Task Priority: " + priorityString + "\n" +
                    "Due Date: " + dueDate.format(DateTimeFormatter.ofPattern("d-M-yyyy"));
        }


    }


    private static List<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy");



    //the program's starting point(main function)
    public static void main(String[] args) {
        boolean running = true;

        //display menu while the program is running(user didn't choose exit)
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    removeTask();
                    break;
                case "3":
                    displayTaskDetails();
                    break;
                case "4":
                    editTask();
                    break;
                case "5":
                    sortTasks();
                    break;
                case "6":
                    displayList();
                    break;
                case "7":
                    clearList();
                    break;
                case "8":
                    saveListToFile();
                    break;
                case "9":
                    running = false;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose from 1 to 9.");
            }
        }

        scanner.close();
    }


    //function for printing the menu
    private static void printMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1) Add a task");
        System.out.println("2) Remove a task");
        System.out.println("3) Display task details");
        System.out.println("4) Edit a task");
        System.out.println("5) Sort tasks");
        System.out.println("6) Display list");
        System.out.println("7) Clear list");
        System.out.println("8) Save list to a file");
        System.out.println("9) Exit");
        System.out.print("Select an option: ");
    }



    //take task info through the add task menu option
    private static void addTask() {
        System.out.print("Enter Task Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine().trim();

        int priority = 0;
        while (true) {
            System.out.print("Enter Task Priority (1 for low, 2 for average, 3 for high): ");
            String priorityInput = scanner.nextLine().trim();
            try {
                priority = Integer.parseInt(priorityInput);
                if (priority >= 1 && priority <= 3) {
                    break;
                } else {
                    System.out.println("Invalid input. Priority must be 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
            }
        }

        LocalDate dueDate = null;
        while (true) {
            System.out.print("Enter Due Date (day-month-year, e.g., 5-11-2025): ");
            String dateInput = scanner.nextLine().trim();
            try {
                dueDate = LocalDate.parse(dateInput, DATE_FORMAT);
                // Additional check for month and day ranges are implicitly handled by LocalDate parsing.
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format or value. Please follow the format day-month-year and ensure valid day/month.");
            }
        }

        Task task = new Task(name, description, priority, dueDate);
        tasks.add(task);
        System.out.println("Task added successfully.");
    }



    //remove task throgh the remove task menu option
    private static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }
        displayList();
        System.out.print("Enter the number of the task to remove: ");
        String input = scanner.nextLine().trim();
        //defensive programming
        try {
            int taskNumber = Integer.parseInt(input);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("Error: Task number does not exist.");
            } else {
                tasks.remove(taskNumber - 1);
                System.out.println("Task removed successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task number.");
        }
    }



    //display task details through display task menu option
    private static void displayTaskDetails() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
            return;
        }
        displayList();
        System.out.print("Enter the number of the task to display details: ");
        String input = scanner.nextLine().trim();
        try {
            int taskNumber = Integer.parseInt(input);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("Error: Task number does not exist.");
            } else {
                Task task = tasks.get(taskNumber - 1);
                System.out.println("\nTask Details:");
                System.out.println(task.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task number.");
        }
    }


    //edit task through the edit task menu option
    private static void editTask() {
        //defensive programming validate the list isn't empty
        if (tasks.isEmpty()) {
            System.out.println("No tasks to edit.");
            return;
        }
        displayList();
        System.out.print("Enter the number of the task to edit: ");
        String input = scanner.nextLine().trim();
        try {
            int taskNumber = Integer.parseInt(input);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("Error: Task number does not exist.");
            } else {
                // Edit task: get new information same as addTask
                Task task = tasks.get(taskNumber - 1);
                System.out.print("Enter new Task Name: ");
                String name = scanner.nextLine().trim();

                System.out.print("Enter new Task Description: ");
                String description = scanner.nextLine().trim();

                int priority = 0;
                while (true) {
                    System.out.print("Enter new Task Priority (1 for low, 2 for average, 3 for high): ");
                    String priorityInput = scanner.nextLine().trim();
                    try {
                        priority = Integer.parseInt(priorityInput);
                        if (priority >= 1 && priority <= 3) {
                            break;
                        } else {
                            System.out.println("Invalid input. Priority must be 1, 2, or 3.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                    }
                }

                LocalDate dueDate = null;
                while (true) {
                    System.out.print("Enter new Due Date (day-month-year, e.g., 5-11-2025): ");
                    String dateInput = scanner.nextLine().trim();
                    try {
                        dueDate = LocalDate.parse(dateInput, DATE_FORMAT);
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format or value. Please follow the format day-month-year and ensure valid day/month.");
                    }
                }

                // Update task fields
                task.setName(name);
                task.setDescription(description);
                task.setPriority(priority);
                task.setDueDate(dueDate);
                System.out.println("Task updated successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task number.");
        }
    }


    //sort tasks with two keys(priority or due date) through the sort tasks menu option
    private static void sortTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to sort.");
            return;
        }
        System.out.println("Sort tasks by:");
        System.out.println("1) Due Date (nearest first)");
        System.out.println("2) Priority (highest to lowest)");
        System.out.print("Enter your choice: ");
        String input = scanner.nextLine().trim();
        if ("1".equals(input)) {
            tasks.sort(Comparator.comparing(Task::getDueDate));
            System.out.println("Tasks sorted by due date.");
        } else if ("2".equals(input)) {
            // Higher priority means higher value (3 is high), so sort descending
            tasks.sort(Comparator.comparing(Task::getPriority).reversed());
            System.out.println("Tasks sorted by priority.");
        } else {
            System.out.println("Invalid input. Returning to main menu.");
        }
    }


    //display list on console screen through display list menu option
    private static void displayList() {
        if (tasks.isEmpty()) {
            System.out.println("Task list is empty.");
            return;
        }
        System.out.println("\nCurrent Task List:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ") " + tasks.get(i).getName());
        }
    }


    //clear list through clear list menu option
    private static void clearList() {
        if (tasks.isEmpty()) {
            System.out.println("Task list is already empty.");
            return;
        }
        System.out.print("Are you sure you want to clear the list? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        if ("y".equals(input) || "yes".equals(input)) {
            tasks.clear();
            System.out.println("Task list cleared.");
        } else {
            System.out.println("Clear list canceled.");
        }
    }



    //save list to a .txt file so user can return to it later
    private static void saveListToFile() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to save.");
            return;
        }
        System.out.print("Enter the filename to save the list: ");
        String filename = scanner.nextLine().trim();
        try (FileWriter writer = new FileWriter(filename)) {
            for (Task task : tasks) {
                writer.write(task.toString() + "\n----------------\n");
            }
            System.out.println("Task list saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }


}
