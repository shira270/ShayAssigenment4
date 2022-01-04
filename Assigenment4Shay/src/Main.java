import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        User u = new User("Shira", "Shira270$", "0536246186", true);
        User[] users = {u, null, null, null, null};
        Property[] properties = new Property[10];
        RealEstate realEstate = new RealEstate(users, properties);
        realEstate.setUsers(users);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose one of the options by pressing the option Number: \n" +
                    "1 - create an account \n" +
                    "2 - log into an existing account \n" +
                    "3 - exit");
            String choice = scanner.nextLine();
            User user;
            switch (choice) {
                case "1":
                    realEstate.createUser();
                    continue;
                case "2":
                    user = realEstate.login();
                    if (user == null) {
                        System.out.println("incorrect username or password.");
                        continue;
                    }
                    break;
                case "3":
                    return;
                default:
                    continue;
            }
            System.out.println("Choose 1 - 6");
            System.out.println("1 - post a new property \n" +
                    "2 - delete property post \n" +
                    "3 - print all properties \n" +
                    "4 - print all your posted properties \n" +
                    "5 - search property by parameter \n" +
                    "6 - disconnect and return to main menu");
            choice = scanner.nextLine();
            Property[] searchedProperties;
            switch (choice) {
                case "1":
                    System.out.println(realEstate.postNewProperty(user));
                    break;
                case "2":
                    realEstate.removeProperty(user);
                    break;
                case "3":
                    realEstate.printAllProperties();
                    break;
                case "4":
                    realEstate.printAllUserProperties(user);
                    break;
                case "5":
                    searchedProperties = realEstate.search();
                    break;
                case "6":
                    continue;


            }


        }

    }
}


