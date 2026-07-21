package todo;

import todo.services.TodoServices;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Todo is running");

        TodoServices service = new TodoServices();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add 2. View 3. Search 4. Edit 5. Delete 6. Set Done mark 7. Set Undone Mark 8. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

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
}
