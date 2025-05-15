package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class contactmanager {
    private List<contact> contacts = new ArrayList<>();

    public void loadfromfile(String filename) {
        contacts.clear(); // Clear existing contacts
        File file = new File(filename);
        if (!file.exists()) {
            System.err.println("File not found: " + filename);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                contacts.add(contact.fromCSV(line));
            }
        } catch (IOException e) {
            System.err.println("Error loading contacts: " + e.getMessage());
        }
    }
    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (contact c : contacts) {
                bw.write(c.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }
    }
    public void addContact(contact c) {
        contacts.add(c);
    }
    public contact findContactById(String id) {
        for (contact c : contacts) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }
    public boolean update(contact Updated){
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(Updated.getId())) {
                contacts.set(i, Updated);
                return true;
            }
        }
        return false;
    }
    public boolean deleteContact(String id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(id)) {
                contacts.remove(i);
                return true;
            }
        }
        return false;
    }
    public List<contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }
}
