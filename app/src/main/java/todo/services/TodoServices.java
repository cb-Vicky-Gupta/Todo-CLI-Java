package todo.services;

import todo.entities.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoServices {
    private List<Todo> todos = new ArrayList<>();
    public void add(String title){
        String id = String.valueOf(todos.size()+1);
        todos.add(new Todo(id, title, false));
    }
    public void viewAll(){
        if(todos.isEmpty()){
            System.out.println("No Tasks yet"); return;
        }
        for (Todo todo : todos){
            System.out.println(todo.getId() + ". [" + (todo.isDone() ? "x" : " ") + "] " + todo.getTitle());
        }
    }
    public void search(String keyword){
        todos.stream()
                .filter(t-> t.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .forEach(todo -> System.out.println(todo.getTitle()));
    }
    public void edit(String id, String title){

    }
}
