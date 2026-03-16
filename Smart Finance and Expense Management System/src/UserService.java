import java.util.TreeMap;

public class UserService {

    private TreeMap<String, User> users;

    // Constructor injection
    public UserService(TreeMap<String, User> users) {
        this.users = users;
    }

    public TreeMap<String, User> getUsers() {
        return users;
    }
    public boolean deleteUser(String username){
        if (!users.containsKey(username)){
            return false;
        }
        users.remove(username);
        return true;
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password));
        return true;
    }

    public User login(String username, String password) {
        if (!users.containsKey(username)) {
            return null;
        }
        User user = users.get(username);
        return user.getPassword().equals(password) ? user : null;
    }
}
