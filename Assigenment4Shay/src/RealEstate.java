import java.util.Scanner;
public class RealEstate {
    private User[] users;
    private int currentUserIndex;
    private Property[] properties;
    private Address[] addresses;
    private int currentProperty;

    public RealEstate(User[] users, Property[] properties) {
        this.users = users;
        this.properties = properties;
        this.currentUserIndex = 0;
        this.currentProperty = 0;
        Address address1 = new Address("Ashkelon", "Shapira");
        Address address2 = new Address("Ashkelon", "Herzel");
        Address address3 = new Address("Tel Aviv", "Herzel");
        Address address4 = new Address("Tel Aviv", "Ben Gurion");
        Address address5 = new Address("Jerusalem", "Ben Gurion");
        Address address6 = new Address("Ashdod", "Yoseftal");
        Address address7 = new Address("Ber Sheva", "Zibotinski");
        Address address8 = new Address("Ashkelon", "Barnea");
        Address address9 = new Address("Ashkelon", "Moshe Yulish");
        Address address10 = new Address("Jerusalem", "Yehuda");
        this.addresses = new Address[10];
        this.addresses[0] = address1;
        this.addresses[1] = address2;
        this.addresses[2] = address3;
        this.addresses[3] = address4;
        this.addresses[4] = address5;
        this.addresses[5] = address6;
        this.addresses[6] = address7;
        this.addresses[7] = address8;
        this.addresses[8] = address9;
        this.addresses[9] = address10;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return this.users;
    }

    private void addUser(User user) {
        this.users[this.currentUserIndex] = user;
        this.currentUserIndex++;
    }

