package kafkaclient;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String firstName;
    private String nickName;
    private String lastName;
    private int age;
    private ArrayList<String> emails;
    private PhoneNumber phoneNumber;
    private StatusEnum status;

    public Employee(String firstName, String nickName, String lastName, int age, ArrayList<String> emails, PhoneNumber phoneNumber, StatusEnum status) {
        this.firstName = firstName;
        this.nickName = nickName;
        this.lastName = lastName;
        this.age = age;
        this.emails = emails;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", emails=" + emails +
                ", phoneNumber=" + phoneNumber +
                ", status=" + status +
                '}';
    }
}
