package auth_validations;

public class Registration_validator{

    String uname, username, email, password;

    public Registration_validator(String uname, String username, String email, String password) {
        this.uname = uname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Boolean validation() {
        if (uname.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("\t\t\t Please fill in all the fields.");
            return false;
        }

        if (username.length() < 3) {
            System.out.println("\t\t\t Username must be at least 3 characters long.");
            return false;
        }

        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("\t\t\t Email must contain '@' and '.'");
            return false;
        }

        if (password.length() < 6) {
            System.out.println("\t\t\t Password must be at least 6 characters long.");
            return false;
        }

        if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*[0-9].*")) {
            System.out.println("\t\t\t Password must contain both letters and numbers.");
            return false;
        }
        return true;
    }

}
