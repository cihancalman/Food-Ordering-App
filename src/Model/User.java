package Model;

public abstract class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String town;

    public User(String name, String surname, String email, String password, String town) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
