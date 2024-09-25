import java.util.Scanner;

public class BookingManager {
    public static void stage1(EventToBook event){
        Scanner scanner = new Scanner(System.in);

            System.out.print("Enter event title: ");
            String title = scanner.nextLine();

            System.out.print("Enter price per person: ");
            int price = scanner.nextInt();

            System.out.print("Enter number of people: ");
            int quantity = scanner.nextInt();

            event.setTitle(title);
            event.setPrice(price);
            event.setQuantity(quantity);

            System.out.println(event.toString());
            
    }
    public static void stage2(Booking booking){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer's name: ");
        String customerName = scanner.nextLine();
        booking.setCustomerName(customerName);

        System.out.print("Enter booking date: ");
        String currentDate = scanner.nextLine();
        booking.setDate(currentDate);

        booking.printTotal();

        String moreEvents;
        do {
            // Prompt the user for event details
            System.out.print("Enter event title: ");
            String title = scanner.nextLine();

            System.out.print("Enter price per person: ");
            int price = scanner.nextInt();

            System.out.print("Enter number of people: ");
            int quantity = scanner.nextInt();

            // Create an EventToBook object with the provided details
            EventToBook event = new EventToBook();
            event.setTitle(title);
            event.setPrice(price);
            event.setQuantity(quantity);

            boolean added = booking.addEvent(event); // Add the event to the booking
            if (!added) {
                System.out.println("Failed to add the event.");
            }

            scanner.nextLine(); // Consume the newline character

            System.out.print("Do you want to add more events? (yes/no): ");
            moreEvents = scanner.nextLine();
        } while (moreEvents.equalsIgnoreCase("yes"));
        booking.printTotal();
    }

    public static void stage3(Booking booking){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to remove an event? (yes/no): ");
        String removeEventAnswer = scanner.nextLine();

        if (removeEventAnswer.equalsIgnoreCase("yes")) {
            System.out.print("Enter the event name to be removed: ");
            String removeEventTitle = scanner.nextLine();
    
            boolean removed = booking.removeEvent(removeEventTitle);
            if (removed) {
                System.out.println("Updated Booking:");
                booking.printTotal();
            }
        }

        System.out.print("Do you want to modify an event? (yes/no): ");
        String modifyEventAnswer = scanner.nextLine();

        if (modifyEventAnswer.equalsIgnoreCase("yes")) {
            System.out.print("Enter the event name to be modified: ");
            String modifyEventTitle = scanner.nextLine();

            System.out.print("Enter the new number of participants: ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            boolean modified = booking.modifyEvent(modifyEventTitle, newQuantity);
            if (modified) {
            System.out.println("Updated Booking:");
            booking.printTotal();
        }
    }

        // Check-out
        System.out.print("Do you want to check-out? (yes/no): ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            booking.checkout();
            System.out.println("Check-out completed!");
        }
    }
    

    public static void stage4(Booking booking){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer's name: ");
        String customerName = scanner.nextLine();
        booking.setCustomerName(customerName);

        System.out.print("Enter booking date: ");
        String currentDate = scanner.nextLine();
        booking.setDate(currentDate);

        System.out.print("Enter the available points: ");
        int points = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        booking.setPoints(points);

        String moreEvents;
        do {
            // Prompt the user for event details
            System.out.print("Enter the title of the event: ");
            String title = scanner.nextLine();

            System.out.print("Enter the price per person: ");
            int price = scanner.nextInt();
            scanner.nextLine();// Consume the newline character

            System.out.print("Enter the number of people: ");
            int quantity = scanner.nextInt();
            

            // Create an EventToBook object with the provided details
            EventToBook event = new EventToBook();
            event.setTitle(title);
            event.setPrice(price);
            event.setQuantity(quantity);

            boolean added = booking.addEvent(event); // Add the event to the booking
            if (!added) {
                System.out.println("Failed to add the event.");
            }

            scanner.nextLine(); // Consume the newline character

            
            System.out.print("Add more? (Y/N): ");
            moreEvents = scanner.nextLine();
        } while (moreEvents.equalsIgnoreCase("Y"));
        booking.printTotal();
        
        if(booking.getTotalCost() >= 100){
                double totalCost = booking.getTotalCost();
                double discount = totalCost * 0.05;
                totalCost -= discount;
                System.out.println("Total Price after Discount: $" + totalCost);
            }
            
        double bookingPoints = booking.getCollectedPoints();
        double getTotalCost = booking.getTotalCost();
        while (true) {
            System.out.print("Redeem points? Y/N: ");
            String redeemChoice = scanner.nextLine().toUpperCase();
            
            if (redeemChoice.equals("N")) {
                break; // Exit the loop if the answer is 'N'
            } else if (redeemChoice.equals("Y")) {
                System.out.print("Points to be redeemed, -1 to quit: ");
                int pointsToRedeem = Integer.parseInt(scanner.nextLine());
                
                if (pointsToRedeem == -1) {
                    break; // Exit the loop if the answer is -1
                } else if (pointsToRedeem < 50) {
                    System.out.println("Less than 50, please retry. -1 to quit.");
                    continue; // Restart the loop
                } else if (pointsToRedeem > bookingPoints) {
                    System.out.println("Not enough points, please retry. -1 to quit.");
                    continue; // Restart the loop
                } else {
                    int roundedPoints = (int) (Math.floor(pointsToRedeem / 50.0) * 50);
                    System.out.println("Redeeming " + roundedPoints + " points.");
                    for (int i = 0; i < roundedPoints / 50; i++) {
                        getTotalCost -= 1;
                    }
                    System.out.println("Total to pay: $" + getTotalCost);
                    System.out.println(getTotalCost+"points added");
                    break; // Exit the loop
                }
            }
            System.out.println("Thank you and bye...");
        }
        scanner.close();
        }
    

    public static void main(String[] args) {
        EventToBook event = new EventToBook();
        Booking booking = new Booking();
        Booking special = new Booking();
        System.out.println("************** Stage 1 **************");
        stage1(event);
        System.out.println("************** Stage 2 **************");
        stage2(booking);
        System.out.println("************** Stage 3 **************");
        stage3(booking);
        System.out.println("************** Stage 4 **************");
        stage4(special);
    }
}

