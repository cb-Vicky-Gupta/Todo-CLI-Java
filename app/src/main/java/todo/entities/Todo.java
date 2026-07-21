package todo.entities;

public class Todo {
    private String id;
    private String ownerId; // which user this todo belongs to
    private String title;
    private boolean isDone;

    public Todo(){};
    // create a todo
    public Todo(String id, String ownerId, String title, boolean isDone){
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.isDone = isDone;
    }

    // getter
    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    // setter (Jackson uses these to load todos from JSON)
    public void setId(String id){
        this.id = id;
    }
    public void setOwnerId(String ownerId){
        this.ownerId = ownerId;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDone(boolean isDone){
        this.isDone = isDone;
    }
}
