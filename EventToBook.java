// Purpose: EventToBook class for A2

public class EventToBook {
    // The fields of the EventToBook object.
    private String eventTitle;
    private int pricePerPerson;
    private int numOfPeople;
    // The no-argument constructor initializes the fields of the EventToBook object to default values.
    public EventToBook() {
        eventTitle = "none";
        pricePerPerson = 0;
        numOfPeople = 0;
    }
    // The constructor with arguments initializes the fields of the EventToBook object to the specified values.
    public EventToBook(String title, int price, int quantity) {
        eventTitle = title;
        pricePerPerson = price;
        numOfPeople = quantity;
    }
    // The setTitle method sets the event title of the EventToBook object.
    public void setTitle(String title) {
        eventTitle = title;
    }
    // The getTitle method returns the event title of the EventToBook object.
    public String getTitle() {
        return eventTitle;
    }
    // The setPrice method sets the price per person of the EventToBook object.
    public void setPrice(int price) {
        pricePerPerson = price;
    }
    // The getPrice method returns the price per person of the EventToBook object.
    public int getPrice() {
        return pricePerPerson;
    }
    // The setQuantity method sets the number of people attending the event of the EventToBook object.
    public void setQuantity(int quantity) {
        numOfPeople = quantity;
    }
    // The getQuantity method returns the number of people attending the event of the EventToBook object.
    public int getQuantity() {
        return numOfPeople;
    }
    // The getCost method returns the total cost of the EventToBook object.
    public int getCost() {
        return pricePerPerson * numOfPeople;
    }
    // The toString method returns a string representation of the EventToBook object.
    public String toString() {
        int titleLength = eventTitle.length();
        String paddedTitle = (titleLength > 20) ? eventTitle.substring(0, 17) + "..." : eventTitle;
        int totalCost = getCost();
        return String.format("%-20s: %d @ $%d = $%d", paddedTitle, numOfPeople, pricePerPerson, totalCost);
    }
}
    // public static void main(String[] args) {
    //     stage1(new EventToBook());
    // }
    