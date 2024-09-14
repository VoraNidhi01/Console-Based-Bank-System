import java.util.HashMap;
import java.util.Map;

public class User {
    private Map<String, String> userMap; // email -> password
    private Map<String, String> fullNameMap; // email -> full name

    public User() {
        userMap = new HashMap<>();
        fullNameMap = new HashMap<>();
    }

    public void register(String email, String password, String fullName) {
        if (userMap.containsKey(email)) {
            System.out.println("User Already Exists for this Email Address!!");
            return;
        }
        userMap.put(email, password);
        fullNameMap.put(email, fullName);
        //System.out.println("Registration Successful!");
    }

    public String login(String email, String password) {
        String storedPassword = userMap.get(email);
        if (storedPassword != null && storedPassword.equals(password)) {
            return fullNameMap.get(email); // Return full name if login is successful
        }
        return null;
    }
}
