package src;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Iterator;

public class Queue extends Member {
    private QueueNode front;
    private QueueNode rear;

    // Constructor
    public Queue() {
        this.front = null;
        this.rear = null;
    }

    // Add a new member to the queue
    public void enqueue(Scanner scnr) {
        LocalDateTime currentTime = LocalDateTime.now();
//        Member.searchByID(scnr.nextInt());
        for (Member member : memberInfo) {
            if (member.getIDNumber() == Member.tempID) {
                QueueNode newNode = new QueueNode(member, currentTime);

                if (rear == null) {
                    front = newNode;
                    rear = newNode;
                } else {
                    rear.next = newNode;
                    rear = newNode;
                }
            }
        }
    }


    // Remove members who have been in the gym for more than 1 hour
//    public void removeExpiredMembers() {
//        LocalDateTime currentTime = LocalDateTime.now();
//        while (front != null && currentTime.minusHours(1).isAfter(front.entryTime)) {
//            System.out.println("Member " + front.member.getName() + " has been in the gym for over an hour. Removing...");
//            front = front.next;  // Remove the front member
//        }
//    }

    public void removeExpiredMembers() {
        LocalDateTime currentTime = LocalDateTime.now();
        while (front != null && currentTime.minusMinutes(1).isAfter(front.entryTime)) {
            System.out.println("Member " + front.member.getName() + " has been in the gym for over a minute. Removing...");
            front = front.next;  // Remove the front member
        }
    }

    // Get the size of the queue (number of members in the gym)
    public int size() {
        int size = 0;
        QueueNode current = front;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public void printQueue() {
        QueueNode current = front;
        int i = 1;
        System.out.println("------------------------------------------------");
        System.out.println("Current Members in Gym:");
        while (current != null) {
            System.out.println(i + ". Name: " + current.member.getName()
                    + " | ID: " + current.member.getIDNumber()
                    + " | Entry Time: " + current.entryTime);
            current = current.next;
            i++;
        }
        System.out.println("------------------------------------------------");
        System.out.println("Total Members in Gym: " + size());
    }
}

