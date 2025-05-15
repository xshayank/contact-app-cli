package contacts;
public class contact {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;

    public contact(String id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toCSV() {
        return String.join(",", id, name, email, phoneNumber);
    }

    public static contact fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid CSV format");
        }
        return new contact(parts[0], parts[1], parts[2], parts[3]);
    }

    ;

    @Override
    public String toString() {
        return String.format("Contact[id=%s, name=%s, email=%s, phoneNumber=%s]", id, name, email, phoneNumber);
    }
}
