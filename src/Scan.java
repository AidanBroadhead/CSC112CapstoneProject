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

        // Track number of people in the gym
        int count = 0;
        int totalCount = 0;


        // Initialize the crowdMeter
        CrowdMeter crowdMeter = null;
        try {
            crowdMeter = new CrowdMeter("/Users/aidanbroadhead/IdeaProjects/CSC 112Labs/CapstoneProject/src/CrowdMeterVisual.txt");
        } catch (IOException e) {
            System.out.println("Crowd meter visual not found");
            return;
        }

        // DateTimeFormatter for consistent timestamp format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

        // create queue
        Queue gymQueue = new Queue();

        // Main loop for gym operations
        boolean isOpen = true;
        try {
            FileWriter fWriter = new FileWriter(loggedTime);
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            System.out.println("Press 'nm' for new members, 'm' for existing members, 'c' for queue, 'p' for printing, or 'q' to quit");

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
                    // Handle member entry, remove expired members, adjust count
                    gymQueue.removeExpiredMembers();
                    count = gymQueue.size();
                    if (Member.welcomeMember(scanner)) {
                        gymQueue.enqueue(scanner);
                        count = addCount(count);
                        totalCount += 1;
                        // Log the crowd size
                        crowdMeter.logCrowdSize(currentTime, count);
                        System.out.println("You were scanned in at: " + currentTime.format(formatter));
                        System.out.println("Total number of people in gym: " + count);
                        System.out.println("\nPress 'nm' for new members, 'm' for existing members, 'c' for queue, 'p' for printing, or 'q' to quit");
                    } else {
                        System.out.println("\nPress 'nm' for new members, 'm' for existing members, 'c' for queue, 'p' for printing, or 'q' to quit");
                    }
                } else if (scanInput.equals("nm")) {
                    gymQueue.removeExpiredMembers();
                    count = gymQueue.size();
                    // New member scan
                    Member.createNewMember(scanner);  // Create a new member if 'nm' is pressed
                    System.out.println("Your account has been created!");
                    System.out.println("\nPress 'nm' for new members, 'm' for existing members, 'c' for queue, 'p' for printing, or 'q' to quit");
                } else if (scanInput.equals("s")) {
                    // Handle scan out
                    gymQueue.removeExpiredMembers();  // Remove members who have been in for over 1 hour
                    count = gymQueue.size() - 1;  // Update the count
                    System.out.println("A person has scanned out. Total number in gym: " + count);
                } else if (scanInput.equals("p")) {
                    // Print all members to the output file
                    Member.printAllMembers();
                } else if (scanInput.equals("c")) {
                    // remove expired, adjusts count, prints queue
                    gymQueue.removeExpiredMembers();
                    count = gymQueue.size();
                    gymQueue.printQueue();
                } else if (scanInput.equals("t")) {
                    summaryStats(totalCount);
                } else {
                    gymQueue.removeExpiredMembers();
                    count = gymQueue.size();
                    // Log scan-in with timestamp
                    bWriter.write("ID Scanned: " + scanInput + " at " + currentTime.format(formatter));
                    bWriter.newLine();
                }

                // write to the crowd meter file
                crowdMeter.logCrowdSize(currentTime, gymQueue.size());
                System.out.println();
            }

            // Close the crowd meter writer
            if (crowdMeter != null) {
                crowdMeter.close();
            }

            // Close the BufferedWriter
            bWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing to file");
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

    // Method to decrement the count when someone scans out
    public static void summaryStats(int totalCount) {
        System.out.println("Total number of patrons at gym today: " + totalCount);
    }

}
