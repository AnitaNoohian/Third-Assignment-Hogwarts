import java.util.HashMap;
import java.util.UUID;

public class Account implements AccountManagement{

    private String username;
    // TODO: Passwords should hashed
    private String password;
    private UUID accountID;
   // HashMap<UUID, Account> hashMap;
    public Account(String username, String password){
        this.password = password;
        this.username = username;
        this.accountID = UUID.randomUUID();
    //    this.hashMap = new HashMap<>();
    }
    private String hashPassword(String password){
        long mod = 1000000009, mabna = 203,
                pow = 1, pass = 0;
        for (int i = 0; i < password.length(); i++){
            int save = password.charAt(i) - '0';
            pass = (pass + (pow * save)) % mod;
            pow = (pow * mabna) % mod;
        }
        return String.valueOf(pass);
    }
    @Override
    public boolean validatePassword(String enteredPassword) {

        return enteredPassword.equals(this.password);
    }

    @Override
    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
    public String getUsername(){
        return username;
    }
    public UUID getAccountID(){
        return accountID;
    }
}
