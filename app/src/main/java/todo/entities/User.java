package todo.entities;

public class User {
    private String username;
    private String id;
    private String password;
    private String fullName;
    private String email;
    private String hashedPassword;

    public User(){}

    public User(String username, String id, String password, String fullName, String email, String hashedPassword){
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.hashedPassword = hashedPassword;
    }

    // getter
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getHashedPassword() { return hashedPassword; }

    //setter
    public void setId(String id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

}
