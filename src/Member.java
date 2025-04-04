package src;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Member {
    // Private fields
    private String name;
    private int age;
    private int IDNumber;

    // ArrayList of members
    static ArrayList<Member> memberInfo = new ArrayList<>();
    static int tempID = 0;

    // Default constructor
    public Member() {
        this.name = "";
        this.age = 0;
        this.IDNumber = 0;
    }

    // Parameterized constructor
    public Member(String name, int age, int IDNumber) {
        this.name = name;
        this.age = age;
        this.IDNumber = IDNumber;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getIDNumber() {
        return IDNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIDNumber(int IDNumber) {
        this.IDNumber = IDNumber;
    }


    // Method to sort members by ID
    public static void sortByIDNumber(ArrayList<Member> memberInfo) {
        Member.memberInfo.sort((m1, m2) -> Integer.compare(m1.getIDNumber(), m2.getIDNumber()));
    }

    // Method to search for a member by ID
    public static String searchByID(int IDNumber) {
        for (Member member : memberInfo) {
            if (member.getIDNumber() == IDNumber) {
                return member.getName();
            }
        }
        return "ID not found";
    }

    // Method to print member info
    public void printMemberInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Member ID: " + IDNumber);
    }

    // Method to welcome a member
    public static void welcomeMember(Scanner scnr) {
        System.out.println("Enter your ID number:");
        int inputID = scnr.nextInt();
        String memberName = searchByID(inputID);
        System.out.println("Welcome, " + memberName + "!");
        tempID = inputID;
    }

    // Method to create a new member
    public static void createNewMember(Scanner scnr) {
        System.out.println("Enter your name:");
        String name = scnr.next();

        System.out.println("Enter your age:");
        int age = scnr.nextInt();

        // Assign a unique ID
        int newID = assignID();

        // Create new member and add to the list
        Member newMember = new Member(name, age, newID);
        memberInfo.add(newMember);

        System.out.println("Here is your new ID number: " + newID);
    }

    // method to print all the members into file
    public static void printAllMembers() {
        // Sort members by ID
        sortByIDNumber(memberInfo);

        // Define the output file path
        File outputFile = new File("/Users/aidanbroadhead/IdeaProjects/CSC 112Labs/CapstoneProject/src/OutputSortedMembers.txt");

        // Attempt to write member information to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Loop through all members and write their info to the file
            for (Member member : memberInfo) {
                writer.write("Name: " + member.getName());
                writer.newLine();
                writer.write("Age: " + member.getAge());
                writer.newLine();
                writer.write("ID Number: " + member.getIDNumber());
                writer.newLine();
                writer.newLine();
            }
            System.out.println("Member information has been written to the file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to generate a unique ID
    private static int assignID() {
        int randomID = 0;
        boolean isUnique = false;
        int attempts = 0;

        Random rand = new Random();
        // Generate a unique ID
        while (!isUnique) {
            randomID = rand.nextInt(5000);  // Random ID in range 0-4999
            isUnique = true;

            // Check if the ID is unique by iterating through existing members
            for (Member member : memberInfo) {
                if (member.getIDNumber() == randomID) {
                    isUnique = false;  // If ID is not unique, break and generate a new one
                    break;
                }
            }

            attempts++;

            if (attempts >= 5000) {
                throw new IllegalStateException("Unable to generate a unique ID, ID space exhausted.");
            }
        }
        return randomID;
    }

}