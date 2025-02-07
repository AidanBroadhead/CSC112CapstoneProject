// imports everything for the scanners and date/time
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Scan {

public static void main(String[] args){
    // names the scanner
    Scanner scanner = new Scanner(System.in);

    // sets the boolean to true because the gym is open
    boolean isOpen = true;

    int count = 0;

    // sets the format for the date and time at scan
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

    // while the store is open, check to see if an ID is scanned and save the time
    while(isOpen){

        // creates an empty string and creates object for current time
        String closeGym = "";
        LocalDateTime currentTime = LocalDateTime.now();

        // prints the time the person was scanned in at if certain key is pressed (aka ID is scanned)
        // System.out.println("Current Time: " + currentTime.format(formatter));
        System.out.println();
        System.out.println("Scan your ID (press enter)...");

        // if enter key is pressed, the time of the scan is logged
        closeGym = scanner.nextLine();
        count += 1;
        System.out.println("You were scanned in at: " + currentTime.format(formatter));
        System.out.println("Total number of people in gym: " + count);
        System.out.println();

        // if "q" is pressed, the gym closes and loop stops running
        if(closeGym.equals("q")) {
            isOpen = false;
        }
    }
}

}
