package auth_validations;

public class Login_validator {
	
	String username = null, password = null;
	
	public Login_validator(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	 public Boolean validation() {
	       
	        if (username.length() < 3) {
	            System.out.println("\t\t\t Username must be at least 3 characters long.");
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
