package todo.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import todo.entities.Todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoServices {
    private static final String FILE_PATH = "app/src/main/java/todo/db/todos.json";

    private final ObjectMapper mapper = new ObjectMapper();
    private List<Todo> todos = new ArrayList<>();
    private final String ownerId;

    public TodoServices(String ownerId) {
        this.ownerId = ownerId;
        load();
    }

    public void add(String title){
        todos.add(new Todo(nextId(), ownerId, title, false));
        save();
    }

    public void viewAll(){
        boolean any = false;
        for (Todo todo : todos){
            if (isMine(todo)) {
                System.out.println(todo.getId() + ". [" + (todo.isDone() ? "x" : " ") + "] " + todo.getTitle());
                any = true;
            }
        }
        if (!any) {
            System.out.println("No Tasks yet");
        }
    }

    public void search(String keyword){
        todos.stream()
                .filter(this::isMine)
                .filter(t -> t.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .forEach(todo -> System.out.println(todo.getTitle()));
    }

    public void edit(String id, String title){
        for (Todo t : todos){
            if(isMine(t) && t.getId().equals(id)){
                t.setTitle(title);
                save();
                return;
            }
        }
    }

    public void delete(String id){
        boolean removed = todos.removeIf(todo -> isMine(todo) && todo.getId().equals(id));
        if (removed) save();
    }

    public void setDone(String id){
        for (Todo t : todos){
            if(isMine(t) && t.getId().equals(id)){
                t.setDone(true);
                save();
                return;
            }
        }
    }

    public void setUndone(String id){
        for (Todo t : todos){
            if(isMine(t) && t.getId().equals(id)){
                t.setDone(false);
                save();
                return;
            }
        }
    }


    private boolean isMine(Todo t) {
        return ownerId.equals(t.getOwnerId());
    }

    private String nextId() {
        int max = 0;
        for (Todo t : todos) {
            try {
                max = Math.max(max, Integer.parseInt(t.getId()));
            } catch (NumberFormatException ignored) {
            }
        }
        return String.valueOf(max + 1);
    }


    private void load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try {
            todos = mapper.readValue(file, new TypeReference<List<Todo>>() {});
        } catch (IOException e) {
            System.out.println("Could not read todos file: " + e.getMessage());
        }
    }

    private void save() {
        try {
            File file = new File(FILE_PATH);
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, todos);
        } catch (IOException e) {
            System.out.println("Could not save todos file: " + e.getMessage());
        }
    }
}
