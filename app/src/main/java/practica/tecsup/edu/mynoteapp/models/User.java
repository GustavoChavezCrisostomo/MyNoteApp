package practica.tecsup.edu.mynoteapp.models;

/**
 * Created by ASUS on 2/05/2017.
 */

public class User {
    private String email;
    private String password;
    private String fullname;

    public User() {
    }

    public User( String fullname, String email, String password) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String fullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "User{" +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}