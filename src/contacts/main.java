package contacts;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        contactmanager contacts = new contactmanager();
        String filename = "contacts.csv";

        contacts.loadfromfile(filename);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("/n=== Contact Manager ===");
            System.out.println("1. Add Contact");
            System.out.println("2. Update Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. List Contacts");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    contacts.addContact(new contact(id, name, phoneNumber, email));
                    break;
                case 2:
                    System.out.print("Enter ID of contact to update: ");
                    String updateId = sc.nextLine();
                    contact existingContact = contacts.findContactById(updateId);
                    if (existingContact != null) {
                        System.out.print("Enter new Name (leave blank to keep current): ");
                        String newName = sc.nextLine();
                        if (!newName.isEmpty()) {
                            existingContact.setName(newName);
                        }
                        System.out.print("Enter new Phone Number (leave blank to keep current): ");
                        String newPhoneNumber = sc.nextLine();
                        if (!newPhoneNumber.isEmpty()) {
                            existingContact.setPhoneNumber(newPhoneNumber);
                        }
                        System.out.print("Enter new Email (leave blank to keep current): ");
                        String newEmail = sc.nextLine();
                        if (!newEmail.isEmpty()) {
                            existingContact.setEmail(newEmail);
                        }
                        contacts.update(existingContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter ID of contact to delete: ");
                    String deleteId = sc.nextLine();
                    if (contacts.deleteContact(deleteId)) {
                        System.out.println("Contact deleted.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 4:
                    List<contact> allContacts = contacts.getAllContacts();
                    for (contact c : allContacts) {
                        System.out.println(c.toCSV());
                    }
                    break;
                case 5:
                    contacts.saveToFile(filename);
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
