package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Scan {

    public static void main(String[] args) {

        // Create a Scanner object to capture user input
        Scanner scanner = new Scanner(System.in);

        // Open files for logging timestamps and sorted members
        File loggedTime = new File("/Users/aidanbroadhead/IdeaProjects/CSC 112Labs/CapstoneProject/src/TimeStamps.txt");
        File sortedMembers = new File("/Users/aidanbroadhead/IdeaProjects/CSC 112Labs/CapstoneProject/src/OutputSortedMembers.txt");

        // Track number of people in the gymp
        int count = 0;

        // DateTimeFormatter for consistent timestamp format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

        Queue gymQueue = new Queue();

        // Main loop for gym operations
        boolean isOpen = true;
        try {
            FileWriter fWriter = new FileWriter(loggedTime);
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            System.out.println("Scan your ID (press enter) or type 'q' to quit...");

            while (isOpen) {
                // Get the current time for logging
                LocalDateTime currentTime = LocalDateTime.now();

                // Capture user input
                String scanInput = scanner.nextLine();

                if (scanInput.equals("q")) {
                    isOpen = false;
                    break;
                }
                // Handle member scan-in or scan-out
                if (scanInput.equals("m")) {
                    // Handle member entry
                    Member.welcomeMember(scanner);
                    gymQueue.enqueue(scanner);
                    count = addCount(count);
                    System.out.println("You were scanned in at: " + currentTime.format(formatter));
                    System.out.println("Total number of people in gym: " + count);
                    System.out.println("\nScan your ID (press enter) or type 'q' to quit...");
                } else if (scanInput.equals("nm")) {
                    // New member scan
                    Member.createNewMember(scanner);  // Create a new member if 'nm' is pressed
                    System.out.println("Your account has been created!");
                    System.out.println("\nScan your ID (press enter) or type 'q' to quit...");
                } else if (scanInput.equals("s")) {
                    // Handle scan out
                    gymQueue.removeExpiredMembers();  // Remove members who have been in for over 1 hour
                    count = gymQueue.size();  // Update the count
                    System.out.println("A person has scanned out. Total number in gym: " + count);
                } else if (scanInput.equals("p")) {
                    // Print all members to the output file
                    Member.printAllMembers();
                    System.out.println("All members have been printed to the file.");

                } else if (scanInput.equals("c")) {
                    gymQueue.printQueue();
                }else {
                    // Log scan-in with timestamp
                    bWriter.write("ID Scanned: " + scanInput + " at " + currentTime.format(formatter));
                    bWriter.newLine();
                }

                System.out.println();
            }

            // Close the BufferedWriter
            bWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to increment the count
    public static int addCount(int count) {
        count += 1;
        return count;
    }

    // Method to decrement the count when someone scans out
    public static int subtractCount(int count) {
        if (count > 0) {
            count -= 1;
        }
        return count;
    }
}
