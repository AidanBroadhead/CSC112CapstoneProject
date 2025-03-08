package src;
import java.util.ArrayList;

public class Member {
    // private fields
    private String name;
    private int age;
    private int IDNumber;

    // default constructor
    public Member () {
        name = "";
        age = 0;
        IDNumber = 0;
    }

    // parameterized constructor
    public Member(String name, int age, int IDNumber) {
        this.name = name;
        this.age = age;
        this.IDNumber = IDNumber;
    }

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

    // sorting method
    public static void sortByIDNumber(ArrayList<Member> memberInfo) {
        // set boolean to false and length to the size
        boolean isSorted = false;
        int length = memberInfo.size();

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < length - 1; i++) {
                // uses comparator to compare adjacent elements
                if (new compare().compare(memberInfo.get(i), memberInfo.get(i + 1)) > 0) {
                    // call swap
                    swap(memberInfo, i, i + 1);
                    // make the loop run again since a swap occurred
                    isSorted = false;
                }
            }
            // lowers size of list by 1
            length = length - 1;
        }
    }

    // swap method
    public static void swap(ArrayList<Member> memberInfo, int a, int b) {
        // creates temporary variable for the swap and then swaps consecutive elements
        Member temp = memberInfo.get(a);
        memberInfo.set(a, memberInfo.get(b));
        memberInfo.set(b, temp);
    }

    // prints member info
    public void printMemberInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Member ID: " + IDNumber);
    }
}