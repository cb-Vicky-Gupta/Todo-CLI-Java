package todo;

import todo.entities.Todo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Todo is running");
        Todo task = new Todo("1","Buy Milk", false);
        task.setTitle("Buy Milk");
        System.out.println(task.getTitle());
        System.out.println(task.isDone());
        task.setDone(true);
        System.out.println(task.isDone());
        Scanner sc = new Scanner(System.in);
        boolean running  = true;
        while(running){
            System.out.println("\n1. Add 2. View 3. Search 4. Edit 5. Delete 6.Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1 -> System.out.println("Todo : Add");
                case 2 -> System.out.println("Todo : View");
                case 3 -> System.out.println("Todo : Search");
                case 4 -> System.out.println("Todo : Edit");
                case 5 -> System.out.println("Todo : Delete");
                case 6 -> System.out.println("Exit and Close the program");
                default -> System.out.println("Unknown option");
            }

            System.out.println("Goodbye");
        }
    }
}
