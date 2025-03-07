package src;// imports everything for the scanners and date/time

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Scan {

    public static void main(String[] args) {
    // names the scanner
    Scanner scanner = new Scanner(System.in);

    // Open file
    File loggedTime = new File("/Users/aidanbroadhead/IdeaProjects/CSC 112/CapstoneProject/src/TimeStamps.txt");

    // initializes count to 0
    int count = 0;

    // sets the format for the date and time at scan
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

        // writes the timestamps of people entering and leaving the gym into txt file

        try {
            FileWriter fWriter = new FileWriter(loggedTime);
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            // sets the boolean to true because the gym is open
            boolean isOpen = true;

            // while the store is open, check to see if an ID is scanned and save the time
            while (isOpen) {

                // creates an empty string and creates object for current time
                String closeGym = "";
                String openGym = "";
                LocalDateTime currentTime = LocalDateTime.now();

                // prints the time the person was scanned in at if certain key is pressed (aka ID is scanned)
                // System.out.println("Current Time: " + currentTime.format(formatter));
                System.out.println();
                System.out.println("Scan your ID (press enter)...");

                // if enter key is pressed, the time of the scan is logged
                closeGym = scanner.nextLine();
                // openGym = scanner.nextLine();


                // if "q" is pressed, the gym closes and loop stops running
                if (closeGym.equals("q")) {
                    isOpen = false;
                    break;
                }

                bWriter.write("ID Scanned: " + closeGym + "at " + currentTime.format(formatter));
                bWriter.newLine();

                count = addCount(count);
                System.out.println("You were scanned in at: " + currentTime.format(formatter));
                System.out.println("Total number of people in gym: " + count);
                System.out.println();

//                if (openGym.equals("o")) {
//                    isOpen = true;
//                }
            }
            // Close the BufferWriter Connection
            bWriter.close();

        } catch (IOException e) {
            System.out.println("File not found" + e.getMessage());
        }


    }

    public static int addCount(int count) {
        // class that adds to count when someone scans in
        count += 1;
        return count;
    }

    /* potential classes

    public void subtractCount(int count) {
        // class that subtracts from count when someone scans out
    }

    public void closeGym(String closeGym) {
        // class that closes the gym if certain key is pressed
    }

    public void openGym(String openGym) {
        // class that opens the gym if certain key is pressed
    }

    public void statistics() {
        // class that outputs summary statistics for the day
    }

    */

    }
