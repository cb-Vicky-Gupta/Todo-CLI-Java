package todo;

import todo.entities.User;
import todo.services.TodoServices;
import todo.services.UserServices;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        UserServices userService = new UserServices();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome!");
        User currentUser = null;
        while (currentUser == null) {
            System.out.println("\n1. Signup  2. Login  3. Exit");
            int choice = readChoice(sc);

            switch (choice) {
                case 1 -> {
                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    System.out.print("Full name: ");
                    String fullName = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    boolean created = userService.register(username, fullName, email, password);
                    if (created) {
                        System.out.println("Signup successful! Please log in.");
                    } else {
                        System.out.println("That username is already taken.");
                    }
                }
                case 2 -> {
                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    User user = userService.login(username, password);
                    if (user != null) {
                        currentUser = user;
                        System.out.println("Welcome back, " + user.getFullName() + "!");
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                }
                case 3 -> {
                    System.out.println("Goodbye");
                    sc.close();
                    return;
                }
                default -> System.out.println("Unknown option");
            }
        }



        System.out.println("Todo is running");
        TodoServices service = new TodoServices(currentUser.getId());
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add 2. View 3. Search 4. Edit 5. Delete 6. Set Done mark 7. Set Undone Mark 8. Exit");
            int choice = readChoice(sc);

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    service.add(title);
                    System.out.println("Todo Added Successfully.");
                }
                case 2 -> service.viewAll();

                case 3 -> {
                    System.out.print("Search keyword: ");
                    String keyword = sc.nextLine();
                    service.search(keyword);
                }
                case 4 -> {
                    System.out.print("Id to edit: ");
                    String id = sc.nextLine();
                    System.out.print("New title: ");
                    String title = sc.nextLine();
                    service.edit(id, title);
                }
                case 5 -> {
                    System.out.print("Id to delete: ");
                    String id = sc.nextLine();
                    service.delete(id);
                }
                case 6 ->{
                    System.out.print("Id to mark as done: ");
                    String id = sc.nextLine();
                    service.setDone(id);
                }
                case 7 ->{
                    System.out.print("Id to mark as undone: ");
                    String id = sc.nextLine();
                    service.setUndone(id);
                }
                case 8 -> {
                    System.out.println("Exit and close the program");
                    running = false;
                }
                default -> System.out.println("Unknown option");
            }
        }

        System.out.println("Goodbye");
        sc.close();
    }

    // Read a menu number safely. Returns -1 for blank/non-numeric input,
    // which falls through to the "Unknown option" branch instead of crashing.
    private static int readChoice(Scanner sc) {
        if (!sc.hasNextLine()) {
            return 3; // no more input (e.g. stream closed) — treat as Exit
        }
        String line = sc.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
