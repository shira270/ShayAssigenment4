public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean isBroker;

    public User(String userName, String password, String phoneNumber, boolean isBroker) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isBroker = isBroker;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isBroker() {
        return this.isBroker;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String toString() {
        return this.userName + " " + this.password + " " + this.phoneNumber + " " + this.isBroker;
    }

}
