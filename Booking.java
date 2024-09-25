public class Booking {
    private static final int CAPACITY = 4;
    private String customerName;
    private String currentDate;
    private EventToBook[] eventArray;
    private int eventCount;
    private int collectedPoints;
    private double totalPrice;

    public Booking() {
        this.customerName = "Unknown";
        this.currentDate = "1 May 2023";
        this.eventArray = new EventToBook[CAPACITY];
        this.eventCount = 0;
    }

    public Booking(String name, String date, double totalPrice) {
        this.customerName = name;
        this.currentDate = date;
        this.eventArray = new EventToBook[CAPACITY];
        this.eventCount = 0;
        this.totalPrice = totalPrice;
        this.collectedPoints = 0;
    }

    public String getCustomerName() {
        return this.customerName;
    }
    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getDate() {
        return this.currentDate;
    }
    public void setDate(String date) {
        this.currentDate = date;
    }

    public boolean addEvent(EventToBook event) {
        if (eventCount == CAPACITY) {
            System.out.println("BOOKING LIMIT IS REACHED");
            return false;
        }

        for (int i = 0; i < eventCount; i++) {
            if (eventArray[i].getTitle().equals(event.getTitle())) {
                System.out.println("EVENT ALREADY EXISTS");
                return false;
            }
        }

        eventArray[eventCount++] = event;
        return true;
    }

    public int getTotalNumPeople() {
        int totalNumPeople = 0;
        for (int i = 0; i < eventCount; i++) {
            totalNumPeople += eventArray[i].getQuantity();
        }
        return totalNumPeople;
    }

    public double getTotalCost() {
        int totalCost = 0;
        for (int i = 0; i < eventCount; i++) {
            totalCost += eventArray[i].getCost();
        }
        return totalCost;
    }

    public void printTotal(){
        System.out.println(getCustomerName() + " - " + currentDate);
    System.out.println("Number of people: " + getTotalNumPeople());
    
    boolean eventFound = false;
    for (int i = 0; i < eventCount; i++) {
        EventToBook event = eventArray[i];
        System.out.println(event.getTitle() + ": " + event.getQuantity() + " @ $" +
                event.getPrice() + " = $" + event.getCost());
        eventFound = true;
    }
    
    if (!eventFound) {
        System.out.println("NO BOOKING!");
    }
    
    System.out.println("Total: $" + getTotalCost());
    System.out.println();
    }

    public boolean modifyEvent(String eventTitle, int quantity) {
        EventToBook foundEvent = null;
        for (int i = 0; i < eventCount; i++) {
            EventToBook event = eventArray[i];
            if (event.getTitle().equalsIgnoreCase(eventTitle)) {
                foundEvent = event;
                break;
            }
        }

        if (foundEvent == null) {
            System.out.println(eventTitle + " not found in the booking");
            return false;
        }

        if (quantity <= 0) {
            System.out.println("Invalid number!");
            return false;
        }

        foundEvent.setQuantity(quantity);
        return true;
    }

    public boolean removeEvent(String eventTitle) {
        for (int i = 0; i < eventCount; i++) {
            EventToBook event = eventArray[i];
            if (event.getTitle().equalsIgnoreCase(eventTitle)) {
                for (int j = i; j < eventCount - 1; j++) {
                    eventArray[j] = eventArray[j + 1];
                }
                eventArray[eventCount - 1] = null;
                eventCount--;
                System.out.println(eventTitle + " is removed from your booking");
                return true;
            }
        }

        System.out.println(eventTitle + " not found in the booking");
        return false;
    }

    public void checkout() {
        printTotal();
        eventArray = new EventToBook[CAPACITY];
        eventCount = 0;
    }

//Stage 4
    public double getTotalPrice() {
    return totalPrice;
    }

    public int getCollectedPoints() {
    return collectedPoints;
    }
    
    public void setPoints(int points) {
        this.collectedPoints = points;
    }

    public void applyDiscount() {
        double totalCost = getTotalCost();
        if (totalCost >= 100) {
            double discount = totalCost * 0.05;
            totalCost -= discount;
        }
        totalCost = Math.round(totalCost);
    }

    public void collectPoints() {
        collectedPoints = (int) Math.round(totalPrice);
    }

    public double usePoints(int pointsToUse) {
        if (pointsToUse < 50 || pointsToUse > collectedPoints || pointsToUse % 50 != 0) {
            return 0;
        }
        int pointsInDollars = pointsToUse / 50;
        double discount = pointsInDollars;
        totalPrice -= discount;
        collectedPoints -= pointsToUse;
        return discount;
    }

    
}