package src;

public class Member {
    private String name;
    private int age;
    private int IDNumber;

    public Member () {
        name = "";
        age = 0;
        IDNumber = 0;
    }

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

    public void printMemberInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Member ID: " + IDNumber);
    }
}