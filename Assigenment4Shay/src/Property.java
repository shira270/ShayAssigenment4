public class Property {
    private User publishingUser;
    private Address address;
    private String numberOfRooms;
    private String price;
    private String type;
    private boolean isForRent;
    private String houseNumber;
    private String floorNumber;

    public Property(User publishingUser, Address address, String numberOfRooms, String price, String type, boolean isForRent, String houseNumber, String floorNumber){
        this.publishingUser = publishingUser;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.price = price;
        this.type = type;
        this.isForRent = isForRent;
        this.houseNumber = houseNumber;
        this.floorNumber = floorNumber;
    }

    public User getPublishingUser(){
        return this.getPublishingUser();
    }

    public Address getAddress(){
        return this.address;
    }

    public String getHouseNumber(){
        return this.houseNumber;
    }

    public String getFloorNumber() {
        return this.floorNumber;
    }

    public String getNumberOfRooms() {
        return this.numberOfRooms;
    }

    public String getPrice() {
        return this.price;
    }

    public String getType() {
        return this.type;
    }

    public boolean isForRent() {
        return this.isForRent;
    }

    public String toString(){
        String rentOrSale = "sale";
        if (isForRent){
            rentOrSale = "rent";
        }
        String floorNumber = "";
        if (!type.equals("Penthouse")){
            floorNumber = ", floor" + this.floorNumber;
        }
        String brokerOrNot = "";
        if(this.publishingUser.isBroker()){
            brokerOrNot = "(real estate broker)";
        }
        return this.type + " - " + "for " + rentOrSale + ": " + this.numberOfRooms + " rooms" + floorNumber
                + "\n Price: " + this.price + "$"
                + "\n Contact Info: " + this.publishingUser.getUserName() + " " + this.publishingUser.getPhoneNumber() + brokerOrNot;
    }

}



