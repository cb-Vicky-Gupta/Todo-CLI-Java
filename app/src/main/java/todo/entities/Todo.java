package todo.entities;

public class Todo {
    private String id;
    private String title;
    private boolean isDone;

    public Todo(){};
    // create a todo
    public Todo(String id, String title, boolean isDone){
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }

    // getter
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    // setter
    public void setTitle(String title){
        this.title = title;
    }
    public void setDone(boolean isDone){
        this.isDone = isDone;
    }
}
