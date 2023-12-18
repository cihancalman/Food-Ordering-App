package Models;

public class Customer extends User {

    private String fullAdress;

    public Customer(String name, String surname, String email, String password, String town, String fullAdress) {
        super(name, surname, email, password, town);
        this.fullAdress = fullAdress;
    }


    public String getFullAdress() {
        return fullAdress;
    }

    public void setFullAdress(String fullAdress) {
        this.fullAdress = fullAdress;
    }


    @Override
    public String toString() {

        return this.getName()+";"+this.getSurname()+";"+this.getEmail()+";"+this.getPassword()+";"+this.getTown()+";"+this.getFullAdress();
    }
}
