import java.util.*;

public class AuthService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new Admin("admin", "admin123"));
    }

    public void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        User user= new User(username,password,false);
        users.add(user);
        System.out.println("Registration successful!");
    }

    public User login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome, " + username);
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }
}
