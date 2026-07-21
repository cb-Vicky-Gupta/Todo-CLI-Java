package todo.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import todo.entities.User;
import todo.utils.PasswordHasher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServices {
    private static final String FILE_PATH = "app/src/main/java/todo/db/users.json";

    private final ObjectMapper mapper = new ObjectMapper();
    private List<User> users = new ArrayList<>();

    public UserServices(){
        load();
    }
    public boolean register(String username, String fullName, String email, String password){
        if (findByUsername(username) != null) {
            return false;
        }
        String id = String.valueOf(users.size()+1);
        String hashedPassword = PasswordHasher.hash(password);
        users.add(new User(username, id, password, fullName, email, hashedPassword));
        save();
        return true;
    }
    public User login(String username , String password){
        User user = findByUsername(username);
        if(user == null){
            return null;
        }
        if(PasswordHasher.matchPassword(password, user.getHashedPassword())){
            return user;
        }
        return null;
    }

    private User findByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }
    private void load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try {
            users = mapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            System.out.println("Could not read users file: " + e.getMessage());
        }
    }

    private void save() {
        try {
            File file = new File(FILE_PATH);
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
            System.out.println("Saved users to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Could not save users file: " + e.getMessage());
        }
    }

}
