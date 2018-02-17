package techture.edutechture.bean.request;

/**
 * Created by Akshay on 02/17/18.
 */

public class AdminLoginRequest {

    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