    private boolean checkUserName(String userName) {

        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i] == null) {
                return true;
            }
            if (userName.equals(this.users[i].getUserName())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPassword(String password) {
        boolean isDigitIn = false;
        boolean isSymbolIn = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                isDigitIn = true;
            }
            if (password.charAt(i) == '$' || password.charAt(i) == '%' || password.charAt(i) == '.' || password.charAt(i) == '_' || password.charAt(i) == ',') {
                isSymbolIn = true;
            }
        }
        return isDigitIn && isSymbolIn;
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) {
            return false;
        }
        if (phoneNumber.charAt(0) != '0' && phoneNumber.charAt(1) != '5') {
            return false;
        }
        for (int i = 2; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a username: ");
        String userName = scanner.nextLine();
        System.out.println("Enter a strong password that contains at least 1 number and one of the following symbols: $%_.,");
        String password = scanner.nextLine();
        System.out.println("Enter an israeli phone number: ");
        String phoneNumber = scanner.nextLine();
        boolean userFlag;
        boolean passwordFlag;
        boolean phoneFlag;
        do {
            userFlag = checkUserName(userName);
            if (!userFlag) {
                System.out.println("username already exists enter another one: ");
                userName = scanner.nextLine();
            }
            passwordFlag = checkPassword(password);
            if (!passwordFlag) {
                System.out.println("password not strong enough, a strong password contains at least 1 number and one of the following symbols: $%_., ");
                password = scanner.nextLine();
            }
            phoneFlag = checkPhoneNumber(phoneNumber);
            if (!phoneFlag) {
                System.out.println("phone number is not valid");
                phoneNumber = scanner.nextLine();
            }
        } while (!userFlag || !passwordFlag || !phoneFlag);
        boolean isBroker = false;
        System.out.println("Enter 1 if you are a broker or anything else if you are a regular user");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            isBroker = true;
        }
        User user = new User(userName, password, phoneNumber, isBroker);
        addUser(user);
    }

    public User login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String userName = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i] == null){
                System.out.println("incorrect username or password");
                break;
            }
            if (this.users[i].getUserName().equals(userName)) {
                if (this.users[i].getPassword().equals(password)) {
                    return this.users[i];
                }
                return null;
            }
        }
        return null;
    }

    public boolean postNewProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        int amountAllowed = 0;
        if (user.isBroker()) {
            amountAllowed = 10;
        } else {
            amountAllowed = 3;
        }
        int count = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i] == null) {
                break;
            }
            if (this.properties[i].getPublishingUser().getUserName().equals(user.getUserName())) {
                count++;
            }
        }
        if (count >= amountAllowed) {
            System.out.println("you reached you property limit");
            return false;
        }

        for (int i = 0; i < this.addresses.length - 1; i++) {
            boolean flag = true;
            for (int j = i + 1; j < this.addresses.length; j++) {
                if (this.addresses[i].getCityName().equals(this.addresses[j].getCityName())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(this.addresses[i].getCityName() + '\n');
            }
        }
        System.out.println("Enter a city name from the list: ");
        String cityName = scanner.nextLine();
        boolean flag = false;
        for (int i = 0; i < this.addresses.length; i++) {
            if (cityName.equals(this.addresses[i].getCityName())) {
                flag = true;
                System.out.println(this.addresses[i].getStreetName() + " ");
            }
        }
        if (!flag) {
            System.out.println("no such city in the list");
            return false;
        }
        System.out.println("Enter a street name: ");
        String streetName = scanner.nextLine();
        flag = false;
        for (int i = 0; i < this.addresses.length; i++) {
            if (this.addresses[i].getCityName().equals(cityName) && this.addresses[i].getStreetName().equals(streetName)) {
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("no such street in the list");
            return false;
        }

        System.out.println("What is the type of the property: " + "\n"
                + "1 - regular apartment" + "\n" +
                "2 - penthouse" + "\n" +
                "3 - private house");
        String choice = scanner.nextLine();

        Address address = new Address(cityName, streetName);
        String floor = "0";
        if (choice.equals("1")) {
            System.out.println("how many floors in the building? ");
            floor = scanner.nextLine();
        }
        System.out.println("how many rooms in the apartment? ");
        String rooms = scanner.nextLine();
        System.out.println("what is the property number? ");
        String propertyNumber = scanner.nextLine();
        System.out.println("is it for sale or rent? ");
        String isForSaleOrRent = scanner.nextLine();
        System.out.println("what is the price of the property? ");
        String price = scanner.nextLine();
        boolean isForRent = true;
        isForSaleOrRent = isForSaleOrRent.toLowerCase();
        if (isForSaleOrRent.equals("sale")) {
            isForRent = false;
        }
        Property newProperty = new Property(user, address, rooms, price, "apartment type " + choice, isForRent, propertyNumber, floor);
        this.properties[this.currentProperty] = newProperty;
        this.currentProperty++;
        return true;
    }

    public void removeProperty(User user) {
        boolean flag = false;
        for (int i = 0; i < this.properties.length; i++) {
            if (properties[i] == null) {
                System.out.println("There are no properties to remove");
                break;
            }
            if (this.properties[i].getPublishingUser().getUserName().equals(user.getUserName())) {
                System.out.println(this.properties[i].getHouseNumber() + ": " + this.properties[i].getAddress());
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("you have no properties published.");
            return;
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("choose a property to remove by entering a house number");
            String choice = scanner.nextLine();
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].getHouseNumber().equals(choice)) {
                    System.out.println("property: " + this.properties[i].getAddress() + " has been deleted");
                    this.properties[i] = null;
                }
            }
        }
    }

    public void printAllProperties() {
        for (int i = 0; i < this.properties.length; i++) {
            if (properties[i] == null){
                System.out.println("There are no properties to show");
                break;
            }
            System.out.println(this.properties[i]);

        }
    }

    public void printAllUserProperties(User user) {
        for (int i = 0; i < this.properties.length; i++) {
            if (properties[i] == null) {
                System.out.println("This user haven't posted any properties yet");
                break;
            }
            if (user.getUserName().equals(this.properties[i].getPublishingUser().getUserName())) {
                System.out.println(this.properties[i]);
            }
        }
    }

    public Property[] search() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("is for sale or rent? ");
        String isForSaleOrRent = scanner.nextLine();
        System.out.println("what is the type? ");
        String type = scanner.nextLine();
        System.out.println("what is the number of rooms? ");
        String numberOfRooms = scanner.nextLine();
        System.out.println("what is the minimum price range");
        String price = scanner.nextLine();
        String[] priceRange = price.split("-");
        boolean isForRent = true;
        if (isForSaleOrRent.equals("sale")) {
            isForRent = false;
        }
        Property[] returnedProperties = new Property[this.properties.length];
        int index = 0;
        for (int i = 0; i < this.properties.length; i++) {
            Property currentProperty = this.properties[i];
            if (properties[i] == null) {
                System.out.println("There are no properties to search");
                break;
            }
            int propertyPrice = Integer.parseInt(currentProperty.getPrice());
            if ((currentProperty.isForRent() == isForRent || isForSaleOrRent.equals("-999")) && (currentProperty.getType().equals(type) || type.equals("-999"))
                    && (currentProperty.getNumberOfRooms().equals(numberOfRooms) || numberOfRooms.equals("-999")) && (price.equals("-999") ||
                    (propertyPrice >= Integer.parseInt(priceRange[0]) && propertyPrice <= Integer.parseInt(priceRange[1])))) {
                returnedProperties[index] = currentProperty;
                index++;
                System.out.println(currentProperty);
            }
        }
        Property[] returnedPropertiesCleaned = new Property[index];
        for (int i = 0; i < returnedPropertiesCleaned.length; i++) {
            returnedPropertiesCleaned[i] = returnedProperties[i];
        }
        return returnedPropertiesCleaned;
    }
}

